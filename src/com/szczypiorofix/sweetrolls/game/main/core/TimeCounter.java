package com.szczypiorofix.sweetrolls.game.main.core;

import com.szczypiorofix.sweetrolls.game.enums.PlayerState;
import com.szczypiorofix.sweetrolls.game.objects.characters.Player;

public class TimeCounter {

    private Player player;

    private int dayCounter;
    private int hourCounter;
    private final int WORLD_MAP_HOUR_COUNTER = 1;
    private final int INNER_MAP_HOUR_COUNTER = 1;
    private final int DAY = 24;
    private float dayNightEffect = 0f;

    public TimeCounter(Player player) {
        this.player = player;
        dayCounter = 0;
        hourCounter = 0;
    }

    public void nextTurn() {
        if (player.getPlayerState() == PlayerState.MOVING_WORLD_MAP) {
            hourCounter += WORLD_MAP_HOUR_COUNTER;
        } else if (player.getPlayerState() == PlayerState.MOVING_INNER_LOCATION) {
            hourCounter += INNER_MAP_HOUR_COUNTER;
        }

        if (hourCounter >= DAY) {
            dayCounter++;
            hourCounter = Math.abs(DAY - hourCounter);
        }

        dayNightEffect = 0.5f - (float) (hourCounter) / (float) (DAY);

    }

    public float getDayNightEffect() {
        return dayNightEffect;
    }

    public int getDayCounter() {
        return dayCounter;
    }

    public void setDayCounter(int dayCounter) {
        this.dayCounter = dayCounter;
    }

    public int getHourCounter() {
        return hourCounter;
    }

    public void setHourCounter(int hourCounter) {
        this.hourCounter = hourCounter;
    }
}
