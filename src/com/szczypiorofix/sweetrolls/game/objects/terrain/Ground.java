package com.szczypiorofix.sweetrolls.game.objects.terrain;

import com.szczypiorofix.sweetrolls.game.enums.ResourceType;
import com.szczypiorofix.sweetrolls.game.main.core.TerrainResources;
import com.szczypiorofix.sweetrolls.game.objects.GameObject;
import com.szczypiorofix.sweetrolls.game.tilemap.CollisionObject;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;


public class Ground extends GameObject {

    private Image image;
    private Color highlighColor = new Color(1f, 1f, 1f, 0.65f);
    private TerrainResources terrainResources;

    public Ground(String name, float x, float y, float width, float height, Image image, boolean visible, CollisionObject collisionObject) {
        super(name, x, y, width, height);
        this.image = image;
        this.visible = visible;
        //this.image.setFilter(Image.FILTER_NEAREST);

        setCollisions(collisionObject);
        terrainResources = new TerrainResources(objectType);

    }


    @Override
    public void update(int delta, float offsetX, float offsetY) {
        hover = false;
    }

    @Override
    public void render(Graphics g, float offsetX, float offsetY) {
        if (visible) {
            if (hover) {
                g.drawImage(image, - offsetX + x, - offsetY + y, highlighColor);
            } else
                g.drawImage(image, - offsetX + x, - offsetY + y);
            //g.drawString(terrainResources.getResources().get(ResourceType.WATER).getAmount()+"", - offsetX + x, - offsetY + y + 4);
        }
    }

    @Override
    public void turn() {
        terrainResources.getResources().get(ResourceType.WATER).round();
    }


    public Color getMiniMapColor() {
        Color c;
        switch (objectType) {
            case FOREST: {
                c = new Color(26, 66, 23);
                break;
            }
            case WATER: {
                c = Color.blue;
                break;
            }
            case PLAINS: {
                c = new Color(22, 124, 15);
                break;
            }
            case MOUNTAINS: {
                c = Color.gray;
                break;
            }
            case SWAMP: {
                c = new Color(132, 123, 71);
                break;
            }
            case SWAMP_TREE: {
                c = new Color(96, 92, 64);
                break;
            }
            case DESERT: {
                c = Color.orange;
                break;
            }
            case DESERT_TREE: {
                c = new Color(155, 138, 45);
                break;
            }
            default: {
                c = Color.black;
                break;
            }
        }
        return c;
    }

    public TerrainResources getTerrainResources() {
        return terrainResources;
    }

}
