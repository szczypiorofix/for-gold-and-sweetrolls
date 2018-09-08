/*
 * Developed by szczypiorofix on 09.09.18 00:04.
 * Copyright (c) 2018. All rights reserved.
 *
 */

package com.szczypiorofix.sweetrolls.game.main.fonts;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;


public class BitMapFont {

    private final float SPACE_WIDTH = 0.06f;
    private int fontWidth, fontHeight, fontSpace;
    private ArrayList<FontChar> charsArray;
    private Image fontImage;


    public BitMapFont(String name) {
        charsArray = new ArrayList<>();
        try {
            fontImage = new Image("fonts/" +name);
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public float getStringLength(String text) {
        float c = 0;
        for (int i = 0; i < text.length(); i++) {
            for (int j = 0; j < charsArray.size(); j++) {
                if (text.charAt(i) == charsArray.get(j).getAscii()
                        || ((int) (text.charAt(i)) == charsArray.get(j).getUcode() && (charsArray.get(j).getUcode() > 0))
                        ) {

                    c += this.charsArray.get(j).getWidth()
                            + this.charsArray.get(j).getLeading()
                            + this.charsArray.get(j).getTrailing();
                }
                if (text.charAt(i) == 32) {
                    c += SPACE_WIDTH;
                }
            }
        }
        return c;
    }


    public void draw(String text, float x, float y) {
        float currentCharLength = 0;
        if (text != null) {
            char[] chars = text.toCharArray();
            int line = 0;
            for (int i = 0; i < chars.length; i++) {
                for (int j = 0; j < this.charsArray.size(); j++) {

                    if (chars[i] == '\n' || chars[i] == '\r') {
                        line++;
                        currentCharLength = 0;
                        break;
                    }

                    if (chars[i] == this.charsArray.get(j).getAscii()
                            || ((int) chars[i] == this.charsArray.get(j).getUcode() && (this.charsArray.get(j).getUcode() > 0))
                    ) {

                        fontImage.getSubImage(
                                this.charsArray.get(j).getX(),
                                this.charsArray.get(j).getY(),
                                this.charsArray.get(j).getWidth(),
                                this.charsArray.get(j).getHeight()
                        ).draw(
                                x + currentCharLength,
                                (int) (( y + this.charsArray.get(j).getTop()) + fontHeight) - fontHeight + (line * (fontHeight + fontSpace))
                        );

                        currentCharLength += this.charsArray.get(j).getWidth()
                                + this.charsArray.get(j).getLeading()
                                + this.charsArray.get(j).getTrailing();
                    }

                    if (chars[i] == 32) {
                        currentCharLength += SPACE_WIDTH;
                    }
                }
            }
        }
    }


    void addChar(FontChar fontChar) {
        charsArray.add(fontChar);
    }

    void setFontWidth(int fontWidth) {
        this.fontWidth = fontWidth;
    }

    void setFontHeight(int fontHeight) {
        this.fontHeight = fontHeight;
    }

    void setFontSpace(int fontSpace) {
        this.fontSpace = fontSpace;
    }


}
