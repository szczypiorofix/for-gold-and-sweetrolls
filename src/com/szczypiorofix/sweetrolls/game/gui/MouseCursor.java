package com.szczypiorofix.sweetrolls.game.gui;

import com.szczypiorofix.sweetrolls.game.enums.ObjectType;
import com.szczypiorofix.sweetrolls.game.objects.GameObject;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

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

    public void setPositionTile(int tileX, int tileY) {
        this.tileX = tileX;
        this.tileY = tileY;
    }

    public void setTileX(int tileX) {
        this.tileX = tileX;
    }

    public void setTileY(int tileY) {
        this.tileY = tileY;
    }

    @Override
    public int getTileY() {
        return tileY;
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta, float offsetX, float offsetY) {
        x = gc.getInput().getMouseX();
        y = gc.getInput().getMouseY();
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.tileX = ((int) ((x / width) + (offsetX / width)) >= 0) ? (int) ((x / width) + (offsetX / width)) : 0;
        this.tileY = ((int) ((y / height) + (offsetY / height)) >= 0) ? (int) ((y / height) + (offsetY / height)) : 0;
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g, float offsetX, float offsetY) {

    }

    @Override
    public void turn() {

    }

}
