/*
 * Developed by szczypiorofix on 24.08.18 13:37.
 * Copyright (c) 2018. All rights reserved.
 *
 */

package com.szczypiorofix.sweetrolls.game.main.graphics;

import com.szczypiorofix.sweetrolls.game.main.MainClass;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

import java.util.logging.Level;

/**
 * Class for loading sprites from image files.
 */
public class Textures {

    private static Textures instance = null;

    public SpriteSheet miscItems = null;
    public SpriteSheet classm32 = null;

    public SpriteSheet mainMenuMainButtons = null;
    public SpriteSheet mainMenuControlls = null;
    public Image dialogueFrame = null;

    private Textures() {
        try {
            miscItems = new SpriteSheet("map/dg_misc32.png", 32, 32);
            classm32 = new SpriteSheet("map/dg_classm32.png", 32, 32);
            mainMenuMainButtons = new SpriteSheet("assets/mm-gui-button.png", 148, 32);
            mainMenuControlls = new SpriteSheet("assets/mm-gui-controlls.png", 32, 32);
            dialogueFrame = new Image("assets/dialogue-frame.png");
        } catch (Exception e) {
            e.printStackTrace();
            MainClass.logging(true, Level.WARNING, MainClass.getStackTrace(e));
        }
    }

    /**
     * Returns only one instance of Textures object.
     * @return Texture instance
     */
    public static Textures getInstance() {
        if (instance == null) instance = new Textures();
        return instance;
    }
}
