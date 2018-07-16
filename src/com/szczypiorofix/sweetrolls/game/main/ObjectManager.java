package com.szczypiorofix.sweetrolls.game.main;


import com.szczypiorofix.sweetrolls.game.objects.GameObject;
import com.szczypiorofix.sweetrolls.game.objects.character.Player;
import com.szczypiorofix.sweetrolls.game.tilemap.TileMap;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;

class ObjectManager {


    private TileMap level;
    private GameContainer gameContainer;
    private Player player;
    private ArrayList<GameObject> items;

    ObjectManager(GameContainer gc) {
        gameContainer = gc;
        items = new ArrayList<>();
    }

    public void setLevel(TileMap level) {
        this.level = level;

        player = new Player("PGarvey",10, 10, 32, 32);

    }


    private void iteratingUpdate(GameContainer gc, StateBasedGame sbg, int delta, ArrayList<GameObject> list) throws SlickException {
       for(GameObject i : list) {
           i.update(gc, sbg, delta);
       }
    }


    private void iteratingRender(GameContainer gc, StateBasedGame sbg, Graphics g, ArrayList<GameObject> list) throws SlickException {
        for(GameObject i : list) {
            i.render(gc, sbg, g);
        }
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        iteratingUpdate(gc, sbg, delta, items);
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        iteratingRender(gc, sbg, g, items);
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList<GameObject> getItems() {
        return items;
    }
}
