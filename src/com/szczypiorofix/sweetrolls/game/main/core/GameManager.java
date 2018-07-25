package com.szczypiorofix.sweetrolls.game.main.core;

import com.szczypiorofix.sweetrolls.game.enums.ObjectType;
import com.szczypiorofix.sweetrolls.game.enums.PlayerState;
import com.szczypiorofix.sweetrolls.game.gui.HUD;
import com.szczypiorofix.sweetrolls.game.gui.MouseCursor;
import com.szczypiorofix.sweetrolls.game.main.MainClass;
import com.szczypiorofix.sweetrolls.game.objects.characters.Player;
import com.szczypiorofix.sweetrolls.game.tilemap.TileMap;

import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.EmptyTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import java.util.HashMap;


public class GameManager {

    public static final String WORLD_MAP_NAME = "worldmap.tmx";
    private float offsetX, offsetY;
    private int tileWidth, tileHeight;
    private int mapWidth, mapHeight;
    private Input input;
    private Player player;
    private HUD hud;
    private final LevelManager levelManager;
    private ObjectManager objectManager;
    private MouseCursor mouseCursor;
    private int gameWidth, gameHeight;
    private boolean setNextRound;
    private HashMap<String, TileMap> levels;
    private String currentLevelName;

    public GameManager() {
        offsetX = 0;
        offsetY = 0;
        levels = new HashMap<>();
        levelManager = new LevelManager();
    }

    private void changeLevel(String levelName) {
        this.currentLevelName = levelName;
        TileMap levelMap;
        if (!levels.containsKey(levelName)) {
            levelManager.loadLevel(levelName);
            levelMap = levelManager.getCurrentLevel().getTileMap();
            levels.put(levelName, levelMap);
            objectManager.generateLevel(levelMap, levelName);
            player = objectManager.getPlayer();
        } else {
            levelMap = levels.get(levelName);
        }
        tileWidth = levelMap.getTileWidth();
        tileHeight = levelMap.getTileHeight();
        mapWidth = levelMap.getWidth();
        mapHeight = levelMap.getHeight();
        objectManager.setLevel(levelMap, levelName);
        player.setCurrentLevelName(currentLevelName);
    }

    public void init(GameContainer gc) {

        gameWidth = gc.getWidth();
        gameHeight = gc.getHeight();

        input = gc.getInput();

        objectManager = new ObjectManager(gameWidth, gameHeight);

        // INITIAL WORLD MAP
        changeLevel(WORLD_MAP_NAME);
        player.setPlayerState(PlayerState.MOVING_WORLD_MAP);

        mouseCursor = new MouseCursor("Mouse Cursor Game", input.getMouseX(), input.getMouseY(), 1, 1, ObjectType.MOUSECURSOR);

        player.setCurrentLevelName(currentLevelName);
        hud = new HUD(player);
    }


    public void handleInputs(GameContainer gc, StateBasedGame sgb, int delta) {

        mouseCursor.update(gc, sgb, delta, offsetX, offsetY);

        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            input.clearKeyPressedRecord();
            sgb.enterState(MainClass.MAINMENU, new FadeOutTransition(Color.black), new EmptyTransition());
        }

        if (player.getPlayerState() == PlayerState.MOVING_WORLD_MAP || player.getPlayerState() == PlayerState.MOVING_INNER_LOCATION) {
            if (input.isKeyPressed(Input.KEY_RIGHT) || gc.getInput().isKeyPressed(Input.KEY_D)) {
                player.moveEast(tileWidth);
                setNextRound = true;
            }

            if (input.isKeyPressed((Input.KEY_LEFT)) || gc.getInput().isKeyPressed(Input.KEY_A)) {
                player.moveWest(tileWidth);
                setNextRound = true;
            }

            if (input.isKeyPressed(Input.KEY_UP) || gc.getInput().isKeyPressed(Input.KEY_W)) {
                player.moveNorth(tileHeight);
                setNextRound = true;
            }

            if (input.isKeyPressed((Input.KEY_DOWN)) || gc.getInput().isKeyPressed(Input.KEY_S)) {
                player.moveSouth(tileHeight);
                setNextRound = true;
            }

            if (input.isKeyPressed(Input.KEY_E)) {
                if (player.getPlayerState() == PlayerState.MOVING_WORLD_MAP) {

                    if (objectManager.getPlaces()[player.getTileX()][player.getTileY()] != null) {
                        System.out.println("Entering: "+objectManager.getPlaces()[player.getTileX()][player.getTileY()].getStringProperty("name")+".");
                        player.setPlayerState(PlayerState.MOVING_INNER_LOCATION);
                        changeLevel(objectManager.getPlaces()[player.getTileX()][player.getTileY()].getStringProperty("filename"));
                    }
                } else {
                    if (player.getTileX() <= 0
                            || player.getTileY() <= 0
                            || player.getTileX() >= mapWidth-1
                            || player.getTileY() >= mapHeight-1
                            ) {

                        System.out.println("Exiting to world map.");
                        player.setPlayerState(PlayerState.MOVING_WORLD_MAP);
                        changeLevel(WORLD_MAP_NAME);
                    }
                }
            }

            if (input.isKeyPressed(Input.KEY_SPACE)) {
                player.statistics.currentLevelBar++;
            }
        }


        offsetX = player.getX() - (gameWidth / 2) + (4 * tileWidth);
        offsetY = player.getY() - (gameHeight / 2);
    }


    public void handleLogic(GameContainer gc, StateBasedGame sgb, int delta) throws SlickException {

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

    public void render(GameContainer gc, StateBasedGame sgb, Graphics g) throws SlickException {

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
