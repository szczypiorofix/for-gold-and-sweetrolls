/*
 * Developed by szczypiorofix on 10.09.18 17:32.
 * Copyright (c) 2018. All rights reserved.
 *
 */

package com.szczypiorofix.sweetrolls.game.main.core;


import com.szczypiorofix.sweetrolls.game.enums.LevelType;
import com.szczypiorofix.sweetrolls.game.enums.ObjectType;
import com.szczypiorofix.sweetrolls.game.main.states.FGASGame;
import com.szczypiorofix.sweetrolls.game.objects.GameObject;
import com.szczypiorofix.sweetrolls.game.objects.characters.NPC;
import com.szczypiorofix.sweetrolls.game.objects.characters.Player;
import com.szczypiorofix.sweetrolls.game.objects.item.Item;
import com.szczypiorofix.sweetrolls.game.objects.terrain.Place;
import com.szczypiorofix.sweetrolls.game.objects.terrain.Ground;
import com.szczypiorofix.sweetrolls.game.tilemap.CollisionObject;
import com.szczypiorofix.sweetrolls.game.tilemap.ObjectGroupObject;
import com.szczypiorofix.sweetrolls.game.tilemap.TileMap;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import java.util.HashMap;


public class ObjectManager {


    private TileMap currentTileMap;
    private Player player;

    // WORLD MAP OBJECTS
    private Ground[][] grounds;
    private Place[][] places;
    private NPC[][] npcs;
    private Item[][] items;

    private int gameWidth, gameHeight;

    private HashMap<String, LevelMap> levelMaps;
    private HashMap<String, TileMap> tileMaps;
    private LevelMap currentLevelMap;

    private int tilesToWest, tilesToEast, tilesToNorth, tilesToSouth;

    public ObjectManager(int width, int height) {
        gameWidth = width;
        gameHeight = height;
        levelMaps = new HashMap<>();
        tileMaps = new HashMap<>();
    }

