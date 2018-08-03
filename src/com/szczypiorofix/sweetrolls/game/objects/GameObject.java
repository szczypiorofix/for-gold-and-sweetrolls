package com.szczypiorofix.sweetrolls.game.objects;

import com.szczypiorofix.sweetrolls.game.enums.ObjectType;
import com.szczypiorofix.sweetrolls.game.main.core.Registry;
import com.szczypiorofix.sweetrolls.game.tilemap.CollisionObject;
import com.szczypiorofix.sweetrolls.game.tilemap.Property;
import com.szczypiorofix.sweetrolls.game.tilemap.PropertyType;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;

abstract public class GameObject {

    protected final long id;
    protected String name;
    protected int x = 0;
    protected int y = 0;
    protected int width = 0;
    protected int height = 0;
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
        id = Registry.getInstance().register(this);
    }

    protected GameObject(String name) {
        this();
        this.name = name;
        this.objectType = ObjectType.DEFAULT;
        collisions = new CollisionObject();
    }

    protected GameObject(String name, ObjectType objectType) {
        this(name);
        this.objectType = objectType;
        this.x = 0;
        this.y = 0;
        this.properties = new ArrayList<>();
    }

    protected GameObject(String name, int x, int y) {
        this(name);
        this.x = x;
        this.y = y;
    }

    protected GameObject(String name, int x, int y, int width, int height) {
        this(name, x, y);
        this.width = width;
        this.height = height;
    }

    protected GameObject(String name, int x, int y, ObjectType objectType) {
        this(name, x, y);
        this.objectType = objectType;
    }

    protected GameObject(String name, int x, int y, int width, int height, ObjectType objectType) {
        this(name, x, y, objectType);
        this.width = width;
        this.height = height;
    }

    protected GameObject(String name, int x, int y, int width, int height, ObjectType objectType, ArrayList<Property> properties) {
        this(name, x, y, objectType);
        this.width = width;
        this.height = height;
        this.properties = properties;
    }


    // ########## ABSTRACT METHODS ##########
    public abstract void update(GameContainer gc, StateBasedGame sbg, int delta, int offsetX, int offsetY) throws SlickException;

    public abstract void render(GameContainer gc, StateBasedGame sbg,  Graphics g, int offsetX, int offsetY) throws SlickException;

    public abstract void turn();


    // ########## COMMON METHODS ##########
    public boolean intersects(GameObject gameObject) {
        return (
                x > gameObject.x && x < gameObject.x + gameObject.width && y > gameObject.y && y < gameObject.y + gameObject.height);
    }

    public boolean intersects(int x, int y, int width, int height) {
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
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
        //return ((x + (width / 2)) / width) +offset;
        return ((x + 16) / width) +offset;
    }

    public int getTileY(int offset) {
        //return ((y + (height / 2)) / height) + offset;
        return ((y + 16) / height) +offset;
    }

    public int getTileX() {
        //return ((x + (width / 2)) / width) > 0 ? (x + (width / 2) / width) : 0;
        return ((x + 16) / width) > 0 ? (x / width) : 0;
    }

    public int getTileY() {
        //return ((y + (height / 2)) / height) > 0 ? (y + (height / 2) / height) : 0;
        return ((y + 16) / height) > 0 ? (y / height) : 0;
    }
}
