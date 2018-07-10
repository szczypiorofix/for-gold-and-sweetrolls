package com.szczypiorofix.racoon.game.main;

import com.szczypiorofix.racoon.game.def.Level;
import com.szczypiorofix.racoon.game.objects.character.Player;
import org.newdawn.slick.*;
import org.newdawn.slick.tiled.TiledMap;


class GameManager {

    private TiledMap levelMap;
    private double x, y;
    private int mapX, mapY;

    private int tilesInWidth = 0, tilesInHeight = 0;
    private final static int SPEED = 2;

    private Player player;

    private LevelManager levelManager;
    private ObjectManager objectManager;
    private Camera camera;

    GameManager() {
        x = 0d;
        y = 0d;
        mapX = 0;
        mapY = 0;

        levelManager = new LevelManager();
    }


    void init(GameContainer gc) throws SlickException {
        objectManager = new ObjectManager(gc);
        levelManager.loadLevel(Level.WORLD_MAP);
        levelMap = levelManager.getLevelMap();
        objectManager.setLevel(levelMap);

        tilesInWidth = gc.getWidth() / levelMap.getTileWidth();
        tilesInHeight = gc.getHeight() / levelMap.getTileHeight();

        player = objectManager.getPlayer();
        //System.out.println(player.getX()+":"+player.getY());

        // PLAYER W CENTRUM EKRANU
        camera = new Camera(player.x, player.y, gc.getWidth(), gc.getHeight(), levelMap);
    }


    void handleInputs(GameContainer gc, int delta) {
        if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
            exitGame(gc);
        }

        if (gc.getInput().isKeyDown(Input.KEY_LEFT) || gc.getInput().isKeyDown(Input.KEY_A)) {
            //x += delta / SPEED;
            player.moveWest(delta / SPEED);
        }
        if (gc.getInput().isKeyDown((Input.KEY_RIGHT)) || gc.getInput().isKeyDown(Input.KEY_D)) {
            //x -= delta / SPEED;
            player.moveEast(delta / SPEED);
        }
        if (gc.getInput().isKeyDown(Input.KEY_UP) || gc.getInput().isKeyDown(Input.KEY_W)) {
            //y += delta / SPEED;
            player.moveNorth(delta / SPEED);
        }
        if (gc.getInput().isKeyDown((Input.KEY_DOWN)) || gc.getInput().isKeyDown(Input.KEY_S)) {
            //y -= delta / SPEED;
            player.moveSouth(delta / SPEED);
        }
    }

    void handleLogic(GameContainer gc, int delta) throws SlickException {
        if (x < 0) {
            mapX++;
            x = 32;
        }

        if (x > 32) {
            mapX--;
            x = 0;
        }

        if (y < 0) {
            mapY++;
            y = 32;
        }

        if (y > 32) {
            mapY--;
            y = 0;
        }

        player.setSx(gc.getWidth() / 2);
        player.setSy(gc.getHeight() / 2);

        objectManager.update(gc, delta);
        camera.update(player);

    }

    void render(GameContainer gc, Graphics g) throws SlickException {

        g.translate(- camera.getX(), - camera.getY());

        levelMap.render(
                (int) (x - levelMap.getTileWidth()),
                (int) (x - levelMap.getTileHeight()),
                mapX,
                mapY,
                tilesInWidth,
                tilesInHeight
                );

        objectManager.render(gc, g);

        g.translate(camera.getX(), camera.getY());

        player.render(gc, g);

        g.drawString("P: X:"+player.getX()+" Y:"+player.getY(), 10, 25);
        g.drawString("PS: X:"+player.getSx()+" Y:"+player.getSy(), 10, 35);
        g.drawString("C: X:"+camera.getX()+" Y:"+camera.getY(), 10, 45);
    }

    private void exitGame(GameContainer gameContainer) {
        gameContainer.exit();
    }

}
