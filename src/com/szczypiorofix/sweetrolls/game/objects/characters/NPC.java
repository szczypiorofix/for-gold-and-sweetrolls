package com.szczypiorofix.sweetrolls.game.objects.characters;

import com.szczypiorofix.sweetrolls.game.enums.ObjectType;
import com.szczypiorofix.sweetrolls.game.graphics.Textures;
import com.szczypiorofix.sweetrolls.game.tilemap.Property;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;

public class NPC extends Character {

    private Image npcImage;

    public NPC(String name, float x, float y, float width, float height, Image image, ArrayList<Property> properties) {
        super(name, x, y, width, height, ObjectType.NPC, properties);
        if (image == null) {
            npcImage = Textures.getInstance().classm32.getSprite(0, 0);
        } else npcImage = image;
        statistics.maxHealth = getIntegerProperty("maxhealth");
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta, float offsetX, float offsetY) {

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g, float offsetX, float offsetY) {
        npcImage.draw(- offsetX + x, - offsetY + y);
        if (hover) {
            g.drawString(name +" "+statistics.maxHealth, - offsetX + x, - offsetY + y - 15);
            g.drawString("w: "+width +", h: "+height, - offsetX + x, - offsetY + y - 30);
            g.drawString("x: "+x +", y: "+y, - offsetX + x, - offsetY + y - 45);
        }
    }

    @Override
    public void turn() {

    }
}
