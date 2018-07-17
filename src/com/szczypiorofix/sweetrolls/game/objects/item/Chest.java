package com.szczypiorofix.sweetrolls.game.objects.item;

import com.szczypiorofix.sweetrolls.game.def.ObjectType;
import com.szczypiorofix.sweetrolls.game.graphics.Textures;
import com.szczypiorofix.sweetrolls.game.objects.GameObject;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

public class Chest extends GameObject {

    private Image chestImage;

    public Chest(String name, float x, float y, float width, float height, ObjectType objectType, Image image) {
        super(name, x, y, width, height, objectType);
        chestImage = image;
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta, float offsetX, float offsetY) throws SlickException {
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg,  Graphics g, float offsetX, float offsetY) throws SlickException {
        chestImage.draw(- offsetX + x, - offsetY + y);
        if (hover) {
            g.drawString(name, - offsetX + x, - offsetY + y - 15);
        }
    }
}
