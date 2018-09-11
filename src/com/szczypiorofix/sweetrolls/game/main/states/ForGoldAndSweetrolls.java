/*
 * Developed by szczypiorofix on 24.08.18 13:36.
 * Copyright (c) 2018. All rights reserved.
 *
 */

package com.szczypiorofix.sweetrolls.game.main.states;


import com.szczypiorofix.sweetrolls.game.enums.CharacterRace;
import com.szczypiorofix.sweetrolls.game.enums.CharacterSex;
import com.szczypiorofix.sweetrolls.game.enums.GameState;
import com.szczypiorofix.sweetrolls.game.enums.ObjectType;
import com.szczypiorofix.sweetrolls.game.gui.MouseCursor;
import com.szczypiorofix.sweetrolls.game.main.core.Configuration;
import com.szczypiorofix.sweetrolls.game.main.graphics.Textures;
import com.szczypiorofix.sweetrolls.game.main.sounds.SFX;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.*;

import java.util.ArrayList;


public final class ForGoldAndSweetrolls extends BasicGame {

    private Input input;
    private FGASGame FGASGame;
    private FGASMainMenu FGASMainMenu;
    private FGASCreatePlayer FGASCreatePlayer;
    private MouseCursor mouseCursor;

    private GameState gameState;
    private Music gameMusic, mainMenuMusic;
    private SFX sfx;
    private int musicVolume = 100;
    private int sfxVolume = 100;


    public ForGoldAndSweetrolls(String title, ArrayList<DisplayMode> modes, Configuration config) {
        super(title);
        FGASMainMenu = new FGASMainMenu(this, config, modes);
        FGASGame = new FGASGame(this);
        FGASCreatePlayer = new FGASCreatePlayer(this, FGASMainMenu);
        gameState = GameState.MAIN_MENU;
        musicVolume = config.musicVolume;
        sfxVolume = config.sfxVolume;
    }


    @Override
    public void init(GameContainer gc) throws SlickException {
        input = gc.getInput();

        gameMusic = new Music("music/ex-11-towards_heaven_2017.xm");

        // https://modarchive.org/index.php?request=view_by_moduleid&query=59256  - 0rigin.xm
        mainMenuMusic = new Music("music/mm-0rigin.xm");
        mainMenuMusic.loop(1f, musicVolume);

        sfx = new SFX("sword-unsheathe.ogg");

        // ##################### FONT #####################
        // https://www.fontsquirrel.com/fonts/Immortal

        // https://opengameart.org/content/dwarven-cursor
        gc.setMouseCursor(Textures.getInstance().mouseCursor, 0, 0);
        mouseCursor = new MouseCursor("Mouse Cursor Main Menu", input.getMouseX(), input.getMouseY(), 32, 32, ObjectType.MOUSECURSOR, input);

        FGASMainMenu.init(gc, input, mouseCursor);
        FGASCreatePlayer.init(gc, input, mouseCursor);
        FGASGame.init(gc, input, mouseCursor);
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {

        if (gameState == GameState.MAIN_MENU) {
            FGASMainMenu.update(gc, delta);
        } else if (gameState == GameState.CREATION_MENU) {
            FGASCreatePlayer.update(gc, delta);
        } else if (gameState == GameState.GAME) {
            FGASGame.handleInputs(gc, delta);
            FGASGame.handleLogic(gc, delta);
        } else if (gameState == GameState.EXIT) {
            gc.exit();
        }

        input.clearKeyPressedRecord();
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {

        if (gameState == GameState.MAIN_MENU) {
            FGASMainMenu.render(gc, g);
        } else if (gameState == GameState.CREATION_MENU) {
            FGASCreatePlayer.render(gc, g);
        } else if (gameState == GameState.GAME) {
            FGASGame.render(gc, g);
        }

    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState){
        switch (gameState) {
            case MAIN_MENU: {
                gameMusic.stop();
                mainMenuMusic.loop(1f, musicVolume);
                FGASGame.setSaveGameLoaded(false);
                break;
            }
            case GAME: {
                mainMenuMusic.stop();
                gameMusic.loop(1f, musicVolume);
                break;
            }
            case CREATION_MENU: {
                break;
            }
            default: {
                gameMusic.stop();
                mainMenuMusic.stop();
                break;
            }
        }
        this.gameState = gameState;
    }

    public com.szczypiorofix.sweetrolls.game.main.states.FGASGame getFGASGame() {
        return FGASGame;
    }

    public com.szczypiorofix.sweetrolls.game.main.states.FGASMainMenu getFGASMainMenu() {
        return FGASMainMenu;
    }

    public float getMusicVolume() {
        return musicVolume;
    }

    public void setMusicVolume(int musicVolume) {
        this.musicVolume = musicVolume;
    }

    public Music getMainMenuMusic() {
        return mainMenuMusic;
    }

    public Music getGameMusic() {
        return gameMusic;
    }

    public SFX getSfx() {
        return sfx;
    }

    public int getSfxVolume() {
        return sfxVolume;
    }

    public void setSfxVolume(int sfxVolume) {
        this.sfxVolume = sfxVolume;
    }

}
