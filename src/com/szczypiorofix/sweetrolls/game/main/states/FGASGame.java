/*
 * Developed by szczypiorofix on 25.09.18 23:44.
 * Copyright (c) 2018. All rights reserved.
 *
 */

package com.szczypiorofix.sweetrolls.game.main.states;

import com.szczypiorofix.sweetrolls.game.enums.*;
import com.szczypiorofix.sweetrolls.game.gui.*;
import com.szczypiorofix.sweetrolls.game.interfaces.ConsumableListener;
import com.szczypiorofix.sweetrolls.game.interfaces.DroppableListener;
import com.szczypiorofix.sweetrolls.game.main.MainClass;
import com.szczypiorofix.sweetrolls.game.main.core.*;
import com.szczypiorofix.sweetrolls.game.main.graphics.Textures;
import com.szczypiorofix.sweetrolls.game.objects.characters.Player;
import com.szczypiorofix.sweetrolls.game.objects.item.Item;
import com.szczypiorofix.sweetrolls.game.tilemap.CollisionObject;
import com.szczypiorofix.sweetrolls.game.tilemap.TileMap;
import com.szczypiorofix.sweetrolls.game.tilemap.TileSet;
import org.newdawn.slick.*;

import java.io.File;
import java.util.HashMap;


/**
 * This is the main class of the game (gameplay).
 */
public class FGASGame implements DroppableListener, ConsumableListener {

    public static final String WORLD_MAP_NAME = "worldmap.tmx";
    private final boolean COLLISIONS_ENABLED = true;

    private final LevelManager levelManager = new LevelManager();

    private Image worldMapImage;
    private Input input;
    private Player player;
    private HUD hud;
    private Inventory inventory;
    private ObjectManager objectManager;
    private MouseCursor mouseCursor;
    private String currentLevelName;
    private DialogueFrame dialogueFrame;
    private ForGoldAndSweetrolls forGoldAndSweetrolls;
    private ActionHistory actionHistory;
    private TimeCounter timeCounter;
    private MainMenuButton[] pauseMenuButtons;
    private TileMap currentTileMap;
    private SaveGameData saveGameData;
    private HashMap<String, TileSet> loadedTileSets;

    private float offsetX, offsetY;
    private int tileWidth, tileHeight;
    private int gameWidth, gameHeight;
    private boolean setNextRound;
    private boolean saveGameLoaded;



    /**
     * Main FGASGame constructor.
     * @param forGoldAndSweetrolls (ForGoldAndSweetrolls) - main class of game (starting main menu state and game state - FGASGame).
     */
    public FGASGame(ForGoldAndSweetrolls forGoldAndSweetrolls) {
        this.forGoldAndSweetrolls = forGoldAndSweetrolls;
        offsetX = 0;
        offsetY = 0;
    }


    void loadSaveGame(SaveGameData saveGameData) {
        this.saveGameData = saveGameData;
        restartGame();

        player = saveGameData.getPlayer();
        objectManager.setPlayer(saveGameData.getPlayer());
        player.setImage(Textures.getInstance().classm32.getSprite(3, 0));
        player.setPlayerAction(PlayerAction.MOVE);
        currentLevelName = saveGameData.getCurrentMapName();

        actionHistory = saveGameData.getActionHistory();
        timeCounter = saveGameData.getTimeCounter();
        hud  = new HUD(saveGameData.getPlayer(), saveGameData.getTimeCounter(), saveGameData.getActionHistory());

        hud = new HUD(saveGameData.getPlayer(), saveGameData.getTimeCounter(), saveGameData.getActionHistory());
        inventory  = saveGameData.getInventory();
        inventory.setMouseCursor(forGoldAndSweetrolls.getFGASGame().getMouseCursor());
        inventory.setup(loadedTileSets);

        calculateOffset();
    }


