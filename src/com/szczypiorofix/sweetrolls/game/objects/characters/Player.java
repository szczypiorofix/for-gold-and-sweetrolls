package com.szczypiorofix.sweetrolls.game.objects.characters;

import com.szczypiorofix.sweetrolls.game.enums.ObjectType;
import com.szczypiorofix.sweetrolls.game.enums.PlayerAction;
import com.szczypiorofix.sweetrolls.game.enums.PlayerState;
import com.szczypiorofix.sweetrolls.game.gui.ActionHistory;
import com.szczypiorofix.sweetrolls.game.main.core.TimeCounter;
import com.szczypiorofix.sweetrolls.game.main.graphics.Textures;
import com.szczypiorofix.sweetrolls.game.tilemap.Property;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import java.util.ArrayList;

public class Player extends Character {


    private int playerTurn;
    private float offsetX, offsetY;
    private int worldMapTileX = 0;
    private int worldMapTileY = 0;
    private String currentLevelName;

    private Image image;
    private ObjectType terrainType;
    private PlayerState playerState;
    private PlayerAction playerAction;
    private TimeCounter timeCounter;

    private ActionHistory actionHistory;

    public Player(String name, float x, float y, float width, float height, ArrayList<Property> properties) {
        super(name, x, y, width, height, ObjectType.PLAYER, properties);
        this.name = name;
        this.living = true;
        this.dynamic = true;
        this.visible = true;
        this.moving = true;

        playerAction = PlayerAction.MOVE;
        playerState = PlayerState.MOVING_WORLD_MAP;

        image = Textures.getInstance().classm32.getSprite(3, 0);

        statistics.gold = 0;
        statistics.level = 1;
        statistics.currentLevelBar = 1;
        statistics.currentLevelMaxBar = 8;
        statistics.foodRations = 20;
        statistics.foodUsagePerHour = 0.1f;
        statistics.water = 20;
        statistics.watetUsagePerHour = 0.1f;
        statistics.strength = 10;
        statistics.dexterity = 10;
        statistics.constitution = 10;
        statistics.intelligence = 10;

        statistics.armorClass = 0;
        statistics.damage = 1;

        statistics.maxHealth = (statistics.constitution * 10) + 50;
        statistics.health = statistics.maxHealth;

        playerTurn = 0;

        setWorldMapTileX(getTileX());
        setWorldMapTileY(getTileY());

        actionHistory = new ActionHistory();

        timeCounter = new TimeCounter(this);
    }


    @Override
    public void update(int delta, float offsetX, float offsetY) {
        hover = false;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    @Override
    public void render(Graphics g, float offsetX, float offsetY) {

        //g.setDrawMode(Graphics.MODE_ADD);
        image.draw( - offsetX + x, - offsetY + y);
        //g.setDrawMode(Graphics.MODE_NORMAL);

        if (hover) {
            g.drawString(this.name, - offsetX + x, - offsetY + y - 15);
            g.drawString("w: "+width +", h: "+height, - offsetX + x, - offsetY + y - 30);
            g.drawString("x: "+x +", y: "+y, - offsetX + x, - offsetY + y - 45);
        }
    }

    @Override
    public void turn() {
        playerTurn++;

        timeCounter.nextTurn();

        if (playerState == PlayerState.MOVING_WORLD_MAP) {
            worldMapTileX = getTileX();
            worldMapTileY = getTileY();
        }

    }

    public void calculateSurvival() {
        statistics.foodRations -= statistics.foodUsagePerHour;
        statistics.water -= statistics.watetUsagePerHour;
    }

    public ActionHistory getActionHistory() {
        return actionHistory;
    }

    public TimeCounter getTimeCounter() {
        return timeCounter;
    }

    public float getOffsetX() {
        return offsetX;
    }

    public float getOffsetY() {
        return offsetY;
    }

    public void moveNorth(int offset) {
        y -= offset;
        actionHistory.addValue("Kierunek: północ");
    }

    public void moveSouth(int offset) {
        y += offset;
        actionHistory.addValue("Kierunek: południe");
    }

    public void moveWest(int offset) {
        x -= offset;
        actionHistory.addValue("Kierunek: zachód");
    }

    public void moveEast(int offset) {
        x += offset;
        actionHistory.addValue("Kierunek: wschód");
    }

    public void moveNorth() {
        y -= 32;
        actionHistory.addValue("Kierunek: północ");
    }

    public void moveSouth() {
        y += 32;
        actionHistory.addValue("Kierunek: południe");
    }

    public void moveWest() {
        x -= 32;
        actionHistory.addValue("Kierunek: zachód");
    }

    public void moveEast() {
        x += 32;
        actionHistory.addValue("Kierunek: wschód");
    }

    public int getPlayerTurn() {
        return playerTurn;
    }

    public ObjectType getTerrainType() {
        return terrainType;
    }

    public void setTerrainType(ObjectType terrainType) {
        this.terrainType = terrainType;
    }

    public void setPlayerTurn(int playerTurn) {
        this.playerTurn = playerTurn;
    }

    public PlayerState getPlayerState() {
        return playerState;
    }

    public void setPlayerState(PlayerState playerState) {
        this.playerState = playerState;
    }

    public int getWorldMapTileX() {
        return worldMapTileX;
    }

    public void setWorldMapTileX(int worldMapTileX) {
        this.worldMapTileX = worldMapTileX;
    }

    public int getWorldMapTileY() {
        return worldMapTileY;
    }

    public void setWorldMapTileY(int worldMapTileY) {
        this.worldMapTileY = worldMapTileY;
    }

    public String getCurrentLevelName() {
        return currentLevelName;
    }

    public void setCurrentLevelName(String currentLevelName) {
        this.currentLevelName = currentLevelName;
    }

    public PlayerAction getPlayerAction() {
        return playerAction;
    }

    public void setPlayerAction(PlayerAction playerAction) {
        this.playerAction = playerAction;
    }
}
