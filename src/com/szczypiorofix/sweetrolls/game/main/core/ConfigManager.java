package com.szczypiorofix.sweetrolls.game.main.core;

import com.szczypiorofix.sweetrolls.game.main.MainClass;

import java.io.*;
import java.util.Properties;
import java.util.logging.Level;

public class ConfigManager {

    private File configFile;
    public boolean defaultConfiguration = true;
    private Properties prop = new Properties();
    private InputStream propStream;
    private OutputStream outputStream;
    private Configuration cfg;

    public ConfigManager() {
        cfg = new Configuration().getDefaultConfiguration();
        System.out.println("Otwieranie pliku");
        try {
            configFile = new File("fgas.cfg");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean saveToFile(Configuration cfg) {
        System.out.println("Zapisywanie do pliku...");
        try {
            outputStream = new FileOutputStream(configFile);
            prop.setProperty(Configuration.gameWidthName, String.valueOf(cfg.gameWidth));
            prop.setProperty(Configuration.gameHeightName, String.valueOf(cfg.gameHeight));
            prop.setProperty(Configuration.refreshRateName, String.valueOf(cfg.gameRefreshRate));
            prop.setProperty(Configuration.keepAspectRatioame, String.valueOf(cfg.keepAspectRatio));
            prop.setProperty(Configuration.fullScreenName, String.valueOf(cfg.fullScreen));
            prop.setProperty(Configuration.vSyncName, String.valueOf(cfg.vsync));
            prop.store(outputStream, null);
        } catch (IOException io) {
            io.printStackTrace();
            return false;
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    public Configuration loadSettings() {
        if(configFile.exists() && !configFile.isDirectory()) {
            defaultConfiguration = false;
            System.out.println("Próba odczytu danych z pliku");
            try {
                propStream = new FileInputStream(configFile);
                prop.load(propStream);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            } finally {
                if (propStream != null) {
                    try {
                        propStream.close();
                    } catch (IOException e) {
                        MainClass.logging(false, Level.WARNING,  "Plik ustawień gry został‚ nieprawidłowo zamknięty!");
                    }
                }
            }
            cfg.gameWidth = Integer.parseInt(prop.getProperty(Configuration.gameWidthName));
            cfg.gameHeight = Integer.parseInt(prop.getProperty(Configuration.gameHeightName));
            cfg.gameRefreshRate = Integer.parseInt(prop.getProperty(Configuration.refreshRateName));
            cfg.fullScreen = Boolean.parseBoolean(prop.getProperty(Configuration.fullScreenName));
            cfg.vsync = Boolean.parseBoolean(prop.getProperty(Configuration.vSyncName));
            cfg.keepAspectRatio = Boolean.parseBoolean(prop.getProperty(Configuration.keepAspectRatioame));

        } else {
            System.out.println("Plik nie istnieje - tworzenie pliku z domyślnymi wartościami.");
            if (!saveToFile(cfg)) cfg = cfg.getDefaultConfiguration();
        }
        return cfg;
    }
}
