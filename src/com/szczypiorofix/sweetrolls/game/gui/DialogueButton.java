package com.szczypiorofix.sweetrolls.game.gui;

import com.szczypiorofix.sweetrolls.game.enums.ObjectType;
import com.szczypiorofix.sweetrolls.game.main.fonts.BitMapFont;
import com.szczypiorofix.sweetrolls.game.main.fonts.FontParser;
import com.szczypiorofix.sweetrolls.game.objects.GameObject;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

public class DialogueButton extends GameObject {

    private String name;
    private boolean hover = false;
    private boolean active = false;
    private BitMapFont font;
    private boolean endButton;
    private boolean clicked;
    private float x, y, width, height;
    private int nextDialogueState;

    public DialogueButton(String name, float x, float y, float width, float height, boolean endButton, int nextDialogueState) {
        super(name, x, y, width, height, ObjectType.GUI);
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.endButton = endButton;
        this.nextDialogueState = nextDialogueState;
        font = FontParser.getFont("Immortal Menu Button Bitmap Font", "immortal-bitmap.xml", "immortal-bitmap.png");
        font.setSize(4f);
    }


    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta, float offsetX, float offsetY) throws SlickException {

     }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g, float offsetX, float offsetY) throws SlickException {

        //if (visible) {
            //image.draw(x, y);
            //Color c = g.getColor();
            //g.setColor(Color.gray);
            //g.drawRect(x, y, width ,height);
            //g.setColor(c);

            font.draw(name, x, y + 5);
        //}
    }

    @Override
    public void turn() {

    }

    public boolean isClicked() {
        return clicked;
    }

    public boolean isEndButton() {
        return endButton;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean isHover() {
        return hover;
    }

    @Override
    public void setHover(boolean hover) {
        this.hover = hover;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public BitMapFont getFont() {
        return font;
    }

    public void setFont(BitMapFont font) {
        this.font = font;
    }

    public int getNextDialogueState() {
        return nextDialogueState;
    }
}
