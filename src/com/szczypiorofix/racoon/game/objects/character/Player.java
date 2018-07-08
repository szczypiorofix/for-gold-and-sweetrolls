package com.szczypiorofix.racoon.game.objects.character;

import com.szczypiorofix.racoon.game.def.ObjectType;
import com.szczypiorofix.racoon.game.objects.GameObject;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Player extends GameObject {

    private Image image;

    public Player(String name, int x, int y) {
        super(name, x, y, ObjectType.PLAYER);
        this.living = true;
        this.dynamic = true;
        this.visible = true;
        this.moving = true;
        try {
            this.image = new Image("res/tiles/player.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void update(GameContainer gc, int delta) {

    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        image.draw(this.x, this.y);
    }

}
