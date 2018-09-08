/*
 * Developed by szczypiorofix on 09.09.18 00:04.
 * Copyright (c) 2018. All rights reserved.
 *
 */

package com.szczypiorofix.sweetrolls.game.tilemap;

import java.io.Serializable;
import java.util.ArrayList;

public class ObjectGroup implements Serializable {

    private String name;
    private ArrayList<ObjectGroupObject> objects;

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

    public ArrayList<ObjectGroupObject> getObjects() {
        return objects;
    }

    public void setObjects(ArrayList<ObjectGroupObject> objects) {
        this.objects = objects;
    }

    public void addObject(ObjectGroupObject object) {
        objects.add(object);
    }

}
