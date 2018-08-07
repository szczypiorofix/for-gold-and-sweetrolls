package com.szczypiorofix.sweetrolls.game.tilemap;


import java.util.Arrays;

public class TileLayer {

    private String name;
    private int width;
    private int height;
    private boolean locked = false;
    private boolean visible = true;
    private TileObject[][] tileObjects;

    public TileLayer(String name, int width, int height) {
        this.name = name;
        this.width = width;
        this.height = height;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public int[] getDataCSV() {
//        return dataCSV;
//    }

    public TileObject[][] getData() {
        return tileObjects;
    }

    public TileObject getTile(int x, int y) {
        return tileObjects[x][y];
    }

    public void setData(TileObject[][] tileObjects) {
        this.tileObjects = tileObjects;
    }

    public void setDataCSVFromString(String dataCSV) {
        String[] data = dataCSV.replace("\n", "").replace("\r", "").trim().split(",");
        int[] dataArray= Arrays.stream(data).mapToInt(Integer::parseInt).toArray();
        this.tileObjects = new TileObject[width][height];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                this.tileObjects[j][i] = new TileObject(dataArray[i * width + j]);
            }
        }
    }

}
