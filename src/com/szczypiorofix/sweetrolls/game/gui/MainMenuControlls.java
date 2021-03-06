/*
 * Developed by szczypiorofix on 24.08.18 13:33.
 * Copyright (c) 2018. All rights reserved.
 *
 */

package com.szczypiorofix.sweetrolls.game.gui;

import com.szczypiorofix.sweetrolls.game.enums.ObjectType;
import com.szczypiorofix.sweetrolls.game.interfaces.CloseableFrameListener;
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
        TEXT,
        UP_ARROW,
        DOWN_ARROW,
        RADIO_BUTTON
    }
    private ControlType controlType;
    private Image imageDefault, imageHover, imageActive;
    private boolean hover = false;
    private boolean active = false;
    private boolean checked;
    private boolean defaultChecked;
    private BitMapFont font;
    private String text;
    private String defaultText;
    private boolean textBased;
    private CloseableFrameListener closeableFrameListener;


    public MainMenuControlls(ControlType controlType, String text, Boolean checked, float x, float y) {
        super(text, x, y, 32, 32, ObjectType.GUI);
        this.text = text;
        this.defaultText = text;
        this.checked = checked;
        this.defaultChecked = checked;
        this.controlType = controlType;

        textBased = controlType == ControlType.TEXT;

        switch (controlType) {
            case OK: {
                imageDefault = Textures.getInstance().mainMenuControlls.getSprite(0, 1);
                imageHover = Textures.getInstance().mainMenuControlls.getSprite(1, 1);
                imageActive = Textures.getInstance().mainMenuControlls.getSprite(2, 1);
                break;
            }
            case LEF_ARROW: {
                imageDefault = Textures.getInstance().mainMenuControlls.getSprite(0, 2);
                imageHover = Textures.getInstance().mainMenuControlls.getSprite(1, 2);
                imageActive = Textures.getInstance().mainMenuControlls.getSprite(2, 2);
                break;
            }
            case RIGHT_ARROW: {
                imageDefault = Textures.getInstance().mainMenuControlls.getSprite(0, 3);
                imageHover = Textures.getInstance().mainMenuControlls.getSprite(1, 3);
                imageActive = Textures.getInstance().mainMenuControlls.getSprite(2, 3);
                break;
            }
            case CHECK_BOX: {
                imageDefault = Textures.getInstance().mainMenuControlls.getSprite(0, 4);
                imageHover = Textures.getInstance().mainMenuControlls.getSprite(1, 4);
                imageActive = Textures.getInstance().mainMenuControlls.getSprite(2, 4);
                break;
            }
            case UP_ARROW: {
                imageDefault = Textures.getInstance().mainMenuControlls.getSprite(0, 5);
                imageHover = Textures.getInstance().mainMenuControlls.getSprite(1, 5);
                imageActive = Textures.getInstance().mainMenuControlls.getSprite(2, 5);
                break;
            }
            case DOWN_ARROW: {
                imageDefault = Textures.getInstance().mainMenuControlls.getSprite(0, 6);
                imageHover = Textures.getInstance().mainMenuControlls.getSprite(1, 6);
                imageActive = Textures.getInstance().mainMenuControlls.getSprite(2, 6);
                break;
            }
            case RADIO_BUTTON: {
                imageDefault = Textures.getInstance().mainMenuControlls.getSprite(0, 7);
                imageHover = Textures.getInstance().mainMenuControlls.getSprite(1, 7);
                imageActive = Textures.getInstance().mainMenuControlls.getSprite(2, 7);
                break;
            }
            default: {
                imageDefault = Textures.getInstance().mainMenuControlls.getSprite(0, 0);
                imageHover = Textures.getInstance().mainMenuControlls.getSprite(1, 0);
                imageActive = Textures.getInstance().mainMenuControlls.getSprite(2, 0);
                break;
            }
        }

        font = FontParser.getFont();
    }

    @Override
    public void update(int delta, float offsetX, float offsetY) {
    }

    @Override
    public void render(Graphics g, float offsetX, float offsetY) {

        if (textBased) {
            font.draw(text, x, y);
        } else {
            if (active || checked) {
                imageActive.draw(x, y);
            } else if (hover) {
                imageHover.draw(x, y);
            } else imageDefault.draw(x, y);
        }
    }

    public void reset() {
        checked = defaultChecked;
        text = defaultText;
    }

    public void setCloseableFrameListener(CloseableFrameListener closeableFrameListener) {
        this.closeableFrameListener = closeableFrameListener;
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

    public void close() {
        closeableFrameListener.closeFrame();
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

    public ControlType getControlType() {
        return controlType;
    }
}

