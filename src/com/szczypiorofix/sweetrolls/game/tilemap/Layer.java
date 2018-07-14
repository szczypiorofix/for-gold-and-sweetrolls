package com.szczypiorofix.sweetrolls.game.tilemap;

import java.util.ArrayList;

public class Layer {

    private int number = 0;
    private String name;
    private ArrayList<Tile> tiles;

    public Layer(int number, String name) {
        this.number = number;
        this.name = name;
        tiles = new ArrayList<>();
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public void setTiles(ArrayList<Tile> tiles) {
        this.tiles = tiles;
    }

    public void addTile(Tile tile) {
        this.tiles.add(tile);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
