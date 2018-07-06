package com.szczypiorofix.racoon.game.objects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Player extends GameObject {

    private Image image;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        this.living = true;
        this.dynamic = true;
        this.visible = true;
        try {
            this.image = new Image("res/tiles/player.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {

    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        image.draw(this.x, this.y);
    }


}
