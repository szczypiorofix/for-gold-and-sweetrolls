/*
 * Developed by szczypiorofix on 24.08.18 13:37.
 * Copyright (c) 2018. All rights reserved.
 *
 */

package com.szczypiorofix.sweetrolls.game.main.core;

public class Configuration {

    public static String gameWidthName = "GAME_WIDTH";
    public static String gameHeightName = "GAME_HEIGHT";
    public static String refreshRateName = "REFRESH_RATE";
    public static String fullScreenName = "FULL_SCREEN";
    public static String keepAspectRatioName = "KEEP_ASPECT_RATIO";
    public static String vSyncName = "V_SYNC";
    public static String musicVolumeName = "MUSIC_VOLUME";
    public static String showFPSName = "SHOW_FPS";

    public int gameWidth;
    public int gameHeight;
    public int gameRefreshRate;
    public boolean fullScreen;
    public boolean keepAspectRatio;
    public boolean vsync;
    public boolean showFps;
    public float musicVolume;

    public Configuration() {}

    public Configuration getDefaultConfiguration() {

        Configuration defaultConfiguration = new Configuration();
        defaultConfiguration.fullScreen = false;
        defaultConfiguration.gameWidth = 800;
        defaultConfiguration.gameHeight = 600;
        defaultConfiguration.gameRefreshRate = 60;
        defaultConfiguration.keepAspectRatio = true;
        defaultConfiguration.vsync = true;
        defaultConfiguration.musicVolume = 1.0f;
        defaultConfiguration.showFps = true;

        return defaultConfiguration;
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "gameWidth=" + gameWidth +
                ", gameHeight=" + gameHeight +
                ", gameRefreshRate=" + gameRefreshRate +
                ", fullScreen=" + fullScreen +
                ", keepAspectRatio=" + keepAspectRatio +
                ", vsync=" + vsync +
                ", showFps=" + showFps +
                ", musicVolume=" + musicVolume +
                '}';
    }
}
