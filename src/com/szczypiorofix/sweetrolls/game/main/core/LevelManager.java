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

    public void loadLevel(String level) {
        System.out.print("Loading data from "+level +" file ...");
        currentLevel.loadFromTiledMap(level);
        System.out.println(" done.");
        //System.out.println(currentLevel.getTileMap());
    }

}
