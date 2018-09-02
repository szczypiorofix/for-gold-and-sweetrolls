/*
 * Developed by szczypiorofix on 24.08.18 13:31.
 * Copyright (c) 2018. All rights reserved.
 *
 */

package com.szczypiorofix.sweetrolls.game.gui;

import com.szczypiorofix.sweetrolls.game.enums.ObjectType;
import com.szczypiorofix.sweetrolls.game.main.fonts.BitMapFont;
import com.szczypiorofix.sweetrolls.game.main.fonts.FontParser;
import com.szczypiorofix.sweetrolls.game.objects.GameObject;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class InventoryOptionsButton extends GameObject {

    private BitMapFont font;
    private String command;

    public InventoryOptionsButton(String name, int x, int y, String command) {
        super(name, x, y, 100, 25, ObjectType.GUI);
        this.command = command;
        font = FontParser.getFont();
    }

    @Override
    public void update(int delta, float offsetX, float offsetY) throws SlickException {}

    @Override
    public void render(Graphics g, float offsetX, float offsetY) throws SlickException {
        Color c = g.getColor();
        g.setColor(Color.black);
        g.fillRect(x - 4, y - 2, width + 4, height + 4);
        g.setColor(c);
        font.draw(name, (int) x, (int) (y + 5));
    }



    public String getCommand() {
        return command;
    }
}
