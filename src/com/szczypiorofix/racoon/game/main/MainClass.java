package com.szczypiorofix.racoon.game.main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class MainClass {

    private MainClass() {
        RacoonGame racoonGame = new RacoonGame("Racoon Game");
        try {
            AppGameContainer app = new AppGameContainer(racoonGame);
            String[] icons = {"res/icon.png"};
            app.setIcons(icons);
            app.setDisplayMode(640, 480, false);
            app.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new MainClass();
    }

}
