package com.szczypiorofix.racoon.game.main;


import org.newdawn.slick.*;
import org.newdawn.slick.tiled.TiledMap;

public class RacoonGame extends BasicGame {

    private TiledMap grassMap;
    private Float x, y;
    private final Integer SPEED = 2;
    private Integer mapWidth = 0, mapHeight = 0;

    RacoonGame(String title) {
        super(title);
        this.x = 0f;
        this.y = 0f;
    }

    private void exitGame(GameContainer gameContainer) {
        gameContainer.exit();
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        grassMap = new TiledMap("res/map/mainmap.tmx");
        this.mapWidth = grassMap.getWidth();
        this.mapHeight = grassMap.getHeight();
        System.out.println(this.mapWidth+":"+this.mapHeight);
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        Input input = gameContainer.getInput();
        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            exitGame(gameContainer);
        }
        if (input.isKeyDown(Input.KEY_LEFT) && this.x < 0) {
            this.x += SPEED;
        }
        if (input.isKeyDown((Input.KEY_RIGHT)) && this.x > - (this.mapWidth * this.grassMap.getTileWidth()) + gameContainer.getWidth()) {
            this.x -= SPEED;
        }
        if (input.isKeyDown(Input.KEY_UP) && this.y < 0) {
            this.y += SPEED;
        }
        if (input.isKeyDown((Input.KEY_DOWN)) && this.y > - (this.mapHeight * this.grassMap.getTileHeight()) + gameContainer.getHeight()) {
            this.y -= SPEED;
        }
        //System.out.println(this.x+":"+this.y);
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        grassMap.render( Math.round(this.x), Math.round(this.y));
    }

}
