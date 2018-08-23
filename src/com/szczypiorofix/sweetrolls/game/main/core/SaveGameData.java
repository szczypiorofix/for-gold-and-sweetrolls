package com.szczypiorofix.sweetrolls.game.main.core;

import com.szczypiorofix.sweetrolls.game.enums.LevelType;
import com.szczypiorofix.sweetrolls.game.objects.Statistics;

import java.io.Serializable;
import java.util.HashSet;

public class SaveGameData implements Serializable {

    private int playerWorldMapTileX, playerWorldMapTileY;
    private float playerX, playerY;
    private Statistics playerStatistics;
    private String currentWorldName;
    private HashSet<String> levels;
    private long timeCounterTimeStamp;
    private int timeCounterDayCounter;
    private int timeCounterHourCounter;
    private int timeCounterMinuteCounter;
    private LevelType levelType;

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

    public void setPlayerX(float playerX) {
        this.playerX = playerX;
    }

    public void setPlayerY(float playerY) {
        this.playerY = playerY;
    }

    public void setCurrentWorldName(String currentWorldName) {
        this.currentWorldName = currentWorldName;
    }

    public void setLevels(HashSet<String> levels) {
        this.levels = levels;
    }

    public void setPlayerStatistics(Statistics playerStatistics) {
        this.playerStatistics = playerStatistics;
    }

    public int getPlayerWorldMapTileX() {
        return playerWorldMapTileX;
    }

    public void setPlayerWorldMapTileX(int playerWorldMapTileX) {
        this.playerWorldMapTileX = playerWorldMapTileX;
    }

    public int getPlayerWorldMapTileY() {
        return playerWorldMapTileY;
    }

    public void setPlayerWorldMapTileY(int playerWorldMapTileY) {
        this.playerWorldMapTileY = playerWorldMapTileY;
    }

    public float getPlayerX() {
        return playerX;
    }

    public float getPlayerY() {
        return playerY;
    }

    public Statistics getPlayerStatistics() {
        return playerStatistics;
    }

    public String getCurrentWorldName() {
        return currentWorldName;
    }

    public HashSet<String> getLevels() {
        return levels;
    }

    public LevelType getLevelType() {
        return levelType;
    }

    public void setLevelType(LevelType levelType) {
        this.levelType = levelType;
    }
}
