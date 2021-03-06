/*
 * Developed by szczypiorofix on 24.08.18 13:33.
 * Copyright (c) 2018. All rights reserved.
 *
 */

package com.szczypiorofix.sweetrolls.game.gui;

import com.szczypiorofix.sweetrolls.game.enums.ObjectType;
import com.szczypiorofix.sweetrolls.game.objects.GameObject;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;


public class MouseCursor extends GameObject {

    private float offsetX, offsetY;
    private int tileX, tileY;
    private Input input;

    public MouseCursor(String name, float x, float y, float width, float height, ObjectType objectType, Input input) {
        super(name, x, y, width, height, objectType);
        this.input = input;
    }

    @Override
    public int getTileX() {
        return tileX;
    }

    @Override
    public int getTileY() {
        return tileY;
    }

    public void setPositionTile(int tileX, int tileY) {
        this.tileX = tileX;
        this.tileY = tileY;
    }

    public void resetPosition() {
        setPositionTile(0, 0);
    }

    @Override
    public void update(int delta, float offsetX, float offsetY) {
        x = input.getMouseX();
        y = input.getMouseY();
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.tileX = ((int) ((x / width) + (offsetX / width)) >= 0) ? (int) ((x / width) + (offsetX / width)) : 0;
        this.tileY = ((int) ((y / height) + (offsetY / height)) >= 0) ? (int) ((y / height) + (offsetY / height)) : 0;
    }

    @Override
    public void render(Graphics g, float offsetX, float offsetY) {}


    public Input getInput() {
        return input;
    }

}
