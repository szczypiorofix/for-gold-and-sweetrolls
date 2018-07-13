package com.szczypiorofix.sweetrolls.game.main;

import com.szczypiorofix.sweetrolls.game.def.Level;
import com.szczypiorofix.sweetrolls.game.def.ObjectType;
import com.szczypiorofix.sweetrolls.game.gui.MouseCursor;
import com.szczypiorofix.sweetrolls.game.objects.GameObject;
import com.szczypiorofix.sweetrolls.game.objects.character.Player;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.EmptyTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.tiled.TiledMap;

import java.util.ArrayList;


class GameManager {

    private TiledMap levelMap;
    private double x, y;
    private int mapX, mapY;

    private int tilesInWidth = 0, tilesInHeight = 0;
    private final static int SPEED = 2;

    private Input input;

    private Player player;

    private LevelManager levelManager;
    private ObjectManager objectManager;
    private Camera camera;

    private MouseCursor mouseCursor;

    GameManager() {
        x = 0d;
        y = 0d;
        mapX = 0;
        mapY = 0;

        levelManager = new LevelManager();
    }


    void init(GameContainer gc) throws SlickException {

        input = gc.getInput();

        objectManager = new ObjectManager(gc);
        levelManager.loadLevel(Level.WORLD_MAP);
        levelMap = levelManager.getLevelMap();
        objectManager.setLevel(levelMap);

        tilesInWidth = gc.getWidth() / levelMap.getTileWidth();
        tilesInHeight = gc.getHeight() / levelMap.getTileHeight();

        player = objectManager.getPlayer();

        camera = new Camera(player.x, player.y, gc.getWidth(), gc.getHeight(), levelMap);

        mouseCursor = new MouseCursor("Mouse Cursor Game", input.getMouseX(), input.getMouseY(), 1, 1, ObjectType.MOUSECURSOR);

    }


    void handleInputs(GameContainer gc, StateBasedGame sgb, int delta) throws SlickException {

        mouseCursor.update(gc, sgb, delta);

        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            input.clearKeyPressedRecord();
            sgb.enterState(MainClass.MAINMENU, new FadeOutTransition(Color.black), new EmptyTransition());
        }

        if (input.isKeyDown(Input.KEY_LEFT) || gc.getInput().isKeyDown(Input.KEY_A)) {
            player.moveWest(delta / SPEED);
        }

        if (input.isKeyDown((Input.KEY_RIGHT)) || gc.getInput().isKeyDown(Input.KEY_D)) {
            player.moveEast(delta / SPEED);
        }

        if (input.isKeyDown(Input.KEY_UP) || gc.getInput().isKeyDown(Input.KEY_W)) {
            player.moveNorth(delta / SPEED);
        }

        if (input.isKeyDown((Input.KEY_DOWN)) || gc.getInput().isKeyDown(Input.KEY_S)) {
            player.moveSouth(delta / SPEED);
        }



    }

    void handleLogic(GameContainer gc, StateBasedGame sgb, int delta) throws SlickException {
        if (x < 0) {
            mapX++;
            x = 32;
        }

        if (x > 32) {
            mapX--;
            x = 0;
        }

        if (y < 0) {
            mapY++;
            y = 32;
        }

        if (y > 32) {
            mapY--;
            y = 0;
        }

        player.setSx(gc.getWidth() / 2);
        player.setSy(gc.getHeight() / 2);

        objectManager.update(gc, sgb, delta);

        camera.update(player);

        if (mouseCursor.intersects(player.getSx(), player.getSy(), player.getWidth(), player.getHeight())) {
            player.setHover(true);
        } else player.setHover(false);

        for(GameObject item : objectManager.getItems()) {
            if (mouseCursor.intersects(item.getX() - (player.getX() - player.getSx()), item.getY() - (player.getY() - player.getSy()), item.width, item.height)) {
                item.setHover(true);
            } else item.setHover(false);
        }

    }

    void render(GameContainer gc, StateBasedGame sgb, Graphics g) throws SlickException {

        g.translate(- camera.getX(), - camera.getY());

        levelMap.render(
                (int) (x - levelMap.getTileWidth()),
                (int) (x - levelMap.getTileHeight()),
                mapX,
                mapY,
                tilesInWidth,
                tilesInHeight
                );

        objectManager.render(gc, sgb, g);

        g.translate(camera.getX(), camera.getY());

        player.render(gc, sgb, g);

        g.drawString("P: X:"+player.getX()+" Y:"+player.getY(), 10, 25);
        g.drawString("PS: X:"+player.getSx()+" Y:"+player.getSy(), 10, 35);
        g.drawString("C: X:"+camera.getX()+" Y:"+camera.getY(), 10, 45);

        g.drawString("MX: "+mouseCursor.getX(), 10, 55);
        g.drawString("MY: "+mouseCursor.getY(), 10, 65);
    }

}
