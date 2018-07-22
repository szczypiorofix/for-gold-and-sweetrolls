package com.szczypiorofix.sweetrolls.game.objects.characters;

import com.szczypiorofix.sweetrolls.game.enums.ObjectType;
import com.szczypiorofix.sweetrolls.game.enums.PlayerClass;
import com.szczypiorofix.sweetrolls.game.graphics.Textures;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

public class Player extends Character {

    private Image image;
    PlayerClass playerClass;
    private int playerTurn;
    private ObjectType terrainType;

    public Player(String name, float x, float y, float width, float height) {
        super(name, x, y, width, height, ObjectType.PLAYER);
        this.living = true;
        this.dynamic = true;
        this.visible = true;
        this.moving = true;
        image = Textures.getInstance().classm32.getSprite(3, 0);

        playerClass = PlayerClass.WARRIOR;
        statistics.strength = playerClass.strength;
        statistics.dexterity = playerClass.dexterity;
        statistics.constitution = playerClass.constitution;
        statistics.intelligence = playerClass.intelligence;
        statistics.maxHealth = playerClass.maxHealth;
        statistics.health = playerClass.maxHealth;
        statistics.level = 1;
        playerTurn = 0;
    }


    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta, float offsetX, float offsetY) {

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g, float offsetX, float offsetY) {
        image.draw( - offsetX + x, - offsetY + y);
        if (hover) {
            g.drawString(name, - offsetX + x, - offsetY + y - 15);
        }
    }

    @Override
    public void turn() {
        playerTurn++;
    }

    public int getTileX(int offset) {
        return (int) ((x + (width / 2)) / width) +offset;
    }

    public int getTileY(int offset) {
        return (int) ((y + (height / 2)) / height) + offset;
    }

    public PlayerClass getPlayerClass() {
        return playerClass;
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
}
