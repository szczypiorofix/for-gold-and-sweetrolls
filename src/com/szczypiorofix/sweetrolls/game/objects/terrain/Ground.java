package com.szczypiorofix.sweetrolls.game.objects.terrain;

import com.szczypiorofix.sweetrolls.game.def.ObjectType;
import com.szczypiorofix.sweetrolls.game.objects.GameObject;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

public class Ground extends GameObject {

    private Image image;

    public Ground(String name, float x, float y, float width, float height, ObjectType objectType, Image image) {
        super(name, x, y, width, height, objectType);
        this.image = image;
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta, float offsetX, float offsetY) {

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g, float offsetX, float offsetY) {
        g.drawImage(image, - offsetX + x, - offsetY + y);
    }

}
