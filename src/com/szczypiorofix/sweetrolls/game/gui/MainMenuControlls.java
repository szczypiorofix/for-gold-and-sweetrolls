package com.szczypiorofix.sweetrolls.game.gui;

import com.szczypiorofix.sweetrolls.game.enums.ObjectType;
import com.szczypiorofix.sweetrolls.game.main.fonts.BitMapFont;
import com.szczypiorofix.sweetrolls.game.main.fonts.FontParser;
import com.szczypiorofix.sweetrolls.game.main.graphics.Textures;
import com.szczypiorofix.sweetrolls.game.objects.GameObject;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class MainMenuControlls extends GameObject {

    public enum ControlType {
        OK,
        CANCEL,
        LEF_ARROW,
        RIGHT_ARROW,
        CHECK_BOX,
        TEXT
    }
    private ControlType controlType;
    private Image image;
    private boolean hover = false;
    private boolean active = false;
    private boolean checked;
    private boolean defaultChecked;
    private Image imagePressed;
    private BitMapFont font;
    private String text;
    private String defaultText;
    private boolean textBased;


    public MainMenuControlls(ControlType controlType, String text, Boolean checked, float x, float y, float width, float height) {
        super(text, x, y, width, height, ObjectType.GUI);
        this.text = text;
        this.defaultText = text;
        this.checked = checked;
        this.defaultChecked = checked;
        this.controlType = controlType;

        textBased = controlType == ControlType.TEXT;

        switch (controlType) {
            case OK: {
                image = Textures.getInstance().mainMenuControlls.getSprite(1, 0);
                imagePressed = Textures.getInstance().mainMenuControlls.getSprite(1, 1);
                break;
            }
            case LEF_ARROW: {
                image = Textures.getInstance().mainMenuControlls.getSprite(0, 2);
                imagePressed = Textures.getInstance().mainMenuControlls.getSprite(0, 3);
                break;
            }
            case RIGHT_ARROW: {
                image = Textures.getInstance().mainMenuControlls.getSprite(1, 2);
                imagePressed = Textures.getInstance().mainMenuControlls.getSprite(1, 3);
                break;
            }
            case CHECK_BOX: {
                image = Textures.getInstance().mainMenuControlls.getSprite(0, 4);
                imagePressed = Textures.getInstance().mainMenuControlls.getSprite(1, 4);
                break;
            }
            default: {
                image = Textures.getInstance().mainMenuControlls.getSprite(0, 0);
                imagePressed = Textures.getInstance().mainMenuControlls.getSprite(0, 1);
                break;
            }
        }

        font = FontParser.getFont("Immortal Controlls Button Bitmap Font", "immortal-bitmap.xml", "immortal-bitmap.png");
        font.setSize(5f);
    }

    @Override
    public void update(int delta, float offsetX, float offsetY) {
    }

    @Override
    public void render(Graphics g, float offsetX, float offsetY) {

        if (textBased) {
            font.draw(text, x, y);
        } else {
            if (checked) {
                imagePressed.draw(x, y);
            } else {

                image.draw(x, y);

            }
        }
    }

    public void reset() {
        checked = defaultChecked;
        text = defaultText;
    }

    @Override
    public void turn() {}

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

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isDefaultChecked() {
        return defaultChecked;
    }

    public String getDefaultText() {
        return defaultText;
    }
}

