package com.szczypiorofix.sweetrolls.game.tilemap;


import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import com.szczypiorofix.sweetrolls.game.main.MainClass;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class Level {

    private TileMap tileMap;

    public Level() {
    }


    public void loadFromTiledMap(String fileName) {
        try {
            File inputFile = new File("src/res/map/" + fileName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList mapList = doc.getElementsByTagName("map");

            for (int temp = 0; temp < mapList.getLength(); temp++) {
                Node mapNode = mapList.item(temp);

                if (mapNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element mapElement = (Element) mapNode;

                    tileMap = new TileMap(
                            Integer.parseInt(mapElement.getAttribute("width")),
                            Integer.parseInt(mapElement.getAttribute("height")),
                            Integer.parseInt(mapElement.getAttribute("tilewidth")),
                            Integer.parseInt(mapElement.getAttribute("tileheight"))
                    );

                    NodeList layersList = doc.getElementsByTagName("layer");
                    for (int i = 0; i < layersList.getLength(); i++) {

                        Node layersNode = layersList.item(i);
                        if (layersNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element layerElement = (Element) layersNode;
                            Layer layer = new Layer(
                                    layerElement.getAttribute("name"),
                                    Integer.parseInt(layerElement.getAttribute("width")),
                                    Integer.parseInt(layerElement.getAttribute("height"))
                            );
                            layer.setDataCSV(layerElement.getElementsByTagName("data").item(0).getTextContent());
                            tileMap.addLayer(layer);
                        }
                    }

                    NodeList tilesetList = doc.getElementsByTagName("tileset");
                    for (int i = 0; i < tilesetList.getLength(); i++) {

                        Node tilesetNode = tilesetList.item(i);

                        if (tilesetNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element tilesetElement = (Element) tilesetNode;

                            int firstGrid = Integer.parseInt(tilesetElement.getAttribute("firstgid"));
                            String tilesetName = tilesetElement.getAttribute("name");

                            int tileWidth = Integer.parseInt(tilesetElement.getAttribute("tilewidth"));
                            int tileHeight = Integer.parseInt(tilesetElement.getAttribute("tileheight"));
                            int tileCount = Integer.parseInt(tilesetElement.getAttribute("tilecount"));
                            int columns = Integer.parseInt(tilesetElement.getAttribute("columns"));

                            NodeList imageList = doc.getElementsByTagName("image");
                            Node imageNode = imageList.item(i);
                            if (imageNode.getNodeType() == Node.ELEMENT_NODE) {
                                Element imageElement = (Element) imageNode;
                                String imageSource = imageElement.getAttribute("source");
                                int sourceWidth = Integer.parseInt(imageElement.getAttribute("width"));
                                int sourceHeight = Integer.parseInt(imageElement.getAttribute("height"));
                                SpriteSheet image = null;

                                try {
                                    image = new SpriteSheet(MainClass.RES +"map/" +imageSource, tileWidth, tileHeight);
                                } catch (SlickException e) {
                                    e.printStackTrace();
                                }
                                tileMap.addTileSet(new TileSet(
                                        firstGrid,
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

                    NodeList objectGroupList = doc.getElementsByTagName("objectgroup");
                    for (int i = 0; i < objectGroupList.getLength(); i++) {
                        Node objectGroupNode = objectGroupList.item(i);
                        if (objectGroupNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element objectGroupElement = (Element) objectGroupNode;
                            ObjectGroup objectGroup = new ObjectGroup(objectGroupElement.getAttribute("name"));

                            NodeList objectsList = doc.getElementsByTagName("object");
                            for (int j = 0; j < objectsList.getLength(); j++) {
                                Node objectsNode = objectsList.item(j);
                                if (objectsNode.getNodeType() == Node.ELEMENT_NODE) {
                                    Element objectsElement = (Element) objectsNode;
                                    objectGroup.addObject(new TileObject(
                                            Integer.parseInt(objectsElement.getAttribute("id")),
                                            objectsElement.getAttribute("name"),
                                            Integer.parseInt(objectsElement.getAttribute("x")),
                                            Integer.parseInt(objectsElement.getAttribute("y")),
                                            Integer.parseInt(objectsElement.getAttribute("width")),
                                            Integer.parseInt(objectsElement.getAttribute("height"))
                                    ));

                                    NodeList objectsProperties = objectsElement.getChildNodes();
                                    // first is #text, second is "properties" node
                                    if (objectsProperties.getLength() > 0) {
                                        Node objectPropertyList = objectsProperties.item(1);
                                        NodeList objectProperties = objectPropertyList.getChildNodes();

                                        for (int k = 0; k < objectProperties.getLength(); k++) {
                                            Node objectPropertyNode = objectProperties.item(k);

                                            if (objectPropertyNode.getNodeType() == Node.ELEMENT_NODE) {
                                                Element objectPropertyElement = (Element) objectPropertyNode;

                                                objectGroup.getObjects().get(j).addProperty(new Property(
                                                        objectPropertyElement.getAttribute("name"),
                                                        objectPropertyElement.getAttribute("type").equals("") ? "string" : objectPropertyElement.getAttribute("type"),
                                                        objectPropertyElement.getAttribute("value")
                                                ));
                                            }
                                        }
                                    }
                                }
                            }
                            tileMap.addObjectGroup(objectGroup);
                        }
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public TileMap getTileMap() {
        return tileMap;
    }

}
