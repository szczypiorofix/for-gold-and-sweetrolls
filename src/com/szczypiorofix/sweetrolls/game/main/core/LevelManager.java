package com.szczypiorofix.sweetrolls.game.main.core;

import com.szczypiorofix.sweetrolls.game.tilemap.*;



public class LevelManager {


    public LevelManager() {}

    public TileMap loadGeneratedLevel(String name) {
        return new LevelGenerator(
                name,
                100,
                100,
                31,
                6,
                4,
                4,
                0.47f,
                8).getTileMap();
    }

    public TileMap loadLevel(String levelName) {
        return new TileMap(levelName);
    }

}
