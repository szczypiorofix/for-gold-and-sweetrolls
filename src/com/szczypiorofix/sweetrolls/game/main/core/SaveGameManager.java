/*
 * Developed by szczypiorofix on 12.09.18 08:16.
 * Copyright (c) 2018. All rights reserved.
 *
 */

package com.szczypiorofix.sweetrolls.game.main.core;

import com.szczypiorofix.sweetrolls.game.main.states.FGASGame;

import java.io.*;

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

    public static boolean saveGame(String saveGameFileName, FGASGame game) {

        boolean successfull;
        SaveGameData saveGameData = new SaveGameData();

        saveGameData.setActionHistory(game.getActionHistory());
        saveGameData.setPlayer(game.getPlayer());
        saveGameData.setCurrentMapName(game.getCurrentLevelName());
        saveGameData.setTimeCounter(game.getTimeCounter());
        saveGameData.setInventory(game.getInventory());
        saveGameData.setCurrentMapName(game.getPlayer().getCurrentLevelName());
        saveGameData.setLevels(game.getObjectManager().getLevelMaps());
        saveGameData.setCurrentLevelType(game.getObjectManager().getCurrentLevelMap().getLevelType());

        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            fos = new FileOutputStream(new File(saveGameFileName));
            oos = new ObjectOutputStream(fos);
            oos.writeObject(saveGameData);
            successfull = true;
        } catch (FileNotFoundException e) {
            System.out.println("File not found !");
            e.printStackTrace();
            successfull = false;
        } catch (IOException e) {
            System.out.println("Error initializing stream !");
            e.printStackTrace();
            successfull = false;
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
        return successfull;
    }

}
