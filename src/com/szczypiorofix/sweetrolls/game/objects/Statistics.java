/*
 * Developed by szczypiorofix on 24.08.18 13:34.
 * Copyright (c) 2018. All rights reserved.
 *
 */

package com.szczypiorofix.sweetrolls.game.objects;


import com.szczypiorofix.sweetrolls.game.enums.CharacterRace;
import com.szczypiorofix.sweetrolls.game.enums.CharacterSex;

import java.io.Serializable;

public class Statistics implements Serializable {

    // PLAYER

    public int p_Gold;

    public String p_Name;
    public CharacterSex p_Sex;
    public CharacterRace p_Race;

    public int p_Stat_Strength;
    public int p_Stat_Dexterity;
    public int p_Stat_Constitution;
    public int p_Stat_Intelligence;

    public int p_Skill_Alchemy;
    public int p_Skill_Lockpicking;
    public int p_Skill_Speech;
    public int p_Skill_Archery;
    public int p_Skill_Survival;
    public int p_Skill_Sword;
    public int p_Skill_Shield;
    public int p_Skill_Armor;


    public int p_Health;
    public int p_MaxHealth;

    public int p_ArmorClass = 0;
    public int p_Damage = 1;

    public int p_Level;
    public int p_CurrentLevelBar;
    public int p_CurrentLevelMaxBar;

    public final static float P_MAX_FOOD = 20f;
    public float p_Food;
    public float p_FoodUsagePerHour;

    public final static float P_MAX_WATER = 20f;
    public float p_Water;
    public float p_WaterUsagePerHour;


    // WORLD

    public int w_DiscoveredPlaces;
    public int w_PickedUpItems;



}
