package com.szczypiorofix.racoon.game.main;


import com.szczypiorofix.racoon.game.objects.GameObject;
import com.szczypiorofix.racoon.game.objects.character.Player;
import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.tiled.TiledMap;

import java.util.ArrayList;

class ObjectManager {


    private TiledMap level;
    private GameContainer gameContainer;
    private Player player;
    private ArrayList<GameObject> worldMapTowns;

    ObjectManager(GameContainer gc) {
        gameContainer = gc;
        worldMapTowns = new ArrayList<>();
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
                    System.out.println(player.getX()+":"+player.getY());
                    return;
                }
            }
        }
    }

    public void update(GameContainer gc, int delta) {
        player.update(gc, delta);
    }

    public void render(GameContainer gc, Graphics g) {
        player.render(gc, g);
    }

    public Player getPlayer() {
        return player;
    }


}
