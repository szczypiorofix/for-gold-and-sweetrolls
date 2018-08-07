package com.szczypiorofix.sweetrolls.game.gui;

import com.szczypiorofix.sweetrolls.game.main.MainClass;
import com.szczypiorofix.sweetrolls.game.main.fonts.BitMapFont;
import com.szczypiorofix.sweetrolls.game.main.fonts.FontParser;
import com.szczypiorofix.sweetrolls.game.objects.characters.Player;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Inventory {

    private Player player;
    private MouseCursor mouseCursor;
    private BitMapFont font;
    private Image image;
    private boolean show;

    public Inventory(Player player, MouseCursor mouseCursor) {
        this.player = player;
        this.mouseCursor = mouseCursor;

        try {
            image = new Image(MainClass.RES + "assets/inventory.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }

        font = FontParser.getFont("Immortal HUD Bitmap Font", "immortal-bitmap.xml", "immortal-bitmap.png");
        font.setSize(4.5f);
    }

    public void render(GameContainer gc, StateBasedGame sgb, Graphics g) {
        if (show) {
            image.draw(150, 90);
        }
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }
}
