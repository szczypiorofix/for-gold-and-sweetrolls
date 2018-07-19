package com.szczypiorofix.sweetrolls.game.main;


import com.szczypiorofix.sweetrolls.game.def.ObjectType;
import com.szczypiorofix.sweetrolls.game.gui.MouseCursor;
import com.szczypiorofix.sweetrolls.game.objects.GameObject;
import com.szczypiorofix.sweetrolls.game.objects.Ground;
import com.szczypiorofix.sweetrolls.game.objects.character.Player;
import com.szczypiorofix.sweetrolls.game.objects.item.Chest;
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
    private GameObject[][] onGround;
    private GameObject[][] foreGround;

    private int tilesToWest, tilesToEast, tilesToNorth, tilesToSouth;

    ObjectManager(GameContainer gc) {
        this.gc = gc;
    }

    void setLevel(TileMap tileMap) {
        this.level = tileMap;
        int maxX = gc.getWidth() / tileMap.getTileWidth();
        int maxY = gc.getHeight() / tileMap.getTileHeight();

//        tilesToWest = - maxX / 2 - 1;
//        tilesToEast = maxX / 2 + 1;
//        tilesToNorth = - maxY / 2 - 1;
//        tilesToSouth = maxY / 2 + 1;

        int margin = 5;

        tilesToWest = - margin;
        tilesToEast = margin;
        tilesToNorth = - margin;
        tilesToSouth = margin;

        ground = new GameObject[level.getWidth()][level.getHeight()];
        onGround = new GameObject[level.getWidth()][level.getHeight()];
        foreGround = new GameObject[level.getWidth()][level.getHeight()];


        // ############ OBIEKTY
        for (int i = 0; i < tileMap.getObjectGroups().size(); i++) {

            if (tileMap.getObjectGroups().get(i).getName().equals("player")) {

                // Nie może być więcej niż 1 player!

                player = new Player(
                        tileMap.getObjectGroups().get(i).getObjects().get(0).getStringProperty("name")
                                + "_" +tileMap.getObjectGroups().get(i).getObjects().get(0).getIntegerProperty("age"),
                        tileMap.getObjectGroups().get(i).getObjects().get(0).getX(),
                        tileMap.getObjectGroups().get(i).getObjects().get(0).getY(),
                        tileMap.getObjectGroups().get(i).getObjects().get(0).getWidth(),
                        tileMap.getObjectGroups().get(i).getObjects().get(0).getHeight());
            }
        }

        // ############ TILESY
        int tileSet = 0;
        for (int layers = 0; layers < tileMap.getLayers().size(); layers++) {

            for (int i = 0; i < tileMap.getWidth(); i++) {
                for (int j = 0; j < tileMap.getHeight(); j++) {
                    tileSet = 0;

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
                                    )
                            ;


                            tileSet = 0;
                        } else {
                            ground[i][j] = null;
                        }
                    }

                    if (layers == 1) {
                        if (tileMap.getLayers().get(layers).getTileData(i + j * tileMap.getWidth()) > 0) {

                            if (tileMap.getLayers().get(layers).getTileData((i + j * tileMap.getWidth()))
                                    > tileMap.getTileSets().get(tileSet).getTileCount() ) {
                                tileSet++;
                            }

                            onGround[i][j] = new Chest(
                                            "Chest",
                                            i * tileMap.getTileWidth(),
                                            j * tileMap.getTileHeight(),
                                            tileMap.getTileWidth(),
                                            tileMap.getTileHeight(),
                                            ObjectType.ITEM,
                                            tileMap.getTileSets().get(tileSet).getImageSprite(
                                                    tileMap.getLayers().get(layers).getTileData(i + j * tileMap.getWidth())
                                                            - tileMap.getTileSets().get(tileSet).getFirstGrid())
                                    );

                            tileSet = 0;
                        } else {
                            onGround[i][j] = null;
                        }
                    }

                    if (layers == 2) {
                        if (tileMap.getLayers().get(layers).getTileData(i + j * tileMap.getWidth()) > 0) {

                            if (tileMap.getLayers().get(layers).getTileData((i + j * tileMap.getWidth()))
                                    > tileMap.getTileSets().get(tileSet).getTileCount() ) {
                                tileSet++;
                            }

                            foreGround[i][j] = new Chest(
                                            "Chest 2",
                                            i * tileMap.getTileWidth(),
                                            j * tileMap.getTileHeight(),
                                            tileMap.getTileWidth(),
                                            tileMap.getTileHeight(),
                                            ObjectType.ITEM,
                                            tileMap.getTileSets().get(tileSet).getImageSprite(
                                                    tileMap.getLayers().get(layers).getTileData(i + j * tileMap.getWidth())
                                                            - tileMap.getTileSets().get(tileSet).getFirstGrid())
                                    );

                            tileSet = 0;
                        } else {
                            foreGround[i][j] = null;
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

    private void draw(GameObject[][] matrix, GameContainer gc, StateBasedGame sbg, Graphics g, GameObject[][] list, float offsetX, float offsetY) throws SlickException {
        for (int i = 0; i < matrix.length; i ++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] != null)
                matrix[i][j].render(gc, sbg, g, offsetX, offsetY);
            }
        }
    }

    private void iterateRender(GameContainer gc, StateBasedGame sbg, Graphics g, GameObject[][] list, float offsetX, float offsetY) throws SlickException {
//        GameObject[][] matrix = new GameObject[list.length][list[0].length];
//        for (int i = 0; i < matrix.length; i++) {
//            for (int j = 0; j < matrix[0].length; j++) {
//                matrix[i][j] = null;
//            }
//        }
        for (int x = tilesToWest; x < tilesToEast; x++) {
            for (int y = tilesToNorth; y < tilesToSouth; y++) {
                if (player.getTileX(x) >= 0
                        && player.getTileY(y) >= 0
                        && player.getTileX(x) < level.getWidth()
                        && player.getTileY(y) < level.getHeight()) {

                    if (list[player.getTileX(0) + x][player.getTileY(0) + y] != null) {
                        list[player.getTileX(0) + x][player.getTileY(0) + y].setOpacity(1f);

                        if ((x == 1 || x == 2 || x == -1 || x == -2) && (y == 1 || y == 2 || y == -1 || y == -2)) {
                            list[player.getTileX(0) + x][player.getTileY(0) + y].setOpacity(0.75f);
                        }
                        if ((x == 3 || x == 4 || x == -3 || x == -4) && (y == 3 || y == 4 || y == -3 || y == -4)) {
                            list[player.getTileX(0) + x][player.getTileY(0) + y].setOpacity(0.5f);
                        }
                        if ((x == 5 || x == 6 || x == -5 || x == -6) && (y == 5 || y == 6 || y == -5 || y == -6)) {
                            list[player.getTileX(0) + x][player.getTileY(0) + y].setOpacity(0.25f);
                        }

                        list[player.getTileX(0) + x][player.getTileY(0) + y].render(gc, sbg, g, offsetX, offsetY);


                        //matrix[x - tilesToWest][y - tilesToNorth] = list[player.getTileX(0) + x][player.getTileY(0) + y];
                    } //else matrix[x - tilesToWest][y - tilesToNorth] = null;

                }
            }
        }
        //draw(matrix, gc, sbg, g, list, offsetX, offsetY);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta, MouseCursor mouseCursor, float offsetX, float offsetY) throws SlickException {
        //iterateUpdate(gc, sbg, delta, ground, mouseCursor, offsetX, offsetY);
        //iterateUpdate(gc, sbg, delta, onGround, mouseCursor, offsetX, offsetY);
        //iterateUpdate(gc, sbg, delta, foreGround, mouseCursor, offsetX, offsetY);
    }

    private void graczSwieci(int x,  int y, int sila) {
        for (int sx = x - sila; sx <= x + sila; sx++) {
            for (int sy = y - sila; sy <= y + sila; sy++) {
                int dist = (int) (Math.max(0, sila - Math.sqrt(Math.pow(sx - x, 2) + Math.pow(sy - y, 2))));
                System.out.print(dist);
            }
            System.out.println();
        }
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g, float offsetX, float offsetY) throws SlickException {
        iterateRender(gc, sbg, g, ground, offsetX, offsetY);
        //iterateRender(gc, sbg, g, onGround, offsetX, offsetY);
        //iterateRender(gc, sbg, g, foreGround, offsetX, offsetY);
    }

    public Player getPlayer() {
        return player;
    }


    public GameObject[][] getGround() {
        return ground;
    }

    public GameObject[][] getOnGround() {
        return onGround;
    }

    public GameObject[][] getForeGround() {
        return foreGround;
    }
}
