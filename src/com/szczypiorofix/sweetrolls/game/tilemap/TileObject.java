package com.szczypiorofix.sweetrolls.game.tilemap;

public class TileObject {

    private int gid;
    private int x;
    private int y;
    private int width;
    private int height;
    private CollisionObject collisionObject;


    public TileObject(int gid) {
        this.gid = gid;
        collisionObject = new CollisionObject();
    }


    public CollisionObject getCollisionObject() {
        return collisionObject;
    }

    public void setCollisionObject(CollisionObject collisionObject) {
        this.collisionObject = collisionObject;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
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
