package com.szczypiorofix.sweetrolls.game.tilemap;

import java.util.ArrayList;

public class ObjectGroup {

    private String name;
    private ArrayList<TileObject> objects;

    public ObjectGroup(String name) {
        this.name = name;
        objects = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<TileObject> getObjects() {
        return objects;
    }

    public void setObjects(ArrayList<TileObject> objects) {
        this.objects = objects;
    }

    public void addObject(TileObject object) {
        objects.add(object);
    }

}
