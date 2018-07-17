package com.szczypiorofix.sweetrolls.game.main;

import com.szczypiorofix.sweetrolls.game.objects.character.Player;
import com.szczypiorofix.sweetrolls.game.tilemap.TileMap;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.tiled.TiledMap;

public class Camera {

    private float x, y;
    private float width, height;
    private TileMap tiledMap;
    private int tileX = 0, tileY = 0;


    public Camera(float x, float y, float width, float height, TileMap tiledMap) {

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.tiledMap = tiledMap;

    }


    public void update(Player player) {

        //if (x > 150) {
            x = - player.getX() + (width / 2);
        //}

        //if (y > 150) {
            y = - player.getY() + (height / 2);
        //}

    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getTileX() {
        return tileX;
    }

    public void setTileX(int tileX) {
        this.tileX = tileX;
    }

    public int getTileY() {
        return tileY;
    }

    public void setTileY(int tileY) {
        this.tileY = tileY;
    }
}

