package com.szczypiorofix.sweetrolls.game.objects.characters;

import com.szczypiorofix.sweetrolls.game.enums.ObjectType;
import com.szczypiorofix.sweetrolls.game.objects.GameObject;
import com.szczypiorofix.sweetrolls.game.objects.Statistics;
import com.szczypiorofix.sweetrolls.game.tilemap.Property;

import java.util.ArrayList;


public abstract class Character extends GameObject {

    public Statistics statistics;
    public boolean shortTalk;
    public int randomTalk = 0;
    public int shortTalkCounter = 0;
    public int shortTalkCounerMax = 120;


    Character(String name, float x, float y, float width, float height, ObjectType objectType, ArrayList<Property> properties) {
        super(name, x, y, width, height, objectType, properties);
        statistics = new Statistics();
    }

}
