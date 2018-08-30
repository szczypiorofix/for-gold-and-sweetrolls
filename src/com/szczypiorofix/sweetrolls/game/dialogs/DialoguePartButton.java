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

    private BitMapFont font;

    private String response;
    private boolean endButton;
    private boolean random;
    private int nextId;
    private int order;
    private int rangeFrom, rangeTo;
    public static final int BX = 30;
    public static int BY = 570;
    public static final int BWIDTH = 250;
    public static final int BHEIGHT = 40;


    public DialoguePartButton(String order, String response, String endButton, String nextId, String random, String rangeFrom, String rangeTo) {
        super(response, BX, BY, BWIDTH, BHEIGHT, ObjectType.GUI);
        this.order = Integer.parseInt(order);
        this.response = response;
        this.endButton = Boolean.parseBoolean(endButton);
        this.random = Boolean.parseBoolean(random);
        this.nextId = Integer.parseInt(nextId);
        this.rangeFrom = Integer.parseInt(rangeFrom);
        this.rangeTo = Integer.parseInt(rangeTo);
        font = FontParser.getFont("Immortal Menu Button Bitmap Font", "immortal-bitmap.xml", "immortal-bitmap.png");
        font.setSize(5f);
    }

    public int getRangeFrom() {
        return rangeFrom;
    }

    public void setRangeFrom(int rangeFrom) {
        this.rangeFrom = rangeFrom;
    }

    public int getRangeTo() {
        return rangeTo;
    }

    public void setRangeTo(int rangeTo) {
        this.rangeTo = rangeTo;
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

    public boolean isRandom() {
        return random;
    }

    public void setRandom(boolean random) {
        this.random = random;
    }

    @Override
    public void update(int delta, float offsetX, float offsetY) throws SlickException {}

    @Override
    public void render(Graphics g, float offsetX, float offsetY) throws SlickException {
        g.drawRect(x - 5, y - 2, width + 5, height + 4);
        font.draw(response, x, y + 10);
    }
}
