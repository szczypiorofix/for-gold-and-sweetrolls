package com.szczypiorofix.sweetrolls.game.tilemap;

import java.util.ArrayList;

public class TileObject {

    private int id;
    private String name;
    private int x, y;
    private int width, height;
    private int gid;
    private ArrayList<Property> properties;

    public TileObject(int id, String name, int x, int y, int width, int height) {
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        properties = new ArrayList<>();
    }

    public TileObject(int id, String name, int x, int y, int width, int height, int gid) {
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.gid = gid;
        properties = new ArrayList<>();
    }

    public void addProperty(Property property) {
        properties.add(property);
    }

    public String getStringProperty(String prop) {
        String r = "null";
        for (int i = 0; i < properties.size(); i++) {
            if (properties.get(i).getType() == PropertyType.STRING && properties.get(i).getName().equalsIgnoreCase(prop)) {
                r = properties.get(i).getValue();
            }
        }
        return r;
    }

    public int getIntegerProperty(String prop) {
        int r = 0;
        for (int i = 0; i < properties.size(); i++) {
            if (properties.get(i).getType() == PropertyType.INTEGER
                    && properties.get(i).getName().equalsIgnoreCase(prop)) {
                r = Integer.parseInt(properties.get(i).getValue());
            }
        }
        return r;
    }

    public boolean getBooleanProperty(String prop) {
        boolean r = false;
        for (int i = 0; i < properties.size(); i++) {
            if (properties.get(i).getType() == PropertyType.BOOLEAN
                    && properties.get(i).getName().equalsIgnoreCase(prop)) {
                r = Boolean.parseBoolean(properties.get(i).getValue());
            }
        }
        return r;
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
}
