package com.szczypiorofix.sweetrolls.game.main.core;

import com.szczypiorofix.sweetrolls.game.objects.characters.NPC;
import com.szczypiorofix.sweetrolls.game.objects.item.Item;
import com.szczypiorofix.sweetrolls.game.objects.terrain.Place;
import com.szczypiorofix.sweetrolls.game.objects.terrain.Ground;

import java.io.Serializable;

public class LevelMap implements Serializable {

    private Ground[][] ground;
    private Place[][] places;
    private NPC[][] npc;
    private Item[][] items;
    private int playerLastTileX, playerLastTileY;

    public LevelMap(Ground[][] ground, Place[][] places, NPC[][] npc, Item[][] items, int playerLastTileX, int playerLastTileY) {
        this.ground = ground;
        this.places = places;
        this.npc = npc;
        this.items = items;
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

    public Item[][] getItems() {
        return items;
    }

    public void setItems(Item[][] items) {
        this.items = items;
    }
}
