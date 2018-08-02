package com.szczypiorofix.sweetrolls.game.main.core;

import com.szczypiorofix.sweetrolls.game.tilemap.*;



public class LevelManager {


    public LevelManager() {}

    public TileMap loadGeneratedLevel(String name) {
        System.out.print("Generating level...");
        LevelGenerator levelGenerator = new LevelGenerator(
                name,
                100,
                100,
                31,
                6,
                3,
                4,
                0.4f,
                8);
        System.out.println(" done.");
        return levelGenerator.getTileMap();
    }

    public TileMap loadLevel(String levelName) {
        TileMap tilemap;
        System.out.print("Loading data from "+levelName +" file ...");
        tilemap = new Level(levelName).getTileMap();
        System.out.println(" done.");
        return tilemap;
    }

}
