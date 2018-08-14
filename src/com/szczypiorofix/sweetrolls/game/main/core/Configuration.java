package com.szczypiorofix.sweetrolls.game.main.core;

public class Configuration {

    public static String gameWidthName = "GAME_WIDTH";
    public static String gameHeightName = "GAME_HEIGHT";
    public static String refreshRateName = "REFRESH_RATE";
    public static String fullScreenName = "FULL_SCREEN";
    public static String keepAspectRatioame = "KEEP_ASPECT_RATIO";
    public static String vSyncName = "V_SYNC";

    public int gameWidth;
    public int gameHeight;
    public int gameRefreshRate;
    public boolean fullScreen;
    public boolean keepAspectRatio;
    public boolean vsync;

    public Configuration() {}

    public Configuration getDefaultConfiguration() {
        Configuration defaultConfiguration = new Configuration();
        defaultConfiguration.fullScreen = false;
        defaultConfiguration.gameWidth = 800;
        defaultConfiguration.gameHeight = 600;
        defaultConfiguration.gameRefreshRate = 60;
        defaultConfiguration.keepAspectRatio = true;
        defaultConfiguration.vsync = true;

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
                '}';
    }
}