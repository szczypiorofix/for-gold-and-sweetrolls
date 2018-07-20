package com.szczypiorofix.sweetrolls.game.main;


import com.szczypiorofix.sweetrolls.game.def.ObjectType;
import com.szczypiorofix.sweetrolls.game.gui.MouseCursor;
import com.szczypiorofix.sweetrolls.game.objects.GameObject;
import com.szczypiorofix.sweetrolls.game.objects.terrain.Ground;
import com.szczypiorofix.sweetrolls.game.objects.character.NPC;
import com.szczypiorofix.sweetrolls.game.objects.character.Player;
import com.szczypiorofix.sweetrolls.game.tilemap.TileMap;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


class ObjectManager {


    private TileMap level;
    private GameContainer gc;
    private Player player;

    private GameObject[][] ground;
    private GameObject[][] npc;
    private GameObject[][] items;



    private int tilesToWest, tilesToEast, tilesToNorth, tilesToSouth;

    ObjectManager(GameContainer gc) {
        this.gc = gc;
    }

    void setLevel(TileMap tileMap) {
        this.level = tileMap;

        int maxX = gc.getWidth() / tileMap.getTileWidth();
        int maxY = gc.getHeight() / tileMap.getTileHeight();

        tilesToWest = - maxX / 2 - 1;
        tilesToEast = maxX / 2 - 3;
        tilesToNorth = - maxY / 2 - 1;
        tilesToSouth = maxY / 2 + 1;

//        int margin = 5;
//
//        tilesToWest = - margin;
//        tilesToEast = margin;
//        tilesToNorth = - margin;
//        tilesToSouth = margin;

        ground = new GameObject[level.getWidth()][level.getHeight()];
        npc = new GameObject[level.getWidth()][level.getHeight()];
        items = new GameObject[level.getWidth()][level.getHeight()];



        // ############ OBIEKTY

        for (int objectGroups = 0; objectGroups < tileMap.getObjectGroups().size(); objectGroups++) {

            // ######## PLAYER
            if (tileMap.getObjectGroups().get(objectGroups).getName().equals("player")) {

                // Nie może być więcej niż 1 player!
                if (player == null)
                player = new Player(
                        tileMap.getObjectGroups().get(objectGroups).getObjects().get(0).getStringProperty("name")
                                + "_" +tileMap.getObjectGroups().get(objectGroups).getObjects().get(0).getIntegerProperty("age"),
                        tileMap.getObjectGroups().get(objectGroups).getObjects().get(0).getX(),
                        tileMap.getObjectGroups().get(objectGroups).getObjects().get(0).getY(),
                        tileMap.getObjectGroups().get(objectGroups).getObjects().get(0).getWidth(),
                        tileMap.getObjectGroups().get(objectGroups).getObjects().get(0).getHeight());

            }

            // ######## NPC
            if (tileMap.getObjectGroups().get(objectGroups).getName().equals("npc")) {
                int tileSet;
                for (int npcs = 0; npcs < tileMap.getObjectGroups().get(objectGroups).getObjects().size(); npcs++) {

                    tileSet = 0;
                    while (tileMap.getObjectGroups().get(objectGroups).getObjects().get(npcs).getGid() >
                            tileMap.getTileSets().get(tileSet).getFirstGrid() + tileMap.getTileSets().get(tileSet).getTileCount()
                            ) {
                        tileSet++;
                    }

                     npc[tileMap.getObjectGroups().get(objectGroups).getObjects().get(npcs).getX() / tileMap.getTileWidth()]
                        [tileMap.getObjectGroups().get(objectGroups).getObjects().get(npcs).getY() / tileMap.getTileHeight()] =
                        new NPC(
                                tileMap.getObjectGroups().get(objectGroups).getObjects().get(npcs).getStringProperty("name").equalsIgnoreCase("null")
                                        ? tileMap.getObjectGroups().get(objectGroups).getObjects().get(npcs).getName()
                                        : tileMap.getObjectGroups().get(objectGroups).getObjects().get(npcs).getStringProperty("name"),
                                tileMap.getObjectGroups().get(objectGroups).getObjects().get(npcs).getX(),
                                tileMap.getObjectGroups().get(objectGroups).getObjects().get(npcs).getY(),
                                tileMap.getObjectGroups().get(objectGroups).getObjects().get(npcs).getWidth(),
                                tileMap.getObjectGroups().get(objectGroups).getObjects().get(npcs).getHeight(),
                                tileMap.getObjectGroups().get(objectGroups).getObjects().get(npcs).getGid() != -1 ?
                                    tileMap.getTileSets().get(tileSet).getImageSprite(
                                        tileMap.getObjectGroups().get(objectGroups).getObjects().get(npcs).getGid()
                                        - tileMap.getTileSets().get(tileSet).getFirstGrid())
                                        : null
                        );
                }
            }

            // ######## ITEMS
            if (tileMap.getObjectGroups().get(objectGroups).getName().equals("items")) {
                int tileSet;
                for (int item = 0; item < tileMap.getObjectGroups().get(objectGroups).getObjects().size(); item++) {

                    tileSet = 0;
                    while (tileMap.getObjectGroups().get(objectGroups).getObjects().get(item).getGid() >
                            tileMap.getTileSets().get(tileSet).getFirstGrid() + tileMap.getTileSets().get(tileSet).getTileCount()
                            ) {
                        tileSet++;
                    }

                    items[tileMap.getObjectGroups().get(objectGroups).getObjects().get(item).getX() / tileMap.getTileWidth()]
                            [tileMap.getObjectGroups().get(objectGroups).getObjects().get(item).getY() / tileMap.getTileHeight()] =
                            new NPC(
                                    tileMap.getObjectGroups().get(objectGroups).getObjects().get(item).getStringProperty("name").equalsIgnoreCase("null")
                                            ? tileMap.getObjectGroups().get(objectGroups).getObjects().get(item).getName()
                                            : tileMap.getObjectGroups().get(objectGroups).getObjects().get(item).getStringProperty("name"),
                                    tileMap.getObjectGroups().get(objectGroups).getObjects().get(item).getX(),
                                    tileMap.getObjectGroups().get(objectGroups).getObjects().get(item).getY(),
                                    tileMap.getObjectGroups().get(objectGroups).getObjects().get(item).getWidth(),
                                    tileMap.getObjectGroups().get(objectGroups).getObjects().get(item).getHeight(),
                                    tileMap.getObjectGroups().get(objectGroups).getObjects().get(item).getGid() != -1 ?
                                            tileMap.getTileSets().get(tileSet).getImageSprite(
                                                    tileMap.getObjectGroups().get(objectGroups).getObjects().get(item).getGid()
                                                            - tileMap.getTileSets().get(tileSet).getFirstGrid())
                                            : null
                            );
                }
            }
        }

        // ############ TILESY
        int tileSet;
        for (int layers = 0; layers < tileMap.getLayers().size(); layers++) {

            for (int i = 0; i < tileMap.getWidth(); i++) {
                for (int j = 0; j < tileMap.getHeight(); j++) {
                    tileSet = 0;

                    // ###### BACKGROUND
                    if (layers == 0) {
                        if (tileMap.getLayers().get(layers).getTileData(i + j * tileMap.getWidth()) > 0) {

                            if (tileMap.getLayers().get(layers).getTileData((i + j * tileMap.getWidth()))
                                    > tileMap.getTileSets().get(tileSet).getTileCount() ) {
                                tileSet++;
                            }

                            ground[i][j] = new Ground(
                                            "Ground",
                                            i * tileMap.getTileWidth(),
                                            j * tileMap.getTileHeight(),
                                            tileMap.getTileWidth(),
                                            tileMap.getTileHeight(),
                                            ObjectType.GROUND,
                                            tileMap.getTileSets().get(tileSet).getImageSprite(
                                                    tileMap.getLayers().get(layers).getTileData(i + j * tileMap.getWidth())
                                                            - tileMap.getTileSets().get(tileSet).getFirstGrid())
                            );

                            tileSet = 0;
                        } else {
                            ground[i][j] = null;
                        }
                    }
                }
            }
        }
    }


