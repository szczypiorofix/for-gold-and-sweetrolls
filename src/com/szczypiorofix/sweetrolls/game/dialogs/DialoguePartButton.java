/*
 * Developed by szczypiorofix on 29.08.18 11:38.
 * Copyright (c) 2018. All rights reserved.
 *
 */

package com.szczypiorofix.sweetrolls.game.dialogs;

import com.szczypiorofix.sweetrolls.game.enums.ObjectType;
import com.szczypiorofix.sweetrolls.game.main.fonts.BitMapFont;
import com.szczypiorofix.sweetrolls.game.main.fonts.FontParser;
import com.szczypiorofix.sweetrolls.game.objects.GameObject;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class DialoguePartButton extends GameObject {

    private String response;
    private boolean endButton;
    private int nextId;
    private int order;
    public static final int BX = 30;
    public static int BY = 570;
    public static final int BWIDTH = 250;
    public static final int BHEIGHT = 40;
    private BitMapFont font;


    public DialoguePartButton(String order, String response, String endButton, String nextId) {
        super(response, BX, BY, BWIDTH, BHEIGHT, ObjectType.GUI);
        this.order = Integer.parseInt(order);
        this.response = response;
        this.endButton = Boolean.parseBoolean(endButton);
        this.nextId = Integer.parseInt(nextId);
        font = FontParser.getFont("Immortal Menu Button Bitmap Font", "immortal-bitmap.xml", "immortal-bitmap.png");
        font.setSize(5f);
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public boolean isEndButton() {
        return endButton;
    }

    public void setEndButton(boolean endButton) {
        this.endButton = endButton;
    }

    public int getNextId() {
        return nextId;
    }

    public void setNextId(int nextId) {
        this.nextId = nextId;
    }

    @Override
    public void update(int delta, float offsetX, float offsetY) throws SlickException {}

    @Override
    public void render(Graphics g, float offsetX, float offsetY) throws SlickException {
        g.drawRect(x - 4, y - 2, width + 4, height + 4);
        font.draw(name, x, y + 5);
    }
}
