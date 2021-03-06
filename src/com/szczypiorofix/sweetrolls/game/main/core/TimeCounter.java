/*
 * Developed by szczypiorofix on 09.09.18 00:04.
 * Copyright (c) 2018. All rights reserved.
 *
 */

package com.szczypiorofix.sweetrolls.game.main.core;

import com.szczypiorofix.sweetrolls.game.enums.LevelType;
import com.szczypiorofix.sweetrolls.game.objects.characters.Player;
import org.newdawn.slick.Color;

import java.io.Serializable;
import java.math.BigDecimal;

public class TimeCounter implements Serializable {

    private Player player;

    private int dayCounter;
    private int hourCounter;
    private int minuteCounter;
    private final static int WORLD_MAP_MINUTE_COUNTER = 60;
    private final static int INNER_MAP_MINUTE_COUNTER = 10;
    private final static int HOURS_IN_DAY = 24;
    private final static int MINUTES_IN_HOUR = 60;
    private Color dayNightEffect;
    private float darkness = 0f;
    private float timeC = 0f;
    private long timeStamp;

    public TimeCounter(Player player) {
        this.player = player;
        dayCounter = 0;
        hourCounter = 9;
        minuteCounter = 0;
        timeStamp = 0L;
        dayNightEffect = new Color(0.6f - darkness, 0f, 0.6f - darkness, darkness * 1.5f);
    }

    private void calculateDarkness() {
        if (hourCounter > 7 && hourCounter < 19) {
            darkness = 0f;
        } else if (hourCounter >= 19 && hourCounter < 23 ) {
            darkness += 0.11f;
        } else if (hourCounter > 4 && hourCounter <= 7 ) {
            darkness -= 0.11f;
        }

        timeC = TimeCounter.round((hourCounter + (minuteCounter / (float) MINUTES_IN_HOUR) ) / 1000f, 4);


        System.out.println(timeC);


        dayNightEffect = new Color(1f - darkness, 0f, 1f - darkness, darkness);
    }

    private static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

    public void turn() {
        if (player.getLevelState() == LevelType.WORLD_MAP) {
            minuteCounter += WORLD_MAP_MINUTE_COUNTER;
        } else if (player.getLevelState() == LevelType.INNER_MAP || player.getLevelState() == LevelType.INNER_RANDOM_MAP) {
            minuteCounter += INNER_MAP_MINUTE_COUNTER;
        }

        if (minuteCounter >= MINUTES_IN_HOUR) {
            hourCounter++;
            minuteCounter = Math.abs(MINUTES_IN_HOUR - minuteCounter);
            player.calculateSurvival();
        }

        if (hourCounter >= HOURS_IN_DAY) {
            dayCounter++;
            hourCounter = Math.abs(HOURS_IN_DAY - hourCounter);
            player.calculateSurvival();
        }

        //calculateDarkness();
        timeStamp += getCurrentTurnTime();
    }

    public int getCurrentTurnTime() {
        if (player.getLevelState() == LevelType.WORLD_MAP) {
            return WORLD_MAP_MINUTE_COUNTER;
        } else {
            return INNER_MAP_MINUTE_COUNTER;
        }
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public Color getDayNightEffect() {
        return dayNightEffect;
    }

    public int getDayCounter() {
        return dayCounter;
    }

    public int getMinuteCounter() {
        return minuteCounter;
    }

    public int getHourCounter() {
        return hourCounter;
    }

    public void setDayCounter(int dayCounter) {
        this.dayCounter = dayCounter;
    }

    public void setHourCounter(int hourCounter) {
        this.hourCounter = hourCounter;
    }

    public void setMinuteCounter(int minuteCounter) {
        this.minuteCounter = minuteCounter;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