    /**
     * Method for changing level.
     * @param levelName (String) - level name.
     * @param levelType (LevelType) - level type (generated or created).
     */
    public void changeLevel(String levelName, String prevLevelName, LevelType levelType) {

        if ( !prevLevelName.equalsIgnoreCase("") ) {
            objectManager.getLevelMaps().get(prevLevelName).setPlayerLastTiles(player.getTileX(), player.getTileY());
        }

//        if (levelType == LevelType.INNER_RANDOM_MAP) {
//            levelName = levelName+player.getTileX()+"x"+player.getTileY();
//
//            if (!objectManager.getLevelMaps().containsKey(levelName)) {
//                currentTileMap = levelManager.loadGeneratedLevel(
//                        levelName,
//                        objectManager.getGround(player.getTileX(), player.getTileY()).getCollisions().getTypeName(),
//                        actionHistory
//                );
//                tileMapLevels.put(levelName, currentTileMap);
//                objectManager.generateLevel(currentTileMap, levelName);
//                player = objectManager.getPlayer();
//
//                //STATISTICS: Discovered Places +1
//                player.statistics.w_DiscoveredPlaces++;
//            } else {
//                currentTileMap = tileMapLevels.get(levelName);
//            }
//
//        } else {


            if (!objectManager.getLevelMaps().containsKey(levelName)) {
                System.out.println("Odczyt nowej lokacji "+levelName);
                currentTileMap = levelManager.loadLevel(levelName);
                objectManager.generateLevel(currentTileMap, levelName);
                player = objectManager.getPlayer();

                //STATISTICS: Discovered Places +1
                player.statistics.w_DiscoveredPlaces++;

            } else {
                System.out.println("Wczytanie lokacji z pamięci "+levelName);

                objectManager.setCurrentTileMap(levelName);
                objectManager.setCurrentLevelMap(levelName);
            }

        //}

        if (!prevLevelName.equalsIgnoreCase("")) {
            int stx = objectManager.getLevelMaps().get(levelName).getPlayerLastTileX();// * tileWidth;
            int sty = objectManager.getLevelMaps().get(levelName).getPlayerLastTileY();// * tileHeight;
            player.setX(stx * tileWidth);
            player.setY(sty * tileHeight);
        }

        this.currentLevelName = levelName;
        tileWidth = currentTileMap.getTileWidth();
        tileHeight = currentTileMap.getTileHeight();

        if (saveGameLoaded) {
            if (!objectManager.getLevelMaps().get(levelName).isSaveGameContentLoaded()) {
                objectManager
                        .getLevelMaps()
                        .get(levelName)
                        .setNpc(
                                saveGameData.getLevels()
                                        .get(saveGameData.getCurrentMapName()).getNpc());

                // USTAWIANIE NPCów
                for (int i = 0; i < objectManager.getLevelMaps().get(levelName).getNpc().length; i++) {
                    for (int j = 0; j < objectManager.getLevelMaps().get(levelName).getNpc()[0].length; j++) {
                        if (objectManager.getLevelMaps().get(levelName).getNpc()[i][j] != null) {
                            objectManager.getLevelMaps().get(levelName).getNpc()[i][j].prepareNPCAfterSaveGameLoad(
                                    objectManager.getTileMaps().get(levelName).getTileSets()
                            );
                        }
                    }
                }

                objectManager
                        .getLevelMaps()
                        .get(levelName)
                        .setItems(
                                saveGameData.getLevels()
                                        .get(saveGameData.getCurrentMapName()).getItems());
                // USTAWIANIE ITEMÓW
                for (int i = 0; i < objectManager.getLevelMaps().get(levelName).getItems().length; i++) {
                    for (int j = 0; j < objectManager.getLevelMaps().get(levelName).getItems()[0].length; j++) {
                        if (objectManager.getLevelMaps().get(levelName).getItems()[i][j] != null) {
                            objectManager.getLevelMaps().get(levelName).getItems()[i][j].prepareItemAfterSaveGameLoad(
                                    loadedTileSets
                            );
                        }
                    }
                }

                objectManager.getLevelMaps().get(levelName).setSaveGameContentLoaded(true);
            }
        }
        objectManager.setLevel();

        // PLAYER IN THE MIDDLE OF GENERATED OPEN MAP
        if (levelType == LevelType.INNER_RANDOM_MAP) {
            player.setX( (float) (objectManager.getCurrentLevelMap().getWidth() * objectManager.getCurrentLevelMap().getTileWidth()) / 2);
            player.setY( (float) (objectManager.getCurrentLevelMap().getHeight() * objectManager.getCurrentLevelMap().getTileHeight()) / 2);
        }

        player.setLevelState(levelType);
        player.setCurrentLevelName(currentLevelName);
        offsetX = 0;
        offsetY = 0;
    }

    private File[] finder(){
        File dir = new File("res/map");
        return dir.listFiles((dir1, filename) -> filename.endsWith(".tsx"));
    }

