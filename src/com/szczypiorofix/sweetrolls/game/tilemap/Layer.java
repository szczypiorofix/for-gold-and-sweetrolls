package com.szczypiorofix.sweetrolls.game.tilemap;


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

    public void setDataCSV(String dataCSV) {
        String[] data = dataCSV.split(",");
        this.dataCSV = new int[data.length];
        for (int i = 0; i < data.length; i++) {
            this.dataCSV[i] = Integer.parseInt(data[i].trim());
        }
    }

}
