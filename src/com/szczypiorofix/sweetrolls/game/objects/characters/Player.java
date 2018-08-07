package com.szczypiorofix.sweetrolls.game.objects.characters;

import com.szczypiorofix.sweetrolls.game.enums.ObjectType;
import com.szczypiorofix.sweetrolls.game.enums.PlayerAction;
import com.szczypiorofix.sweetrolls.game.enums.PlayerState;
import com.szczypiorofix.sweetrolls.game.main.core.TimeCounter;
import com.szczypiorofix.sweetrolls.game.main.graphics.Textures;
import com.szczypiorofix.sweetrolls.game.tilemap.Property;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

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

        statistics.maxHealth = (statistics.constitution * 10) + 50;
        statistics.health = statistics.maxHealth;

        playerTurn = 0;

        setWorldMapTileX(getTileX());
        setWorldMapTileY(getTileY());

        timeCounter = new TimeCounter(this);
    }


    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta, float offsetX, float offsetY) {
        hover = false;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g, float offsetX, float offsetY) {

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
    }

    public void moveSouth(int offset) {
        y += offset;
    }

    public void moveWest(int offset) {
        x -= offset;
    }

    public void moveEast(int offset) {
        x += offset;
    }

    public void moveNorth() {
        y -= 32;
    }

    public void moveSouth() {
        y += 32;
    }

    public void moveWest() {
        x -= 32;
    }

    public void moveEast() {
        x += 32;
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
