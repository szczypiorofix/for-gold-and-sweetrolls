package com.szczypiorofix.sweetrolls.game.tilemap;


import com.szczypiorofix.sweetrolls.game.graphics.Textures;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;

public class TileMap {

    private ArrayList<Layer> layers;
    private ArrayList<TileSet> tileSets;
    private ArrayList<ObjectGroup> objectGroups;
    private int tileWidth;
    private int tileWeight;
    private int width;
    private int height;
    private int nextobjectid;

    public TileMap(int width, int height, int tileWidth, int tileheight) {
        this.width = width;
        this.height = height;
        this.tileWidth = tileWidth;
        this.tileWeight = tileheight;
        layers = new ArrayList<>();
        tileSets = new ArrayList<>();
        objectGroups = new ArrayList<>();
    }


    public void addLayer(Layer layer) {
        this.layers.add(layer);
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public void setTileWidth(int tileWidth) {
        this.tileWidth = tileWidth;
    }

    public int getTileWeight() {
        return tileWeight;
    }

    public void setTileWeight(int tileWeight) {
        this.tileWeight = tileWeight;
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

    public int getNextobjectid() {
        return nextobjectid;
    }

    public void setNextobjectid(int nextobjectid) {
        this.nextobjectid = nextobjectid;
    }

    public ArrayList<ObjectGroup> getObjectGroups() {
        return objectGroups;
    }

    public void setObjectGroups(ArrayList<ObjectGroup> objectGroups) {
        this.objectGroups = objectGroups;
    }

    public void addObjectGroup(ObjectGroup objectGroup) {
        objectGroups.add(objectGroup);
    }

    public ArrayList<TileSet> getTileSets() {
        return tileSets;
    }

    public void addTileSet(TileSet tileSet) {
        tileSets.add(tileSet);
    }

    public void setTileSets(ArrayList<TileSet> tileSets) {
        this.tileSets = tileSets;
    }

    @Override
    public String toString() {
        String r = "\nTILEMAP size "+ width +":" + height +", tiles: "+ tileWidth +":"+ tileWeight +", layers: "+layers.size()+"\n";

        for(TileSet t : tileSets) {
            r += "TILESET firstgrid: "+t.getFirstGrid() +", source: "+t.getSource()+"\n";
        }


        for (int i = 0; i < layers.size(); i++) {
            r += "\nLayer "+i +": "+layers.get(i).getName() +" size: " +layers.get(i).getDataCSV().length;
        }

        for (int i = 0; i < objectGroups.size(); i++) {
            r += "\nObjectGroups "+i +": "+objectGroups.get(i).getName() +" size: " +objectGroups.get(i).getObjects().size();
        }
        return r;
    }



    public void draw(Graphics g, float x, float y, float width, float height, int mapX, int mapY) {

        for (int i = 0; i < width ; i += tileWidth) {
            for (int j = 0; j < height; j += tileWeight) {
                g.drawImage(Textures.getInstance().dg_grounds32.getSprite(1, 1), i, j);
            }
        }


    }

}
