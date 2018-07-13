package com.szczypiorofix.sweetrolls.game.objects.character;

import com.szczypiorofix.sweetrolls.game.def.ObjectType;
import com.szczypiorofix.sweetrolls.game.graphics.Textures;
import com.szczypiorofix.sweetrolls.game.objects.GameObject;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

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
        image = Textures.getInstance().classm32.getSprite(3, 0);
    }



    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) {

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {
        image.draw(sx, sy);
        if (hover) {
            g.drawString(name, sx, sy - 15);
        }
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
