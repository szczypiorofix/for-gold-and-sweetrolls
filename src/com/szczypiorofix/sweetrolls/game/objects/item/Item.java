package com.szczypiorofix.sweetrolls.game.objects.item;

import com.szczypiorofix.sweetrolls.game.enums.ObjectType;
import com.szczypiorofix.sweetrolls.game.objects.GameObject;
import com.szczypiorofix.sweetrolls.game.tilemap.Property;
import org.newdawn.slick.Image;


import java.util.ArrayList;

abstract public class Item extends GameObject {

    protected Image image;

    Item(String name, float x, float y, float width, float height, ObjectType objectType, ArrayList<Property> properties) {
        super(name, x, y, width, height, objectType, properties);
    }

    Item(String name, float x, float y, float width, float height, Image image, ObjectType objectType, ArrayList<Property> properties) {
        super(name, x, y, width, height, objectType, properties);
        this.image = image;
    }

}