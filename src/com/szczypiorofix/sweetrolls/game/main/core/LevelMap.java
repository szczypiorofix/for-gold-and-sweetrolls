package com.szczypiorofix.sweetrolls.game.main.core;

import com.szczypiorofix.sweetrolls.game.objects.GameObject;

public class LevelMap {

    private GameObject[][] ground;
    private GameObject[][] places;
    private GameObject[][] npc;
    private int playerSpawnX, playerSpawnY;

    public LevelMap(GameObject[][] ground, GameObject[][] places, GameObject[][] npc, int playerSpawnX, int playerSpawnY) {
        this.ground = ground;
        this.places = places;
        this.npc = npc;
        this.playerSpawnX = playerSpawnX;
        this.playerSpawnY = playerSpawnY;
    }

    public GameObject[][] getGround() {
        return ground;
    }

    public void setGround(GameObject[][] ground) {
        this.ground = ground;
    }

    public GameObject[][] getPlaces() {
        return places;
    }

    public void setPlaces(GameObject[][] places) {
        this.places = places;
    }

    public GameObject[][] getNpc() {
        return npc;
    }

    public void setNpc(GameObject[][] npc) {
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
