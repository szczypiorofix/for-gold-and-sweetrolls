package com.szczypiorofix.sweetrolls.game.main.core;

import com.szczypiorofix.sweetrolls.game.enums.ObjectType;
import com.szczypiorofix.sweetrolls.game.gui.DialogueFrame;
import com.szczypiorofix.sweetrolls.game.gui.HUD;
import com.szczypiorofix.sweetrolls.game.gui.MouseCursor;
import com.szczypiorofix.sweetrolls.game.main.MainClass;
import com.szczypiorofix.sweetrolls.game.objects.characters.NPC;
import com.szczypiorofix.sweetrolls.game.objects.characters.Player;
import com.szczypiorofix.sweetrolls.game.tilemap.CollisionObject;
import com.szczypiorofix.sweetrolls.game.tilemap.TileMap;

import org.lwjgl.Sys;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.logging.Level;

import static com.szczypiorofix.sweetrolls.game.enums.PlayerAction.MOVE;
import static com.szczypiorofix.sweetrolls.game.enums.PlayerAction.TALK;
import static com.szczypiorofix.sweetrolls.game.enums.PlayerState.MOVING_INNER_LOCATION;
import static com.szczypiorofix.sweetrolls.game.enums.PlayerState.MOVING_WORLD_MAP;


public class GameManager {

    public static final String WORLD_MAP_NAME = "worldmap.tmx";

    private final LevelManager levelManager = new LevelManager();
    private final HashMap<String, TileMap> levels = new HashMap<>();
    private final boolean collisionsEnabled = true;

    private float offsetX, offsetY;
    private int tileWidth, tileHeight;
    private int mapWidth, mapHeight;
    private int gameWidth, gameHeight;
    private boolean setNextRound;

    private Input input;
    private Player player;
    private HUD hud;
    private ObjectManager objectManager;
    private MouseCursor mouseCursor;
    private String currentLevelName;
    private DialogueFrame dialogueFrame;


    private enum LevelType {
        CREATED,
        GENERATED
    }

    public GameManager() {
        offsetX = 0;
        offsetY = 0;
        dialogueFrame = new DialogueFrame();
    }

    private void changeLevel(String levelName, LevelType levelType) {
        this.currentLevelName = levelName;
        TileMap levelMap;

        if (levelType == LevelType.GENERATED) {
            levelName = levelName+player.getTileX()+"x"+player.getTileY();

            if (!levels.containsKey(levelName)) {
                MainClass.logging(false, Level.INFO, "Generowanie nowego losowego poziomu: "+levelName);
                levelMap = levelManager.loadGeneratedLevel(levelName);
                levels.put(levelName, levelMap);
                objectManager.generateLevel(levelMap, levelName);
                player = objectManager.getPlayer();
            } else {
                MainClass.logging(false, Level.INFO, "Pobieranie danych o losowym poziomie z listy: "+levelName);
                levelMap = levels.get(levelName);
            }

        } else {

            if (!levels.containsKey(levelName)) {
                MainClass.logging(false, Level.INFO, "Wczytywanie danych zdefiniowanego poziomu: "+levelName);
                levelMap = levelManager.loadLevel(levelName);
                levels.put(levelName, levelMap);
                objectManager.generateLevel(levelMap, levelName);
                player = objectManager.getPlayer();
            } else {
                MainClass.logging(false, Level.INFO, "Pobieranie danych o zdefiniowanym poziomie z listy: "+levelName);
                levelMap = levels.get(levelName);
            }

        }

        tileWidth = levelMap.getTileWidth();
        tileHeight = levelMap.getTileHeight();
        mapWidth = levelMap.getWidth();
        mapHeight = levelMap.getHeight();
        objectManager.setLevel(levelMap, levelName);
        player.setCurrentLevelName(currentLevelName);
        offsetX = 0;
        offsetY = 0;
    }

    public void init(GameContainer gc) {

        gameWidth = gc.getWidth();
        gameHeight = gc.getHeight();

        input = gc.getInput();

        objectManager = new ObjectManager(gameWidth, gameHeight);

        // INITIAL WORLD MAP
        changeLevel(WORLD_MAP_NAME, LevelType.CREATED);
        calculateOffset();

        mouseCursor = new MouseCursor("Mouse Cursor Game", input.getMouseX(), input.getMouseY(), 32, 32, ObjectType.MOUSECURSOR, input);

        player.setCurrentLevelName(currentLevelName);
        hud = new HUD(player, mouseCursor);
    }


    public void handleInputs(GameContainer gc, StateBasedGame sgb, int delta) {

        mouseCursor.update(gc, sgb, delta, offsetX, offsetY);

        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            if (player.getPlayerAction() == MOVE) {
                input.clearKeyPressedRecord();
                //sgb.enterState(MainClass.MAINMENU, new FadeOutTransition(Color.black), new EmptyTransition());
                sgb.enterState(MainClass.MAINMENU);
            } else if (player.getPlayerAction() == TALK) {
                player.setPlayerAction(MOVE);
            }
        }

