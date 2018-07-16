package com.szczypiorofix.sweetrolls.game.tilemap;

public class TileObject {

    private int id;
    private String name;
    private int x, y;
    private int width, height;

    public TileObject(int id, String name, int x, int y, int width, int height) {
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
