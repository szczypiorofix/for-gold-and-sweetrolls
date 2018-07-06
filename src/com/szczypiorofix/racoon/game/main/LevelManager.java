package com.szczypiorofix.racoon.game.main;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import static com.szczypiorofix.racoon.game.main.Level.TOWN3;

public class LevelManager {

    public int levelId = 0;
    public String levelName = "FirstLevel";
    public Level[] levels = {Level.WORLD_MAP, Level.TOWN1, Level.TOWN2, TOWN3};
    private TiledMap levelMap;

    public LevelManager() {

    }

    public void loadLevel(Level level) {
        try {
        switch (level) {
                case WORLD_MAP: {
                    levelMap = new TiledMap("res/map/mainmap.tmx");
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
        } catch (SlickException slickException) {
            slickException.printStackTrace();
        }
    }

    public TiledMap getLevelMap() {
        return this.levelMap;
    }
}