    void restartGame() {

        // TODO Ladowanie wszystkich tilesetów od razu.
        System.out.println("Restarting ...");
        loadedTileSets = new HashMap<>();
        File[] tilesetFiles = finder();
        for (File f : tilesetFiles) {
            loadedTileSets.put(f.getName(), new TileSet(0, f.getName()));
        }


        objectManager = new ObjectManager(gameWidth, gameHeight);
        changeLevel(WORLD_MAP_NAME, "", LevelType.WORLD_MAP);
        calculateOffset();
        player.setCurrentLevelName(currentLevelName);
        timeCounter = new TimeCounter(player);
        actionHistory = new ActionHistory();
        hud = new HUD(player, timeCounter, actionHistory);

        inventory = new Inventory(player, mouseCursor);
        inventory.setConsumableListener(this);
        inventory.setDroppableListener(this);

        dialogueFrame = new DialogueFrame(player, mouseCursor);

        pauseMenuButtons = new MainMenuButton[2];
        pauseMenuButtons[0] = new MainMenuButton("Wznów", 230, 280);
        pauseMenuButtons[1] = new MainMenuButton("Zapisz i wyjdź", 230, 320);

        // CREATE WORLD MAP IMAGE
        try {
            int imgWidth = 300;
            int imgHeight = 300;
            ImageBuffer ib = new ImageBuffer(imgWidth, imgHeight);
            for (int i = 0; i < imgWidth; i++) {
                for (int j = 0; j < imgHeight; j++) {
                    ib.setRGBA(i,
                            j,
                            objectManager.getGrounds()[i][j].getMiniMapColor().getRed(),
                            objectManager.getGrounds()[i][j].getMiniMapColor().getGreen(),
                            objectManager.getGrounds()[i][j].getMiniMapColor().getBlue(),
                            objectManager.getGrounds()[i][j].getMiniMapColor().getAlpha()
                    );
                }
            }
            worldMapImage = ib.getImage();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void init(GameContainer gc, Input input, MouseCursor mouseCursor) {
        this.input = input;
        this.mouseCursor = mouseCursor;
        gameWidth = gc.getWidth();
        gameHeight = gc.getHeight();
    }



    public void handleLogic(GameContainer gc, int delta) throws SlickException {

        mouseCursor.update(delta, offsetX, offsetY);

        if (input.isKeyPressed(Input.KEY_ESCAPE)) {

            // PAUSE MENU
            if (player.getPlayerAction() == PlayerAction.MOVE) {
                player.setPlayerAction(PlayerAction.PAUSE_MENU);
            } else if (player.getPlayerAction() == PlayerAction.INVENTORY) {
                inventory.setShow(false);
                player.setPlayerAction(PlayerAction.MOVE);
            } else if (player.getPlayerAction() == PlayerAction.MAP) {
                player.setPlayerAction(PlayerAction.MOVE);
            } else player.setPlayerAction(PlayerAction.MOVE);
        }


        // INVENTORY
        if (input.isKeyPressed(Input.KEY_I)) {
            if (player.getPlayerAction() == PlayerAction.MOVE) {
                player.setPlayerAction(PlayerAction.INVENTORY);
                inventory.setShow(true);
            }
        }

        // WORLD MAP
        if (input.isKeyPressed(Input.KEY_M)) {
            if (player.getPlayerAction() == PlayerAction.MOVE)
                player.setPlayerAction(PlayerAction.MAP);

        }

        // PLAYER CONTROLS ON MOVE
        if (player.getPlayerAction() == PlayerAction.MOVE) {

            if (input.isKeyPressed(Input.KEY_RIGHT) || gc.getInput().isKeyPressed(Input.KEY_D)) {

                if (player.getTileX() < objectManager.getCurrentTileMap().getWidth()-1) {
                    boolean pass = false;

                    if (objectManager.getGround(player.getTileX(1), player.getTileY()).getCollisions().getCollisionType() == CollisionObject.CollisionType.PASSABLE
                            || !COLLISIONS_ENABLED) pass = true;

                    if (objectManager.getNpcs()[player.getTileX(1)][player.getTileY()] != null)
                        if (objectManager.getNpcs()[player.getTileX(1)][player.getTileY()].getCollisions().getCollisionType() == CollisionObject.CollisionType.COLLISION) pass = false;

                    if (pass) {
                        player.moveEast();
                        setNextRound = true;
                    }
                }
                calculateOffset();
            }

            if (input.isKeyPressed((Input.KEY_LEFT)) || gc.getInput().isKeyPressed(Input.KEY_A)) {
                if (player.getTileX() > 0) {
                    boolean pass = false;

                    if (objectManager.getGround(player.getTileX(-1), player.getTileY()).getCollisions().getCollisionType() == CollisionObject.CollisionType.PASSABLE
                            || !COLLISIONS_ENABLED) pass = true;

                    if (objectManager.getNpcs()[player.getTileX(-1)][player.getTileY()] != null)
                        if (objectManager.getNpcs()[player.getTileX(-1)][player.getTileY()].getCollisions().getCollisionType() == CollisionObject.CollisionType.COLLISION) pass = false;

                    if (pass) {
                        player.moveWest();
                        setNextRound = true;
                    }
                }
                calculateOffset();
            }

            if (input.isKeyPressed(Input.KEY_UP) || gc.getInput().isKeyPressed(Input.KEY_W)) {
                if (player.getTileY() > 0) {
                    boolean pass = false;

                    if (objectManager.getGround(player.getTileX(), player.getTileY(-1)).getCollisions().getCollisionType() == CollisionObject.CollisionType.PASSABLE
                            || !COLLISIONS_ENABLED) pass = true;

                    if (objectManager.getNpcs()[player.getTileX()][player.getTileY(-1)] != null)
                        if (objectManager.getNpcs()[player.getTileX()][player.getTileY(-1)].getCollisions().getCollisionType() == CollisionObject.CollisionType.COLLISION) pass = false;

                    if (pass) {
                        player.moveNorth();
                        setNextRound = true;
                    }
                }
                calculateOffset();
            }

            if (input.isKeyPressed((Input.KEY_DOWN)) || gc.getInput().isKeyPressed(Input.KEY_S)) {
                if (player.getTileY() < objectManager.getCurrentTileMap().getHeight()-1) {
                    boolean pass = false;

                    if (objectManager.getGround(player.getTileX(), player.getTileY(1)).getCollisions().getCollisionType() == CollisionObject.CollisionType.PASSABLE
                            || !COLLISIONS_ENABLED) pass = true;

                    if (objectManager.getNpcs()[player.getTileX()][player.getTileY(1)] != null)
                        if (objectManager.getNpcs()[player.getTileX()][player.getTileY(1)].getCollisions().getCollisionType() == CollisionObject.CollisionType.COLLISION) pass = false;

                    if (pass) {
                        player.moveSouth();
                        setNextRound = true;
                    }
                }
                calculateOffset();
            }

            if (input.isKeyPressed(Input.KEY_E)) {

                if (objectManager.getPlace(player.getTileX(), player.getTileY()) != null) {
                    actionHistory.addValue(objectManager.getPlace(player.getTileX(), player.getTileY()).getStringProperty("name"));
                    mouseCursor.resetPosition();
                    changeLevel(objectManager.getPlace(player.getTileX(),player.getTileY()).getStringProperty("filename"), currentLevelName, objectManager.getPlace(player.getTileX(),player.getTileY()).getStringProperty("filename").equalsIgnoreCase(WORLD_MAP_NAME) ? LevelType.WORLD_MAP : LevelType.INNER_MAP);
                } else if (objectManager.getCurrentLevelMap().getLevelType() == LevelType.WORLD_MAP) {

                    //player.setLevelState(LevelMap.LevelType.INNER_RANDOM_MAP);
                    mouseCursor.resetPosition();
                    changeLevel("generate", currentLevelName, LevelType.INNER_RANDOM_MAP);



//                    actionHistory.addValue("Szukanie zasobów...");
//                    if (
//                            objectManager.getGround(player.getTileX(), player.getTileY()).getLastStamp()
//                                    < timeCounter.getTimeStamp()
//                                    - objectManager.getGround(player.getTileX(), player.getTileY()).getTerrainResources().getResources().get(ResourceType.WATER).getType().timeToRenewResource
//                    ) {
//                        actionHistory.addValue("Ostatni timestamp: "+objectManager.getGround(player.getTileX(), player.getTileY()).getLastStamp());
//                        actionHistory.addValue("Pobieram zasoby wody: "+objectManager.getGround(player.getTileX(), player.getTileY()).getTerrainResources().getResources().get(ResourceType.WATER).getAmount());
//                        objectManager.getGround(player.getTileX(), player.getTileY()).setLastStamp(timeCounter.getTimeStamp());
//                    } else {
//                        actionHistory.addValue("Brak zasobów wody.");
//                    }



                } else {
                    // ######## Podnoszenie przedmiotów za pomocą "E"
                    if (objectManager.getItems(player.getTileX(), player.getTileY()) != null) {
                        Item currentItem = objectManager.getItems(player.getTileX(), player.getTileY());
                        pickUpItem(currentItem, 0, 0);
                    }

                    if (
                            ((player.getTileX() <= 0
                                    || player.getTileY() <= 0
                                    || player.getTileX() >= objectManager.getCurrentLevelMap().getWidth()-1
                                    || player.getTileY() >= objectManager.getCurrentLevelMap().getHeight()-1))

                    ) {
                        //actionHistory.addValue("Wyjście z generowanego poziomu...");
                        mouseCursor.resetPosition();
                        changeLevel(WORLD_MAP_NAME, currentLevelName, LevelType.WORLD_MAP);
                    }
                }

                calculateOffset();
            }


            if (input.isKeyPressed(Input.KEY_SPACE)) {
                //player.statistics.p_CurrentLevelBar++;
                setNextRound = true;
            }

            if (input.isKeyPressed(Input.KEY_F1)) {
                player.statistics.p_Health -= 7;
            }

            if (objectManager.getCurrentLevelMap().getLevelType() == LevelType.WORLD_MAP) {
                player.setWorldMapTileX(player.getTileX());
                player.setWorldMapTileY(player.getTileY());
            }

        }

        objectManager.update(delta, offsetX, offsetY);
        player.update(delta, offsetX, offsetY);

        if (player.getPlayerAction() == PlayerAction.DIALOGUE) dialogueFrame.update(gc, delta, offsetX, offsetY);
        if (player.getPlayerAction() == PlayerAction.INVENTORY) inventory.update(gc, delta);

        if (setNextRound) {
            objectManager.turn();
            player.turn();
            hud.turn();
            timeCounter.turn();
        }
        setNextRound = false;

        // #################################### HOVER ####################################
        // MOUSE HOVER ON PLAYER
//        if (mouseCursor.intersects(player.getX() - offsetX, player.getY() - offsetY, player.getWidth(), player.getHeight())) {
//            player.setHover(true);
//        }
//
        // ON MOVE
        if (player.getPlayerAction() == PlayerAction.MOVE) {

            if (player.getTileX() >= mouseCursor.getTileX() - 1
                    && player.getTileX() <= mouseCursor.getTileX() + 1
                    && player.getTileY() >= mouseCursor.getTileY() - 1
                    && player.getTileY() <= mouseCursor.getTileY() + 1
            ) {
                if (objectManager.getGround(mouseCursor.getTileX(), mouseCursor.getTileY()) != null) {
                    for (int i = -1; i < 2; i++) {
                        for (int j = -1; j < 2; j++) {
                            if ( (i != 0 || j != 0)
                                    && player.getTileX(i) > 0
                                    && player.getTileY(j) > 0
                                    && player.getTileX(i) < objectManager.getCurrentTileMap().getWidth()-1
                                    && player.getTileY(j) < objectManager.getCurrentTileMap().getHeight()-1) {

                                //objectManager.getGround(player.getTileX(i), player.getTileY(j)).setHover(false);

                                objectManager.getGround(mouseCursor.getTileX(), mouseCursor.getTileY()).setHover(true);

                                // NPCs
                                if (objectManager.getNpc(player.getTileX(i), player.getTileY(j)) != null
                                        && objectManager.getNpc(player.getTileX(i), player.getTileY(j)).getTileX() == mouseCursor.getTileX()
                                        && objectManager.getNpc(player.getTileX(i), player.getTileY(j)).getTileY() == mouseCursor.getTileY()
                                ) {

                                    objectManager.getNpc(player.getTileX(i), player.getTileY(j)).setHover(true);

                                    if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                                        if (!objectManager.getNpc(player.getTileX(i), player.getTileY(j)).isShortTalk()) {
                                            if (objectManager.getNpc(player.getTileX(i), player.getTileY(j)).isLondTalk()) {
                                                dialogueFrame.setNpc(objectManager.getNpc(player.getTileX(i), player.getTileY(j)));
                                                dialogueFrame.setShowDialog(true);
                                                player.setPlayerAction(PlayerAction.DIALOGUE);
                                            } else {
                                                objectManager.getNpc(player.getTileX(i), player.getTileY(j)).setShortTalk(true);
                                            }
                                        }
                                    }
                                }

                                //Items
                                if (objectManager.getItems(player.getTileX(i), player.getTileY(j)) != null
                                        && objectManager.getItems(player.getTileX(i), player.getTileY(j)).getTileX() == mouseCursor.getTileX()
                                        && objectManager.getItems(player.getTileX(i), player.getTileY(j)).getTileY() == mouseCursor.getTileY()
                                ) {

                                    objectManager.getItems(player.getTileX(i), player.getTileY(j)).setHover(true);

                                    if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                                        Item currentItem = objectManager.getItems(player.getTileX(i), player.getTileY(j));
                                        pickUpItem(currentItem, i, j);
                                    }
                                }

                            }
                        }
                    }
                }
            }

            // MOUSE HOVER ON PLACES
            if (objectManager.getCurrentLevelMap().getLevelType() == LevelType.WORLD_MAP) {
                if (objectManager.getPlace(mouseCursor.getTileX(), mouseCursor.getTileY()) != null) {
                    objectManager.getPlace(mouseCursor.getTileX(), mouseCursor.getTileY()).setHover(true);
                }
            }
        }

        if (player.getPlayerAction() == PlayerAction.PAUSE_MENU) {
            for (int i = 0; i < pauseMenuButtons.length; i++) {
                if (mouseCursor.intersects(pauseMenuButtons[i])) {
                    pauseMenuButtons[i].setHover(true);
                    if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                        pauseMenuButtons[i].setActive(true);

                        switch (i) {
                            case 0: {
                                input.clearKeyPressedRecord();
                                input.clearMousePressedRecord();
                                player.setPlayerAction(PlayerAction.MOVE);
                                break;
                            }
                            case 1: {
                                input.clearKeyPressedRecord();
                                input.clearMousePressedRecord();

                                if (!SaveGameManager.saveGame("player.sav", this)) {
                                    System.out.println("Błąd przy wczytywaniu pliku zapisu.");
                                    System.exit(-1);
                                }
                                forGoldAndSweetrolls.setGameState(GameState.MAIN_MENU);
                                break;
                            }
                        }

                    } else pauseMenuButtons[i].setActive(false);
                } else pauseMenuButtons[i].setHover(false);
            }
        }

    }


    private void calculateOffset() {

        if ((player.getTileX() >= objectManager.getTilesToEast())
                &&
                (player.getTileX() < objectManager.getCurrentTileMap().getWidth() - objectManager.getTilesToEast() + 1)
        ) {
            offsetX = player.getX() - (float) (gameWidth / 2) + (3 * tileWidth) + (player.getWidth()/2);
        }

        if ((player.getTileY() >= objectManager.getTilesToSouth() - 1)
                &&
                (player.getTileY() < objectManager.getCurrentTileMap().getHeight() - objectManager.getTilesToSouth() + 1)
        ) {
            offsetY = player.getY() - (float) (gameHeight / 2) + (player.getHeight()/2) - 4;
        }

    }

    /**
     * Slick2D main render method.
     * @param gc (GameContainer) - Slick2D GameContainer object.
     * @param g (Graphics) - Slick2D Graphics object.
     * @throws SlickException - Slick2D exception.
     */
    public void render(GameContainer gc, Graphics g) throws SlickException {

        objectManager.render(g, offsetX, offsetY);
        player.render(g, offsetX, offsetY);

//        Color c = g.getColor();
//        g.setColor(timeCounter.getDayNightEffect());
//        g.fillRect(0, 0, gameWidth - 230, gameHeight);
//        g.setColor(c);

        if (player.getPlayerAction() == PlayerAction.DIALOGUE) dialogueFrame.render(g);
        hud.render(gc, g);
        inventory.render(g);

        if (player.getPlayerAction() == PlayerAction.MAP) {
            worldMapImage.draw(60, 50, 450, 450);
            g.drawRect(59, 49, 451, 451);
            g.drawRect(60 + (int) ((player.getWorldMapTileX() * 450)/300f), 50 + (int) ((player.getWorldMapTileY() * 450)/300f), 1, 1);
        }

        if (player.getPlayerAction() == PlayerAction.PAUSE_MENU) {
            for (int i = 0; i < pauseMenuButtons.length; i++) {
                pauseMenuButtons[i].render(g, 0, 0);
            }
        }

//        if (objectManager.getGround(player.getTileX(), player.getTileY()).getCollisions() != null) {
//            g.drawString(objectManager.getGround(player.getTileX(), player.getTileY()).getCollisions().getTypeName(), 20, 50);
//        }

    }


    public static String getWorldMapName() {
        return WORLD_MAP_NAME;
    }

    public String getCurrentLevelName() {
        return currentLevelName;
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public TimeCounter getTimeCounter() {
        return timeCounter;
    }

    public HUD getHud() {
        return hud;
    }

    public void setHud(HUD hud) {
        this.hud = hud;
    }

    public MouseCursor getMouseCursor() {
        return mouseCursor;
    }

    public TileMap getCurrentTileMap() {
        return currentTileMap;
    }

    public void setCurrentTileMap(TileMap currentTileMap) {
        this.currentTileMap = currentTileMap;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ActionHistory getActionHistory() {
        return actionHistory;
    }

    public void setTimeCounter(TimeCounter timeCounter) {
        this.timeCounter = timeCounter;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void setActionHistory(ActionHistory actionHistory) {
        this.actionHistory = actionHistory;
        this.hud.setActionHistory(this.actionHistory);
    }

    public boolean isSaveGameLoaded() {
        return saveGameLoaded;
    }

    public void setSaveGameLoaded(boolean saveGameLoaded) {
        this.saveGameLoaded = saveGameLoaded;
    }

    public Player getPlayer() {
        return player;
    }

    public ObjectManager getObjectManager() {
        return objectManager;
    }


    /**
     * Method for picking up items from ground
     * @param currentItem (Item) current object - clicked by mouse left button or in the same tile as polayer ("E" key presssed)
     * @param i (int) offset x - used on mouse offset - 1 field around the player
     * @param j (int) offset y - used on mouse offset - 1 field around the player
     */
    private void pickUpItem(Item currentItem, int i, int j) {
        if (currentItem.isPickable()) {
            if (currentItem.getItemType() == ItemType.GOLD) {
                int goldGained = currentItem.getIntegerProperty("value");
                actionHistory.addValue("Znaleziono złoto: "+goldGained);
                player.statistics.p_Gold += goldGained;
                objectManager.getItems()[player.getTileX(i)][player.getTileY(j)] = null;
            } // Złoto nie pojawia się w ekwipunku.
            else {
                actionHistory.addValue("Podniesiono: "+currentItem.getStringProperty("name"));
                // STATISTICS: Picked Up item +1
                if (!currentItem.isFound()) player.statistics.w_PickedUpItems++;
                if (inventory.putToInventory(currentItem)) {
                    objectManager.getItems()[player.getTileX(i)][player.getTileY(j)] = null;
                } else {
                    actionHistory.addValue("Plecak jest pełny !!!");
                }
            }
        } else {
            if (currentItem.getItemType() == ItemType.CHEST) {
                int goldGained = MainClass.RANDOM.nextInt(currentItem.getIntegerProperty("value")) + 1;
                player.statistics.p_Gold += goldGained;
                actionHistory.addValue("Przeszukiwanie skrzyni...");
                actionHistory.addValue("Znaleziono: złoto: " + goldGained);
                objectManager.getItems()[player.getTileX(i)][player.getTileY(j)] = null;
            }
        }
    }



    @Override
    public void drop(Item item) {
        if (objectManager.getItems()[player.getTileX()][player.getTileY()] == null) {
            objectManager.getItems()[player.getTileX()][player.getTileY()] = inventory.getItemForDrop();
            objectManager.getItems()[player.getTileX()][player.getTileY()].setX(player.getX());
            objectManager.getItems()[player.getTileX()][player.getTileY()].setY(player.getY());
            actionHistory.addValue("Upuszczono "+objectManager.getItems()[player.getTileX()][player.getTileY()].getStringProperty("name"));
            inventory.removeDroppedItemFromInventory();
        } else actionHistory.addValue("Brak miejsca na ziemi!");
    }

    @Override
    public void consume(Item item) {
        switch (item.getItemType()) {
            case FOOD: {
                actionHistory.addValue("Zjedzono "+item.getName());
                player.statistics.p_Food += 5;
                break;
            }
            case POTION: {
                actionHistory.addValue("Wypito eliksir...");
                break;
            }
        }
    }

}
