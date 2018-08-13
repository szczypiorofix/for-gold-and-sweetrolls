package com.szczypiorofix.sweetrolls.game.main.states;

import com.szczypiorofix.sweetrolls.game.enums.GameState;
import com.szczypiorofix.sweetrolls.game.gui.DialogueFrame;
import com.szczypiorofix.sweetrolls.game.gui.HUD;
import com.szczypiorofix.sweetrolls.game.gui.Inventory;
import com.szczypiorofix.sweetrolls.game.gui.MouseCursor;
import com.szczypiorofix.sweetrolls.game.main.MainClass;
import com.szczypiorofix.sweetrolls.game.main.core.LevelManager;
import com.szczypiorofix.sweetrolls.game.main.core.ObjectManager;
import com.szczypiorofix.sweetrolls.game.objects.characters.Player;
import com.szczypiorofix.sweetrolls.game.objects.item.Item;
import com.szczypiorofix.sweetrolls.game.tilemap.CollisionObject;
import com.szczypiorofix.sweetrolls.game.tilemap.TileMap;

import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import java.util.HashMap;
import java.util.logging.Level;

import static com.szczypiorofix.sweetrolls.game.enums.PlayerAction.MOVE;
import static com.szczypiorofix.sweetrolls.game.enums.PlayerAction.INVENTORY;
import static com.szczypiorofix.sweetrolls.game.enums.PlayerState.MOVING_INNER_LOCATION;
import static com.szczypiorofix.sweetrolls.game.enums.PlayerState.MOVING_WORLD_MAP;


public class FGAS_Game {

    public static final String WORLD_MAP_NAME = "worldmap.tmx";

    private final LevelManager levelManager = new LevelManager();
    private final HashMap<String, TileMap> levels = new HashMap<>();
    private final boolean collisionsEnabled = true;

    private float offsetX, offsetY;
    private int tileWidth, tileHeight;
    private int mapWidth, mapHeight;
    private int gameWidth, gameHeight;
    private boolean setNextRound;
    private boolean showMap;

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

    private enum LevelType {
        CREATED,
        GENERATED
    }

