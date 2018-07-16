package com.szczypiorofix.sweetrolls.game.tilemap;


import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import com.szczypiorofix.sweetrolls.game.main.MainClass;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class Level {

    private TileMap tileMap;

    public Level() {
    }


    public void generateMap() {

        // TODO odczytać z plików z tileMap.getTileSets()

    }

    public void loadFromTiledMap(String name) {
        try {
            File inputFile = new File("src/res/map/" + name);
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
                            tileMap.addTileSet(new TileSet(
                                    Integer.parseInt(tilesetElement.getAttribute("firstgid")),
                                    MainClass.RES +"map/" +tilesetElement.getAttribute("source")
                                    )
                            );
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
