package com.szczypiorofix.sweetrolls.game.tilemap;


import org.newdawn.slick.Graphics;

import java.util.ArrayList;

public class TileMap {

    private ArrayList<Layer> layers;
    private ArrayList<TileSet> tileSets;
    private ArrayList<ObjectGroup> objectGroups;
    private int tileWidth;
    private int tileHeight;
    private int width;
    private int height;
    private int nextObjectId;

    public TileMap(int width, int height, int tileWidth, int tileHeight) {
        this.width = width;
        this.height = height;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
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

    public int getTileHeight() {
        return tileHeight;
    }

    public void setTileHeight(int tileHeight) {
        this.tileHeight = tileHeight;
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
        return nextObjectId;
    }

    public void setNextobjectid(int nextObjectId) {
        this.nextObjectId = nextObjectId;
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

    public ArrayList<Layer> getLayers() {
        return layers;
    }

    @Override
    public String toString() {
        String r = "\nTILEMAP size "+ width +":" + height +", tiles: "+ tileWidth +":"+ tileHeight +", layers: "+layers.size()+"\n";

        for(TileSet t : tileSets) {
            r += "TILESET firstgrid: "+t.getFirstGrid() +", source: "+t.getSource()+ ", tilecount: " + t.getTileCount() + ", columns: " + t.getColumns() +"\n";
        }

        for (int i = 0; i < layers.size(); i++) {
            r += "\nLayer "+i +": "+layers.get(i).getName() +" size: " +layers.get(i).getDataCSV().length+"\n";
            for (int j = 0; j < layers.get(i).getDataCSV().length; j++) {
                r += layers.get(i).getTileData(j)+",";
            }
            r += "\n";
        }

        for (int i = 0; i < objectGroups.size(); i++) {
            r += "\nObjectGroups "+i +": "+objectGroups.get(i).getName() +" size: " +objectGroups.get(i).getObjects().size();
        }
        return r;
    }


//    public void draw(Graphics g, float x, float y, float width, float height, int mapX, int mapY) {
//
//        int counter = 0;
//        for (int i = 0; i < width / tileWidth; i++) {
//            for (int j = 0; j < height / tileHeight; j++) {
//                g.drawImage(tileSets.get(0)
//                        .getImageSprite(
//                                layers.get(0).getTileData(counter)
//                        ), j * tileWidth, i * tileHeight);
//                counter++;
//            }
//        }
//
//        g.drawImage(tileSets.get(0)
//                .getImageSprite(
//                        layers.get(0).getTileData(0)
//                ), 1, 1
//        );
//
//    }

}
