package com.szczypiorofix.racoon.game.objects.character;

import com.szczypiorofix.racoon.game.def.ObjectType;
import com.szczypiorofix.racoon.game.objects.GameObject;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Player extends GameObject {

    private Image image;
    private float sx, sy;

    public Player(String name, float x, float y, float width, float height) {
        super(name, x, y, width, height, ObjectType.PLAYER);
        sx = 0;
        sy = 0;
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
        image.draw(sx, sy);
    }

    public void moveNorth(float s) {
        y -= s;
    }

    public void moveSouth(float s) {
        y += s;
    }

    public void moveEast(float s) {
        x += s;
    }

    public void moveWest(float s) {
        x -= s;
    }

    public float getSx() {
        return sx;
    }

    public void setSx(float sx) {
        this.sx = sx;
    }

    public float getSy() {
        return sy;
    }

    public void setSy(float sy) {
        this.sy = sy;
    }

}
