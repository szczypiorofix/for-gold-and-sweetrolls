/*
 * Developed by szczypiorofix on 24.08.18 13:37.
 * Copyright (c) 2018. All rights reserved.
 *
 */

package com.szczypiorofix.sweetrolls.game.main.fonts;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;


public class BitMapFont {

    private final float DEFAULT_FONT_SIZE = 1f;
    private int fontWidth, fontHeight, fontSpace;
    private ArrayList<FontChar> chars;
    private Image fontImage;


    public BitMapFont() {
        chars = new ArrayList<>();
    }

    public BitMapFont(float size) {
        //this.size = size;
    }

    public BitMapFont(int fontWidth, int fontHeight, int fontSpace) {
        this();
        this.fontWidth = fontWidth;
        this.fontHeight = fontHeight;
        this.fontSpace = fontSpace;
    }

    public void setFontImage(String name) {
        try {
            fontImage = new Image("fonts/" +name);
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public float getStringLength(String text) {
        float c = 0;
        for (int i = 0; i < text.length(); i++) {
            for (int j = 0; j < chars.size(); j++) {
                if (text.charAt(i) == chars.get(j).getAscii()
                        || ((int) (text.charAt(i)) == chars.get(j).getUcode() && (chars.get(j).getUcode() > 0))
                        ) {

                    c += chars.get(j).getWidth();
                }
                if (text.charAt(i) == 32) {
                    c += 0.2f;
                }
            }
        }
        return c;
    }


    public void draw(String text, float x, float y) {
        float c = 0;
        char[] ch = text.toCharArray();
        int line = 0;
        for (int i = 0; i < ch.length; i++) {
            for (int j = 0; j < chars.size(); j++) {
                if (ch[i] == '\n' || ch[i] == '\r') {
                    line++;
                    c = 0;
                }

                if (ch[i] == chars.get(j).getAscii()
                        || ((int) ch[i] == chars.get(j).getUcode() && (chars.get(j).getUcode() > 0))
                        ) {

                    fontImage.getSubImage(
                            chars.get(j).getX(),
                            chars.get(j).getY(),
                            chars.get(j).getWidth(),
                            chars.get(j).getHeight()
                    ).draw(
                            x + c,
                            (int) (( y + chars.get(j).getTop() ) + fontHeight ) - (fontHeight ) + (line)
                    );
                    c += chars.get(j).getWidth() - chars.get(j).getLeading() + chars.get(j).getTrailing();
                }

                if (ch[i] == 32) {
                    c += 0.07f;
                }
            }
        }
    }


    public void addChar(FontChar fontChar) {
        chars.add(fontChar);
    }

    public ArrayList<FontChar> getChars() {
        return chars;
    }

    public void setChars(ArrayList<FontChar> chars) {
        this.chars = chars;
    }

    public int getFontWidth() {
        return fontWidth;
    }

    public void setFontWidth(int fontWidth) {
        this.fontWidth = fontWidth;
    }

    public int getFontHeight() {
        return fontHeight;
    }

    public void setFontHeight(int fontHeight) {
        this.fontHeight = fontHeight;
    }

    public int getFontSpace() {
        return fontSpace;
    }

    public void setFontSpace(int fontSpace) {
        this.fontSpace = fontSpace;
    }


}
