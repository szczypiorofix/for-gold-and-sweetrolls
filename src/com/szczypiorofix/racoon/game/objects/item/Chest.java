package com.szczypiorofix.racoon.game.objects.item;

import com.szczypiorofix.racoon.game.def.ObjectType;
import com.szczypiorofix.racoon.game.objects.GameObject;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Chest extends GameObject {

    private Image image;

    public Chest(String name, float x, float y, float width, float height, ObjectType objectType) {
        super(name, x, y, width, height, objectType);
        try {
            this.image = new Image("res/tiles/chest.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        image.draw(x, y);
    }
}
