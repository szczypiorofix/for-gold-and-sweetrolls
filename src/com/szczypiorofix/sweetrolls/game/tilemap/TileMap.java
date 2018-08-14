package com.szczypiorofix.sweetrolls.game.tilemap;


import com.szczypiorofix.sweetrolls.game.main.MainClass;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;


public class TileMap {

    private ArrayList<TileLayer> tileLayers;
    private ArrayList<TileSet> tileSets;
    private ArrayList<ObjectGroup> objectGroups;
    private String fileName;
    private int tileWidth;
    private int tileHeight;
    private int width;
    private int height;
    private int nextObjectId;


    private TileMap() {
        tileLayers = new ArrayList<>();
        tileSets = new ArrayList<>();
        objectGroups = new ArrayList<>();
    }

    /**
     *  Constructor for loading data from LevelGenerator
     * @param width
     * @param height
     * @param tileWidth
     * @param tileHeight
     */
    public TileMap(int width, int height, int tileWidth, int tileHeight) {
        this();
        this.width = width;
        this.height = height;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
    }

    /**
     * Constructor for loading data from TiledMap Editor
     * @param fileName (String) - TiledMap Editor file (*.tmx)
     */
    public TileMap(String fileName) {
        this();
        this.fileName = fileName;
        loadFromTiledMap(fileName);
    }

    /**
     * String with floats parsed to Integer type
     * @param s (String) Value to parse
     * @return (int) returned integer value
     */
    private int parseFloatToIntger(String s) {
        int r;
        if (s.contains("."))
            r = Integer.parseInt(s.substring(0, s.indexOf(".")));
        else
            r = Integer.parseInt(s);
        return r;
    }


