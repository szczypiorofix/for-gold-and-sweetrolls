/*
 * Developed by szczypiorofix on 09.09.18 00:04.
 * Copyright (c) 2018. All rights reserved.
 *
 */

package com.szczypiorofix.sweetrolls.game.objects.characters;

import com.szczypiorofix.sweetrolls.game.dialogs.Dialogue;
import com.szczypiorofix.sweetrolls.game.dialogs.DialoguesXMLParser;
import com.szczypiorofix.sweetrolls.game.enums.CharacterType;
import com.szczypiorofix.sweetrolls.game.enums.ObjectType;
import com.szczypiorofix.sweetrolls.game.main.fonts.BitMapFont;
import com.szczypiorofix.sweetrolls.game.main.fonts.FontParser;
import com.szczypiorofix.sweetrolls.game.main.graphics.Textures;
import com.szczypiorofix.sweetrolls.game.objects.Statistics;
import com.szczypiorofix.sweetrolls.game.tilemap.CollisionObject;
import com.szczypiorofix.sweetrolls.game.tilemap.Property;
import com.szczypiorofix.sweetrolls.game.tilemap.TileSet;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class NPC extends Character implements Serializable {

    private transient BitMapFont font;
    private transient Image npcImage;
    private CharacterType characterType;
    private transient Dialogue dialogue;
    private String[] talks = {
        "IT JUST WORKS !"
    };
    private String[] talksGuard = {
      "Uważaj na siebie!", "Patrz w niebo.", "Mamy cię na oku.", "Mam nadzieję, że nie szukasz kłopotów.", "Rozkaz, rozkaz!", "Tylko nic nie ukradnij!"
    };
    private String displayName;
    private boolean londTalk;
    private Statistics statistics = new Statistics();


    public NPC(String name, float x, float y, float width, float height, TileSet tileSet, int gid, ArrayList<Property> properties) {
        super(name, x, y, width, height, ObjectType.NPC, properties);

        npcImage = tileSet.getImageSprite(gid);

        font = FontParser.getFont();

        characterType = estimateCharacterType(getStringProperty("type"));
        statistics.p_MaxHealth = getIntegerProperty("maxhealth");

        londTalk = getBooleanProperty("longTalk");

        if (londTalk) {
            dialogue = DialoguesXMLParser.parseDialogueXML(getStringProperty("dialogueFileName"), false);
        }

        displayName = getStringProperty("name");

        setCollisions(new CollisionObject(
                1,
                "npc",
                0,
                0,
                32,
                32
        ));
    }

    public NPC(String name, float x, float y, float width, float height, Image image, ArrayList<Property> properties) {
        super(name, x, y, width, height, ObjectType.NPC, properties);
        if (image == null) {
            npcImage = Textures.getInstance().classm32.getSprite(0, 0);
        } else npcImage = image;

        font = FontParser.getFont();

        characterType = estimateCharacterType(getStringProperty("type"));
        statistics.p_MaxHealth = getIntegerProperty("maxhealth");

        londTalk = getBooleanProperty("longTalk");

        if (londTalk) {
            dialogue = DialoguesXMLParser.parseDialogueXML(getStringProperty("dialogueFileName"), false);
        }

        displayName = getStringProperty("name");

        setCollisions(new CollisionObject(
                1,
                "npc",
                0,
                0,
                32,
                32
        ));
    }

    private CharacterType estimateCharacterType(String type) {
        CharacterType ct = CharacterType.STRANGER;
        if (type.equalsIgnoreCase("guard")) ct = CharacterType.GUARD;
        else if (type.equalsIgnoreCase("villager")) ct = CharacterType.VILLAGER;
        else if (type.equalsIgnoreCase("townsman")) ct = CharacterType.TOWNSMAN;
        return ct;
    }

    @Override
    public void update(int delta, float offsetX, float offsetY) {
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
    public void render(Graphics g, float offsetX, float offsetY) {
        npcImage.draw(- offsetX + x, - offsetY + y);
        if (hover) {
            font.draw(displayName, - offsetX + x, - offsetY + y - 22);
        }
        if (shortTalk) {
            font.draw(talksGuard[randomTalk], - offsetX + x - 25, - offsetY + y - 36);
        }
    }


    public String[] getTalks() {
        return talks;
    }

    public boolean isShortTalk() {
        return shortTalk;
    }

    public void setShortTalk(boolean shortTalk) {
        if (shortTalk) {
            randomTalk = new Random().nextInt(talksGuard.length);
        }
        this.shortTalk = shortTalk;
    }

    public boolean isLondTalk() {
        return londTalk;
    }

    public Dialogue getDialogue() {
        return dialogue;
    }
}
