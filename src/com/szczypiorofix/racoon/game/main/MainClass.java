package com.szczypiorofix.racoon.game.main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MainClass  {

    public static final int SPLASHSCREEN = 0;
    public static final int MAINMENU = 1;
    public static final int GAME = 2;
    public static final int EXIT = 10;

    private static boolean DEBUG_MODE;
    private final static Logger LOGGER = Logger.getLogger(MainClass.class.getName());
    private FileHandler fileHandler = null;

    private JWindow splashWindow;
    private BufferedImage splashScreen;


    private MainClass() {

        splashScreen();
        // LOGGER
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


        try {
            AppGameContainer app = new AppGameContainer(new ForGoldAndSweetrolls("Racoon Game"));
//            String[] icons = {"res/icon.png"};
//            app.setIcons(icons);
            app.setDisplayMode(800, 600, false);
            app.start();
        } catch (SlickException e) {
            e.printStackTrace();
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

    public BufferedImage loadImage(String path) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResource(path));
            MainClass.logging(false, Level.INFO, "Obraz "+path +" załadowany poprawnie.");
        } catch (IOException ex) {
            MainClass.logging(false, Level.WARNING, "Błąd ładowania obrazu " +path);
            MainClass.logging(false, Level.WARNING, MainClass.getStackTrace(ex));
        }
        return image;
    }

    private void splashScreen() {
        splashWindow = new JWindow();

        splashScreen = loadImage("/background.png");

        JLabel label = new JLabel(new ImageIcon(splashScreen));
        splashWindow.getContentPane().add(label);
        splashWindow.setSize(splashScreen.getWidth(), splashScreen.getHeight());
        label.setBackground(Color.BLACK);
        splashWindow.setLocationRelativeTo(null);
        splashWindow.setVisible(true);
        logging(false, Level.INFO, "Uruchomienie ekranu splashScreen.");

//        try {
//            wait(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        splashWindow.dispose();
    }


    public static String getStackTrace(final Throwable throwable) {
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw, true);
        throwable.printStackTrace(pw);
        return sw.getBuffer().toString();
    }

    public static void main(String[] args) {

        if (args.length > 0)
            if (args[0].equalsIgnoreCase("-debug")) DEBUG_MODE = true;
            else DEBUG_MODE = false;

        new MainClass();

    }

}
