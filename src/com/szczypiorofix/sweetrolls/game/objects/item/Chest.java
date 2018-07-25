package com.szczypiorofix.sweetrolls.game.objects.item;

import com.szczypiorofix.sweetrolls.game.enums.ObjectType;
import com.szczypiorofix.sweetrolls.game.objects.GameObject;
import com.szczypiorofix.sweetrolls.game.tilemap.Property;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;

public class Chest extends Item {

    private final Image chestImage;

    public Chest(String name, float x, float y, float width, float height, Image image, ArrayList<Property> properties) {
        super(name, x, y, width, height, ObjectType.ITEM, properties);
        chestImage = image;
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta, float offsetX, float offsetY) {
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg,  Graphics g, float offsetX, float offsetY) {
        chestImage.draw(- offsetX + x, - offsetY + y);
        if (hover) {
            g.drawString(name, - offsetX + x, - offsetY + y - 15);
        }
    }

    @Override
    public void turn() {

    }
}
