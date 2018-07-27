package com.szczypiorofix.sweetrolls.game.main.fonts;

public class FontChar {

    private int ascii;
    private int ucode;
    private char raw;
    private int bottom;
    private int top;
    private int x, y;
    private int width, height;
    private int trailing;
    private int leading;

    public FontChar(int ascii, int ucode, char raw, int bottom, int top, int x, int y, int width, int height, int leading, int trailing) {
        this.ascii = ascii;
        this.ucode = ucode;
        this.raw = raw;
        this.bottom = bottom;
        this.top = top;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.leading = leading;
        this.trailing = trailing;
    }

    public int getAscii() {
        return ascii;
    }

    public int getUcode() {
        return ucode;
    }

    public char getRaw() {
        return raw;
    }

    public int getBottom() {
        return bottom;
    }

    public int getTop() {
        return top;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getTrailing() {
        return trailing;
    }

    public int getLeading() {
        return leading;
    }
}
