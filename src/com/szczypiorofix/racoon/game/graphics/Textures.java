package com.szczypiorofix.racoon.game.graphics;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Textures {

    private static Textures instance = null;

    public SpriteSheet miscItems = null;
    public SpriteSheet classm32 = null;

    private Textures() {
        System.out.println("Creating Textures ...");

        try {
            miscItems = new SpriteSheet("res/spritesheets/dg_misc32.png", 32, 32);
            classm32 = new SpriteSheet("res/spritesheets/dg_classm32.png", 32, 32);
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public static Textures getInstance() {
        if (instance == null) instance = new Textures();
        return instance;
    }
}
