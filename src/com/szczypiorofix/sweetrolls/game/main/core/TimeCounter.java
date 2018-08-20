package com.szczypiorofix.sweetrolls.game.main.core;

import com.szczypiorofix.sweetrolls.game.objects.characters.Player;
import org.newdawn.slick.Color;

import java.io.Serializable;

public class TimeCounter implements Serializable {

    private Player player;

    private int dayCounter;
    private int hourCounter;
    private int minuteCounter;
    private final int WORLD_MAP_MINUTE_COUNTER = 60;
    private final int INNER_MAP_MINUTE_COUNTER = 10;
    private final int HOURS_IN_DAY = 24;
    private final int MINUTES_IN_HOUR = 60;
    private Color dayNightEffect;
    private float darkness = 0f;
    private float timeC = 0f;

    public TimeCounter(Player player) {
        this.player = player;
        dayCounter = 0;
        hourCounter = 9;
        minuteCounter = 0;
        dayNightEffect = new Color(0.6f - darkness, 0f, 0.6f - darkness, darkness * 1.5f);
    }

    private void calculateDarkness() {
//        if (hourCounter > 7 && hourCounter < 19) {
//            darkness = 0f;
//        } else if (hourCounter >= 19 && hourCounter < 23 ) {
//            darkness += 0.1f;
//        }
//        else if (hourCounter > 4 && hourCounter <= 7 ) {
//            darkness -= 0.1f;
//        }
        if (hourCounter >= 7 && hourCounter < 19) { // 12h wieczór i noc
            darkness = 0f;
        } else {

            if (hourCounter >= 19 && hourCounter < 22) {
//                timeC = ( (
//                        (HOURS_IN_DAY - hourCounter)
//                                * MINUTES_IN_HOUR) + minuteCounter ) / 1000f;

                //timeC++;

                //timeC = ( ( (float) (HOURS_IN_DAY - hourCounter) * MINUTES_IN_HOUR ) + ( (float) minuteCounter / (float) MINUTES_IN_HOUR ) );// / 1000f;

            } else if (hourCounter > 3 && hourCounter < 7) {

//                timeC = hourCounter + ( (float) minuteCounter / (float) MINUTES_IN_HOUR);

                //timeC--;


            } else {
                timeC = 3f;
            }


            //darkness = 0.5f - Math.abs(timeC);


            System.out.println(timeC);
        }

        //dayNightEffect = new Color(0.6f - darkness, 0f, 0.6f - darkness, darkness * 1.5f);
    }

    public void turn() {
        if (player.getLevelState() == LevelMap.LevelType.WORLD_MAP) {
            minuteCounter += WORLD_MAP_MINUTE_COUNTER;
        } else if (player.getLevelState() == LevelMap.LevelType.INNER_MAP) {
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

        calculateDarkness();

    }

    public int getCurrentTurnTime() {
        if (player.getLevelState() == LevelMap.LevelType.WORLD_MAP) {
            return WORLD_MAP_MINUTE_COUNTER;
        } else {
            return INNER_MAP_MINUTE_COUNTER;
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

    public int getHourCounter() {
        return hourCounter;
    }

}
