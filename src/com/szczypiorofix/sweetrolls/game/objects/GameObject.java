/*
 * Developed by szczypiorofix on 09.09.18 00:04.
 * Copyright (c) 2018. All rights reserved.
 *
 */

package com.szczypiorofix.sweetrolls.game.objects;

import com.szczypiorofix.sweetrolls.game.enums.ObjectType;
import com.szczypiorofix.sweetrolls.game.main.core.Registry;
import com.szczypiorofix.sweetrolls.game.tilemap.CollisionObject;
import com.szczypiorofix.sweetrolls.game.tilemap.Property;
import com.szczypiorofix.sweetrolls.game.tilemap.PropertyType;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import java.io.Serializable;
import java.util.ArrayList;

abstract public class GameObject implements Serializable {

    protected long id;
    protected String name;
    protected float x = 0f;
    protected float y = 0f;
    protected float width = 0f;
    protected float height = 0f;
    protected boolean moving = false;
    protected boolean moveable = false;
    protected boolean living = false;
    protected boolean dynamic = false;
    protected boolean visible = true;
    protected boolean passable = true;
    protected boolean hover = false;
    protected ObjectType objectType;
    protected ArrayList<Property> properties;
    protected CollisionObject collisions;


    protected GameObject() {
        //id = Registry.getInstance().register(this);
        this.properties = new ArrayList<>();
    }

    protected GameObject(String name, ObjectType objectType) {
        this();
        this.name = name;
        this.objectType = objectType;
        this.x = 0f;
        this.y = 0f;
        this.properties = new ArrayList<>();
    }

    protected GameObject(String name, float x, float y) {
        this();
        this.name = name;
        this.x = x;
        this.y = y;
    }

    protected GameObject(String name, float x, float y, float width, float height) {
        this(name, x, y);
        this.width = width;
        this.height = height;
    }

    protected GameObject(String name, float x, float y, ObjectType objectType) {
        this(name, x, y);
        this.objectType = objectType;
    }

    protected GameObject(String name, float x, float y, float width, float height, ObjectType objectType) {
        this(name, x, y, objectType);
        this.width = width;
        this.height = height;
    }

    protected GameObject(String name, float x, float y, float width, float height, ObjectType objectType, ArrayList<Property> properties) {
        this(name, x, y, objectType);
        this.width = width;
        this.height = height;
        this.properties = properties;
    }


    // ########## ABSTRACT METHODS ##########
    public abstract void update(int delta, float offsetX, float offsetY) throws SlickException;

    public abstract void render(Graphics g, float offsetX, float offsetY) throws SlickException;

    public void turn() {}


    // ########## COMMON METHODS ##########
    public boolean intersects(GameObject gameObject) {
        return (
                x > gameObject.x && x < gameObject.x + gameObject.width && y > gameObject.y && y < gameObject.y + gameObject.height);
    }

    public boolean intersects(float x, float y, float width, float height) {
        return (
                this.x > x && this.x < x + width && this.y > y && this.y < y + height);
    }

    // #############################################
    // GETTERS & SETTERS


    public CollisionObject getCollisions() {
        return collisions;
    }

    public void setCollisions(CollisionObject collisions) {
        this.collisions = collisions;
        switch (collisions.getTypeName()) {
            case "water": {
                objectType = ObjectType.WATER;
                break;
            }
            case "wall": {
                objectType = ObjectType.WALL;
                break;
            }
            case "plains": {
                objectType = ObjectType.PLAINS;
                break;
            }
            case "place": {
                objectType = ObjectType.PLACE;
                break;
            }
            case "hills": {
                objectType = ObjectType.HILLS;
                break;
            }
            case "swamp": {
                objectType = ObjectType.SWAMP;
                break;
            }
            case "forest": {
                objectType = ObjectType.FOREST;
                break;
            }
            case "mountains": {
                objectType = ObjectType.MOUNTAINS;
                break;
            }
            case "desert": {
                objectType = ObjectType.DESERT;
                break;
            }
            case "deserttree": {
                objectType = ObjectType.DESERT_TREE;
                break;
            }
            case "swamptree": {
                objectType = ObjectType.SWAMP_TREE;
                break;
            }
            case "settlement": {
                objectType = ObjectType.SETTLEMENT;
                break;
            }
            default: {
                objectType = ObjectType.DEFAULT;
                break;
            }
        }
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public boolean isLiving() {
        return living;
    }

    public void setLiving(boolean living) {
        this.living = living;
    }

    public boolean isDynamic() {
        return dynamic;
    }

    public void setDynamic(boolean dynamic) {
        this.dynamic = dynamic;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public ObjectType getObjectType() {
        return objectType;
    }

    public void setObjectType(ObjectType objectType) {
        this.objectType = objectType;
    }

    public boolean isMoveable() {
        return moveable;
    }

    public void setMoveable(boolean moveable) {
        this.moveable = moveable;
    }

    public boolean isHover() {
        return hover;
    }

    public void setHover(boolean hover) {
        this.hover = hover;
    }

    public boolean isPassable() {
        return passable;
    }

    public void setPassable(boolean passable) {
        this.passable = passable;
    }

    public ArrayList<Property> getProperties() {
        return properties;
    }

    public void setProperties(ArrayList<Property> properties) {
        this.properties = properties;
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

    public int getTileX(int offset) {
        return (int) ((x + (width / 2)) / width) +offset;
    }

    public int getTileY(int offset) {
        return (int) ((y + (height / 2)) / height) + offset;
    }

    public int getTileX() {
        return ((int) ((x + (width / 2)) / width) > 0) ? (int) ((x + (width / 2)) / width) : 0;
    }

    public int getTileY() {
        return ((int) ((y + (height / 2)) / height) > 0) ? (int) ((y + (height / 2)) / height) : 0;
    }

}
