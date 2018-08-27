/*
 * Developed by szczypiorofix on 24.08.18 13:38.
 * Copyright (c) 2018. All rights reserved.
 *
 */

package com.szczypiorofix.sweetrolls.game.main.core;

import com.szczypiorofix.sweetrolls.game.main.states.FGAS_Game;
import com.szczypiorofix.sweetrolls.game.tilemap.TileMap;

import java.io.*;
import java.util.HashSet;
import java.util.Map;

public class SaveGameManager {


    private SaveGameManager() {}

    public static SaveGameData load(String saveGameFileName) {
        SaveGameData sgd = null;
        try {

            FileInputStream fi = new FileInputStream(new File(saveGameFileName));
            ObjectInputStream oi = new ObjectInputStream(fi);

            sgd = (SaveGameData) oi.readObject();

            System.out.println("Wczytano świat : " + sgd.getCurrentWorldName());

            oi.close();
            fi.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error initializing stream");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return sgd;
    }

    public static void saveGame(String saveGameFileName, FGAS_Game game) {

        SaveGameData saveGameData = new SaveGameData();

        // PLAYER
        saveGameData.setPlayerX(game.getPlayer().getX());
        saveGameData.setPlayerY(game.getPlayer().getY());
        saveGameData.setPlayerWorldMapTileX(game.getPlayer().getWorldMapTileX());
        saveGameData.setPlayerWorldMapTileY(game.getPlayer().getWorldMapTileY());
        saveGameData.setPlayerStatistics(game.getPlayer().statistics);
        saveGameData.setTimeCounterDayCounter(game.getTimeCounter().getDayCounter());
        saveGameData.setTimeCounterHourCounter(game.getTimeCounter().getHourCounter());
        saveGameData.setTimeCounterMinuteCounter(game.getTimeCounter().getMinuteCounter());
        saveGameData.setTimeCounterTimeStamp(game.getTimeCounter().getTimeStamp());
        saveGameData.setLevelType(game.getObjectManager().getCurrentMap().getLevelType());
        saveGameData.setActionHistory(game.getActionHistory());


        // LEVELS
        saveGameData.setCurrentWorldName(game.getCurrentLevelName());
        HashSet<String> levels = new HashSet<>();

        for (Map.Entry<String, TileMap> map : game.getLevels().entrySet()) {
            levels.add(map.getKey());
        }
        saveGameData.setLevels(levels);

        try {
            FileOutputStream f = new FileOutputStream(new File(saveGameFileName));
            ObjectOutputStream o = new ObjectOutputStream(f);

            // Write objects to file
            o.writeObject(saveGameData);

            o.close();
            f.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        }

        System.out.println("Save complete...");

    }

}
