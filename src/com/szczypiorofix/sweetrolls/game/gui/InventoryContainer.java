package com.szczypiorofix.sweetrolls.game.gui;

import com.szczypiorofix.sweetrolls.game.enums.InventorySlotType;
import com.szczypiorofix.sweetrolls.game.enums.ObjectType;
import com.szczypiorofix.sweetrolls.game.main.fonts.BitMapFont;
import com.szczypiorofix.sweetrolls.game.main.fonts.FontParser;
import com.szczypiorofix.sweetrolls.game.objects.GameObject;
import com.szczypiorofix.sweetrolls.game.objects.item.Item;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class InventoryContainer extends GameObject {

    private int x, y;
    private int c;
    private InventorySlotType inventorySlotType;
    public Item item;
    public BitMapFont font;

    public InventoryContainer(int c, InventorySlotType inventorySlotType, int x, int y, Item item) {
        super("Inventory container", x, y, 32, 32, ObjectType.INVENTORY_CONTAINER);
        this.c = c;
        this.inventorySlotType = inventorySlotType;
        this.x = x;
        this.y = y;
        this.item = item;
        font = FontParser.getFont("BitMap Inventory Font", "immortal-bitmap.xml", "immortal-bitmap.png");
        font.setSize(4.5f);
    }


    @Override
    public void update(int delta, float offsetX, float offsetY) {
        hover = false;
    }

    @Override
    public void render(Graphics g, float offsetX, float offsetY) {
        if (item != null) {
            if (item.getImage() != null)
                item.getImage().draw(item.getX(), item.getY());
        }
        if (hover) {
            g.drawRect(x-1, y, width-2, height-2);
            if (item != null) {
                font.draw(item.getName(), x , y - 25);
            }
        }

        //g.drawString(c+"", x, y);
    }


    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
        this.item.setX(this.x);
        this.item.setY(this.y);
    }

    public InventorySlotType getInventorySlotType() {
        return inventorySlotType;
    }
}
