package com.szczypiorofix.sweetrolls.game.graphics;

import com.szczypiorofix.sweetrolls.game.main.MainClass;
import org.newdawn.slick.font.effects.ColorEffect;


public class Fonts {

    private org.newdawn.slick.UnicodeFont uniFont;


    public Fonts(String name, int style, float size) {

        try {
            java.awt.Font UIFont1 = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT,
                    org.newdawn.slick.util.ResourceLoader.getResourceAsStream(MainClass.RES + "fonts/" + name));
            UIFont1 = UIFont1.deriveFont(style, size);
            uniFont = new org.newdawn.slick.UnicodeFont(UIFont1);
            uniFont.addAsciiGlyphs();
            uniFont.getEffects().add(new ColorEffect(java.awt.Color.white));
            uniFont.addAsciiGlyphs();
            uniFont.loadGlyphs();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void draw(String text, float x, float y, org.newdawn.slick.Color color) {
        uniFont.drawString(x, y, text, color);
    }
}
