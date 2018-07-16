package com.szczypiorofix.sweetrolls.game.main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import java.io.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MainClass implements Runnable {

    public static final String RES = "src/res/";

    public static final int SPLASHSCREEN = 0;
    public static final int MAINMENU = 1;
    public static final int GAME = 2;
    public static final int EXIT = 10;

    private static boolean DEBUG_MODE;
    private final static Logger LOGGER = Logger.getLogger(MainClass.class.getName());
    private FileHandler fileHandler = null;
    public static NetworkClient networkClient;

    static boolean serverOnline = false;


    private MainClass() {

//        Set<Thread> threads = Thread.getAllStackTraces().keySet();
//
//        for (Thread t : threads) {
//            String name = t.getName();
//            Thread.State state = t.getState();
//            int priority = t.getPriority();
//            String type = t.isDaemon() ? "Daemon" : "Normal";
//            System.out.printf("%-20s \t %s \t %d \t %s\n", name, state, priority, type);
//        }


        loggerSetup();

        //networkClientStart();

        applicationStart();

    }

    private void networkClientStart() {
        Thread serverThread = new Thread(this);
        serverThread.start();
    }

    private void applicationStart() {
        try {
            AppGameContainer app = new AppGameContainer(new ForGoldAndSweetrolls("For Gold and Sweetrolls"));
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


    public static String getStackTrace(final Throwable throwable) {
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw, true);
        throwable.printStackTrace(pw);
        return sw.getBuffer().toString();
    }


    @Override
    public void run() {
        System.out.println("Another thread is running..");

        System.out.println("Pierwsze łączenie z serwerem ...");

        NetworkClient.startConnection();

        boolean serverThreadRunning = true;

        while (serverThreadRunning) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            serverOnline = NetworkClient.isConnected();
            System.out.println("Server online?: " +serverOnline);

        }
    }


    /**
     * public static void main - this method starts all the pretty stuff ;)
     * @param args String - params
     */
    public static void main(String[] args) {
        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("-debug")) DEBUG_MODE = true;
            else DEBUG_MODE = false;
        }
        //System.out.println("HELLO!");

        new MainClass();

    }

}
