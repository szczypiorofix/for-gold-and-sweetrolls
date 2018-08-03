package com.szczypiorofix.sweetrolls.game.objects.characters;

import com.szczypiorofix.sweetrolls.game.dialogs.Dialogue;
import com.szczypiorofix.sweetrolls.game.dialogs.npc.DialoguePete;
import com.szczypiorofix.sweetrolls.game.dialogs.npc.DialogueTodd;
import com.szczypiorofix.sweetrolls.game.enums.ObjectType;
import com.szczypiorofix.sweetrolls.game.main.fonts.BitMapFont;
import com.szczypiorofix.sweetrolls.game.main.fonts.FontParser;
import com.szczypiorofix.sweetrolls.game.main.graphics.Textures;
import com.szczypiorofix.sweetrolls.game.tilemap.Property;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.Random;

public class NPC extends Character {

    private BitMapFont font;
    private Image npcImage;
    private Dialogue dialogue;
    private String[] talks = {
        "IT JUST WORKS !", "See that mountain? You can climb it!", "You can do whatever you want!", "Skyrim has infinite quests!"
    };
    private boolean playerKnow;
    private String displayName;


    public NPC(String name, int x, int y, int width, int height, Image image, ArrayList<Property> properties) {
        super(name, x, y, width, height, ObjectType.NPC, properties);
        if (image == null) {
            npcImage = Textures.getInstance().classm32.getSprite(0, 0);
        } else npcImage = image;
        statistics.maxHealth = getIntegerProperty("maxhealth");
        font = FontParser.getFont("Immortal NPC Bitmap Font", "immortal-bitmap.xml", "immortal-bitmap.png");
        font.setSize(5.5f);

        dialogue = new Dialogue();

        if (name.equals("Todd Howard")) {
            dialogue = new DialogueTodd(this);
        }

        if (name.equals("Pete Hines")) {
            dialogue = new DialoguePete(this);
        }

        displayName = getStringProperty("commonname");
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta, int offsetX, int offsetY) {
        hover = false;

        if (playerKnow) displayName = name;

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
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g, int offsetX, int offsetY) {
        npcImage.draw(- offsetX + x, - offsetY + y);
        if (hover) {
            font.draw(displayName, - offsetX + x, - offsetY + y - 22);
        }
        if (shortTalk) {
            font.draw(talks[randomTalk], - offsetX + x - 25, - offsetY + y - 40);
        }
    }

    @Override
    public void turn() {

    }

    public boolean isPlayerKnow() {
        return playerKnow;
    }

    public void setPlayerKnow(boolean playerKnow) {
        this.playerKnow = playerKnow;
    }


    public String[] getTalks() {
        return talks;
    }

    public boolean isShortTalk() {
        return shortTalk;
    }

    public void setShortTalk(boolean shortTalk) {
        if (shortTalk) {
            randomTalk = new Random().nextInt(talks.length);
        }
        this.shortTalk = shortTalk;
    }

    public Dialogue getDialogue() {
        return dialogue;
    }
}
