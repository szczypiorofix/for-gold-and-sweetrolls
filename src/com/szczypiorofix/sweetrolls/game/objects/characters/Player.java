package com.szczypiorofix.sweetrolls.game.objects.characters;

import com.szczypiorofix.sweetrolls.game.enums.ObjectType;
import com.szczypiorofix.sweetrolls.game.enums.PlayerState;
import com.szczypiorofix.sweetrolls.game.graphics.Textures;
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

    public Player(String name, float x, float y, float width, float height, ArrayList<Property> properties) {
        super(name, x, y, width, height, ObjectType.PLAYER, properties);
        this.name = name;
        this.living = true;
        this.dynamic = true;
        this.visible = true;
        this.moving = true;

        image = Textures.getInstance().classm32.getSprite(3, 0);

        statistics.level = 1;
        statistics.currentLevelBar = 1;
        statistics.currentLevelMaxBar = 8;
        playerTurn = 0;

    }


    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta, float offsetX, float offsetY) {

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g, float offsetX, float offsetY) {
        image.draw( - offsetX + x, - offsetY + y);
        if (hover) {
            g.drawString(this.name, - offsetX + x, - offsetY + y - 15);
            g.drawString("w: "+width +", h: "+height, - offsetX + x, - offsetY + y - 30);
            g.drawString("x: "+x +", y: "+y, - offsetX + x, - offsetY + y - 45);
        }
    }

    @Override
    public void turn() {
        playerTurn++;
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

    public int getTileX(int offset) {
        return (int) ((x + (width / 2)) / width) +offset;
    }

    public int getTileY(int offset) {
        return (int) ((y + (height / 2)) / height) + offset;
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
}
