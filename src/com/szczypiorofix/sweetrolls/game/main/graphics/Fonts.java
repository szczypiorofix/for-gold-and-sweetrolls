package com.szczypiorofix.sweetrolls.game.main.graphics;

import com.szczypiorofix.sweetrolls.game.main.MainClass;
import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.font.effects.ColorEffect;



public class Fonts {

    private org.newdawn.slick.UnicodeFont uniFont;
    private TrueTypeFont font2;
    AngelCodeFont font;

    public Fonts(String name, int style, float size) {
        try {
            //Image fontImage = new Image(MainClass.RES + "fonts/nowy_00.tga", false, Image.FILTER_NEAREST);
            //font = new AngelCodeFont(MainClass.RES + "fonts/nowy.fnt", fontImage);

            java.awt.Font UIFont1 = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT,
                    org.newdawn.slick.util.ResourceLoader.getResourceAsStream(MainClass.RES + "fonts/"+name));
            UIFont1 = UIFont1.deriveFont(style, size);
            //font2 = new TrueTypeFont(UIFont1, false);
            uniFont = new org.newdawn.slick.UnicodeFont(UIFont1);
            uniFont.addAsciiGlyphs();
            uniFont.getEffects().add(new ColorEffect(java.awt.Color.white));
            uniFont.addAsciiGlyphs();
            uniFont.loadGlyphs();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void draw(String text, float x, float y) {
        uniFont.drawString(x, y, text, Color.white);
        //font.drawString(x, y, text);
    }

    public void draw(String text, float x, float y, org.newdawn.slick.Color color) {
        uniFont.drawString(x, y, text, color);
        //font.drawString(x, y, text, color);
    }
}