    public void generateLevel(TileMap tileMap, String levelName) {
        this.currentTileMap = tileMap;
        tileMaps.put(levelName, tileMap);

        grounds = new Ground[currentTileMap.getWidth()][currentTileMap.getHeight()];
        places = new Place[currentTileMap.getWidth()][currentTileMap.getHeight()];
        npcs = new NPC[currentTileMap.getWidth()][currentTileMap.getHeight()];
        items = new Item[currentTileMap.getWidth()][currentTileMap.getHeight()];

        // ############ OBIEKTY
        for (int objectGroups = 0; objectGroups < tileMap.getObjectGroups().size(); objectGroups++) {

            // ######## PLAYER
            if (tileMap.getObjectGroups().get(objectGroups).getName().equals("player")) {

                // Nie może być więcej niż 1 player!
                ObjectGroupObject playerObject = tileMap.getObjectGroups().get(objectGroups).getObjects().get(0);
                if (player == null) {
                    player = new Player(
                            playerObject.getStringProperty("name"),
                            playerObject.getX(),
                            playerObject.getY(),
                            playerObject.getWidth(),
                            playerObject.getHeight(),
                            playerObject.getProperties());

                } else {
                    if (levelMaps.containsKey(levelName)) {
                        player.setX(levelMaps.get(levelName).getPlayerLastTileX() * tileMap.getWidth());
                        player.setY(levelMaps.get(levelName).getPlayerLastTileY() * tileMap.getHeight());
                    } else {
                        player.setX(playerObject.getX());
                        player.setY(playerObject.getY());
                    }
                }
            }

            // ######## ITEMS
            if (tileMap.getObjectGroups().get(objectGroups).getName().equals("items")) {
                int tileSet;
                for (int item = 0; item < tileMap.getObjectGroups().get(objectGroups).getObjects().size(); item++) {

                    ObjectGroupObject currentItem = tileMap.getObjectGroups().get(objectGroups).getObjects().get(item);
                    tileSet = 0;
                    while (currentItem.getGid() >
                            tileMap.getTileSets().get(tileSet).getFirstGid() + tileMap.getTileSets().get(tileSet).getTileCount() - (tileSet + 1)
                            ) {
                        tileSet++;
                    }
                    items[currentItem.getX() / tileMap.getTileWidth()]
                            [currentItem.getY() / tileMap.getTileHeight()] =
                            new Item(
                                    currentItem.getStringProperty("name").equalsIgnoreCase("null")
                                            ? currentItem.getName()
                                            : currentItem.getStringProperty("name"),
                                    currentItem.getX(),
                                    currentItem.getY(),
                                    currentItem.getWidth(),
                                    currentItem.getHeight(),
//                                    currentItem.getGid() >= 0 ?
//                                            tileMap.getTileSets().get(tileSet).getImageSprite(
//                                                    currentItem.getGid()
//                                                            - tileMap.getTileSets().get(tileSet).getFirstGid()
//                                            )
//                                            : null,
                                    tileMap.getTileSets().get(tileSet),
                                    currentItem.getGid() - tileMap.getTileSets().get(tileSet).getFirstGid(),
                                    currentItem.getGid(),
                                    ObjectType.ITEM,
                                    currentItem.getType(),
                                    currentItem.getProperties()
                            );
                }
            }

            // ######## PLACES
            if (tileMap.getObjectGroups().get(objectGroups).getName().equals("places")) {
                int tileSet;
                for (int item = 0; item < tileMap.getObjectGroups().get(objectGroups).getObjects().size(); item++) {

                    ObjectGroupObject currentItem = tileMap.getObjectGroups().get(objectGroups).getObjects().get(item);
                    tileSet = 0;
                    while (currentItem.getGid() >
                            tileMap.getTileSets().get(tileSet).getFirstGid() + tileMap.getTileSets().get(tileSet).getTileCount() - (tileSet + 1)
                            ) {
                        tileSet++;
                    }

                    places[currentItem.getX() / tileMap.getTileWidth()]
                            [currentItem.getY() / tileMap.getTileHeight()] =
                            new Place(
                                    currentItem.getStringProperty("name").equalsIgnoreCase("null")
                                            ? currentItem.getName()
                                            : currentItem.getStringProperty("name"),
                                    currentItem.getX(),
                                    currentItem.getY(),
                                    currentItem.getWidth(),
                                    currentItem.getHeight(),
                                    currentItem.getGid() >= 0 ?
                                            tileMap.getTileSets().get(tileSet).getImageSprite(
                                                    currentItem.getGid()
                                                            - tileMap.getTileSets().get(tileSet).getFirstGid())
                                            : null,
                                    currentItem.getType(),
                                    currentItem.getProperties()
                            );
                }
            }


            // ######## NPCs
            if (tileMap.getObjectGroups().get(objectGroups).getName().equals("npc")) {
                int tileSet;
                for (int npcs = 0; npcs < tileMap.getObjectGroups().get(objectGroups).getObjects().size(); npcs++) {

                    ObjectGroupObject currentItem = tileMap.getObjectGroups().get(objectGroups).getObjects().get(npcs);
                    tileSet = 0;
                    while (currentItem.getGid() >
                            tileMap.getTileSets().get(tileSet).getFirstGid() + tileMap.getTileSets().get(tileSet).getTileCount() - (tileSet + 1)
                            ) {
                        tileSet++;
                    }

                    this.npcs[currentItem.getX() / tileMap.getTileWidth()]
                            [currentItem.getY() / tileMap.getTileHeight()] =
                            new NPC(
                                    currentItem.getStringProperty("name").equalsIgnoreCase("null")
                                            ? currentItem.getName()
                                            : currentItem.getStringProperty("name"),
                                    currentItem.getX(),
                                    currentItem.getY(),
                                    currentItem.getWidth(),
                                    currentItem.getHeight(),
//                                    currentItem.getGid() >= 0 ?
//                                            tileMap.getTileSets().get(tileSet).getImageSprite(
//                                                    currentItem.getGid()
//                                                            - tileMap.getTileSets().get(tileSet).getFirstGid())
//                                            : null,
                                    tileMap.getTileSets().get(tileSet),
                                    currentItem.getGid() - tileMap.getTileSets().get(tileSet).getFirstGid(),
                                    currentItem.getProperties()
                            );
                }
            }

        }

        // ############ TILESY
        int tileSet;
        for (int layers = 0; layers < tileMap.getTileLayers().size(); layers++) {

            for (int i = 0; i < tileMap.getWidth(); i++) {
                for (int j = 0; j < tileMap.getHeight(); j++) {


                    // ###### BACKGROUND
                    if (layers == 0) {
                        if (tileMap.getTileLayers().get(layers).getTile(i, j).getGid() > 0) {
                            tileSet = 0;
                            while (tileMap.getTileLayers().get(layers).getTile(i, j).getGid() >
                                    tileMap.getTileSets().get(tileSet).getFirstGid() + tileMap.getTileSets().get(tileSet).getTileCount() - (tileSet + 1) ) {
                                tileSet++;
                            }

                            Ground tempGround = new Ground(
                                    "Ground",
                                    i * tileMap.getTileWidth(),
                                    j * tileMap.getTileHeight(),
                                    tileMap.getTileWidth(),
                                    tileMap.getTileHeight(),
                                    tileMap.getTileSets().get(tileSet).getImageSprite(
                                            tileMap.getTileLayers().get(layers).getTile(i, j).getGid()
                                                    - tileMap.getTileSets().get(tileSet).getFirstGid()),
                                    tileMap.getTileLayers().get(layers).isVisible(),
                                    new CollisionObject(
                                            tileMap.getTileLayers().get(layers).getTile(i, j).getCollisionObject().getId(),
                                            tileMap.getTileLayers().get(layers).getTile(i, j).getCollisionObject().getTypeName(),
                                            i * tileMap.getTileWidth(),
                                            j * tileMap.getTileHeight(),
                                            tileMap.getTileLayers().get(layers).getTile(i, j).getCollisionObject().getWidth(),
                                            tileMap.getTileLayers().get(layers).getTile(i, j).getCollisionObject().getHeight()
                                    )
                            );

                            grounds[i][j] = tempGround;
                        } else {
                            grounds[i][j] = null;
                        }
                    }
                }
            }
        }

        currentLevelMap = new LevelMap(
                levelName,
                tileMap.getTileWidth(),
                tileMap.getTileHeight(),
                levelName.equalsIgnoreCase(FGASGame.WORLD_MAP_NAME) ? LevelType.WORLD_MAP : LevelType.INNER_MAP,
                grounds,
                places,
                npcs,
                items,
                player.getTileX(),
                player.getTileY());

        levelMaps.put(levelName, currentLevelMap);
    }

