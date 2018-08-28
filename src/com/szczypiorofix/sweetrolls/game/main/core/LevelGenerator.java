/*
 * Developed by szczypiorofix on 24.08.18 13:38.
 * Copyright (c) 2018. All rights reserved.
 *
 */

package com.szczypiorofix.sweetrolls.game.main.core;

import com.szczypiorofix.sweetrolls.game.main.MainClass;
import com.szczypiorofix.sweetrolls.game.objects.Vector;
import com.szczypiorofix.sweetrolls.game.tilemap.*;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;



public class LevelGenerator {


    private TileMap tileMap;
    private int levelWidth, levelHeight;
    private String name;
    private final int TILEWIDTH = 32;
    private final int TILEHEIGHT = 32;
    private float chanceToStartAlive;
    private int deathLimit = 4;
    private int birthLimit = 3;
    private int emptyFieldGid;
    private int wallFieldGid;
    private int simulationSteps = 5;
    private boolean innerWorldMap;
    private Vector playerVector;
    private Vector exitVector;
    private Vector itemVector;


    public LevelGenerator(String name, int levelWidth, int levelHeight, int wallFieldGid, int emptyFieldGid, int deathLimit, int birthLimit, float chanceToStartAlive, int simulationSteps, boolean innerWorldMap) {
        this.name = name;
        this.levelWidth = levelWidth;
        this.levelHeight = levelHeight;
        this.emptyFieldGid = emptyFieldGid;
        this.wallFieldGid = wallFieldGid;
        this.deathLimit = deathLimit;
        this.birthLimit = birthLimit;
        this.chanceToStartAlive = chanceToStartAlive;
        this.simulationSteps = simulationSteps;
        this.innerWorldMap = innerWorldMap;
        createTileMap();
    }

    private void createTileMap() {
        tileMap = new TileMap(levelWidth, levelHeight, TILEWIDTH,TILEHEIGHT);
        SpriteSheet image1 = null;
        SpriteSheet image2 = null;
        SpriteSheet image3 = null;

        try {
            image1 = new SpriteSheet("map/dg_grounds32.png", TILEWIDTH, TILEHEIGHT);
            image2 = new SpriteSheet("map/dg_town332.png", TILEWIDTH, TILEHEIGHT);
            image3 = new SpriteSheet("map/dg_weapons32.png", TILEWIDTH, TILEHEIGHT);
        } catch (SlickException e) {
            e.printStackTrace();
        }
        tileMap.addTileSet(new TileSet(
                1,
                "dg_grounds32",
                "dg_grounds32.png",
                TILEWIDTH,
                TILEHEIGHT,
                171,
                9,
                288,
                608,
                image1
        ));
        tileMap.addTileSet(new TileSet(
                172,
                "dg_town332",
                "dg_town332.png",
                TILEWIDTH,
                TILEHEIGHT,
                36,
                9,
                288,
                128,
                image2
        ));
        tileMap.addTileSet(new TileSet(
                208,
                "dg_weapons32",
                "dg_weapons32.png",
                TILEWIDTH,
                TILEHEIGHT,
                100,
                10,
                320,
                320,
                image3
        ));


        TileLayer layer = new TileLayer("ground", levelWidth, levelHeight);

        TileObject[][] map = randomMap();
        for (int i = 0; i < simulationSteps; i++) {
            map = doSimulationStep(map);
        }

        playerVector = new Vector();
        exitVector = new Vector();
        itemVector = new Vector();

        playerVector = placeOnMap(map);
        if (innerWorldMap) {
            playerVector.setXY(levelWidth / 2 * TILEWIDTH, levelHeight / 2 * TILEHEIGHT);
        }

        itemVector = placeOnMap(map);

        exitVector.x = playerVector.x;
        exitVector.y = playerVector.y;

        if (!innerWorldMap) {
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {

                    if (map[i][j].getGid() == wallFieldGid) {
                        map[i][j].setCollisionObject(new CollisionObject(
                                map[i][j].getGid(),
                                "dungeonwall",
                                i,
                                j,
                                TILEWIDTH,
                                TILEHEIGHT));
                    }
                }
            }
        }



        layer.setData(map);
        tileMap.addLayer(layer);

