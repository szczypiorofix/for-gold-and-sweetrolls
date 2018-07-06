package com.szczypiorofix.racoon.game.main;

import com.szczypiorofix.racoon.game.objects.Player;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

class GameManager {

    private TiledMap levelMap;
    private double x, y;
    private int mapX, mapY;
    private final static float SPEED = 3f;

    private Player player;

    private LevelManager levelManager;

    GameManager() {
        this.x = 0d;
        this.y = 0d;
        this.mapX = 0;
        this.mapY = 0;

        this.levelManager = new LevelManager();
    }


    void init(GameContainer gc) throws SlickException {
        levelManager.loadLevel(Level.WORLD_MAP);
        this.levelMap = levelManager.getLevelMap();
        this.player = new Player(150,150);
    }



    void handleInputs(GameContainer gc, int delta) {
        if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
            exitGame(gc);
        }
        if (gc.getInput().isKeyDown(Input.KEY_LEFT)) {
            this.x += delta / SPEED;
        }
        if (gc.getInput().isKeyDown((Input.KEY_RIGHT))) {
            this.x -= delta / SPEED;
        }
        if (gc.getInput().isKeyDown(Input.KEY_UP)) {
            this.y += delta / SPEED;
        }
        if (gc.getInput().isKeyDown((Input.KEY_DOWN))) {
            this.y -= delta / SPEED;
        }
    }

    void handleLogic(GameContainer gc, int delta) {
        if (this.x < 0) {
            mapX++;
            x = 32;
        }

        if (this.x > 32) {
            mapX--;
            x = 0;
        }

        if (this.y < 0) {
            mapY++;
            y = 32;
        }

        if (this.y > 32) {
            mapY--;
            y = 0;
        }
    }

    void render(GameContainer gc, Graphics g) throws SlickException {
        levelMap.render(
                (int) Math.round(this.x - levelMap.getTileWidth()),
                (int) Math.round(this.y - levelMap.getTileHeight()),
                mapX,
                mapY,
                levelMap.getTileWidth(),
                levelMap.getTileHeight());
        player.render(gc, g);
    }

    private void exitGame(GameContainer gameContainer) {
        gameContainer.exit();
    }

}
