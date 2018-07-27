package com.szczypiorofix.sweetrolls.game.gui;

import com.szczypiorofix.sweetrolls.game.enums.ObjectType;
import com.szczypiorofix.sweetrolls.game.main.graphics.Textures;
import com.szczypiorofix.sweetrolls.game.objects.GameObject;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

public class MainMenuButton extends GameObject {

    private Image image;
    private String name;
    private float nameX;
    private boolean hover = false;
    private boolean active = false;
    private Image imagePressed;


    public MainMenuButton(String name, float x, float y, float width, float height) {
        super(name, x, y, width, height, ObjectType.GUI);
        this.name = name;
        nameX = x + 60 - (name.length() * 4);
        image = Textures.getInstance().mainMenuMainButtons.getSprite(0, 0);
        imagePressed = Textures.getInstance().mainMenuMainButtons.getSprite(1, 0);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta, float offsetX, float offsetY) {
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g, float offsetX, float offsetY) {

        if (hover) {
            imagePressed.draw(x, y);
            g.drawString(name, nameX + offsetX, y + 8 + offsetY);
        }
        else {
            image.draw(x, y);
            g.drawString(name, nameX + offsetX, y + 5 + offsetY);
        }

    }

    @Override
    public void turn() {

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