        // ########### PLAYER
        ObjectGroup playerObjectGroup = new ObjectGroup("player");
        ObjectGroupObject playerObject = new ObjectGroupObject(
                1,
                "",
                "player spawn",
                playerVector.x,
                playerVector.y,
                TILEWIDTH,
                TILEHEIGHT,
                tileMap.getTileSets()
        );
        playerObject.addProperty(new Property("name", "string", "PGarvey"));
        playerObjectGroup.addObject(playerObject);
        tileMap.addObjectGroup(playerObjectGroup);

        // ############# EXIT
        if (!innerWorldMap) {
            ObjectGroup exitObjectGroup = new ObjectGroup("exit");
            ObjectGroupObject exitObject = new ObjectGroupObject(
                    2,
                    "",
                    "exit",
                    exitVector.x,
                    exitVector.y,
                    TILEWIDTH,
                    TILEHEIGHT,
                    tileMap.getTileSets()
            );
            exitObject.setGid(200); // EXIT GID
            exitObjectGroup.addObject(exitObject);
            tileMap.addObjectGroup(exitObjectGroup);
        }

        // ############# ITEM
        ObjectGroup itemsObjectGroup = new ObjectGroup("items");
        ObjectGroupObject itemObject = new ObjectGroupObject(
                3,
                "item.tx",
                "item",
                itemVector.x,
                itemVector.y,
                TILEWIDTH,
                TILEHEIGHT,
                tileMap.getTileSets()
        );
        itemObject.setGid(208); // ITEM GID  208 - 308

        itemObject.addProperty(new Property("name", "string", "złamany miecz"));
        itemObject.addProperty(new Property("pickable", "bool", "true"));
        itemObject.addProperty(new Property("type", "string", "sword"));


        itemsObjectGroup.addObject(itemObject);
        tileMap.addObjectGroup(itemsObjectGroup);
    }

    private Vector placeOnMap(TileObject[][] map) {
        Vector v = new Vector();
        boolean exitSet = false;
        do {
            v.x = MainClass.RANDOM.nextInt(map.length);
            v.y = MainClass.RANDOM.nextInt(map[0].length);
            if (map[v.x][v.y].getGid() == emptyFieldGid) exitSet = true;
        } while(!exitSet);
        v.multiply(TILEWIDTH, TILEHEIGHT);
        return v;
    }

    public int countAliveNeighbours(TileObject[][] map, int x, int y){
        int count = 0;
        for(int i = -1; i < 2; i++){
            for(int j = -1; j < 2; j++){
                int neighbour_x = x + i;
                int neighbour_y = y + j;
                if (i != 0 || j != 0) {
                    if (neighbour_x < 0 || neighbour_y < 0 || neighbour_x >= map.length || neighbour_y >= map[0].length){
                        count = count + 1;
                    } else if (map[neighbour_x][neighbour_y].getGid() == wallFieldGid){
                        count = count + 1;
                    }
                }
            }
        }
        return count;
    }

    public TileObject[][] doSimulationStep(TileObject[][] oldMap){
        TileObject[][] newMap = new TileObject[levelWidth][levelHeight];
        for(int x = 0; x < oldMap.length; x++){
            for(int y = 0; y < oldMap[0].length; y++){
                int nbs = countAliveNeighbours(oldMap, x, y);
                if (oldMap[x][y].getGid() == wallFieldGid) {
                    if(nbs < deathLimit){
                        newMap[x][y] = new TileObject(emptyFieldGid);
                    } else {
                        newMap[x][y] = new TileObject(wallFieldGid);
                    }
                } else{
                    if (nbs > birthLimit) {
                        newMap[x][y] = new TileObject(wallFieldGid);
                    } else {
                        newMap[x][y] = new TileObject(emptyFieldGid);
                    }
                }
            }
        }
        return newMap;
    }

    private TileObject[][] randomMap() {
        TileObject[][] map = new TileObject[levelWidth][levelHeight];
        for (int i = 0; i < levelWidth; i++) {
            for (int j = 0; j < levelHeight; j++) {
                int tile = emptyFieldGid;

                if ((float) MainClass.RANDOM.nextInt(1001) / 1000  < this.chanceToStartAlive)
                    tile = wallFieldGid;

                if (innerWorldMap)
                    if (i == 0 || j == 0 || i == levelWidth-1 || j == levelHeight-1)
                        tile = wallFieldGid;

                map[i][j] = new TileObject(tile);
            }
        }

        return map;
    }

    public TileMap getTileMap() {
        return tileMap;
    }

}
