/*
 * Developed by szczypiorofix on 24.08.18 13:38.
 * Copyright (c) 2018. All rights reserved.
 *
 */

package com.szczypiorofix.sweetrolls.game.main.core;

import com.szczypiorofix.sweetrolls.game.enums.LevelType;
import com.szczypiorofix.sweetrolls.game.objects.characters.NPC;
import com.szczypiorofix.sweetrolls.game.objects.item.Item;
import com.szczypiorofix.sweetrolls.game.objects.terrain.Place;
import com.szczypiorofix.sweetrolls.game.objects.terrain.Ground;

import java.io.Serializable;


public class LevelMap implements Serializable {

    private transient Ground[][] ground;
    private transient Place[][] places;
    private NPC[][] npc;
    private Item[][] items;
    private int playerLastTileX, playerLastTileY;
    private LevelType levelType;
    private int width, height;
    private int tileWidth, tileHeight;
    private String name;
    private boolean saveGameContentLoaded;

    public LevelMap(String name, int tileWidth, int tileHeight, LevelType levelType, Ground[][] ground, Place[][] places, NPC[][] npc, Item[][] items, int playerLastTileX, int playerLastTileY) {
        this.name = name;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.levelType = levelType;
        this.ground = ground;
        this.places = places;
        this.npc = npc;
        this.items = items;
        this.width = ground.length;
        this.height = ground[0].length;
        this.playerLastTileX = playerLastTileX;
        this.playerLastTileY = playerLastTileY;
    }

    public Ground[][] getGround() {
        return ground;
    }

    public void setGround(Ground[][] ground) {
        this.ground = ground;
    }

    public Place[][] getPlaces() {
        return places;
    }

    public void setPlaces(Place[][] places) {
        this.places = places;
    }

    public NPC[][] getNpc() {
        return npc;
    }

    public void setNpc(NPC[][] npc) {
        this.npc = npc;
    }

    public int getPlayerLastTileX() {
        return playerLastTileX;
    }

    public int getPlayerLastTileY() {
        return playerLastTileY;
    }

    public void setPlayerLastTiles(int x, int y) {
        this.playerLastTileX = x;
        this.playerLastTileY = y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Item[][] getItems() {
        return items;
    }

    public void setItems(Item[][] items) {
        this.items = items;
    }

    public LevelType getLevelType() {
        return levelType;
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public void setPlayerLastTileX(int playerLastTileX) {
        this.playerLastTileX = playerLastTileX;
    }

    public void setPlayerLastTileY(int playerLastTileY) {
        this.playerLastTileY = playerLastTileY;
    }

    public void setLevelType(LevelType levelType) {
        this.levelType = levelType;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setTileWidth(int tileWidth) {
        this.tileWidth = tileWidth;
    }

    public void setTileHeight(int tileHeight) {
        this.tileHeight = tileHeight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSaveGameContentLoaded() {
        return saveGameContentLoaded;
    }

    public void setSaveGameContentLoaded(boolean saveGameContentLoaded) {
        this.saveGameContentLoaded = saveGameContentLoaded;
    }
}
