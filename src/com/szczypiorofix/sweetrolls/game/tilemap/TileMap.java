package com.szczypiorofix.sweetrolls.game.tilemap;


import java.util.ArrayList;

public class TileMap {

    private ArrayList<Layer> layers;
    private int tileswide;
    private int tileshigh;
    private int tilewidth;
    private int tileheight;

    public TileMap() {
        layers = new ArrayList<>();
    }


    public void addLayer(Layer layer) {
        this.layers.add(layer);
    }

    public int getTileswide() {
        return tileswide;
    }

    public void setTileswide(int tileswide) {
        this.tileswide = tileswide;
    }

    public int getTileshigh() {
        return tileshigh;
    }

    public void setTileshigh(int tileshigh) {
        this.tileshigh = tileshigh;
    }

    public int getTilewidth() {
        return tilewidth;
    }

    public void setTilewidth(int tilewidth) {
        this.tilewidth = tilewidth;
    }

    public int getTileheight() {
        return tileheight;
    }

    public void setTileheight(int tileheight) {
        this.tileheight = tileheight;
    }

    @Override
    public String toString() {
        String r = "Tilemap, layers: "+layers.size()+" ";
        for (int i = 0; i < layers.size(); i++) {
            r += "Layer "+i +": "+layers.get(i).getName() +" tiles: "+layers.get(i).getTiles().size()+" ";
        }
        return r;
    }
}
