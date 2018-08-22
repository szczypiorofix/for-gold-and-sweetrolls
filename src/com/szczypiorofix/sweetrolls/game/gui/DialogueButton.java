package com.szczypiorofix.sweetrolls.game.gui;

import com.szczypiorofix.sweetrolls.game.enums.ObjectType;
import com.szczypiorofix.sweetrolls.game.main.fonts.BitMapFont;
import com.szczypiorofix.sweetrolls.game.main.fonts.FontParser;
import com.szczypiorofix.sweetrolls.game.objects.GameObject;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class DialogueButton extends GameObject {

    private boolean active = false;
    private BitMapFont font;
    private boolean endButton;
    private boolean clicked;
    private int nextDialogueState;

    public DialogueButton(String name, float x, float y, float width, float height, boolean endButton, int nextDialogueState) {
        super(name, x, y, width, height, ObjectType.GUI);
        this.name = name;
        this.endButton = endButton;
        this.nextDialogueState = nextDialogueState;
        font = FontParser.getFont("Immortal Menu Button Bitmap Font", "immortal-bitmap.xml", "immortal-bitmap.png");
        font.setSize(5f);
    }


    @Override
    public void update(int delta, float offsetX, float offsetY) {}

    @Override
    public void render(Graphics g, float offsetX, float offsetY) {
        g.drawRect(x - 4, y - 2, width + 4, height + 4);
        font.draw(name, x, y + 5);
    }

    public boolean isClicked() {
        return clicked;
    }

    public boolean isEndButton() {
        return endButton;
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
