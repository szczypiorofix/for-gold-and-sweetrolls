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

    private Image image;
    private int playerTurn;
    private ObjectType terrainType;
    private PlayerState playerState;
    private int worldMapTileX = 0;
    private int worldMapTileY = 0;
    private String currentLevelName;
    private PlayerAction playerAction;
    private float offsetX, offsetY;
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
        image.draw( - offsetX + x, - offsetY + y);
        if (playerAction == PlayerAction.TALK) {
            g.drawString("Zagadaj?", - offsetX + x, - offsetY + y - 15);
        }
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
