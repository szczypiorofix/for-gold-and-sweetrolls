package com.szczypiorofix.sweetrolls.game.main;

import com.szczypiorofix.sweetrolls.game.def.LevelType;
import com.szczypiorofix.sweetrolls.game.def.ObjectType;
import com.szczypiorofix.sweetrolls.game.gui.MouseCursor;
import com.szczypiorofix.sweetrolls.game.objects.GameObject;
import com.szczypiorofix.sweetrolls.game.objects.character.Player;
import com.szczypiorofix.sweetrolls.game.tilemap.TileMap;

import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.EmptyTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;


class GameManager {

    private TileMap levelMap;
    private double x, y;
    private int mapX, mapY;
    private float offsetX, offsetY;

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

        offsetX = 0;
        offsetY = 0;

        levelManager = new LevelManager();
    }


    void init(GameContainer gc) throws SlickException {

        input = gc.getInput();

        objectManager = new ObjectManager(gc);
        levelManager.loadLevel(LevelType.WORLD_MAP);


        levelMap = levelManager.getCurrentLevel().getTileMap();

        objectManager.setLevel(levelMap);

        //tilesInWidth = gc.getWidth() / levelMap.getTileWidth();
        //tilesInHeight = gc.getHeight() / levelMap.getTileHeight();

        player = objectManager.getPlayer();


        camera = new Camera(player.x, player.y, gc.getWidth(), gc.getHeight(), levelMap);

        mouseCursor = new MouseCursor("Mouse Cursor Game", input.getMouseX(), input.getMouseY(), 1, 1, ObjectType.MOUSECURSOR);

    }


    void handleInputs(GameContainer gc, StateBasedGame sgb, int delta) throws SlickException {

        mouseCursor.update(gc, sgb, delta, offsetX, offsetY);

        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            input.clearKeyPressedRecord();
            sgb.enterState(MainClass.MAINMENU, new FadeOutTransition(Color.black), new EmptyTransition());
        }

        if (input.isKeyDown(Input.KEY_RIGHT) || gc.getInput().isKeyDown(Input.KEY_D)) {
            player.setX(player.getX() + (delta/SPEED));
        }

        if (input.isKeyDown((Input.KEY_LEFT)) || gc.getInput().isKeyDown(Input.KEY_A)) {
            player.setX(player.getX() - (delta/SPEED));
        }

        if (input.isKeyDown(Input.KEY_UP) || gc.getInput().isKeyDown(Input.KEY_W)) {
            player.setY(player.getY() - (delta / SPEED));
        }

        if (input.isKeyDown((Input.KEY_DOWN)) || gc.getInput().isKeyDown(Input.KEY_S)) {
            player.setY(player.getY() + (delta / SPEED));
        }

        offsetX = player.getX() - (gc.getWidth() / 2);
        offsetY = player.getY() - (gc.getHeight() / 2);

    }

    void handleLogic(GameContainer gc, StateBasedGame sgb, int delta) throws SlickException {



        player.setSx(gc.getWidth() / 2);
        player.setSy(gc.getHeight() / 2);

        objectManager.update(gc, sgb, delta, offsetX, offsetY);

        player.update(gc, sgb, delta, offsetX, offsetY);

        //camera.update(player);

        if (mouseCursor.intersects(player.getX() - offsetX, player.getY() - offsetY, player.getWidth(), player.getHeight())) {
            player.setHover(true);
        } else player.setHover(false);

        for(GameObject item : objectManager.getOnGround()) {
            if (mouseCursor.intersects(item.getX() - offsetX, item.getY() - offsetY, item.width, item.height)) {
                item.setHover(true);
            } else item.setHover(false);
        }

    }

    void render(GameContainer gc, StateBasedGame sgb, Graphics g) throws SlickException {

        //g.translate(- camera.getX(), - camera.getY());

        objectManager.render(gc, sgb, g, offsetX, offsetY);
        player.render(gc, sgb, g, offsetX, offsetY);

        //g.translate(camera.getX(), camera.getY());


        g.drawString("P: X:"+player.getX()+" Y:"+player.getY(), 10, 25);
        g.drawString("PS: X:"+player.getSx()+" Y:"+player.getSy(), 10, 35);
        //g.drawString("C: X:"+camera.getX()+" Y:"+camera.getY(), 10, 45);

        g.drawString("offsetX: "+offsetX, 10, 55);
        g.drawString("offsetY: "+offsetY, 10, 65);

        g.drawString("MX: "+mouseCursor.getX(), 10, 75);
        g.drawString("MY: "+mouseCursor.getY(), 10, 85);

    }

}
