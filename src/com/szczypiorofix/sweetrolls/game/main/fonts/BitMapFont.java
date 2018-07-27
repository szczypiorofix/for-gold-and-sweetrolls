package com.szczypiorofix.sweetrolls.game.main.fonts;

import com.szczypiorofix.sweetrolls.game.main.MainClass;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

public class BitMapFont {


    private final float DEFAULT_FONT_SIZE = 1f;
    private int fontWidth, fontHeight, fontSpace;
    private String name;
    private ArrayList<FontChar> chars;
    private Image fontImage;
    private float size;
    private float scale;

    public BitMapFont(String name) {
        this.name = name;
        chars = new ArrayList<>();
        size = DEFAULT_FONT_SIZE;
        scale = 1;
    }

    public BitMapFont(String name, float size) {
        this(name);
        this.size = size;
        scale = 1;
    }

    public BitMapFont(int fontWidth, int fontHeight, int fontSpace, String name) {
        this(name);
        this.fontWidth = fontWidth;
        this.fontHeight = fontHeight;
        this.fontSpace = fontSpace;
    }

    public void setFontImage(String name) {
        try {
            fontImage = new Image(MainClass.RES + "fonts/" +name);
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g, String text, int x, int y) {
        for (int i = 0; i < text.length(); i++) {
            for (int j = 0; j < chars.size(); j++) {

                if (text.charAt(i) == chars.get(j).getAscii() || ((int) (text.charAt(i)) == chars.get(j).getUcode() && (chars.get(j).getUcode() > 0))) {

                    fontImage.getSubImage(
                            chars.get(j).getX(),
                            chars.get(j).getY(),
                            chars.get(j).getWidth(),
                            chars.get(j).getHeight()
                    ).draw(
                            x + (i * (chars.get(j).getWidth() * scale)),
                            y,
                            scale
                    );

                    g.drawRect(
                            x + (i * chars.get(j).getWidth()),
                            y,
                            chars.get(j).getWidth(),
                            chars.get(j).getHeight()
                    );
                }
            }
        }
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
