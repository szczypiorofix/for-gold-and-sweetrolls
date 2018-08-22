package com.szczypiorofix.sweetrolls.game.main.core;

import com.szczypiorofix.sweetrolls.game.objects.characters.NPC;
import com.szczypiorofix.sweetrolls.game.objects.item.Item;
import com.szczypiorofix.sweetrolls.game.objects.terrain.Place;
import com.szczypiorofix.sweetrolls.game.objects.terrain.Ground;


public class LevelMap {

    public enum LevelType {
        WORLD_MAP,
        INNER_MAP,
        INNER_RANDOM_MAP;

        public static LevelType getInitialLevelType() {
            return WORLD_MAP;
        }
    }

    private Ground[][] ground;
    private Place[][] places;
    private NPC[][] npc;
    private Item[][] items;
    private int playerLastTileX, playerLastTileY;
    private LevelType levelType;
    private int width, height;

    public LevelMap(LevelType levelType, Ground[][] ground, Place[][] places, NPC[][] npc, Item[][] items, int playerLastTileX, int playerLastTileY) {
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
        //System.out.println("Setting old: "+playerLastTileX+":"+playerLastTileY +" to: "+x+":"+y);
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
}
