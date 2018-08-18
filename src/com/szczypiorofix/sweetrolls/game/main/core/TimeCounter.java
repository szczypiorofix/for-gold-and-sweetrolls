package com.szczypiorofix.sweetrolls.game.main.core;

import com.szczypiorofix.sweetrolls.game.enums.PlayerState;
import com.szczypiorofix.sweetrolls.game.objects.characters.Player;
import org.newdawn.slick.Color;

import java.io.Serializable;

public class TimeCounter implements Serializable {

    private Player player;

    private int dayCounter;
    private int hourCounter;
    private int minuteCounter;
    private final int WORLD_MAP_MINUTE_COUNTER = 30;
    private final int INNER_MAP_MINUTE_COUNTER = 1;
    private final int HOURS_IN_DAY = 24;
    private final int MINUTES_IN_HOUR = 60;
    private Color dayNightEffect;
    private float darkness = 0f;

    public TimeCounter(Player player) {
        this.player = player;
        dayCounter = 0;
        hourCounter = 9;
        minuteCounter = 0;
        dayNightEffect = new Color(0.6f - darkness, 0f, 0.6f - darkness, darkness * 1.5f);
    }

    private void calculateDarkness() {
        if (hourCounter > 7 && hourCounter < 19) {
            darkness = 0f;
        } else if (hourCounter >= 19 && hourCounter < 23 ) {
            darkness += 0.1f;
        }
        else if (hourCounter > 4 && hourCounter <= 7 ) {
            darkness -= 0.1f;
        }
        dayNightEffect = new Color(0.6f - darkness, 0f, 0.6f - darkness, darkness * 1.5f);
    }

    public void nextTurn() {
        if (player.getPlayerState() == PlayerState.MOVING_WORLD_MAP) {
            minuteCounter += WORLD_MAP_MINUTE_COUNTER;
        } else if (player.getPlayerState() == PlayerState.MOVING_INNER_LOCATION) {
            minuteCounter += INNER_MAP_MINUTE_COUNTER;
        }

        if (minuteCounter >= MINUTES_IN_HOUR) {
            hourCounter++;
            minuteCounter = Math.abs(MINUTES_IN_HOUR - minuteCounter);
            calculateDarkness();
            player.calculateSurvival();
        }

        if (hourCounter >= HOURS_IN_DAY) {
            dayCounter++;
            hourCounter = Math.abs(HOURS_IN_DAY - hourCounter);
            calculateDarkness();
            player.calculateSurvival();
        }

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

    public void setMinuteCounter(int minuteCounter) {
        this.minuteCounter = minuteCounter;
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
