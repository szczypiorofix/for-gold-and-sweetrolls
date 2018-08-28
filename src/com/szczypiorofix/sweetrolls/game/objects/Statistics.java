/*
 * Developed by szczypiorofix on 24.08.18 13:34.
 * Copyright (c) 2018. All rights reserved.
 *
 */

package com.szczypiorofix.sweetrolls.game.objects;


import java.io.Serializable;

public class Statistics implements Serializable {

    // PLAYER

    public int P_Gold;

    public int P_Strength;
    public int P_Dexterity;
    public int P_Constitution;
    public int P_Intelligence;

    public int P_Health;
    public int P_MaxHealth;

    public int P_ArmorClass = 0;
    public int P_Damage = 1;

    public int P_Level;
    public int P_CurrentLevelBar;
    public int P_CurrentLevelMaxBar;

    public final static float P_MAX_FOOD = 20f;
    public float P_Food;
    public float P_FoodUsagePerHour;

    public final static float P_MAX_WATER = 20f;
    public float P_Water;
    public float P_WaterUsagePerHour;


    // WORLD

    public int W_DiscoveredPlaces;
    public int W_PickedUpItems;



}