    private void iterateUpdate(GameContainer gc, StateBasedGame sbg, int delta, GameObject[][] list, MouseCursor mouseCursor, float offsetX, float offsetY) throws SlickException {
        for (int x = tilesToWest; x < tilesToEast; x++) {
            for (int y = tilesToNorth; y < tilesToSouth; y++) {
                if (player.getTileX(x) > 0
                        && player.getTileY(y) >= 0
                        && player.getTileX(x) < level.getWidth()
                        && player.getTileY(y) < level.getHeight()
                        ) {
                    if (list[player.getTileX(0) + x][player.getTileY(0) + y] != null) {
                        list[player.getTileX(0) + x][player.getTileY(0) + y].update(gc, sbg, delta, offsetX, offsetY);

                        // Mouse hover
                        if (mouseCursor.intersects(
                                list[player.getTileX(0) + x][player.getTileY(0) + y].getX() - offsetX,
                                list[player.getTileX(0) + x][player.getTileY(0) + y].getY() - offsetY,
                                list[player.getTileX(0) + x][player.getTileY(0) + y].getWidth(),
                                list[player.getTileX(0) + x][player.getTileY(0) + y].getHeight()
                        )) {
                            list[player.getTileX(0) + x][player.getTileY(0) + y].setHover(true);
                        } else list[player.getTileX(0) + x][player.getTileY(0) + y].setHover(false);
                    }
                }
            }
        }
    }

//    private void draw(GameObject[][] matrix, GameContainer gc, StateBasedGame sbg, Graphics g, GameObject[][] list, float offsetX, float offsetY) throws SlickException {
//        for (int i = 0; i < matrix.length; i ++) {
//            for (int j = 0; j < matrix[0].length; j++) {
//                if (matrix[i][j] != null)
//                matrix[i][j].render(gc, sbg, g, offsetX, offsetY);
//            }
//        }
//    }

    private void iterateRender(GameContainer gc, StateBasedGame sbg, Graphics g, GameObject[][] list, float offsetX, float offsetY) throws SlickException {

        for (int x = tilesToWest; x < tilesToEast; x++) {
            for (int y = tilesToNorth; y < tilesToSouth; y++) {
                if (player.getTileX(x) >= 0
                        && player.getTileY(y) >= 0
                        && player.getTileX(x) < level.getWidth()
                        && player.getTileY(y) < level.getHeight()) {

                    if (list[player.getTileX(0) + x][player.getTileY(0) + y] != null) {

                        list[player.getTileX(0) + x][player.getTileY(0) + y].render(gc, sbg, g, offsetX, offsetY);

                    }
                }
            }
        }
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta, MouseCursor mouseCursor, float offsetX, float offsetY) throws SlickException {
        iterateUpdate(gc, sbg, delta, ground, mouseCursor, offsetX, offsetY);
        iterateUpdate(gc, sbg, delta, npc, mouseCursor, offsetX, offsetY);
        iterateUpdate(gc, sbg, delta, items, mouseCursor, offsetX, offsetY);
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

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g, float offsetX, float offsetY) throws SlickException {
        iterateRender(gc, sbg, g, ground, offsetX, offsetY);
        iterateRender(gc, sbg, g, npc, offsetX, offsetY);
        iterateRender(gc, sbg, g, items, offsetX, offsetY);
     }

    Player getPlayer() {
        return player;
    }


}
