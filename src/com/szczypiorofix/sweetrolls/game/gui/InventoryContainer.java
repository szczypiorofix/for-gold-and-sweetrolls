package com.szczypiorofix.sweetrolls.game.gui;

import com.szczypiorofix.sweetrolls.game.enums.ObjectType;
import com.szczypiorofix.sweetrolls.game.objects.GameObject;
import com.szczypiorofix.sweetrolls.game.objects.item.Item;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class InventoryContainer extends GameObject {

    private int id;
    private int x, y;
    public Item item;

    public InventoryContainer(int id, int x, int y, Item item) {
        super("Inventory container", x, y, 32, 32, ObjectType.INVENTORY_CONTAINER);
        this.id = id;
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
            //g.drawString(id+"", x, y);
        }
        if (hover)
            g.drawRect(x-1, y, width-3, height-2);
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
}
