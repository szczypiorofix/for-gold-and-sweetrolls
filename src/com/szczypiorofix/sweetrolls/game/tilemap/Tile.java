package com.szczypiorofix.sweetrolls.game.tilemap;

public class Tile {

    private int x;
    private int y;
    private int tile;
    private int root;
    private boolean flipX;

    public Tile(int x, int y, int tile, int root, boolean flipX) {
        this.x = x;
        this.y = y;
        this.tile = tile;
        this.root = root;
        this.flipX = flipX;
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

    public int getTile() {
        return tile;
    }

    public void setTile(int tile) {
        this.tile = tile;
    }

    public int getRoot() {
        return root;
    }

    public void setRoot(int root) {
        this.root = root;
    }

    public boolean isFlipX() {
        return flipX;
    }

    public void setFlipX(boolean flipX) {
        this.flipX = flipX;
    }
}