    public void setLevel() {

        int maxTileX = gameWidth / currentLevelMap.getTileWidth();
        int maxTileY = gameHeight / currentLevelMap.getTileHeight();

        tilesToWest = - maxTileX / 2 - 1;
        tilesToEast = maxTileX / 2 - 3;
        tilesToNorth = - maxTileY / 2 - 1;
        tilesToSouth = maxTileY / 2 + 1;

        grounds = currentLevelMap.getGround();
        places = currentLevelMap.getPlaces();
        npcs = currentLevelMap.getNpc();
        items = currentLevelMap.getItems();

        // SET PLAYER'S INITIAL GROUND TILE
        player.setTerrainType(grounds[player.getTileX()][player.getTileY()].getObjectType());
    }


    private void iterateUpdate(int delta, GameObject[][] list, float offsetX, float offsetY) throws SlickException {
        for (int x = tilesToWest; x < tilesToEast; x++) {
            for (int y = tilesToNorth; y < tilesToSouth; y++) {
                if (player.getTileX(x) >= 0
                        && player.getTileY(y) >= 0
                        && player.getTileX(x) < currentTileMap.getWidth()
                        && player.getTileY(y) < currentTileMap.getHeight()
                        ) {
                    if (list[player.getTileX() + x][player.getTileY() + y] != null) {
                        list[player.getTileX() + x][player.getTileY() + y].update(delta, offsetX, offsetY);
                    }
                }
            }
        }
    }

