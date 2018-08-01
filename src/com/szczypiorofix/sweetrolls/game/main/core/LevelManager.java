package com.szczypiorofix.sweetrolls.game.main.core;

import com.szczypiorofix.sweetrolls.game.main.MainClass;
import com.szczypiorofix.sweetrolls.game.tilemap.*;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;


public class LevelManager {

    private String l =
                    "10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10," +
                    "10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10," +
                    "10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10," +
                    "10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10," +
                    "10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10," +
                    "10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10," +
                    "10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10," +
                    "10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10," +
                    "10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10," +
                    "10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10," +
                    "10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10," +
                    "10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10," +
                    "10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10," +
                    "10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10," +
                    "10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10," +
                    "10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10," +
                    "10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10," +
                    "10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10," +
                    "10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10," +
                    "10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10";



    public LevelManager() {}

    public TileMap generateLevel(String name) {
        int levelWidth = 20;
        int levelHeight = 20;
        TileMap tilemap = new TileMap(levelWidth, levelHeight, 32,32);
        SpriteSheet image = null;

        try {
            image = new SpriteSheet(MainClass.RES +"map/dg_grounds32.png", 32, 32);
        } catch (SlickException e) {
            e.printStackTrace();
        }
        tilemap.addTileSet(new TileSet(
               1,
                "dg_grounds32",
                "dg_grounds32.png",
                32,
                32,
                171,
                9,
                288,
                608,
                image
        ));
        ObjectGroup objectGroup = new ObjectGroup("player");
        TileObject tileObject = new TileObject(
                1,
                "",
                "player spawn",
                24,
                24,
                32,
                32,
                tilemap.getTileSets()
        );
        tileObject.addProperty(new Property("name", "string", "PGarvey"));
        objectGroup.addObject(tileObject);
        tilemap.addObjectGroup(objectGroup);
        Layer layer = new Layer("ground", levelWidth, levelHeight);
        layer.setDataCSV(l);
        tilemap.addLayer(layer);
        return tilemap;
    }

    public TileMap loadLevel(String levelName) {
        TileMap tilemap;
        System.out.print("Loading data from "+levelName +" file ...");
        tilemap = new Level(levelName).getTileMap();
        System.out.println(" done.");
        return tilemap;
    }

}
