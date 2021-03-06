/*
 * Developed by szczypiorofix on 24.08.18 13:34.
 * Copyright (c) 2018. All rights reserved.
 *
 */

package com.szczypiorofix.sweetrolls.game.objects.terrain;

import com.szczypiorofix.sweetrolls.game.enums.ObjectType;
import com.szczypiorofix.sweetrolls.game.main.fonts.BitMapFont;
import com.szczypiorofix.sweetrolls.game.main.fonts.FontParser;
import com.szczypiorofix.sweetrolls.game.objects.GameObject;
import com.szczypiorofix.sweetrolls.game.tilemap.Property;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import java.io.Serializable;
import java.util.ArrayList;

public class Place extends GameObject {

    private BitMapFont font;
    private Image image;
    private String type;

    public Place(String name, int x, int y, int width, int height, Image image, String type, ArrayList<Property> properties) {
        super(name, x, y, width, height, ObjectType.PLACE, properties);
        font = FontParser.getFont();
        this.type = type;
        this.image = image;
    }

    @Override
    public void update(int delta, float offsetX, float offsetY) {
        hover = false;
    }

    @Override
    public void render(Graphics g, float offsetX, float offsetY) {
        image.draw(- offsetX + x, - offsetY + y);
        if (hover) {
            font.draw(name, - offsetX + x, - offsetY + y - 20);
        }
    }


    public String getType() {
        return type;
    }
}
