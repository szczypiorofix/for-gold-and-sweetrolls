package com.szczypiorofix.sweetrolls.game.main;


import com.szczypiorofix.sweetrolls.game.def.ObjectType;
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

    private int maxX, maxY;

    ObjectManager(GameContainer gc) {
        this.gc = gc;
        items = new ArrayList<>();
        ground = new ArrayList<>();
        onGround = new ArrayList<>();
    }

    public void setLevel(TileMap tileMap) {
        //this.level = tileMap;
        maxX = gc.getWidth() / tileMap.getTileWidth();
        maxY = gc.getHeight() / tileMap.getTileHeight();

        //System.out.println(maxX+":"+maxY);

        player = new Player("PGarvey",0, 0, 32, 32);

        for (int layers = 0; layers < tileMap.getLayers().size(); layers++) {
            for (int i = 0; i < tileMap.getWidth(); i++) {
                for (int j = 0; j < tileMap.getHeight(); j++) {
                    if (layers == 0) {
                        ground.add(new Ground(
                                "Ground",
                                i * tileMap.getTileWidth(),
                                j * tileMap.getTileHeight(),
                                tileMap.getTileWidth(),
                                tileMap.getTileHeight(),
                                ObjectType.GROUND, tileMap.getTileSets().get(layers).getImageSprite(
                                tileMap.getLayers().get(layers).getTileData(i + j * tileMap.getWidth())))
                        );
                    }
//                    if (layers == 1) {
//                        onGround.add(new Chest(
//                           "Chest",
//                            i * tileMap.getTileWidth(),
//                            j * tileMap.getTileHeight(),
//                            tileMap.getTileWidth(),
//                            tileMap.getTileHeight(),
//                            ObjectType.ITEM, tileMap.getTileSets().get(layers).getImageSprite(
//                            tileMap.getLayers().get(layers).getTileData(i + j * tileMap.getWidth()))
//                        ));
//                    }

                }
            }
        }


    }


    private void iteratingUpdate(GameContainer gc, StateBasedGame sbg, int delta, ArrayList<GameObject> list, float offsetX, float offsetY) throws SlickException {
        for(int i = 0; i < list.size(); i++) {
            list.get(i).update(gc, sbg, delta, offsetX, offsetY);
        }
    }


    private void iteratingRender(GameContainer gc, StateBasedGame sbg, Graphics g, ArrayList<GameObject> list, float offsetX, float offsetY) throws SlickException {
        for(int i = 0; i < list.size(); i++) {
            list.get(i).render(gc, sbg, g, offsetX, offsetY);
        }
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta, float offsetX, float offsetY) throws SlickException {
        iteratingUpdate(gc, sbg, delta, ground, offsetX, offsetY);
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g, float offsetX, float offsetY) throws SlickException {
        iteratingRender(gc, sbg, g, ground, offsetX, offsetY);
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList<GameObject> getItems() {
        return items;
    }

    public ArrayList<GameObject> getGround() {
        return ground;
    }
}
