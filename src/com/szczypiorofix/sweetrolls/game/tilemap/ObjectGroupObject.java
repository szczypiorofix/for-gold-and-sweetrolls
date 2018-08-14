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
import java.util.ArrayList;


public class ObjectGroupObject {

    private int id;
    private String name;
    private String type;
    private String template;
    private int x, y;
    private int width, height;
    private int gid;
    private int tilesetFirstGid;
    private String tilesetSource = "";
    private ArrayList<Property> properties;
    private boolean isTemplate = false;

    public ObjectGroupObject(int id, String template, String name, int x, int y, int width, int height, ArrayList<TileSet> tileSets) {
        this.id = id;
        this.template = template;
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        properties = new ArrayList<>();

        if (!template.equalsIgnoreCase("")) {
            isTemplate = true;
            try {
                File inputFile = new File(MainClass.RES + "map/" + template);
                //InputStream inputFile = getClass().getResourceAsStream("/map/" + template);
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(inputFile);
                doc.getDocumentElement().normalize();

                NodeList templateList = doc.getElementsByTagName("template");

                for (int templates = 0; templates < templateList.getLength(); templates++) {
                    Node templateNode = templateList.item(templates);
                    if (templateNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element templatesElement = (Element) templateNode;

                        NodeList tilesetsList = templatesElement.getElementsByTagName("tileset");

                        for (int tilesets = 0; tilesets < tilesetsList.getLength(); tilesets++) {
                            Node tilesetNode = tilesetsList.item(tilesets);
                            if (tilesetNode.getNodeType() == Node.ELEMENT_NODE) {
                                Element tilesetElement = (Element) tilesetNode;
                                this.tilesetFirstGid = Integer.parseInt(tilesetElement.getAttribute("firstgid"));
                                this.tilesetSource = tilesetElement.getAttribute("source");
                            }
                        }

                        NodeList objectsList = templatesElement.getElementsByTagName("object");
                        for (int objects = 0; objects < objectsList.getLength(); objects++) {
                            Node objectNode = objectsList.item(objects);
                            if (objectNode.getNodeType() == Node.ELEMENT_NODE) {
                                Element objectElement = (Element) objectNode;
                                this.name = objectElement.getAttribute("name");
                                this.type = objectElement.getAttribute("type");
                                this.width = Integer.parseInt(objectElement.getAttribute("width"));
                                this.height = Integer.parseInt(objectElement.getAttribute("height"));

                                for (int i = 0; i < tileSets.size(); i++) {
                                    if (tileSets.get(i).getSourceFile().equalsIgnoreCase(tilesetSource)) {
                                        gid = !objectElement.getAttribute("gid").equalsIgnoreCase("")
                                                ? Integer.parseInt(objectElement.getAttribute("gid")) + tileSets.get(i).getFirstGid()-1
                                                : -1;
                                        break;
                                    }
                                }

                                NodeList objectsProperties = objectElement.getElementsByTagName("properties");
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
                                                        addProperty(new Property(
                                                                objectPropertyElement.getAttribute("name"),
                                                                objectPropertyElement.getAttribute("type").equals("") ? "string" : objectPropertyElement.getAttribute("type"),
                                                                objectPropertyElement.getAttribute("value")
                                                        ));
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (ParserConfigurationException | IOException | SAXException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isTemplate() {
        return isTemplate;
    }

    public void addProperty(Property property) {
        properties.add(property);
    }

    public void setProperty(String prop, String type, String value) {
        for (Property property : properties) {
            if (property.getName().equalsIgnoreCase(prop)) {
                property.setValue(value);
                switch (type) {
                    case "bool": {
                        property.setType(PropertyType.BOOLEAN);
                        break;
                    }
                    case "int": {
                        property.setType(PropertyType.INTEGER);
                        break;
                    }
                    case "string": {
                        property.setType(PropertyType.STRING);
                        break;
                    }
                    case "file": {
                        property.setType(PropertyType.FILE);
                        break;
                    }
                    case "float": {
                        property.setType(PropertyType.FLOAT);
                        break;
                    }
                    case "color": {
                        property.setType(PropertyType.COLOR);
                        break;
                    }
                }
            }
        }
    }

    public boolean isSetProperty(String prop) {
        for (Property property : properties) {
            if (property.getName().equalsIgnoreCase(prop)) {
                return true;
            }
        }
        return false;
    }

    public String getStringProperty(String prop) {
        String r = Property.ERROR_MSG;
        for (Property property : properties) {
            if (property.getType() == PropertyType.STRING && property.getName().equalsIgnoreCase(prop)) {
                r = property.getValue();
            }
        }
        return r;
    }

    public int getIntegerProperty(String prop) {
        int r = -1;
        for (Property property : properties) {
            if (property.getType() == PropertyType.INTEGER
                    && property.getName().equalsIgnoreCase(prop)) {
                r = Integer.parseInt(property.getValue());
            }
        }
        return r;
    }

    public boolean getBooleanProperty(String prop) {
        boolean r = false;
        for (Property property : properties) {
            if (property.getType() == PropertyType.BOOLEAN
                    && property.getName().equalsIgnoreCase(prop)) {
                r = Boolean.parseBoolean(property.getValue());
            }
        }
        return r;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<Property> getProperties() {
        return properties;
    }

    public void setProperties(ArrayList<Property> properties) {
        this.properties = properties;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public int getTilesetFirstGid() {
        return tilesetFirstGid;
    }

    public void setTilesetFirstGid(int tilesetFirstGid) {
        this.tilesetFirstGid = tilesetFirstGid;
    }

    public String getTilesetSource() {
        return tilesetSource;
    }

    public void setTilesetSource(String tilesetSource) {
        this.tilesetSource = tilesetSource;
    }
}
