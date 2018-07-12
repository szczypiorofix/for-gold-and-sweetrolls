package com.szczypiorofix.sweetrolls.game.gui;

import com.szczypiorofix.sweetrolls.game.def.ObjectType;
import com.szczypiorofix.sweetrolls.game.graphics.Textures;
import com.szczypiorofix.sweetrolls.game.objects.GameObject;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class MainMenuButton extends GameObject {

    private Image image;
    private String name;
    private float nameX = 0;
    private boolean pressed = false;

    public MainMenuButton(String name, float x, float y, float width, float height) {
        super(name, x, y, width, height, ObjectType.GUI);
        this.name = name;
        nameX = x + 60 - (name.length() * 4);
        image = Textures.getInstance().mainMenuMainButtons.getSprite(0, 0);
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {

    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        image.draw(x, y);
        g.drawString(name, nameX,y+5);

    }


}
