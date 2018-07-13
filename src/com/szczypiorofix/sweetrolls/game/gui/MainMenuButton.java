package com.szczypiorofix.sweetrolls.game.gui;

import com.szczypiorofix.sweetrolls.game.def.ObjectType;
import com.szczypiorofix.sweetrolls.game.graphics.Fonts;
import com.szczypiorofix.sweetrolls.game.graphics.Textures;
import com.szczypiorofix.sweetrolls.game.objects.GameObject;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

public class MainMenuButton extends GameObject {

    private Image image;
    private String name;
    private float nameX = 0;
    private boolean hover = false;
    private boolean active = false;
    private Image imagePressed;
    private Fonts font;


    public MainMenuButton(String name, float x, float y, float width, float height, Fonts font) {
        super(name, x, y, width, height, ObjectType.GUI);
        this.name = name;
        nameX = x + 60 - (name.length() * 4);
        image = Textures.getInstance().mainMenuMainButtons.getSprite(0, 0);
        imagePressed = Textures.getInstance().mainMenuMainButtons.getSprite(1, 0);
        this.font = font;
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

        if (hover) {
            imagePressed.draw(x, y);
            font.draw(name, nameX, y+8, Color.white);
        }
        else {
            image.draw(x, y);
            font.draw(name, nameX, y+5, Color.white);
        }

    }

    public void setHover(boolean hover) {
        this.hover = hover;
    }

    public boolean isHover() {
        return hover;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}