package com.szczypiorofix.sweetrolls.game.gui;

import com.szczypiorofix.sweetrolls.game.enums.ObjectType;
import com.szczypiorofix.sweetrolls.game.objects.GameObject;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class MouseCursor extends GameObject {

    public MouseCursor(String name, float x, float y, float width, float height, ObjectType objectType) {
        super(name, x, y, width, height, objectType);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta, float offsetX, float offsetY) {
        x = gc.getInput().getMouseX();
        y = gc.getInput().getMouseY();
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g, float offsetX, float offsetY) {

    }

    @Override
    public void turn() {

    }

}
