package com.szczypiorofix.sweetrolls.game.tilemap;


import java.util.Arrays;

public class Layer {

    private String name;
    private int width;
    private int height;
    private int[] dataCSV;

    public Layer(String name, int width, int height) {
        this.name = name;
        this.width = width;
        this.height = height;
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

    public int[] getDataCSV() {
        return dataCSV;
    }

    public int getTileData(int tile) {
        return dataCSV[tile];
    }

    public void setDataCSV(String dataCSV) {
        String[] data = dataCSV.replace("\n", "").replace("\r", "").trim().split(",");
        this.dataCSV = Arrays.stream(data).mapToInt(Integer::parseInt).toArray();
    }

}
