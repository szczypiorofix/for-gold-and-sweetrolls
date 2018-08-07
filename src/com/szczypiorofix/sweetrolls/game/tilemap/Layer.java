package com.szczypiorofix.sweetrolls.game.tilemap;


import com.szczypiorofix.sweetrolls.game.enums.ObjectType;
import com.szczypiorofix.sweetrolls.game.objects.GameObject;
import com.szczypiorofix.sweetrolls.game.objects.terrain.Ground;

import java.util.Arrays;

public class Layer {

    private int id;
    private int width, height;
    private int tileWidth, tileHeight;
    private int offsetX, offsetY;
    private String name;
    private GameObject[][] tiles;


    public Layer(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GameObject[][] getTiles() {
        return tiles;
    }

    public void setTiles(GameObject[][] tiles) {
        this.tiles = tiles;
    }

    public void setDataCSVFromString(String stringDataCSV) {
        String[] data = stringDataCSV.replace("\n", "").replace("\r", "").trim().split(",");
        int[] dataCSV = Arrays.stream(data).mapToInt(Integer::parseInt).toArray();
        tiles = new GameObject[width][height];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

            }
        }
    }
}
