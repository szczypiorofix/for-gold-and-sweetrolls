package com.szczypiorofix.sweetrolls.game.graphics;

import com.szczypiorofix.sweetrolls.game.main.MainClass;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Textures {

    private static Textures instance = null;

    public SpriteSheet miscItems = null;
    public SpriteSheet classm32 = null;

    public SpriteSheet mainMenuMainButtons = null;

    private Textures() {
        System.out.println("Creating Textures ...");

        try {
            miscItems = new SpriteSheet(MainClass.RES + "spritesheets/dg_misc32.png", 32, 32);
            classm32 = new SpriteSheet(MainClass.RES + "spritesheets/dg_classm32.png", 32, 32);
            mainMenuMainButtons = new SpriteSheet(MainClass.RES + "assets/mm-gui.png", 128, 32);
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public static Textures getInstance() {
        if (instance == null) instance = new Textures();
        return instance;
    }
}
