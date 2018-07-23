package com.szczypiorofix.sweetrolls.game.main.core;

import com.szczypiorofix.sweetrolls.game.enums.LevelType;
import com.szczypiorofix.sweetrolls.game.tilemap.Level;


public class LevelManager {

    private Level currentLevel;

    public LevelManager() {
        currentLevel = new Level();
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }

    public void loadLevel(LevelType level) {
        switch (level) {
            case WORLD_MAP: {
                currentLevel.loadFromTiledMap("worldmap.tmx");
                System.out.println(currentLevel.getTileMap());
                break;
            }
            case INNER_PLAINS: {
                currentLevel.loadFromTiledMap("worldmap2.tmx");
                break;
            }
        }

    }

}
