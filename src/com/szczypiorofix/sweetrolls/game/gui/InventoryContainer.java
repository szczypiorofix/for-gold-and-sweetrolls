package com.szczypiorofix.sweetrolls.game.gui;

import com.szczypiorofix.sweetrolls.game.enums.InventorySlotType;
import com.szczypiorofix.sweetrolls.game.enums.ObjectType;
import com.szczypiorofix.sweetrolls.game.objects.GameObject;
import com.szczypiorofix.sweetrolls.game.objects.item.Item;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class InventoryContainer extends GameObject {

    private int x, y;
    private int c;
    private InventorySlotType inventorySlotType;
    public Item item;

    public InventoryContainer(int c, InventorySlotType inventorySlotType, int x, int y, Item item) {
        super("Inventory container", x, y, 32, 32, ObjectType.INVENTORY_CONTAINER);
        this.c = c;
        this.inventorySlotType = inventorySlotType;
        this.x = x;
        this.y = y;
        this.item = item;
    }


    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta, float offsetX, float offsetY) throws SlickException {
        hover = false;
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g, float offsetX, float offsetY) throws SlickException {
        if (item != null) {
            if (item.getImage() != null)
                item.getImage().draw(item.getX(), item.getY());
        }
        if (hover)
            g.drawRect(x-1, y, width-2, height-2);
        //g.drawString(c+"", x, y);
    }

    @Override
    public void turn() {}

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
