package com.szczypiorofix.racoon.game.main;


import org.newdawn.slick.*;
import org.newdawn.slick.tiled.TiledMap;

public class RacoonGame extends BasicGame {

    private TiledMap grassMap;
    private double x, y;
    private int mapX, mapY;
    private final Float SPEED = 5f;
    private int mapWidth = 0, mapHeight = 0;

    RacoonGame(String title) {
        super(title);
        this.x = 0d;
        this.y = 0d;
        this.mapX = 0;
        this.mapY = 0;
    }

    private void exitGame(GameContainer gameContainer) {
        gameContainer.exit();
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        grassMap = new TiledMap("res/map/mainmap.tmx");
        this.mapWidth = grassMap.getWidth();
        this.mapHeight = grassMap.getHeight();
        System.out.println(this.mapWidth+":"+this.mapHeight);
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
        if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
            exitGame(gc);
        }
        if (gc.getInput().isKeyDown(Input.KEY_LEFT) && this.x < 0) {
            this.x += delta / SPEED;
        }
        if (gc.getInput().isKeyDown((Input.KEY_RIGHT)) && this.x > - (this.mapWidth * this.grassMap.getTileWidth()) + gc.getWidth()) {
            this.x -= delta / SPEED;
        }
        if (gc.getInput().isKeyDown(Input.KEY_UP) && this.y < 0) {
            this.y += delta / SPEED;
        }
        if (gc.getInput().isKeyDown((Input.KEY_DOWN)) && this.y > - (this.mapHeight * this.grassMap.getTileHeight()) + gc.getHeight()) {
            this.y -= delta / SPEED;
        }
        //System.out.println(this.x+":"+this.y);
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        grassMap.render(
                (int) this.x,
                (int) this.y,
                mapX,
                mapY,
                mapX + 25,
                mapX +25);
    }

}
