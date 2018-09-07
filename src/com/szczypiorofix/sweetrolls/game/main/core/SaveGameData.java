/*
 * Developed by szczypiorofix on 24.08.18 13:38.
 * Copyright (c) 2018. All rights reserved.
 *
 */

package com.szczypiorofix.sweetrolls.game.main.core;

import com.szczypiorofix.sweetrolls.game.enums.LevelType;
import com.szczypiorofix.sweetrolls.game.gui.ActionHistory;
import com.szczypiorofix.sweetrolls.game.objects.Statistics;
import com.szczypiorofix.sweetrolls.game.objects.characters.Player;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;

public class SaveGameData implements Serializable {


    private String currentWorldName;
    private HashMap<String, LevelMap> levels;
    private long timeCounterTimeStamp;
    private int timeCounterDayCounter;
    private int timeCounterHourCounter;
    private int timeCounterMinuteCounter;
    private ActionHistory actionHistory;
    private Player player;

    public SaveGameData() {}


    public long getTimeCounterTimeStamp() {
        return timeCounterTimeStamp;
    }

    public void setTimeCounterTimeStamp(long timeCounterTimeStamp) {
        this.timeCounterTimeStamp = timeCounterTimeStamp;
    }

    public int getTimeCounterDayCounter() {
        return timeCounterDayCounter;
    }

    public void setTimeCounterDayCounter(int timeCounterDayCounter) {
        this.timeCounterDayCounter = timeCounterDayCounter;
    }

    public int getTimeCounterHourCounter() {
        return timeCounterHourCounter;
    }

    public void setTimeCounterHourCounter(int timeCounterHourCounter) {
        this.timeCounterHourCounter = timeCounterHourCounter;
    }

    public int getTimeCounterMinuteCounter() {
        return timeCounterMinuteCounter;
    }

    public void setTimeCounterMinuteCounter(int timeCounterMinuteCounter) {
        this.timeCounterMinuteCounter = timeCounterMinuteCounter;
    }

    public void setCurrentWorldName(String currentWorldName) {
        this.currentWorldName = currentWorldName;
    }

    public String getCurrentWorldName() {
        return currentWorldName;
    }

    public ActionHistory getActionHistory() {
        return actionHistory;
    }

    public void setActionHistory(ActionHistory actionHistory) {
        this.actionHistory = actionHistory;
    }

    public HashMap<String, LevelMap> getLevels() {
        return levels;
    }

    public void setLevels(HashMap<String, LevelMap> levels) {
        this.levels = levels;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

}
