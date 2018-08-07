package com.szczypiorofix.sweetrolls.game.main.core;

import com.szczypiorofix.sweetrolls.game.tilemap.*;



public class LevelManager {


    public LevelManager() {}

    public TileMap loadGeneratedLevel(String name, String type) {

        if (type.equalsIgnoreCase("plains")) {
            System.out.println("GENERATING PLAINS");
            return new LevelGenerator(
                    name,
                    50,
                    50,
                    10,
                    11,
                    4,
                    4,
                    0.43f,
                    4,
                    false).getTileMap();

        } else if (type.equalsIgnoreCase("forest")) {
            System.out.println("GENERATING FOREST");

        } else if (type.equalsIgnoreCase("mountains")) {
            System.out.println("GENERATING MOUNTAINS");

        }

        // DUNGEON
        System.out.println("GENERATING DUNGEON");
        return new LevelGenerator(
                    name,
                    50,
                    50,
                    31,
                    6,
                    4,
                    4,
                    0.43f,
                    8,
                    true).getTileMap();
    }

    public TileMap loadLevel(String levelName) {
        return new TileMap(levelName);
    }

}