    private void iterateRender(Graphics g, GameObject[][] list, float offsetX, float offsetY) throws SlickException {

        int tW = tilesToWest;
        int tE = tilesToEast;
        int tS = tilesToSouth;
        int tN = tilesToNorth;

        if (player.getTileX() <= (getTilesToEast())) {
            tE = Math.abs(tilesToWest) + Math.abs(tilesToEast);
        }

        if (player.getTileX() > (currentTileMap.getWidth() - 10) ) {
            tW = tilesToWest - 4;
        }

        if (player.getTileY() <= getTilesToSouth()) {
            tS = Math.abs(tilesToSouth) + Math.abs(tilesToNorth);
        }

        if (player.getTileY() > (currentTileMap.getHeight() - 10) ) {
            tN = tilesToNorth - tilesToSouth;
        }

        for (int x = tW; x < tE; x++) {
            for (int y = tN; y < tS; y++) {
                if (player.getTileX(x) >= 0
                        && player.getTileY(y) >= 0
                        && player.getTileX(x) < currentTileMap.getWidth()
                        && player.getTileY(y) < currentTileMap.getHeight()) {


                    if (list[player.getTileX() + x][player.getTileY() + y] != null) {

                        list[player.getTileX() + x][player.getTileY() + y].render(g, offsetX, offsetY);

                    }
                }
            }
        }
    }

    private void iterateTurn(GameObject[][] list) {
        for (int x = 0; x < list.length; x++) {
            for (int y = 0; y < list[0].length; y++) {
                if (list[x][y] != null) {
                    list[x][y].turn();
                }
            }
        }
    }

    public void turn() {
        iterateTurn(grounds);
        iterateTurn(places);
        iterateTurn(npcs);
        iterateTurn(items);
        player.setTerrainType(grounds[player.getTileX()][player.getTileY()].getObjectType());
    }

    public void update(int delta, float offsetX, float offsetY) throws SlickException {
        iterateUpdate(delta, grounds,  offsetX, offsetY);
        iterateUpdate(delta, places,  offsetX, offsetY);
        iterateUpdate(delta, npcs,  offsetX, offsetY);
        iterateUpdate(delta, items,  offsetX, offsetY);
    }

    public void render(Graphics g, float offsetX, float offsetY) throws SlickException {
        iterateRender(g, grounds, offsetX, offsetY);
        iterateRender(g, places, offsetX, offsetY);
        iterateRender(g, npcs, offsetX, offsetY);
        iterateRender(g, items, offsetX, offsetY);
     }

    public HashMap<String, LevelMap> getLevelMaps() {
        return levelMaps;
    }

    public void setLevelMaps(HashMap<String, LevelMap> levelMaps) {
        this.levelMaps = levelMaps;
    }

    public Player getPlayer() {
        return player;
    }

    public TileMap getCurrentTileMap() {
        return currentTileMap;
    }

    public Place getPlace(int x, int y) {
        return places[x][y];
    }

    public Ground getGround(int x, int y) {
        return grounds[x][y];
    }

    public NPC getNpc(int x, int y) {
        return npcs[x][y];
    }

    public Item getItems(int x, int y) {
        return items[x][y];
    }

    public Place[][] getPlaces() {
        return places;
    }

    public Ground[][] getGrounds() {
        return grounds;
    }

    public NPC[][] getNpcs() {
        return npcs;
    }

    public Item[][] getItems() {
        return items;
    }

    public int getTilesToWest() {
        return tilesToWest;
    }

    public int getTilesToEast() {
        return tilesToEast;
    }

    public int getTilesToNorth() {
        return tilesToNorth;
    }

    public int getTilesToSouth() {
        return tilesToSouth;
    }

    public LevelMap getCurrentLevelMap() {
        return currentLevelMap;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setCurrentLevelMap(String currentLevelMap) {
        this.currentLevelMap = levelMaps.get(currentLevelMap);
        System.out.println("Ustawianie nowej LevelMap: "+this.currentLevelMap.getName());
    }

    public void setCurrentTileMap(String currentTileMap) {
        this.currentTileMap = tileMaps.get(currentTileMap);
        System.out.println("Ustawianie nowej TileMap: "+this.currentTileMap.getFileName());
    }

    public HashMap<String, TileMap> getTileMaps() {
        return tileMaps;
    }

    //    private void graczSwieci(int x,  int y, int sila) {
//        for (int sx = x - sila; sx <= x + sila; sx++) {
//            for (int sy = y - sila; sy <= y + sila; sy++) {
//                int dist = (int) (Math.max(0, sila - Math.sqrt(Math.pow(sx - x, 2) + Math.pow(sy - y, 2))));
//                System.out.print(dist);
//            }
//            System.out.println();
//        }
//    }


}