    /**
     * Parsing TiledMap Editor map file (.tmx)
     * @param fileName (String) TiledMap Editor map filename.
     */
    private void loadFromTiledMap(String fileName) {

        try {
            //InputStream inputFile = getClass().getResourceAsStream("/map/"+fileName);
            File inputFile = new File(MainClass.RES + "map/" + fileName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList mapList = doc.getElementsByTagName("map");

            for (int temp = 0; temp < mapList.getLength(); temp++) {
                Node mapNode = mapList.item(temp);

                if (mapNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element mapElement = (Element) mapNode;

                    this.width = Integer.parseInt(mapElement.getAttribute("width"));
                    this.height = Integer.parseInt(mapElement.getAttribute("height"));
                    this.tileWidth = Integer.parseInt(mapElement.getAttribute("tilewidth"));
                    this.tileHeight = Integer.parseInt(mapElement.getAttribute("tileheight"));

                    NodeList layersList = mapElement.getElementsByTagName("layer");
                    for (int i = 0; i < layersList.getLength(); i++) {

                        Node layersNode = layersList.item(i);
                        if (layersNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element layerElement = (Element) layersNode;
                            TileLayer tileLayer = new TileLayer(
                                    layerElement.getAttribute("name"),
                                    Integer.parseInt(layerElement.getAttribute("width")),
                                    Integer.parseInt(layerElement.getAttribute("height"))
                            );

                            tileLayer.setDataCSVFromString(layerElement.getElementsByTagName("data").item(0).getFirstChild().getNodeValue());
                            if (layerElement.getAttribute("locked").equals("1")) {
                                tileLayer.setLocked(true);
                            }
                            if (layerElement.getAttribute("visible").equals("0")) {
                                tileLayer.setVisible(false);
                            }
                            tileLayers.add(tileLayer);
                        }
                    }

                    NodeList tilesetList = mapElement.getElementsByTagName("tileset");
                    for (int i = 0; i < tilesetList.getLength(); i++) {

                        Node tilesetNode = tilesetList.item(i);

                        if (tilesetNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element tilesetElement = (Element) tilesetNode;

                            // COMMON ATTRIBUTE
                            int firstGid = Integer.parseInt(tilesetElement.getAttribute("firstgid"));

                            // EMBEDDED TILESETS OR NOT
                            if (!tilesetElement.getAttribute("source").equalsIgnoreCase("")) {
                                tileSets.add(new TileSet(
                                                firstGid,
                                                tilesetElement.getAttribute("source")
                                        )
                                );
                            } else {
                                String tilesetName = tilesetElement.getAttribute("name");
                                int tileWidth = Integer.parseInt(tilesetElement.getAttribute("tilewidth"));
                                int tileHeight = Integer.parseInt(tilesetElement.getAttribute("tileheight"));
                                int tileCount = Integer.parseInt(tilesetElement.getAttribute("tilecount"));
                                int columns = Integer.parseInt(tilesetElement.getAttribute("columns"));

                                NodeList imageList = tilesetElement.getElementsByTagName("image");
                                Node imageNode = imageList.item(i);
                                if (imageNode.getNodeType() == Node.ELEMENT_NODE) {
                                    Element imageElement = (Element) imageNode;
                                    String imageSource = imageElement.getAttribute("source");
                                    int sourceWidth = Integer.parseInt(imageElement.getAttribute("width"));
                                    int sourceHeight = Integer.parseInt(imageElement.getAttribute("height"));
                                    SpriteSheet image = null;

                                    try {
                                        image = new SpriteSheet("map/" +imageSource, tileWidth, tileHeight);
                                    } catch (SlickException e) {
                                        e.printStackTrace();
                                    }
                                    tileSets.add(new TileSet(
                                            firstGid,
                                            tilesetName,
                                            imageSource,
                                            tileWidth,
                                            tileHeight,
                                            tileCount,
                                            columns,
                                            sourceWidth,
                                            sourceHeight,
                                            image)
                                    );
                                }
                            }
                        }
                    }

                    NodeList objectGroupList = mapElement.getChildNodes();
                    for (int i = 0; i < objectGroupList.getLength(); i++) {
                        Node objectGroupNode = objectGroupList.item(i);
                        if (objectGroupNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element objectGroupElement = (Element) objectGroupNode;

                            if (objectGroupElement.getTagName().equalsIgnoreCase("objectgroup")) {

                                ObjectGroup objectGroup = new ObjectGroup(objectGroupElement.getAttribute("name"));

                                NodeList objectsList = objectGroupElement.getElementsByTagName("object");
                                for (int j = 0; j < objectsList.getLength(); j++) {
                                    Node objectsNode = objectsList.item(j);
                                    if (objectsNode.getNodeType() == Node.ELEMENT_NODE) {
                                        Element objectsElement = (Element) objectsNode;

                                        ObjectGroupObject objectGroupObject = new ObjectGroupObject(
                                                Integer.parseInt(objectsElement.getAttribute("id")),
                                                !objectsElement.getAttribute("template").equalsIgnoreCase("") ? objectsElement.getAttribute("template") : "",
                                                objectsElement.getAttribute("name"),
                                                parseFloatToIntger(objectsElement.getAttribute("x")),
                                                parseFloatToIntger(objectsElement.getAttribute("y")),
                                                objectsElement.getAttribute("width").equalsIgnoreCase("") ? -1 : parseFloatToIntger(objectsElement.getAttribute("width")),
                                                objectsElement.getAttribute("height").equalsIgnoreCase("") ? -1 : parseFloatToIntger(objectsElement.getAttribute("height")),
                                                getTileSets()
                                        );
                                        if (!objectsElement.getAttribute("gid").equalsIgnoreCase("")) {
                                            objectGroupObject.setGid(Integer.parseInt(objectsElement.getAttribute("gid")));
                                        }

                                        NodeList objectsProperties = objectsElement.getElementsByTagName("properties");
                                        if (objectsProperties.getLength() > 0) {
                                            for (int k = 0; k < objectsProperties.getLength(); k++) {
                                                Node objectPropertiesNode = objectsProperties.item(k);
                                                if (objectPropertiesNode.getNodeType() == Node.ELEMENT_NODE) {
                                                    Element objectPropertiesElement = (Element) objectPropertiesNode;
                                                    NodeList objectPropertyList = objectPropertiesElement.getElementsByTagName("property");
                                                    if (objectPropertyList.getLength() > 0) {

                                                        for (int p = 0; p < objectPropertyList.getLength(); p++) {
                                                            Node objectPropertyNode = objectPropertyList.item(p);
                                                            if (objectPropertyNode.getNodeType() == Node.ELEMENT_NODE) {
                                                                Element objectPropertyElement = (Element) objectPropertyNode;
                                                                if (!objectGroupObject.isSetProperty("name")) {
                                                                    objectGroupObject.addProperty(new Property(
                                                                            objectPropertyElement.getAttribute("name"),
                                                                            objectPropertyElement.getAttribute("type").equals("") ? "string" : objectPropertyElement.getAttribute("type"),
                                                                            objectPropertyElement.getAttribute("value")
                                                                    ));
                                                                } else {
                                                                    objectGroupObject.setProperty(objectPropertyElement.getAttribute("name"), objectPropertyElement.getAttribute("value"), objectPropertyElement.getAttribute("value"));
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        objectGroup.addObject(objectGroupObject);
                                    }
                                }
                                objectGroups.add(objectGroup);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        calculateCollisions();
    }




    private void calculateCollisions() {

        for (int i = 0; i < tileSets.size(); i++) {

            for (int j = 0; j < tileLayers.size(); j++) {

                if (tileSets.get(i).getCollisions().size() > 0) {

                    for (int c = 0; c < tileSets.get(i).getCollisions().size(); c++) {

                        for (int x = 0; x < width; x++) {
                            for (int y = 0; y < height; y++) {
                                if (tileSets.get(i).getCollisions().get(c).getId() == tileLayers.get(j).getTile(x, y).getGid()) {
                                    tileLayers.get(j).getTile(x, y).setCollisionObject(
                                            new CollisionObject(
                                                    tileSets.get(i).getCollisions().get(c).getId(),
                                                    tileSets.get(i).getCollisions().get(c).getTypeName(),
                                                    tileSets.get(i).getCollisions().get(c).getX(),
                                                    tileSets.get(i).getCollisions().get(c).getY(),
                                                    tileSets.get(i).getCollisions().get(c).getWidth(),
                                                    tileSets.get(i).getCollisions().get(c).getHeight())
                                    );
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public ObjectGroup getObjectGroup(String name) {
        for (ObjectGroup o: objectGroups) {
            if (o.getName().equalsIgnoreCase(name))
                return o;
        }
        return null;
    }

    public void addLayer(TileLayer layer) {
        this.tileLayers.add(layer);
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public void setTileWidth(int tileWidth) {
        this.tileWidth = tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public void setTileHeight(int tileHeight) {
        this.tileHeight = tileHeight;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getNextobjectid() {
        return nextObjectId;
    }

    public void setNextobjectid(int nextObjectId) {
        this.nextObjectId = nextObjectId;
    }

    public ArrayList<ObjectGroup> getObjectGroups() {
        return objectGroups;
    }

    public void setObjectGroups(ArrayList<ObjectGroup> objectGroups) {
        this.objectGroups = objectGroups;
    }

    public void addObjectGroup(ObjectGroup objectGroup) {
        objectGroups.add(objectGroup);
    }

    public ArrayList<TileSet> getTileSets() {
        return tileSets;
    }

    public void addTileSet(TileSet tileSet) {
        tileSets.add(tileSet);
    }

    public void setTileSets(ArrayList<TileSet> tileSets) {
        this.tileSets = tileSets;
    }

    public ArrayList<TileLayer> getTileLayers() {
        return tileLayers;
    }

    @Override
    public String toString() {
        StringBuilder r = new StringBuilder("\nTILEMAP size " + width + ":" + height + ", tiles: " + tileWidth + ":" + tileHeight + ", layers: " + tileLayers.size() + "\n");

        for(TileSet t : tileSets) {
            r.append("TILESET firstgrid: ").append(t.getFirstGid()).append(", source: ").append(t.getImageSource()).append(", tilecount: ").append(t.getTileCount()).append(", columns: ").append(t.getColumns()).append("\n");
        }

        for (int i = 0; i < tileLayers.size(); i++) {
            r.append("\nLayer ").append(i).append(": ").append(tileLayers.get(i).getName()).append(" size: ").append(tileLayers.get(i).getData().length).append("\n");
//            for (int j = 0; j < layers.get(i).getDataCSV().length; j++) {
//                r.append(layers.get(i).getTileData(j)).append(",");
//            }
//            r.append("\n");
        }

        for (int i = 0; i < objectGroups.size(); i++) {
            r.append("\nObjectGroups ").append(i).append(": ").append(objectGroups.get(i).getName()).append(" size: ").append(objectGroups.get(i).getObjects().size()).append("\n");

            for (int j = 0; j < objectGroups.get(i).getObjects().size(); j++) {
                r.append(objectGroups.get(i).getObjects().get(j).getName()).append(" properties:\n");

                for (int k = 0; k < objectGroups.get(i).getObjects().get(j).getProperties().size(); k++) {
                    r.append("name: ").append(objectGroups.get(i).getObjects().get(j).getProperties().get(k).getName()).append(", type:").append(objectGroups.get(i).getObjects().get(j).getProperties().get(k).getType().toString()).append(", value:").append(objectGroups.get(i).getObjects().get(j).getProperties().get(k).getValue()).append("\n");
                }
                r.append("\n");
            }
            r.append("\n\n");
        }
        return r.toString();
    }

}
