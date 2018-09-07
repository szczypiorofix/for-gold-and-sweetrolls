/*
 * Developed by szczypiorofix on 24.08.18 13:35.
 * Copyright (c) 2018. All rights reserved.
 *
 */

package com.szczypiorofix.sweetrolls.game.objects.item;

import com.szczypiorofix.sweetrolls.game.enums.ItemType;
import com.szczypiorofix.sweetrolls.game.enums.ObjectType;
import com.szczypiorofix.sweetrolls.game.main.fonts.BitMapFont;
import com.szczypiorofix.sweetrolls.game.main.fonts.FontParser;
import com.szczypiorofix.sweetrolls.game.objects.GameObject;
import com.szczypiorofix.sweetrolls.game.tilemap.Property;
import com.szczypiorofix.sweetrolls.game.tilemap.TileSet;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.io.Serializable;
import java.util.ArrayList;

public class Item extends GameObject implements Serializable {

    private transient Image image;
    private transient BitMapFont font;
    private boolean pickable;
    private String type;
    private int armorRatio;
    private int damageRatio;
    private ItemType itemType;
    private boolean found = false;

    public Item(String name, float x, float y, float width, float height, TileSet tileSet, int gid, ObjectType objectType, String type, ArrayList<Property> properties) {
        super(name, x, y, width, height, objectType, properties);
        image = tileSet.getImageSprite(gid);
        this.type = type;
        font = FontParser.getFont();
        this.pickable = getBooleanProperty("pickable");
        armorRatio = 1;
        damageRatio = 1;
        this.itemType = estimateItemType(getStringProperty("type"));
    }

    public Item(String name, float x, float y, float width, float height, Image image, ObjectType objectType, String type, ArrayList<Property> properties) {
        super(name, x, y, width, height, objectType, properties);
        this.image = image;
        this.type = type;
        font = FontParser.getFont();
        this.pickable = getBooleanProperty("pickable");
        armorRatio = 1;
        damageRatio = 1;
        this.itemType = estimateItemType(getStringProperty("type"));
    }

    private ItemType estimateItemType(String type) {
        ItemType it = ItemType.DEFAULT;
        if (type.equalsIgnoreCase("dagger")) it = ItemType.DAGGER;
        else if (type.equalsIgnoreCase("sword")) it = ItemType.SWORD;
        else if (type.equalsIgnoreCase("axe")) it = ItemType.AXE;
        else if (type.equalsIgnoreCase("hammer")) it = ItemType.HAMMER;
        else if (type.equalsIgnoreCase("armor_boots")) it = ItemType.ARMOR_BOOTS;
        else if (type.equalsIgnoreCase("armor_chest")) it = ItemType.ARMOR_CHEST;
        else if (type.equalsIgnoreCase("armor_gloves")) it = ItemType.ARMOR_GLOVES;
        else if (type.equalsIgnoreCase("armor_helmet")) it = ItemType.ARMOR_HELMET;
        else if (type.equalsIgnoreCase("armor_shield")) it = ItemType.ARMOR_SHIELD;
        else if (type.equalsIgnoreCase("robe")) it = ItemType.ROBE;
        else if (type.equalsIgnoreCase("chest")) it = ItemType.CHEST;
        else if (type.equalsIgnoreCase("potion")) it = ItemType.POTION;
        else if (type.equalsIgnoreCase("gold")) it = ItemType.GOLD;
        else if (type.equalsIgnoreCase("scroll")) it = ItemType.SCROLL;
        else if (type.equalsIgnoreCase("food")) it = ItemType.FOOD;
        return it;
    }

    @Override
    public void update(int delta, float offsetX, float offsetY) throws SlickException {
        hover = false;
    }

    @Override
    public void render(Graphics g, float offsetX, float offsetY) throws SlickException {
        image.draw(- offsetX + x, - offsetY + y);
        if (hover) {
            font.draw(name, - offsetX + x, - offsetY + y - 20);
        }
    }


    public Image getImage() {
        return image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isPickable() {
        return pickable;
    }

    public void setPickable(boolean pickable) {
        this.pickable = pickable;
    }

    public int getArmorRatio() {
        return armorRatio;
    }

    public int getDamageRatio() {
        return damageRatio;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public boolean isFound() {
        return found;
    }

    public void setFound(boolean found) {
        this.found = found;
    }
}
