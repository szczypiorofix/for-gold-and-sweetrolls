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

    public SpriteSheet classm32 = null;
    public SpriteSheet mainMenuMainButtons = null;
    public SpriteSheet mainMenuControlls = null;
    public SpriteSheet avatarsMales = null;
    public SpriteSheet avatarsFemales = null;
    public Image dialogueFrame = null;
    public Image hudImage = null;
    public Image clockImage = null;
    public Image inventoryImage = null;
    public Image logoTitle = null;
    public Image optionsGui = null;
    public Image mainMenuBackground = null;
    public Image mainMenuBackgroundShade = null;
    public Image mouseCursor = null;
    public Image creationGUI = null;


    private Textures() {
        try {
            classm32 = new SpriteSheet("map/dg_classm32.png", 32, 32);
            mainMenuMainButtons = new SpriteSheet("assets/mm-gui-button.png", 168, 32);
            mainMenuControlls = new SpriteSheet("assets/mm-gui-controlls.png", 32, 32);
            avatarsMales = new SpriteSheet("avatars/males.jpg", 169, 266);
            avatarsFemales = new SpriteSheet("avatars/females.jpg", 169 ,266);
            dialogueFrame = new Image("assets/dialogue-frame.png");
            hudImage = new Image("assets/hud.png");
            clockImage = new Image("assets/time-counter.png");
            inventoryImage = new Image("assets/inventory.png");
            logoTitle = new Image("assets/logo-title.png");
            optionsGui = new Image("assets/mm-gui-options.png");
            mainMenuBackground = new Image("assets/mm-gui-background.png");
            mainMenuBackgroundShade = new Image("assets/mm-background-shade.png");
            mouseCursor = new Image("mouse_cursor.png");
            creationGUI = new Image("assets/creation.png");
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
