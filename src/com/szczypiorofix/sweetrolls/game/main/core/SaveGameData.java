/*
 * Developed by szczypiorofix on 09.09.18 00:04.
 * Copyright (c) 2018. All rights reserved.
 *
 */

package com.szczypiorofix.sweetrolls.game.main.core;

import com.szczypiorofix.sweetrolls.game.enums.LevelType;
import com.szczypiorofix.sweetrolls.game.gui.ActionHistory;
import com.szczypiorofix.sweetrolls.game.gui.Inventory;
import com.szczypiorofix.sweetrolls.game.objects.characters.Player;
import com.szczypiorofix.sweetrolls.game.tilemap.TileMap;

import java.io.Serializable;
import java.util.HashMap;

public class SaveGameData implements Serializable {


    private String currentMapName;
    private HashMap<String, LevelMap> levels;
    private ActionHistory actionHistory;
    private Player player;
    private TimeCounter timeCounter;
    private Inventory inventory;
    private LevelType currentLevelType;


    public SaveGameData() {}


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

    public String getCurrentMapName() {
        return currentMapName;
    }

    public void setCurrentMapName(String currentMapName) {
        this.currentMapName = currentMapName;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public TimeCounter getTimeCounter() {
        return timeCounter;
    }

    public void setTimeCounter(TimeCounter timeCounter) {
        this.timeCounter = timeCounter;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public LevelType getCurrentLevelType() {
        return currentLevelType;
    }

    public void setCurrentLevelType(LevelType currentLevelType) {
        this.currentLevelType = currentLevelType;
    }

}
