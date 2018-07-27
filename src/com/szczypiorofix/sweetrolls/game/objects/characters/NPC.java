package com.szczypiorofix.sweetrolls.game.objects.characters;

import com.szczypiorofix.sweetrolls.game.enums.ObjectType;
import com.szczypiorofix.sweetrolls.game.main.core.GameManager;
import com.szczypiorofix.sweetrolls.game.main.graphics.Fonts;
import com.szczypiorofix.sweetrolls.game.main.graphics.Textures;
import com.szczypiorofix.sweetrolls.game.tilemap.Property;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class NPC extends Character {

    private Image npcImage;
    private Fonts smallHUDFont;
    private String[] talks = {
        "IT JUST WORKS !", "See that mountain? You can climb it!", "You can do whatever you want!", "Skyrim has infinite quests!"
    };

    public NPC(String name, float x, float y, float width, float height, Image image, ArrayList<Property> properties) {
        super(name, x, y, width, height, ObjectType.NPC, properties);
        if (image == null) {
            npcImage = Textures.getInstance().classm32.getSprite(0, 0);
        } else npcImage = image;
        statistics.maxHealth = getIntegerProperty("maxhealth");
        smallHUDFont = new Fonts("medusa-gothic.otf", Font.BOLD, 10f);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta, float offsetX, float offsetY) {
        hover = false;
        if (shortTalk) {
            if (shortTalkCounter < shortTalkCounerMax)
                shortTalkCounter++;
            else {
                shortTalk = false;
                shortTalkCounter = 0;
            }
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g, float offsetX, float offsetY) {
        npcImage.draw(- offsetX + x, - offsetY + y);
        if (hover) {
            g.drawString(name, - offsetX + x, - offsetY + y - 20);
        }
        if (shortTalk) {
            smallHUDFont.draw(talks[randomTalk], - offsetX + x - 25, - offsetY + y - 35);
        }
    }

    @Override
    public void turn() {

    }

    public boolean isShortTalk() {
        return shortTalk;
    }

    public void setShortTalk(boolean shortTalk) {
        if (shortTalk) {
            randomTalk = new Random().nextInt(talks.length);
        }
        System.out.println(talks[randomTalk]);
        this.shortTalk = shortTalk;
    }
}
