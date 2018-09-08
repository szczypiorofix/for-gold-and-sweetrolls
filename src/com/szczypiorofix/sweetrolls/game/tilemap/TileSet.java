/*
 * Developed by szczypiorofix on 09.09.18 00:04.
 * Copyright (c) 2018. All rights reserved.
 *
 */

package com.szczypiorofix.sweetrolls.game.tilemap;


import com.szczypiorofix.sweetrolls.game.main.MainClass;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;


public class TileSet implements Serializable {

    private int firstGid;
    private String name;
    private String sourceFile;
    private String imageSource;
    private int tileWidth;
    private int tileHeight;
    private int tileCount;
    private int columns;
    private int sourceWidth;
    private int sourceHeight;
    private transient SpriteSheet image;
    private ArrayList<CollisionObject> collisions;


    public TileSet(int firstGid, String sourceFile) {
        this.firstGid = firstGid;
        this.sourceFile = sourceFile;
        collisions = new ArrayList<>();

        InputStream inputFile = null;
        try {
            inputFile = getClass().getResourceAsStream("/map/"+sourceFile);
            //File inputFile = new File(MainClass.RES + "map/" + sourceFile);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList tilesetList = doc.getElementsByTagName("tileset");
            for (int tileset = 0; tileset < tilesetList.getLength(); tileset++) {
                Node tilesetNode = tilesetList.item(tileset);

                if (tilesetNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element tilesetElement = (Element) tilesetNode;

                    name = tilesetElement.getAttribute("name");
                    tileWidth = Integer.parseInt(tilesetElement.getAttribute("tilewidth"));
                    tileHeight = Integer.parseInt(tilesetElement.getAttribute("tileheight"));
                    tileCount = Integer.parseInt(tilesetElement.getAttribute("tilecount"));
                    columns = Integer.parseInt(tilesetElement.getAttribute("columns"));


                    NodeList imageList = tilesetElement.getElementsByTagName("image");
                    Node imageNode = imageList.item(0);
                    if (imageNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element imageElement = (Element) imageNode;
                        imageSource = imageElement.getAttribute("source");
                        sourceWidth = Integer.parseInt(imageElement.getAttribute("width"));
                        sourceHeight = Integer.parseInt(imageElement.getAttribute("height"));
                        image = null;

                        try {
                            image = new SpriteSheet("map/" +imageSource, tileWidth, tileHeight);
                        } catch (Exception e) {
                            e.printStackTrace();
                            MainClass.logging(true, Level.WARNING, MainClass.getStackTrace(e));
                        }
                    }


                    NodeList tileList = tilesetElement.getElementsByTagName("tile");
                    for (int i = 0; i < tileList.getLength(); i++) {
                        Node tileNode = tileList.item(i);
                        if (tileNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element tileElement = (Element) tileNode;

                            CollisionObject collisionObject = new CollisionObject(Integer.parseInt(tileElement.getAttribute("id")) + firstGid);
                            collisionObject.setTypeName(tileElement.getAttribute("type"));
//                            NodeList tileObjectGroupList = tileElement.getElementsByTagName("objectgroup");
//                            Node tileObjectGroupNode = tileObjectGroupList.item(0);
//                            if (imageNode.getNodeType() == Node.ELEMENT_NODE) {
//                                Element tileObjectGroupElement = (Element) tileObjectGroupNode;
//
//                                NodeList tileObjectList = tileObjectGroupElement.getElementsByTagName("object");
//                                Node objectNode = tileObjectList.item(0);
//                                if (objectNode.getNodeType() == Node.ELEMENT_NODE) {
//                                    Element objectElement = (Element) objectNode;
//
//                                    collisionObject.setName(objectElement.getAttribute("name"));
//                                    collisionObject.setTypeName(objectElement.getAttribute("type"));
//                                    collisionObject.setX(Integer.parseInt(objectElement.getAttribute("x")));
//                                    collisionObject.setY(Integer.parseInt(objectElement.getAttribute("y")));
//                                    if (!objectElement.getAttribute("width").equals(""))
//                                        collisionObject.setWidth(Integer.parseInt(objectElement.getAttribute("width")));
//                                    if (!objectElement.getAttribute("height").equals(""))
//                                        collisionObject.setHeight(Integer.parseInt(objectElement.getAttribute("height")));
//
//                                    if (!objectElement.getAttribute("template").equals("")) {
//                                        collisionObject.setTemplate(objectElement.getAttribute("template"));
//                                    }
//                                }
//                            }
                            collisions.add(collisionObject);
                        }
                    }
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
            MainClass.logging(true, Level.WARNING, MainClass.getStackTrace(e));
        } finally {
            if (inputFile != null) {
                try {
                    inputFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public TileSet(int firstGid, String name, String imageSource, int tileWidth, int tileHeight, int tileCount, int columns, int sourceWidth, int sourceHeight, SpriteSheet image) {
        this.firstGid = firstGid;
        this.name = name;
        this.imageSource = imageSource;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.tileCount = tileCount;
        this.columns = columns;
        this.sourceWidth = sourceWidth;
        this.sourceHeight = sourceHeight;
        this.image = image;
        this.sourceFile = "";
        collisions = new ArrayList<>();
    }

    public ArrayList<CollisionObject> getCollisions() {
        return collisions;
    }

    public void setCollisions(ArrayList<CollisionObject> collisions) {
        this.collisions = collisions;
    }

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
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

    public int getTileCount() {
        return tileCount;
    }

    public void setTileCount(int tileCount) {
        this.tileCount = tileCount;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getSourceWidth() {
        return sourceWidth;
    }

    public void setSourceWidth(int sourceWidth) {
        this.sourceWidth = sourceWidth;
    }

    public int getSourceHeight() {
        return sourceHeight;
    }

    public void setSourceHeight(int sourceHeight) {
        this.sourceHeight = sourceHeight;
    }

    public int getFirstGid() {
        return firstGid;
    }

    public void setFirstGid(int firstGid) {
        this.firstGid = firstGid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSourceFile() {
        return sourceFile;
    }

    public Image getImageSprite(int id) {
        return image.getSprite(id % columns, id / columns);
    }

}
