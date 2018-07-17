package com.szczypiorofix.sweetrolls.game.objects;

import com.szczypiorofix.sweetrolls.game.def.ObjectType;
import com.szczypiorofix.sweetrolls.game.main.Registry;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

abstract public class GameObject {


    public long id;
    public String name;
    public float x = 0f;
    public float y = 0f;
    public float width = 0f;
    public float height = 0f;
    public boolean moving = false;
    public boolean moveable = false;
    public boolean living = false;
    public boolean dynamic = false;
    public boolean visible = false;
    public boolean hover = false;
    public ObjectType objectType;


    private GameObject() {
        id = Registry.getInstance().register(this);
    }

    public GameObject(String name) {
        this();
        this.name = name;
        this.objectType = ObjectType.DEFAULT;
    }

    public GameObject(String name, ObjectType objectType) {
        this(name);
        this.objectType = objectType;
        this.x = 0;
        this.y = 0;
    }

    public GameObject(String name, float x, float y) {
        this(name);
        this.x = x;
        this.y = y;
    }

    public GameObject(String name, float x, float y, float width, float height) {
        this(name, x, y);
        this.width = width;
        this.height = height;
    }

    public GameObject(String name, float x, float y, ObjectType objectType) {
        this(name, x, y);
        this.objectType = objectType;
    }

    public GameObject(String name, float x, float y, float width, float height, ObjectType objectType) {
        this(name, x, y, objectType);
        this.width = width;
        this.height = height;
    }


    public abstract void update(GameContainer gc, StateBasedGame sbg, int delta, float offsetX, float offsetY) throws SlickException;

    public abstract void render(GameContainer gc, StateBasedGame sbg,  Graphics g, float offsetX, float offsetY) throws SlickException;

    public boolean intersects(GameObject gameObject) {
        return (
                x > gameObject.x && x < gameObject.x + gameObject.width && y > gameObject.y && y < gameObject.y + gameObject.height);
    }

    public boolean intersects(float x, float y, float width, float height) {
        return (
                this.x > x && this.x < x + width && this.y > y && this.y < y + height);
    }

    // GETTERS & SETTERS

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
}
