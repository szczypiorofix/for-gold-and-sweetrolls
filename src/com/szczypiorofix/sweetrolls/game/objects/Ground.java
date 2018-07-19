package com.szczypiorofix.sweetrolls.game.objects;

import com.szczypiorofix.sweetrolls.game.def.ObjectType;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

public class Ground extends GameObject {

    private Image image;
    private Color c;

    public Ground(String name, float x, float y, float width, float height, ObjectType objectType, Image image) {
        super(name, x, y, width, height, objectType);
        this.image = image;
        c = new Color(Color.white);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta, float offsetX, float offsetY) {

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g, float offsetX, float offsetY) {
        c.a = opacity;
        g.drawImage(image, - offsetX + x, - offsetY + y, c);
    }

}
