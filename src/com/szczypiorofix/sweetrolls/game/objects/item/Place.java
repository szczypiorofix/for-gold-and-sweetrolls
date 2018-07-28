package com.szczypiorofix.sweetrolls.game.objects.item;

import com.szczypiorofix.sweetrolls.game.enums.ObjectType;
import com.szczypiorofix.sweetrolls.game.main.fonts.BitMapFont;
import com.szczypiorofix.sweetrolls.game.main.fonts.FontParser;
import com.szczypiorofix.sweetrolls.game.tilemap.Property;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;

public class Place extends Item {

    private BitMapFont font;

    public Place(String name, float x, float y, float width, float height, Image image, ArrayList<Property> properties) {
        super(name, x, y, width, height, image, ObjectType.PLACE, properties);
        font = FontParser.getFont("Immortal HUD Bitmap Font", "immortal-bitmap.xml", "immortal-bitmap.png");
        font.setSize(4.5f);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta, float offsetX, float offsetY) throws SlickException {
        hover = false;
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g, float offsetX, float offsetY) throws SlickException {
        image.draw(- offsetX + x, - offsetY + y);
        if (hover) {
            font.draw(name, - offsetX + x, - offsetY + y - 20);
        }
    }

    @Override
    public void turn() {

    }
}
