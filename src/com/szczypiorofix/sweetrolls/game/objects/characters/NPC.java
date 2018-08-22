package com.szczypiorofix.sweetrolls.game.objects.characters;

import com.szczypiorofix.sweetrolls.game.dialogs.Dialogue;
import com.szczypiorofix.sweetrolls.game.dialogs.npc.DialoguePete;
import com.szczypiorofix.sweetrolls.game.dialogs.npc.DialogueTodd;
import com.szczypiorofix.sweetrolls.game.enums.CharacterType;
import com.szczypiorofix.sweetrolls.game.enums.ObjectType;
import com.szczypiorofix.sweetrolls.game.main.fonts.BitMapFont;
import com.szczypiorofix.sweetrolls.game.main.fonts.FontParser;
import com.szczypiorofix.sweetrolls.game.main.graphics.Textures;
import com.szczypiorofix.sweetrolls.game.objects.Statistics;
import com.szczypiorofix.sweetrolls.game.tilemap.CollisionObject;
import com.szczypiorofix.sweetrolls.game.tilemap.Property;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import java.util.ArrayList;
import java.util.Random;

public class NPC extends Character {

    private BitMapFont font;
    private Image npcImage;
    private CharacterType characterType;
    private Dialogue dialogue;
    private String[] talks = {
        "IT JUST WORKS !"
    };
    private String[] talksGuard = {
      "Uważaj na siebie!", "Patrz w niebo.", "Mamy cię na oku.", "Mam nadzieję, że nie szukasz kłopotów.", "Rozkaz, rozkaz!", "Tylko nic nie ukradnij!"
    };
    private boolean playerKnow;
    private String displayName;
    private boolean londTalk;
    private Statistics statistics = new Statistics();



    public NPC(String name, float x, float y, float width, float height, Image image, ArrayList<Property> properties) {
        super(name, x, y, width, height, ObjectType.NPC, properties);
        if (image == null) {
            npcImage = Textures.getInstance().classm32.getSprite(0, 0);
        } else npcImage = image;

        font = FontParser.getFont("Immortal NPC Bitmap Font", "immortal-bitmap.xml", "immortal-bitmap.png");
        font.setSize(5.5f);

        characterType = estimateCharacterType(getStringProperty("type"));
        statistics.maxHealth = getIntegerProperty("maxhealth");

        dialogue = new Dialogue();

        if (name.equals("Todd Howard")) {
            dialogue = new DialogueTodd(this);
        }

        if (name.equals("Pete Hines")) {
            dialogue = new DialoguePete(this);
        }

        londTalk = getBooleanProperty("longTalk");

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
        //hover = false;

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
    public void render(Graphics g, float offsetX, float offsetY) {
        npcImage.draw(- offsetX + x, - offsetY + y);
        if (hover) {
            font.draw(displayName, - offsetX + x, - offsetY + y - 22);
        }
        if (shortTalk) {
            font.draw(talksGuard[randomTalk], - offsetX + x - 25, - offsetY + y - 36);
        }
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
            randomTalk = new Random().nextInt(talksGuard.length);
        }
        this.shortTalk = shortTalk;
    }

    public Dialogue getDialogue() {
        return dialogue;
    }

    public boolean isLondTalk() {
        return londTalk;
    }
}
