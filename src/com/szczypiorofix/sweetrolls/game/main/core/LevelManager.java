package com.szczypiorofix.sweetrolls.game.main.core;

import com.szczypiorofix.sweetrolls.game.tilemap.*;



public class LevelManager {


    public LevelManager() {}

    public TileMap loadGeneratedLevel(String name) {
        return new LevelGenerator(
                name,
                50,
                50,
                31,
                6,
                4,
                4,
                0.43f,
                8).getTileMap();
    }

    public TileMap loadLevel(String levelName) {
        return new TileMap(levelName);
    }

}
