package com.szczypiorofix.sweetrolls.game.objects.characters;

import com.szczypiorofix.sweetrolls.game.enums.ObjectType;
import com.szczypiorofix.sweetrolls.game.tilemap.Property;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;

public class OtherCharacter extends Character {


    OtherCharacter(String name, float x, float y, float width, float height, ObjectType objectType, ArrayList<Property> properties) {
        super(name, x, y, width, height, objectType, properties);
    }

    @Override
    public void update(int delta, float offsetX, float offsetY) {

    }

    @Override
    public void render(Graphics g, float offsetX, float offsetY) {

    }

    @Override
    public void turn() {

    }
}