    public FGAS_Game(ForGoldAndSweetrolls forGoldAndSweetrolls) {
        this.forGoldAndSweetrolls = forGoldAndSweetrolls;
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
                levelMap = levelManager.loadGeneratedLevel(
                        levelName,
                        objectManager.getGround(player.getTileX(), player.getTileY()).getCollisions().getTypeName(),
                        player
                );
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

    public void init(GameContainer gc, Input input, MouseCursor mouseCursor) {

        gameWidth = gc.getWidth();
        gameHeight = gc.getHeight();

        this.input = input;

        objectManager = new ObjectManager(gameWidth, gameHeight);

        // INITIAL WORLD MAP
        changeLevel(WORLD_MAP_NAME, LevelType.CREATED);
        calculateOffset();

        this.mouseCursor = mouseCursor;

        player.setCurrentLevelName(currentLevelName);
        hud = new HUD(player, mouseCursor);
        inventory = new Inventory(player, mouseCursor);

        try {

            worldMapImage = new Image("res/map/worldmap.png");

            int imgWidth = worldMapImage.getWidth();
            int imgHeight = worldMapImage.getHeight();

            imgWidth = 300;
            imgHeight = 300;

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


    public void handleInputs(GameContainer gc, int delta) throws SlickException {

        mouseCursor.update(delta, offsetX, offsetY);

        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            if (player.getPlayerAction() == MOVE) {
                forGoldAndSweetrolls.setGameState(GameState.MAIN_MENU);
            } else if (player.getPlayerAction() == INVENTORY) {
                player.setPlayerAction(MOVE);
                inventory.setShow(false);
            }
        }

        // INVENTORY
        if (input.isKeyPressed(Input.KEY_I)) {
            inventory.setShow(!inventory.isShow());
            if (inventory.isShow()) {
                player.setPlayerAction(INVENTORY);
            } else player.setPlayerAction(MOVE);
        }

        // PLAYER CONTROLS ON MOVE
        if (player.getPlayerAction() == MOVE) {

            if (input.isKeyPressed(Input.KEY_M)) {
                showMap = !showMap;
            }

            if (input.isKeyPressed(Input.KEY_RIGHT) || gc.getInput().isKeyPressed(Input.KEY_D)) {

                if (player.getTileX() < objectManager.getLevel().getWidth()-1) {
                    boolean pass = false;

                    if (objectManager.getGround(player.getTileX(1), player.getTileY()).getCollisions().getCollisionType() == CollisionObject.CollisionType.PASSABLE
                            || !collisionsEnabled) pass = true;

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
                            || !collisionsEnabled) pass = true;

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
                            || !collisionsEnabled) pass = true;

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
                if (player.getTileY() < objectManager.getLevel().getHeight()-1) {
                    boolean pass = false;

                    if (objectManager.getGround(player.getTileX(), player.getTileY(1)).getCollisions().getCollisionType() == CollisionObject.CollisionType.PASSABLE
                            || !collisionsEnabled) pass = true;

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

                // ENTERING INNER MAP FROM WORLD MAP
                if (player.getPlayerState() == MOVING_WORLD_MAP) {
                    if (objectManager.getPlace(player.getTileX(), player.getTileY()) != null) {

                        MainClass.logging(false, Level.INFO, "Przejście do lokacji: "+objectManager.getPlace(player.getTileX(), player.getTileY()).getStringProperty("name")+".");

                        player.setPlayerState(MOVING_INNER_LOCATION);
                        player.getActionHistory().addValue("Wchodzisz do: "+objectManager.getPlace(player.getTileX(), player.getTileY()).getStringProperty("name"));
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

                        MainClass.logging(false, Level.INFO, "Powrót do mapy głównej.");
                        player.getActionHistory().addValue("Powrót na mapę główną");
                        player.setPlayerState(MOVING_WORLD_MAP);
                        changeLevel(WORLD_MAP_NAME, LevelType.CREATED);
                    }

                    // ######## Podnoszenie przedmiotów za pomocą "E"
                    if (objectManager.getItems(player.getTileX(), player.getTileY()) != null) {
                        Item currentItem = objectManager.getItems(player.getTileX(), player.getTileY());
                        if (currentItem.isPickable()) {

                            // ######################## UT IN INVENTORY

                            // GOLD
                            if (currentItem.getStringProperty("type").equalsIgnoreCase("gold")) {
                                int goldGained = currentItem.getIntegerProperty("value");
                                player.getActionHistory().addValue("Znaleziono złoto: "+goldGained);
                                player.statistics.gold += goldGained;
                                objectManager.getItems()[player.getTileX()][player.getTileY()] = null;
                            } // Złoto nie pojawia się w ekwipunku.
                            else {
                                player.getActionHistory().addValue("Podniesiono: "+currentItem.getStringProperty("name"));
                                if (inventory.putToInventory(currentItem)) {
                                    objectManager.getItems()[player.getTileX()][player.getTileY()] = null;
                                } else {
                                    player.getActionHistory().addValue("Plecak jest pełny !!!");
                                }
                            }
                        }
                    }

                }
                calculateOffset();
            }


            if (input.isKeyPressed(Input.KEY_SPACE)) {
                player.statistics.currentLevelBar++;
            }
        }

        if (player.getPlayerAction() == INVENTORY) {
            inventory.update(gc, delta);
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


    public void handleLogic(GameContainer gc, int delta) throws SlickException {

        objectManager.update(delta, offsetX, offsetY);
        player.update(delta, offsetX, offsetY);

        dialogueFrame.update(gc, delta, offsetX, offsetY);

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
        // ON MOVE
        if (player.getPlayerAction() == MOVE) {

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

                                // NPCs
                                if (objectManager.getNpc(player.getTileX(i), player.getTileY(j)) != null
                                        && objectManager.getNpc(player.getTileX(i), player.getTileY(j)).getTileX() == mouseCursor.getTileX()
                                        && objectManager.getNpc(player.getTileX(i), player.getTileY(j)).getTileY() == mouseCursor.getTileY()
                                        ) {

                                    objectManager.getNpc(player.getTileX(i), player.getTileY(j)).setHover(true);

                                    if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                                        if (!objectManager.getNpc(player.getTileX(i), player.getTileY(j)).isShortTalk()) {
                                            if (objectManager.getNpc(player.getTileX(i), player.getTileY(j)).isLondTalk()) {
                                                dialogueFrame = new DialogueFrame(player, objectManager.getNpc(player.getTileX(i), player.getTileY(j)), mouseCursor);
                                                dialogueFrame.setShowDialog(true);
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
                                        if (currentItem.isPickable()) {

                                            // ######################## UT IN INVENTORY

                                            // GOLD
                                            if (currentItem.getStringProperty("type").equalsIgnoreCase("gold")) {
                                                int goldGained = currentItem.getIntegerProperty("value");
                                                player.getActionHistory().addValue("Znaleziono złoto: "+goldGained);
                                                player.statistics.gold += goldGained;
                                                objectManager.getItems()[player.getTileX(i)][player.getTileY(j)] = null;
                                            } // Złoto nie pojawia się w ekwipunku.
                                            else {
                                                player.getActionHistory().addValue("Podniesiono: "+currentItem.getStringProperty("name"));
                                                if (inventory.putToInventory(currentItem)) {
                                                    objectManager.getItems()[player.getTileX(i)][player.getTileY(j)] = null;
                                                } else {
                                                    player.getActionHistory().addValue("Plecak jest pełny !!!");
                                                }
                                            }
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
        }

        if (player.getPlayerAction() == INVENTORY) {
            if (inventory.isDropping()) {
                if (objectManager.getItems()[player.getTileX()][player.getTileY()] == null) {
                    objectManager.getItems()[player.getTileX()][player.getTileY()] = inventory.dropFromInventory();
                    objectManager.getItems()[player.getTileX()][player.getTileY()].setX(player.getX());
                    objectManager.getItems()[player.getTileX()][player.getTileY()].setY(player.getY());
                    inventory.removeItem();
                } else player.getActionHistory().addValue("Brak miejsca na ziemi!");
                inventory.setDropping(false);
            }
        }
    }

    public void render(GameContainer gc, Graphics g) throws SlickException {

        objectManager.render(g, offsetX, offsetY);
        player.render(g, offsetX, offsetY);

        Color c = g.getColor();
        g.setColor(player.getTimeCounter().getDayNightEffect());
        g.fillRect(0, 0, gameWidth - 230, gameHeight);
        g.setColor(c);

        dialogueFrame.render(g);
        hud.render(gc, g);
        inventory.render(g);

        if (showMap) {
            worldMapImage.draw(60, 50, 450, 450);
            g.drawRect(59, 49, 451, 451);
            g.drawRect(60 + ((player.getTileX() * 450)/300), 50 + ((player.getTileY() * 450)/300), 1, 1);
        }

//        if (objectManager.getGround(player.getTileX(), player.getTileY()).getCollisions() != null) {
//            g.drawString(objectManager.getGround(player.getTileX(), player.getTileY()).getCollisions().getTypeName(), 20, 50);
//        }

    }

}
