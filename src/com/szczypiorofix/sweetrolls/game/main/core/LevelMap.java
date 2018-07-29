package com.szczypiorofix.sweetrolls.game.main.core;

import com.szczypiorofix.sweetrolls.game.objects.characters.NPC;
import com.szczypiorofix.sweetrolls.game.objects.item.Place;
import com.szczypiorofix.sweetrolls.game.objects.terrain.Ground;

public class LevelMap {

    private Ground[][] ground;
    private Place[][] places;
    private NPC[][] npc;
    private int playerSpawnX, playerSpawnY;

    public LevelMap(Ground[][] ground, Place[][] places, NPC[][] npc, int playerSpawnX, int playerSpawnY) {
        this.ground = ground;
        this.places = places;
        this.npc = npc;
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
}
