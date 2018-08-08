package com.szczypiorofix.sweetrolls.game.gui;

import com.szczypiorofix.sweetrolls.game.objects.item.Item;
import org.newdawn.slick.Graphics;

public class InventoryContainer {

    private int id;
    private int x, y;
    private Item item;

    public InventoryContainer(int id, int x, int y, Item item) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.item = item;
    }

    public void draw(Graphics g) {
        if (item != null) {
            if (item.getImage() != null)
                item.getImage().draw(x, y);
        }
        g.drawString(id+"", x, y);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
