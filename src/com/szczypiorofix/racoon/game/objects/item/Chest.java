package com.szczypiorofix.racoon.game.objects.item;

import com.szczypiorofix.racoon.game.def.ObjectType;
import com.szczypiorofix.racoon.game.graphics.Textures;
import com.szczypiorofix.racoon.game.objects.GameObject;
import org.newdawn.slick.*;

public class Chest extends GameObject {

    private Image chestImage;

    public Chest(String name, float x, float y, float width, float height, ObjectType objectType) {
        super(name, x, y, width, height, objectType);
        chestImage = Textures.getInstance().miscItems.getSprite(0, 0);
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        chestImage.draw(x, y);
    }
}
