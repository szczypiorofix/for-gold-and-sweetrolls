package com.szczypiorofix.sweetrolls.game.tilemap;

import com.szczypiorofix.sweetrolls.game.main.MainClass;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class CollisionObject {

    public enum CollisionType {
        COLLISION,
        PASSABLE
    }
    private String name;
    private CollisionType collisionType;
    private String typeName;
    private int id;
    private int x, y;
    private int width, height;

    public CollisionObject() {
    }

    public CollisionObject(int id) {
        this.id = id;
    }

    public CollisionObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.collisionType = CollisionType.PASSABLE;
    }

    public CollisionObject(int x, int y, int width, int height, CollisionType collisionType) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.collisionType = collisionType;
    }

    public void setTemplate(String templateFileName) {

        try {
            File inputFile = new File(MainClass.RES + "map/" + templateFileName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList templatesList = doc.getElementsByTagName("template");
            for (int i = 0; i < templatesList.getLength(); i++) {
                Node templateNode = templatesList.item(i);
                if (templateNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element templateElement = (Element) templateNode;

                    NodeList objectsList = templateElement.getElementsByTagName("object");
                    for (int j = 0; j < objectsList.getLength(); j++) {
                        Node objectsNode = objectsList.item(j);
                        if (objectsNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element objectsElement = (Element) objectsNode;
                            this.width = Integer.parseInt(objectsElement.getAttribute("width"));
                            this.height = Integer.parseInt(objectsElement.getAttribute("height"));
                            this.typeName = objectsElement.getAttribute("type");
                            this.name = objectsElement.getAttribute("name");

                            if (this.typeName.equalsIgnoreCase("collisions")) {
                                this.collisionType = CollisionType.COLLISION;
                            } else this.collisionType = CollisionType.PASSABLE;
                        }
                    }
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
            MainClass.logging(true, Level.WARNING, MainClass.getStackTrace(e));
        }
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CollisionType getCollisionType() {
        return collisionType;
    }

    public void setCollisionType(CollisionType collisionType) {
        this.collisionType = collisionType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
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
}
