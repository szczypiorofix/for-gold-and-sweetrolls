package com.szczypiorofix.racoon.game.main;

import com.szczypiorofix.racoon.game.def.Level;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;


public class LevelManager {

    private TiledMap levelMap;

    public LevelManager() {

    }

    public void loadLevel(Level level) {
        try {
            switch (level) {
                    case WORLD_MAP: {
                        levelMap = new TiledMap("res/map/worldmap.tmx");
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
