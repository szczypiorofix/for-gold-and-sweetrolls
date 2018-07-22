package com.szczypiorofix.sweetrolls.game.main;

import com.szczypiorofix.sweetrolls.game.enums.LevelType;
import com.szczypiorofix.sweetrolls.game.enums.ObjectType;
import com.szczypiorofix.sweetrolls.game.gui.HUD;
import com.szczypiorofix.sweetrolls.game.gui.MouseCursor;
import com.szczypiorofix.sweetrolls.game.objects.characters.Player;
import com.szczypiorofix.sweetrolls.game.tilemap.TileMap;

import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.EmptyTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;


class GameManager {

    private float offsetX, offsetY;
    private int tileWidth, tileHeight;
    private Input input;
    private Player player;
    private HUD hud;
    private LevelManager levelManager;
    private ObjectManager objectManager;
    private MouseCursor mouseCursor;
    private int gameWidth, gameHeight;
    private boolean setNextRound;

    GameManager() {
        offsetX = 0;
        offsetY = 0;
        levelManager = new LevelManager();
    }


    void init(GameContainer gc) {

        input = gc.getInput();

        objectManager = new ObjectManager(gc.getWidth(), gc.getHeight());
        levelManager.loadLevel(LevelType.WORLD_MAP);

        TileMap levelMap = levelManager.getCurrentLevel().getTileMap();

        objectManager.setLevel(levelMap);

        player = objectManager.getPlayer();

        mouseCursor = new MouseCursor("Mouse Cursor Game", input.getMouseX(), input.getMouseY(), 1, 1, ObjectType.MOUSECURSOR);

        tileWidth = levelMap.getTileWidth();
        tileHeight = levelMap.getTileHeight();

        gameWidth = gc.getWidth();
        gameHeight = gc.getHeight();

        hud = new HUD(player);
    }


    void handleInputs(GameContainer gc, StateBasedGame sgb, int delta) throws SlickException {

        mouseCursor.update(gc, sgb, delta, offsetX, offsetY);

        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            input.clearKeyPressedRecord();
            sgb.enterState(MainClass.MAINMENU, new FadeOutTransition(Color.black), new EmptyTransition());
        }

        if (input.isKeyPressed(Input.KEY_RIGHT) || gc.getInput().isKeyPressed(Input.KEY_D)) {
            player.setX(player.getX() + tileWidth);
            setNextRound = true;
        }

        if (input.isKeyPressed((Input.KEY_LEFT)) || gc.getInput().isKeyPressed(Input.KEY_A)) {
            player.setX(player.getX() - tileWidth);
            setNextRound = true;
        }

        if (input.isKeyPressed(Input.KEY_UP) || gc.getInput().isKeyPressed(Input.KEY_W)) {
            player.setY(player.getY() - tileHeight);
            setNextRound = true;
        }

        if (input.isKeyPressed((Input.KEY_DOWN)) || gc.getInput().isKeyPressed(Input.KEY_S)) {
            player.setY(player.getY() + tileHeight);
            setNextRound = true;
        }

        offsetX = player.getX() - (gameWidth / 2) + (4 * tileWidth);
        offsetY = player.getY() - (gameHeight / 2);
    }


    void handleLogic(GameContainer gc, StateBasedGame sgb, int delta) throws SlickException {

        objectManager.update(gc, sgb, delta, mouseCursor, offsetX, offsetY);
        player.update(gc, sgb, delta, offsetX, offsetY);

        // HOVER NA PLATERZE
        if (mouseCursor.intersects(player.getX() - offsetX, player.getY() - offsetY, player.getWidth(), player.getHeight())) {
            player.setHover(true);
        } else player.setHover(false);

        if (setNextRound) {
            objectManager.turn();
            player.turn();
        }
        setNextRound = false;
    }

    void render(GameContainer gc, StateBasedGame sgb, Graphics g) throws SlickException {

        objectManager.render(gc, sgb, g, offsetX, offsetY);
        player.render(gc, sgb, g, offsetX, offsetY);

//        g.drawString("P: X:"+player.getX()+" Y:"+player.getY(), 10, 25);
//        g.drawString("offsetX: "+offsetX, 10, 60);
//        g.drawString("offsetY: "+offsetY, 10, 75);
//        g.drawString("PTX: "+player.getTileX(0), 10, 90);
//        g.drawString("PTY: "+player.getTileY(0), 10, 105);

        hud.render(gc, sgb, g);
    }

}
