package com.szczypiorofix.racoon.game.objects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

abstract public class GameObject {

    int x = 0;
    int y = 0;
    boolean moving = false;
    boolean living = false;
    boolean dynamic = false;
    boolean visible = false;


    public abstract void update(GameContainer gc, int delta) throws SlickException;

    public abstract void render(GameContainer gc, Graphics g) throws SlickException;


}