        // PLAYER CONTROLS
        if (player.getPlayerState() == MOVING_WORLD_MAP || player.getPlayerState() == MOVING_INNER_LOCATION) {

            if (input.isKeyPressed(Input.KEY_T)) {
                FileOutputStream fout = null;
                ObjectOutputStream oos = null;

                try {

                    fout = new FileOutputStream("player.sav");
                    oos = new ObjectOutputStream(fout);
                    oos.writeObject(player);

                    System.out.println("Object player saved !");

                } catch (Exception ex) {

                    ex.printStackTrace();

                } finally {

                    if (fout != null) {
                        try {
                            fout.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    if (oos != null) {
                        try {
                            oos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }

            if (input.isKeyPressed(Input.KEY_RIGHT) || gc.getInput().isKeyPressed(Input.KEY_D)) {

                if (player.getPlayerAction() == MOVE && player.getTileX() < objectManager.getLevel().getWidth()-1) {

                    if (objectManager.getGround(player.getTileX() + 1, player.getTileY()).getCollisions().getCollisionType() == CollisionObject.CollisionType.PASSABLE || !collisionsEnabled) {
                        player.moveEast();
                        setNextRound = true;
                    }
                }
                calculateOffset();
            }

            if (input.isKeyPressed((Input.KEY_LEFT)) || gc.getInput().isKeyPressed(Input.KEY_A)) {
                if (player.getPlayerAction() == MOVE && player.getTileX() > 0) {
                    if (objectManager.getGround(player.getTileX() - 1, player.getTileY()).getCollisions().getCollisionType() == CollisionObject.CollisionType.PASSABLE || !collisionsEnabled) {
                        player.moveWest();
                        setNextRound = true;
                    }
                }
                calculateOffset();
            }

            if (input.isKeyPressed(Input.KEY_UP) || gc.getInput().isKeyPressed(Input.KEY_W)) {
                if (player.getPlayerAction() == MOVE && player.getTileY() > 0) {
                    if (objectManager.getGround(player.getTileX(), player.getTileY() - 1).getCollisions().getCollisionType() == CollisionObject.CollisionType.PASSABLE || !collisionsEnabled) {
                        player.moveNorth();
                        setNextRound = true;
                    }
                }
                calculateOffset();
            }

            if (input.isKeyPressed((Input.KEY_DOWN)) || gc.getInput().isKeyPressed(Input.KEY_S)) {
                if (player.getPlayerAction() == MOVE && player.getTileY() < objectManager.getLevel().getHeight()-1) {
                    if (objectManager.getGround(player.getTileX(), player.getTileY() + 1).getCollisions().getCollisionType() == CollisionObject.CollisionType.PASSABLE || !collisionsEnabled) {
                        player.moveSouth();
                        setNextRound = true;
                    }
                }
                calculateOffset();
            }

            if (input.isKeyPressed(Input.KEY_E)) {

                // ENTERING INNER MAP FROM WORLD MAP
                if (player.getPlayerState() == MOVING_WORLD_MAP) {
                    if (objectManager.getPlace(player.getTileX(), player.getTileY()) != null) {

                        //System.out.println("Entering: "+objectManager.getPlace(player.getTileX(), player.getTileY()).getStringProperty("name")+".");
                        MainClass.logging(false, Level.INFO, "Przejście do lokacji: "+objectManager.getPlace(player.getTileX(), player.getTileY()).getStringProperty("name")+".");

                        player.setPlayerState(MOVING_INNER_LOCATION);
                        mouseCursor.setPositionTile(0, 0);
                        changeLevel(objectManager.getPlace(player.getTileX(), player.getTileY()).getStringProperty("filename"), LevelType.CREATED);
                    } else {
                        player.setPlayerState(MOVING_INNER_LOCATION);
                        mouseCursor.setPositionTile(0, 0);
                        changeLevel("generate", LevelType.GENERATED);
                    }
                } else {
                    // EXIT FROM INNER MAP
                    if (player.getTileX() <= 0
                            || player.getTileY() <= 0
                            || player.getTileX() >= mapWidth-1
                            || player.getTileY() >= mapHeight-1
                            || objectManager.getPlace(player.getTileX(), player.getTileY()) != null
                            ) {

                        //System.out.println("Exiting to world map.");
                        MainClass.logging(false, Level.INFO, "Powrót do mapy głównej.");

                        player.setPlayerState(MOVING_WORLD_MAP);
                        changeLevel(WORLD_MAP_NAME, LevelType.CREATED);
                    }
                }
                calculateOffset();
            }


            if (input.isKeyPressed(Input.KEY_SPACE)) {
                player.statistics.currentLevelBar++;
            }
        }



    }

    private void calculateOffset() {

        if ((player.getTileX() >= objectManager.getTilesToEast())
                &&
                (player.getTileX() < objectManager.getLevel().getWidth() - objectManager.getTilesToEast() + 1)
                ) {
            offsetX = player.getX() - (gameWidth / 2) + (3 * tileWidth) + (player.getWidth()/2);
        }

        if ((player.getTileY() >= objectManager.getTilesToSouth() - 1)
                &&
                (player.getTileY() < objectManager.getLevel().getHeight() - objectManager.getTilesToSouth() + 1)
                ) {
            offsetY = player.getY() - (gameHeight / 2) + (player.getHeight()/2) - 4;
        }

    }


    public void handleLogic(GameContainer gc, StateBasedGame sgb, int delta) throws SlickException {

        objectManager.update(gc, sgb, delta, mouseCursor, offsetX, offsetY);
        player.update(gc, sgb, delta, offsetX, offsetY);

        dialogueFrame.update(gc, sgb, delta, offsetX, offsetY);

        if (setNextRound) {
            objectManager.turn();
            player.turn();
        }
        setNextRound = false;

        // #################################### HOVER ####################################
        // MOUSE HOVER ON PLAYER
//        if (mouseCursor.intersects(player.getX() - offsetX, player.getY() - offsetY, player.getWidth(), player.getHeight())) {
//            player.setHover(true);
//        }
//
        if (player.getTileX() >= mouseCursor.getTileX() - 1
                && player.getTileX() <= mouseCursor.getTileX() + 1
                && player.getTileY() >= mouseCursor.getTileY() - 1
                && player.getTileY() <= mouseCursor.getTileY() + 1
                ) {
            if (objectManager.getGround(mouseCursor.getTileX(), mouseCursor.getTileY()) != null) {
                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        if (!(i == 0 && j == 0)
                                && player.getTileX(i) > 0
                                && player.getTileY(j) > 0
                                && player.getTileX(i) < objectManager.getLevel().getWidth()-1
                                && player.getTileY(j) < objectManager.getLevel().getHeight()-1) {

                            //objectManager.getGround(player.getTileX(i), player.getTileY(j)).setHover(true);

                            objectManager.getGround(mouseCursor.getTileX(), mouseCursor.getTileY()).setHover(true);
                            if (objectManager.getNpc(player.getTileX(i), player.getTileY(j)) != null
                                    && objectManager.getNpc(player.getTileX(i), player.getTileY(j)).getTileX() == mouseCursor.getTileX()
                                    && objectManager.getNpc(player.getTileX(i), player.getTileY(j)).getTileY() == mouseCursor.getTileY()
                                    ) {

                                objectManager.getNpc(player.getTileX(i), player.getTileY(j)).setHover(true);

                                if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                                    NPC npc = (NPC) objectManager.getNpc(player.getTileX(i), player.getTileY(j));
                                    if (!npc.isShortTalk()) {
                                        //npc.setShortTalk(true);
                                        dialogueFrame = new DialogueFrame(player, npc, mouseCursor);
                                        dialogueFrame.setShowDialog(true);
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }

        // MOUSE HOVER ON OBJECTS
        if (player.getPlayerState() == MOVING_WORLD_MAP) {
            if (objectManager.getPlace(mouseCursor.getTileX(), mouseCursor.getTileY()) != null) {
                objectManager.getPlace(mouseCursor.getTileX(), mouseCursor.getTileY()).setHover(true);
            }
        }

//        if (player.getPlayerState() == MOVING_INNER_LOCATION) {
//            if (objectManager.getNpc(mouseCursor.getTileX(), mouseCursor.getTileY()) != null) {
//                objectManager.getNpc(mouseCursor.getTileX(), mouseCursor.getTileY()).setHover(true);
//            }
//        }

    }

    public void render(GameContainer gc, StateBasedGame sgb, Graphics g) throws SlickException {

        objectManager.render(gc, sgb, g, offsetX, offsetY);
        player.render(gc, sgb, g, offsetX, offsetY);

        Color c = g.getColor();
        g.setColor(player.getTimeCounter().getDayNightEffect());
        g.fillRect(0, 0, gameWidth - 230, gameHeight);
        g.setColor(c);

        dialogueFrame.render(gc, sgb, g);
        hud.render(gc, sgb, g);

        if (objectManager.getGround(player.getTileX(), player.getTileY()).getCollisions() != null) {
            g.drawString(objectManager.getGround(player.getTileX(), player.getTileY()).getCollisions().getTypeName(), 20, 50);
        }


    }

}
