package com.szczypiorofix.sweetrolls.game.objects.characters;

import com.szczypiorofix.sweetrolls.game.enums.ObjectType;
import com.szczypiorofix.sweetrolls.game.graphics.Textures;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

public class NPC extends Character {

    private Image npcImage;

    public NPC(String name, float x, float y, float width, float height, Image image) {
        super(name, x, y, width, height, ObjectType.NPC);
        if (image == null) {
            npcImage = Textures.getInstance().classm32.getSprite(0, 0);
        } else npcImage = image;
        statistics.maxHealth = 100;
    }

    public NPC(String name, float x, float y, float width, float height, Image image, int maxhealth) {
        super(name, x, y, width, height, ObjectType.NPC);
        if (image == null) {
            npcImage = Textures.getInstance().classm32.getSprite(0, 0);
        } else npcImage = image;
        statistics.maxHealth = maxhealth;
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta, float offsetX, float offsetY) {

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g, float offsetX, float offsetY) {
        npcImage.draw(- offsetX + x, - offsetY + y);
        if (hover) {
            g.drawString(name +" "+statistics.maxHealth, - offsetX + x, - offsetY + y - 15);
        }
    }

    @Override
    public void turn() {

    }
}
