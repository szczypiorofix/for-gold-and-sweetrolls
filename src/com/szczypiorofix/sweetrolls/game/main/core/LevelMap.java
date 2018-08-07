package com.szczypiorofix.sweetrolls.game.main.core;

import com.szczypiorofix.sweetrolls.game.objects.characters.NPC;
import com.szczypiorofix.sweetrolls.game.objects.item.Item;
import com.szczypiorofix.sweetrolls.game.objects.item.Place;
import com.szczypiorofix.sweetrolls.game.objects.terrain.Ground;

import java.io.Serializable;

public class LevelMap implements Serializable {

    private Ground[][] ground;
    private Place[][] places;
    private NPC[][] npc;
    private Item[][] items;
    private int playerSpawnX, playerSpawnY;

    public LevelMap(Ground[][] ground, Place[][] places, NPC[][] npc, Item[][] items, int playerSpawnX, int playerSpawnY) {
        this.ground = ground;
        this.places = places;
        this.npc = npc;
        this.items = items;
        this.playerSpawnX = playerSpawnX;
        this.playerSpawnY = playerSpawnY;
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

    public int getPlayerSpawnX() {
        return playerSpawnX;
    }

    public void setPlayerSpawnX(int playerSpawnX) {
        this.playerSpawnX = playerSpawnX;
    }

    public int getPlayerSpawnY() {
        return playerSpawnY;
    }

    public void setPlayerSpawnY(int playerSpawnY) {
        this.playerSpawnY = playerSpawnY;
    }

    public Item[][] getItems() {
        return items;
    }

    public void setItems(Item[][] items) {
        this.items = items;
    }
}
