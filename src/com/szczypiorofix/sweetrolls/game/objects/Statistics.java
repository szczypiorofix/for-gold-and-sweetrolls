package com.szczypiorofix.sweetrolls.game.objects;

import java.io.Serializable;

public class Statistics implements Serializable {

    public int gold;

    public int strength;
    public int dexterity;
    public int constitution;
    public int intelligence;

    public int health;
    public int maxHealth;

    public int armorClass = 0;
    public int damage = 1;
    public float chanceToHit = 0.5f; // 0 - 1

    public int level;
    public int currentLevelBar;
    public int currentLevelMaxBar;

    public float foodRations;
    public float foodUsagePerHour;

    public float water;
    public float watetUsagePerHour;



}
