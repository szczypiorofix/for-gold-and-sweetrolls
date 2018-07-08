package com.szczypiorofix.racoon.game.objects;

import com.szczypiorofix.racoon.game.def.ObjectType;
import com.szczypiorofix.racoon.game.main.Registry;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

abstract public class GameObject {


    public long id;
    public String name;
    public int x = 0;
    public int y = 0;
    public boolean moving = false;
    public boolean living = false;
    public boolean dynamic = false;
    public boolean visible = false;
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

    public GameObject(String name, int x, int y) {
        this(name);
        this.x = x;
        this.y = y;
    }

    public GameObject(String name, int x, int y, ObjectType objectType) {
        this(name, x, y);
        this.objectType = objectType;
    }


    public abstract void update(GameContainer gc, int delta) throws SlickException;

    public abstract void render(GameContainer gc, Graphics g) throws SlickException;


    public long getId() {
        return id;
    }

    public void setId(long id) {
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
}
