package com.szczypiorofix.sweetrolls.game.tilemap;


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
        StringBuilder r = new StringBuilder("\nTILEMAP size " + width + ":" + height + ", tiles: " + tileWidth + ":" + tileHeight + ", layers: " + layers.size() + "\n");

        for(TileSet t : tileSets) {
            r.append("TILESET firstgrid: ").append(t.getFirstGid()).append(", source: ").append(t.getImageSource()).append(", tilecount: ").append(t.getTileCount()).append(", columns: ").append(t.getColumns()).append("\n");
        }

        for (int i = 0; i < layers.size(); i++) {
            r.append("\nLayer ").append(i).append(": ").append(layers.get(i).getName()).append(" size: ").append(layers.get(i).getDataCSV().length).append("\n");
//            for (int j = 0; j < layers.get(i).getDataCSV().length; j++) {
//                r.append(layers.get(i).getTileData(j)).append(",");
//            }
//            r.append("\n");
        }

        for (int i = 0; i < objectGroups.size(); i++) {
            r.append("\nObjectGroups ").append(i).append(": ").append(objectGroups.get(i).getName()).append(" size: ").append(objectGroups.get(i).getObjects().size()).append("\n");

            for (int j = 0; j < objectGroups.get(i).getObjects().size(); j++) {
                r.append(objectGroups.get(i).getObjects().get(j).getName()).append(" properties:\n");

                for (int k = 0; k < objectGroups.get(i).getObjects().get(j).getProperties().size(); k++) {
                    r.append("name: ").append(objectGroups.get(i).getObjects().get(j).getProperties().get(k).getName()).append(", type:").append(objectGroups.get(i).getObjects().get(j).getProperties().get(k).getType().toString()).append(", value:").append(objectGroups.get(i).getObjects().get(j).getProperties().get(k).getValue()).append("\n");
                }
                r.append("\n");
            }
            r.append("\n\n");
        }
        return r.toString();
    }

}
