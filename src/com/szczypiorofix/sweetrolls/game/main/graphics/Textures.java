package com.szczypiorofix.sweetrolls.game.main.graphics;

import com.szczypiorofix.sweetrolls.game.main.MainClass;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import java.util.logging.Level;

public class Textures {

    private static Textures instance = null;

    public SpriteSheet miscItems = null;
    public SpriteSheet classm32 = null;

    public SpriteSheet mainMenuMainButtons = null;

    private Textures() {
        MainClass.logging(false, Level.INFO, "Ładowanie tekstur i obrazów...");
        try {
            miscItems = new SpriteSheet("map/dg_misc32.png", 32, 32);
            classm32 = new SpriteSheet("map/dg_classm32.png", 32, 32);
            mainMenuMainButtons = new SpriteSheet("assets/mm-gui.png", 128, 32);
            MainClass.logging(false, Level.INFO, "Tekstury i obrazy załadowane");
        } catch (Exception e) {
            e.printStackTrace();
            MainClass.logging(true, Level.WARNING, MainClass.getStackTrace(e));
        }
    }

    public static Textures getInstance() {
        if (instance == null) instance = new Textures();
        return instance;
    }
}
