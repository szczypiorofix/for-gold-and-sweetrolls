/*
 * Developed by szczypiorofix on 24.08.18 13:33.
 * Copyright (c) 2018. All rights reserved.
 *
 */

package com.szczypiorofix.sweetrolls.game.gui;

import com.szczypiorofix.sweetrolls.game.enums.ObjectType;
import com.szczypiorofix.sweetrolls.game.main.fonts.BitMapFont;
import com.szczypiorofix.sweetrolls.game.main.fonts.FontParser;
import com.szczypiorofix.sweetrolls.game.main.graphics.Textures;
import com.szczypiorofix.sweetrolls.game.objects.GameObject;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class MainMenuButton extends GameObject {

    private Image image;
    private String name;
    private float nameX;
    private boolean hover = false;
    private boolean active = false;
    private Image imagePressed;
    private BitMapFont font;


    public MainMenuButton(String name, float x, float y) {
        super(name, x, y, 148, 32, ObjectType.GUI);
        this.name = name;
        image = Textures.getInstance().mainMenuMainButtons.getSprite(0, 0);
        imagePressed = Textures.getInstance().mainMenuMainButtons.getSprite(0, 1);
        font = FontParser.getFont("Immortal Main Menu Button Bitmap Font", "immortal-bitmap.xml", "immortal-bitmap.png");
        font.setSize(4f);
        nameX = x + (width / 2) - (font.getStringLength(name) / 2);
    }

    @Override
    public void update(int delta, float offsetX, float offsetY) {
    }

    @Override
    public void render(Graphics g, float offsetX, float offsetY) {

        if (hover) {
            imagePressed.draw(x, y);
            font.draw(name, (int) (nameX + offsetX), (int) (y + 8 + offsetY));
        } else {
            image.draw(x, y);
            font.draw(name, (int) (nameX + offsetX), (int) (y + 5 + offsetY));
        }

    }


    public void setHover(boolean hover) {
        this.hover = hover;
    }

    public boolean isHover() {
        return hover;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
