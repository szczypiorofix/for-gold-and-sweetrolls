package com.szczypiorofix.sweetrolls.game.main.core;

import com.szczypiorofix.sweetrolls.game.main.MainClass;
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
    private int playerX, playerY;

    public LevelGenerator(TileMap tileMap, int levelWidth, int levelHeight, String name, int emptyFieldGid, int wallFieldGid) {
        this.tileMap = tileMap;
        this.levelWidth = levelWidth;
        this.levelHeight = levelHeight;
        this.name = name;
        this.emptyFieldGid = emptyFieldGid;
        this.wallFieldGid = wallFieldGid;
    }

    public LevelGenerator(String name, int levelWidth, int levelHeight, int wallFieldGid, int emptyFieldGid, int deathLimit, int birthLimit, float chanceToStartAlive, int simulationSteps) {
        this.name = name;
        this.levelWidth = levelWidth;
        this.levelHeight = levelHeight;
        this.emptyFieldGid = emptyFieldGid;
        this.wallFieldGid = wallFieldGid;
        this.deathLimit = deathLimit;
        this.birthLimit = birthLimit;
        this.chanceToStartAlive = chanceToStartAlive;
        this.simulationSteps = simulationSteps;
        createTileMap();
    }

    private void createTileMap() {
        tileMap = new TileMap(levelWidth, levelHeight, TILEWIDTH,TILEHEIGHT);
        SpriteSheet image = null;

        try {
            image = new SpriteSheet(MainClass.RES +"map/dg_grounds32.png", TILEWIDTH, TILEHEIGHT);
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
                image
        ));

        Layer layer = new Layer("ground", levelWidth, levelHeight);

        int[][] map = randomMap();
        //map = generateMap(map);
        for (int i = 0; i < simulationSteps; i++) {
            map = doSimulationStep(map);
        }

        placeTreasure(map);

        layer.setData(map);
        tileMap.addLayer(layer);

        ObjectGroup objectGroup = new ObjectGroup("player");
        TileObject tileObject = new TileObject(
                1,
                "",
                "player spawn",
                playerX,
                playerY,
                TILEWIDTH,
                TILEHEIGHT,
                tileMap.getTileSets()
        );
        tileObject.addProperty(new Property("name", "string", "PGarvey"));
        objectGroup.addObject(tileObject);
        tileMap.addObjectGroup(objectGroup);
    }

    public int countAliveNeighbours(int[][] map, int x, int y){
        int count = 0;
        for(int i = -1; i < 2; i++){
            for(int j = -1; j < 2; j++){
                int neighbour_x = x + i;
                int neighbour_y = y + j;
                //If we're looking at the middle point
                if(i == 0 && j == 0){
                    //Do nothing, we don't want to add ourselves in!
                }
                //In case the index we're looking at it off the edge of the map
                else if (neighbour_x < 0 || neighbour_y < 0 || neighbour_x >= map.length || neighbour_y >= map[0].length){
                    count = count + 1;
                }
                //Otherwise, a normal check of the neighbour
                else if (map[neighbour_x][neighbour_y] == wallFieldGid){ // true
                    count = count + 1;
                }
            }
        }
        return count;
    }

    public int[][] doSimulationStep(int[][] oldMap){
        int[][] newMap = new int[levelWidth][levelHeight];
        //Loop over each row and column of the map
        for(int x = 0; x < oldMap.length; x++){
            for(int y = 0; y < oldMap[0].length; y++){
                int nbs = countAliveNeighbours(oldMap, x, y);
                //The new value is based on our simulation rules
                //First, if a cell is alive but has too few neighbours, kill it.
                if (oldMap[x][y] == wallFieldGid) { // true
                    if(nbs < deathLimit){
                        newMap[x][y] = emptyFieldGid; // false
                    }
                    else {
                        newMap[x][y] = wallFieldGid; // true
                    }
                } //Otherwise, if the cell is dead now, check if it has the right number of neighbours to be 'born'
                else{
                    if (nbs > birthLimit) {
                        newMap[x][y] = wallFieldGid; // true
                    }
                    else {
                        newMap[x][y] = emptyFieldGid; // false
                    }
                }
            }
        }
        return newMap;
    }


    private int[][] generateMap(int[][] randomMap) {
        // Copy to new array
        int[][] newMap = new int[randomMap.length][randomMap[0].length];
        System.arraycopy( randomMap, 0, newMap, 0, randomMap.length );

        return newMap;
    }

    public void placeTreasure(int[][] world){
        //How hidden does a spot need to be for treasure?
        //I find 5 or 6 is good. 6 for very rare treasure.
        int treasureHiddenLimit = 5;
        for (int x = 0; x < levelWidth; x++){
            for (int y = 0; y < levelHeight; y++){
                if(world[x][y] == emptyFieldGid){
                    int nbs = countAliveNeighbours(world, x, y);
                    if(nbs >= treasureHiddenLimit){
                        playerX = x * TILEWIDTH;
                        playerY = y * TILEHEIGHT;
                    }
                }
            }
        }
    }

    private int[][] randomMap() {

        int[][] map = new int[levelWidth][levelHeight];
        // fill map
        for (int i = 0; i < levelWidth; i++) {
            for (int j = 0; j < levelHeight; j++) {
                int tile = emptyFieldGid; // false
                if ((float) MainClass.RANDOM.nextInt(1001) / 1000  < this.chanceToStartAlive) {
                    tile = wallFieldGid; // true
                }
                if (i == 0 || j == 0 || i == levelWidth-1 || j == levelHeight-1) {
                    tile = wallFieldGid;
                }
                map[i][j] = tile;
            }
        }

        return map;
    }

    public TileMap getTileMap() {
        return tileMap;
    }

}
