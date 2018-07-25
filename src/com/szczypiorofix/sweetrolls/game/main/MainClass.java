package com.szczypiorofix.sweetrolls.game.main;

import com.szczypiorofix.sweetrolls.game.main.core.ForGoldAndSweetrolls;
import com.szczypiorofix.sweetrolls.game.main.states.GameStatesContainer;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import java.io.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public final class MainClass {

    public static final String RES = "res/";

    public static final int MAINMENU = 1;
    public static final int GAME = 2;
    public static final int EXIT = 0;

    private static boolean DEBUG_MODE;
    private final static Logger LOGGER = Logger.getLogger(MainClass.class.getName());
    private FileHandler fileHandler = null;


    private MainClass() {
        loggerSetup();
        applicationStart();
    }

    private void applicationStart() {
        DisplayMode[] modes = null;
        try {
            modes = Display.getAvailableDisplayModes();
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        try {
            GameStatesContainer gameStatesContainer = new GameStatesContainer(modes, "For Gold and Sweetrolls");
            ForGoldAndSweetrolls fgas = new ForGoldAndSweetrolls(gameStatesContainer, 800, 600, true);

            AppGameContainer app = new AppGameContainer(fgas);
            String[] icons = {
                    RES+"icon16x16.png",
                    RES+"icon24x24.png",
                    RES+"icon32x32.png",
                    RES+"icon64x64.png",
                    RES+"icon96x96.png",
                    RES+"icon128x128.png"
            };
            app.setIcons(icons);
            app.setDisplayMode(800, 600, false);
            app.setTargetFrameRate(60);
            app.setVSync(true);
            app.setShowFPS(false);
            app.setUpdateOnlyWhenVisible(true);
            app.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    private void loggerSetup() {
        if (DEBUG_MODE) {
            try {
                fileHandler = new FileHandler("sweetrolls.log", false);
            } catch (IOException e1) {
                e1.printStackTrace();
                System.exit(-1);
            }

            //LOGGER.setUseParentHandlers(false); // WYŚWIETLANIE LOGÓW W KONSOLI

            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.INFO);
            LOGGER.addHandler(fileHandler);
            logging(false, Level.INFO, "Uruchomienie gry w trybie DEBUG. Logger załadowany.");
        }
    }


    public static void logging(boolean critical, Level level, String msg) {
        if (DEBUG_MODE) {
            LOGGER.log(level, msg);
            if (critical) {
                LOGGER.log(Level.WARNING, "Zamykanie programu z błędem.");
                System.exit(-1);
            }
        }
    }


//    public static String getStackTrace(final Throwable throwable) {
//        final StringWriter sw = new StringWriter();
//        final PrintWriter pw = new PrintWriter(sw, true);
//        throwable.printStackTrace(pw);
//        return sw.getBuffer().toString();
//    }

    /**
     * public static void main - this method starts all the pretty stuff ;)
     * @param args String - params
     */
    public static void main(String[] args) {
        if (args.length > 0) {
            DEBUG_MODE = args[0].equalsIgnoreCase("-debug");
        }
        new MainClass();
    }

}
