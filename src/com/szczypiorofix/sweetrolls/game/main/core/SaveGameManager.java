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
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try {

            fis = new FileInputStream(new File(saveGameFileName));
            ois = new ObjectInputStream(fis);

            sgd = (SaveGameData) ois.readObject();

            //System.out.println("Wczytano świat : " + sgd.getCurrentWorldName());

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error initializing stream");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fis != null) fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            fos = new FileOutputStream(new File(saveGameFileName));
            oos = new ObjectOutputStream(fos);
            oos.writeObject(saveGameData);
        } catch (FileNotFoundException e) {
            System.out.println("File not found !");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error initializing stream !");
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fos != null) fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //System.out.println("Save complete...");

    }

}
