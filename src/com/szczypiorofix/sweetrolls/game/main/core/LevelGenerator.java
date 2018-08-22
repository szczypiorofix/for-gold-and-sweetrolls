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


//        for (int i = 0; i < tileMap.getTileSets().size(); i++) {
//            System.out.println(tileMap.getTileSets().get(i).getFirstGid()+", "+tileMap.getTileSets().get(i).getName());
//        }

        TileLayer layer = new TileLayer("ground", levelWidth, levelHeight);

        TileObject[][] map = randomMap();
        //map = generateMap(map);
        for (int i = 0; i < simulationSteps; i++) {
            map = doSimulationStep(map);
        }

        playerVector = new Vector();
        exitVector = new Vector();
        itemVector = new Vector();

        //placePlayerOnMap(map);
        playerVector = placeOnMap(map);
        if (innerWorldMap) {
            playerVector.setXY(levelWidth / 2 * TILEWIDTH, levelHeight / 2 * TILEHEIGHT);
        }

        System.out.println("LevelGenerator, lokalizacja playera: " +playerVector.x / TILEWIDTH+":"+playerVector.y / TILEHEIGHT);

        itemVector = placeOnMap(map);
        //System.out.println(itemVector.x / TILEWIDTH+":"+itemVector.y / TILEHEIGHT);

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
            // TODO - haxxxxx !!!!
            v.x = MainClass.RANDOM.nextInt(map.length - 8);
            v.y = MainClass.RANDOM.nextInt(map[0].length - 8);
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
                //If we're looking at the middle point
                if(i == 0 && j == 0){
                    //Do nothing, we don't want to add ourselves in!
                }
                //In case the index we're looking at it off the edge of the map
                else if (neighbour_x < 0 || neighbour_y < 0 || neighbour_x >= map.length || neighbour_y >= map[0].length){
                    count = count + 1;
                }
                //Otherwise, a normal check of the neighbour
                else if (map[neighbour_x][neighbour_y].getGid() == wallFieldGid){ // true
                    count = count + 1;
                }
            }
        }
        return count;
    }

    public TileObject[][] doSimulationStep(TileObject[][] oldMap){
        TileObject[][] newMap = new TileObject[levelWidth][levelHeight];
        //Loop over each row and column of the map
        for(int x = 0; x < oldMap.length; x++){
            for(int y = 0; y < oldMap[0].length; y++){
                int nbs = countAliveNeighbours(oldMap, x, y);
                //The new value is based on our simulation rules
                //First, if a cell is alive but has too few neighbours, kill it.
                if (oldMap[x][y].getGid() == wallFieldGid) { // true
                    if(nbs < deathLimit){
                        newMap[x][y] = new TileObject(emptyFieldGid); // false
                    }
                    else {
                        newMap[x][y] = new TileObject(wallFieldGid); // true
                    }
                } //Otherwise, if the cell is dead now, check if it has the right number of neighbours to be 'born'
                else{
                    if (nbs > birthLimit) {
                        newMap[x][y] = new TileObject(wallFieldGid); // true
                    }
                    else {
                        newMap[x][y] = new TileObject(emptyFieldGid); // false
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

    public void placePlayerOnMap(TileObject[][] world){
        int treasureHiddenLimit = 5;
        for (int x = 0; x < levelWidth; x++){
            for (int y = 0; y < levelHeight; y++){
                if(world[x][y].getGid() == emptyFieldGid){
                    int nbs = countAliveNeighbours(world, x, y);
                    if(nbs >= treasureHiddenLimit){
                        playerVector.x = x * TILEWIDTH;
                        playerVector.y = y * TILEHEIGHT;
                    }
                }
            }
        }
    }

    private TileObject[][] randomMap() {

        TileObject[][] map = new TileObject[levelWidth][levelHeight];
        // fill map
        for (int i = 0; i < levelWidth; i++) {
            for (int j = 0; j < levelHeight; j++) {
                int tile = emptyFieldGid; // false
                if ((float) MainClass.RANDOM.nextInt(1001) / 1000  < this.chanceToStartAlive) {
                    tile = wallFieldGid; // true
                }
                if (innerWorldMap) {
                    if (i == 0 || j == 0 || i == levelWidth-1 || j == levelHeight-1) {
                        tile = wallFieldGid;
                    }
                }
                map[i][j] = new TileObject(tile);
            }
        }

        return map;
    }

    public TileMap getTileMap() {
        return tileMap;
    }

}
