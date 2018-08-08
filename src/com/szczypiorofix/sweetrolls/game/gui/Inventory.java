package com.szczypiorofix.sweetrolls.game.gui;

import com.szczypiorofix.sweetrolls.game.main.fonts.BitMapFont;
import com.szczypiorofix.sweetrolls.game.main.fonts.FontParser;
import com.szczypiorofix.sweetrolls.game.objects.characters.Player;
import com.szczypiorofix.sweetrolls.game.objects.item.Item;
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
    private int rows = 8;
    private int cols = 5;
    private InventoryContainer[][] items = new InventoryContainer[cols][rows];

    public Inventory(Player player, MouseCursor mouseCursor) {
        this.player = player;
        this.mouseCursor = mouseCursor;

        try {
            image = new Image("assets/inventory.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }

        font = FontParser.getFont("Immortal HUD Bitmap Font", "immortal-bitmap.xml", "immortal-bitmap.png");
        font.setSize(4.5f);
        int id = 0;
        for (int y = 0; y < cols; y++) {
            for (int x = 0; x < rows; x++) {
                items[y][x] = new InventoryContainer(id, 170 + x * 34, 360 + y * 34, null);
                id++;
            }
        }
    }

    public void update(GameContainer gc, StateBasedGame sgb, int delta) {

    }

    public void render(GameContainer gc, StateBasedGame sgb, Graphics g) {
        if (show) {
            image.draw(150, 90);

            for (int x = 0; x < items.length; x++) {
                for (int y = 0; y < items[0].length; y++) {
                    if (items[x][y] != null) {
                        items[x][y].draw(g);
                    }
                }
            }

        }
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public boolean putToInventory(Item item) {
        boolean done = false;
        int c = 0;
        int r = 0;
        do {
            if (c >= cols) return false;
            if (items[c][r].getItem() == null) {
                items[c][r].setItem(item);
                done = true;
            }
            r++;
            if (r >= rows) {
                r = 0;
                c++;
            }
        } while(!done);
        return true;
    }
}