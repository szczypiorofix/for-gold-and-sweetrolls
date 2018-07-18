package com.szczypiorofix.sweetrolls.game.main;


import com.szczypiorofix.sweetrolls.game.def.ObjectType;
import com.szczypiorofix.sweetrolls.game.graphics.Textures;
import com.szczypiorofix.sweetrolls.game.objects.EmptyObject;
import com.szczypiorofix.sweetrolls.game.objects.GameObject;
import com.szczypiorofix.sweetrolls.game.objects.Ground;
import com.szczypiorofix.sweetrolls.game.objects.character.Player;
import com.szczypiorofix.sweetrolls.game.objects.item.Chest;
import com.szczypiorofix.sweetrolls.game.tilemap.TileMap;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;

class ObjectManager {


    private TileMap level;
    private GameContainer gc;
    private Player player;
    private ArrayList<GameObject> items;
    private ArrayList<GameObject> ground;
    private ArrayList<GameObject> onGround;
    private ArrayList<GameObject> foreGround;

    private int maxX, maxY;
    private int tilesToWest, tilesToEast, tilesToNorth, tilesToSouth;

    ObjectManager(GameContainer gc) {
        this.gc = gc;
        items = new ArrayList<>();
        ground = new ArrayList<>();
        onGround = new ArrayList<>();
        foreGround = new ArrayList<>();
    }

    public void setLevel(TileMap tileMap) {
        this.level = tileMap;
        maxX = gc.getWidth() / tileMap.getTileWidth();
        maxY = gc.getHeight() / tileMap.getTileHeight();

        tilesToWest = - maxX / 2 - 1;
        tilesToEast = maxX / 2 + 1;
        tilesToNorth = - maxY / 2 - 1;
        tilesToSouth = maxY / 2 + 1;

        player = new Player("PGarvey",570, 350, 32, 32);

        for (int layers = 0; layers < tileMap.getLayers().size(); layers++) {
            for (int i = 0; i < tileMap.getWidth(); i++) {
                for (int j = 0; j < tileMap.getHeight(); j++) {
                    if (layers == 0) {
                        if (tileMap.getLayers().get(layers).getTileData(i + j * tileMap.getWidth()) > 0) {
                            ground.add(new Ground(
                                            "Ground",
                                            i * tileMap.getTileWidth(),
                                            j * tileMap.getTileHeight(),
                                            tileMap.getTileWidth(),
                                            tileMap.getTileHeight(),
                                            ObjectType.GROUND,
                                            tileMap.getTileSets().get(layers).getImageSprite(
                                                    tileMap.getLayers().get(layers).getTileData(i + j * tileMap.getWidth())
                                            )
                                    )
                            );
                        } else {
                            onGround.add(new EmptyObject("Empty"));
                        }
                    }

                    if (layers == 1) {
                        if (tileMap.getLayers().get(layers).getTileData(i + j * tileMap.getWidth()) > 0) {
                            onGround.add(new Chest(
                                    "Chest",
                                    i * tileMap.getTileWidth(),
                                    j * tileMap.getTileHeight(),
                                    tileMap.getTileWidth(),
                                    tileMap.getTileHeight(),
                                    ObjectType.ITEM,
                                    tileMap.getTileSets().get(0).getImageSprite(
                                        tileMap.getLayers().get(layers).getTileData(i + j * tileMap.getWidth())
                                    )
                                )
                            );
                        } else {
                            onGround.add(new EmptyObject("Empty"));
                        }
                    }

                    if (layers == 2) {
                        if (tileMap.getLayers().get(layers).getTileData(i + j * tileMap.getWidth()) > 0) {
                            foreGround.add(new Chest(
                                            "Chest 2",
                                            i * tileMap.getTileWidth(),
                                            j * tileMap.getTileHeight(),
                                            tileMap.getTileWidth(),
                                            tileMap.getTileHeight(),
                                            ObjectType.ITEM,
                                            tileMap.getTileSets().get(0).getImageSprite(
                                                    tileMap.getLayers().get(layers).getTileData(i + j * tileMap.getWidth())
                                            )
                                    )
                            );
                        } else {
                            foreGround.add(new EmptyObject("Empty"));
                        }
                    }

                }
            }
        }

        //onGround.add(new Chest("Chest", 30, 30, 32, 32, ObjectType.ITEM, Textures.getInstance().miscItems.getSprite(1, 1)));


    }


    private void iteratingUpdate(GameContainer gc, StateBasedGame sbg, int delta, ArrayList<GameObject> list, float offsetX, float offsetY) throws SlickException {

        for (int x = tilesToWest; x < tilesToEast; x++) {
            for (int y = tilesToNorth; y < tilesToSouth; y++) {
                int index = (player.getTileX(0) + x) * level.getWidth() + (player.getTileY(0) + y);
                if (index >= 0) {
                    list.get(index).update(gc, sbg, delta, offsetX, offsetY);
                }
            }
        }
    }


    private void iteratingRender(GameContainer gc, StateBasedGame sbg, Graphics g, ArrayList<GameObject> list, float offsetX, float offsetY) throws SlickException {

        for (int x = tilesToWest; x < tilesToEast; x++) {
            for (int y = tilesToNorth; y < tilesToSouth; y++) {
                int index = (player.getTileX(0) + x) * level.getWidth() + (player.getTileY(0) + y);
                if (index >= 0) list.get(index).render(gc, sbg, g, offsetX, offsetY);
            }
        }

//        for (int i = 0; i < list.size(); i++) {
//            list.get(i).render(gc, sbg, g, offsetX, offsetY);
//        }

    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta, float offsetX, float offsetY) throws SlickException {
        iteratingUpdate(gc, sbg, delta, ground, offsetX, offsetY);
        iteratingUpdate(gc, sbg, delta, onGround, offsetX, offsetY);
        iteratingUpdate(gc, sbg, delta, foreGround, offsetX, offsetY);
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g, float offsetX, float offsetY) throws SlickException {
        iteratingRender(gc, sbg, g, ground, offsetX, offsetY);
        iteratingRender(gc, sbg, g, onGround, offsetX, offsetY);
        iteratingRender(gc, sbg, g, foreGround, offsetX, offsetY);
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList<GameObject> getItems() {
        return items;
    }

    public ArrayList<GameObject> getOnGround() {
        return onGround;
    }

    public ArrayList<GameObject> getGround() {
        return ground;
    }
}
