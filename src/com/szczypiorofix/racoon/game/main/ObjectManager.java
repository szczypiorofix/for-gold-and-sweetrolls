package com.szczypiorofix.racoon.game.main;


import com.szczypiorofix.racoon.game.def.ObjectType;
import com.szczypiorofix.racoon.game.objects.GameObject;
import com.szczypiorofix.racoon.game.objects.character.Player;
import com.szczypiorofix.racoon.game.objects.item.Chest;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import java.util.ArrayList;

class ObjectManager {


    private TiledMap level;
    private GameContainer gameContainer;
    private Player player;
    private ArrayList<GameObject> items;

    ObjectManager(GameContainer gc) {
        gameContainer = gc;
        items = new ArrayList<>();
    }

    public void setLevel(TiledMap level) {
        this.level = level;

        int objectGroupCount = level.getObjectGroupCount();
        for (int i = 0; i < objectGroupCount; i++) {
            int objectCount = level.getObjectCount(i);
            for (int j = 0; j < objectCount; j++) {
                System.out.println("Obiekt: "+level.getObjectName(i, j));

                // Looking for player...
                if (level.getObjectName(i, j).equals("player start")) {
                    player = new Player("PGarvey",level.getObjectX(i , j), level.getObjectY(i, j), level.getTileWidth(), level.getTileHeight());
                }

                if (level.getObjectName(i, j).equals("Chest")) {
                    items.add(new Chest(
                            "Skrzynka",
                            level.getObjectX(i , j),
                            level.getObjectY(i, j),
                            level.getTileWidth(),
                            level.getTileHeight(),
                            ObjectType.ITEM));
                }
            }
        }

    }


    private void iteratingUpdate(GameContainer gc, int delta, ArrayList<GameObject> list) throws SlickException {
       for(GameObject i : list) {
           i.update(gc, delta);
       }
    }


    private void iteratingRender(GameContainer gc, Graphics g, ArrayList<GameObject> list) throws SlickException {
        for(GameObject i : list) {
            i.render(gc, g);
        }
    }

    public void update(GameContainer gc, int delta) throws SlickException {
        iteratingUpdate(gc, delta, items);
    }

    public void render(GameContainer gc, Graphics g) throws SlickException {
        iteratingRender(gc, g, items);
    }

    public Player getPlayer() {
        return player;
    }


}
