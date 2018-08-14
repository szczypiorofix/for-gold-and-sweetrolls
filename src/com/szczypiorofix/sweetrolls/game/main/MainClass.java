package com.szczypiorofix.sweetrolls.game.main;

import com.szczypiorofix.sweetrolls.game.main.core.ConfigManager;
import com.szczypiorofix.sweetrolls.game.main.core.Configuration;
import com.szczypiorofix.sweetrolls.game.main.states.ForGoldAndSweetrolls;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.ScalableGame;

import java.io.*;
import java.util.Random;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


/**
 *
 * ################################## FOR GOLD AND SWEETROLLS MAIN CLASS ################################################
 *
 */
public final class MainClass {

    public static final Random RANDOM = new Random();
    public static final String RES = "res/";

    private static boolean DEBUG_MODE;
    private final static Logger LOGGER = Logger.getLogger(MainClass.class.getName());
    private FileHandler fileHandler = null;
    private ConfigManager configManager;
    private Configuration config;


    /**
     * Main constructor of the class
     */
    private MainClass() {
        loggerSetup();
        checkConfig();
        applicationStart();
    }


    private void checkConfig() {
        configManager = new ConfigManager();
        config = configManager.loadSettings();
        System.out.println(config);
    }

    /**
     * Initialize and start Slick AppGameContainer
     */
    private void applicationStart() {

        /* Obtain display modes. */
        logging(false,  Level.INFO, "Pobieranie danych o DisplayModes");
        DisplayMode[] modes = null;
        try {
            modes = Display.getAvailableDisplayModes();
        } catch (Exception e) {
            e.printStackTrace();
            logging(true,  Level.WARNING, getStackTrace(e));
            System.exit(-1);
        }

        try {
            logging(false,  Level.INFO, "Uruchamianie instancji GameStatesContainer");
            ScalableGame fgas = new ScalableGame(new ForGoldAndSweetrolls("For Gold and Sweetrolls", modes, config), 800, 600, config.keepAspectRatio);

            AppGameContainer app = new AppGameContainer(fgas);
            String[] icons = {
                    "icon16x16.png",
                    "icon24x24.png",
                    "icon32x32.png",
                    "icon64x64.png",
                    "icon96x96.png",
                    "icon128x128.png"
            };
            app.setIcons(icons);
            app.setDisplayMode(800, 600, config.fullScreen);
            app.setTargetFrameRate(60);
            app.setVSync(config.vsync);
            app.setShowFPS(false);
            app.setUpdateOnlyWhenVisible(true);
            app.start();
        } catch (Exception e) {
            e.printStackTrace();
            logging(true,  Level.WARNING, getStackTrace(e));
        }
    }

    /**
     * Initialize logging system.
     */
    private void loggerSetup() {
        if (DEBUG_MODE) {
            try {
                fileHandler = new FileHandler("sweetrolls.log", false);
            } catch (IOException e1) {
                e1.printStackTrace();
                System.exit(-1);
            }

            LOGGER.setUseParentHandlers(true); // WYŚWIETLANIE LOGÓW W KONSOLI

            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.INFO);
            LOGGER.addHandler(fileHandler);
            logging(false, Level.INFO, "Uruchomienie gry w trybie DEBUG. Logger załadowany.");
        }
    }


    /**
     * Main logging method.
     * @param critical (boolean) - if true the application will stop.
     * @param level (Level) - level of logs.
     * @param msg (String) - Logging message.
     */
    public static void logging(boolean critical, Level level, String msg) {
        if (DEBUG_MODE) {
            LOGGER.log(level, msg);
            if (critical) {
                LOGGER.log(Level.WARNING, "Zamykanie programu z błędem.");
                System.exit(-1);
            }
        }
    }


    public static String getStackTrace(final Throwable throwable) {
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw, true);
        throwable.printStackTrace(pw);
        return sw.getBuffer().toString();
    }

    /**
     * public static void main - this method starts all the pretty stuff ;)
     * @param args String - params e.g. "-debug" to enable debigging mode.
     */
    public static void main(String[] args) {

        if (args.length > 0) {
            DEBUG_MODE = args[0].equalsIgnoreCase("-debug");
        }
        new MainClass();
    }

}
