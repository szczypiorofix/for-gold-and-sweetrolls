package com.szczypiorofix.sweetrolls.game.main;

import com.szczypiorofix.sweetrolls.game.def.LevelType;
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
                currentLevel.generateMap();

                System.out.println(currentLevel.getTileMap());
                break;
            }
            case TOWN1: {
                break;
            }
            case TOWN2: {
                break;
            }
            case TOWN3: {
                break;
            }
        }

    }

}
