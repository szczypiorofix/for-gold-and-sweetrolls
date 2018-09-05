/*
 * Developed by szczypiorofix on 24.08.18 13:36.
 * Copyright (c) 2018. All rights reserved.
 *
 */

package com.szczypiorofix.sweetrolls.game.main.states;

import com.szczypiorofix.sweetrolls.game.enums.GameState;
import com.szczypiorofix.sweetrolls.game.gui.MainMenuButton;
import com.szczypiorofix.sweetrolls.game.gui.MainMenuControlls;
import com.szczypiorofix.sweetrolls.game.gui.MouseCursor;
import com.szczypiorofix.sweetrolls.game.main.MainClass;
import com.szczypiorofix.sweetrolls.game.main.core.*;
import com.szczypiorofix.sweetrolls.game.main.graphics.Textures;
import com.szczypiorofix.sweetrolls.game.objects.terrain.Ground;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleIO;
import org.newdawn.slick.particles.ParticleSystem;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;


public class FGASMainMenu {

    private Input input;
    private Image mainMenuBackground;
    private Image mainMenuBackgroundShade;
    private Image optionsGui;
    private Image logoTitle;
    private MainMenuButton[] menuButtons;
    private MainMenuControlls[] settingControls;
    private MouseCursor mouseCursor;
    private ForGoldAndSweetrolls forGoldAndSweetrolls;
    private ObjectManager objectManager;
    private Ground[][] mainMenuMovingBackground;
    //private ParticleSystem system;
    //private Image torch;

    private int selectedGameWidth, selectedGameHeight;
    private int selectedMusicVolume, selectedSFXVolume;
    private boolean selectedFullScreen;
    private boolean selectedVSync;
    private boolean selectedShowFPS;

    private boolean showSettings = false;
    private Configuration config;
    private ArrayList<DisplayMode> modes;
    private int selectedMode = 0;

    private boolean settingsLoaded = false;

    private float offsetX, offsetY;
    private final float initialOffsetX = 700f;
    private final float initialOffsetY = 700f;
    private final float maxOffsetX = 6000f;
    private final float maxOffsetY = 6000f;


    FGASMainMenu(ForGoldAndSweetrolls forGoldAndSweetrolls, Configuration config, ArrayList<DisplayMode> modes) {
        this.forGoldAndSweetrolls = forGoldAndSweetrolls;
        this.config = config;
        this.modes = modes;

        setSettingsToDeclared();

        offsetX = initialOffsetX;
        offsetY = initialOffsetY;
    }

    private void setSettingsToDeclared() {
        selectedFullScreen = config.fullScreen;
        selectedGameWidth = config.gameWidth;
        selectedGameHeight = config.gameHeight;
        selectedVSync = config.vsync;
        selectedMusicVolume = config.musicVolume;
        selectedSFXVolume = config.sfxVolume;
        selectedShowFPS = config.showFps;
        for(int i = 0; i < modes.size(); i++) {
            if (modes.get(i).getWidth() == selectedGameWidth && modes.get(i).getHeight() == selectedGameHeight) {
                selectedMode = i;
                break;
            }
        }
    }

    public void init(GameContainer gc, Input input, MouseCursor mouseCursor) throws SlickException {

        this.mouseCursor = mouseCursor;
        this.input = input;

//        // https://opengameart.org/content/heroic-minority
//        Music mainMenuMusic = new Music("music/menu-music.ogg");
//        mainMenuMusic.play();

        mainMenuBackground = Textures.getInstance().mainMenuBackground;
        mainMenuBackgroundShade = Textures.getInstance().mainMenuBackgroundShade;
        logoTitle = Textures.getInstance().logoTitle;
        optionsGui = Textures.getInstance().optionsGui;


        // ############ USTAWIENIA
        settingControls = new MainMenuControlls[20];

        settingControls[0] = new MainMenuControlls(MainMenuControlls.ControlType.OK, "OK", false,365, 480);
        settingControls[1] = new MainMenuControlls(MainMenuControlls.ControlType.CANCEL, "Cancel", false,410, 480);
        settingControls[2] = new MainMenuControlls(MainMenuControlls.ControlType.LEF_ARROW,  "Left arrow", false,290, 200);
        settingControls[3] = new MainMenuControlls(MainMenuControlls.ControlType.RIGHT_ARROW, "Right arrow", false, 470, 200);

        settingControls[4] = new MainMenuControlls(MainMenuControlls.ControlType.TEXT, "Rozdzielczość", false, 335, 180);
        settingControls[5] = new MainMenuControlls(MainMenuControlls.ControlType.TEXT, selectedGameWidth+"x"+selectedGameHeight, false, 360, 205);

        settingControls[6] = new MainMenuControlls(MainMenuControlls.ControlType.TEXT, "Full screen", false, 340, 254);
        settingControls[7] = new MainMenuControlls(MainMenuControlls.ControlType.CHECK_BOX, "Full screen checkbox", selectedFullScreen, 450, 250);

        settingControls[8] = new MainMenuControlls(MainMenuControlls.ControlType.TEXT, "v-sync", false, 340, 294);
        settingControls[9] = new MainMenuControlls(MainMenuControlls.ControlType.CHECK_BOX, "V-Sync checkbox", selectedVSync, 450, 290);

        settingControls[10] = new MainMenuControlls(MainMenuControlls.ControlType.TEXT, "FPS", false, 340, 334);
        settingControls[11] = new MainMenuControlls(MainMenuControlls.ControlType.CHECK_BOX, "FPS show checkbox", selectedShowFPS, 450, 330);

        settingControls[12] = new MainMenuControlls(MainMenuControlls.ControlType.TEXT, "Muzyka", false, 290, 374);
        settingControls[13] = new MainMenuControlls(MainMenuControlls.ControlType.TEXT, selectedMusicVolume + "", false, 380, 374);
        settingControls[14] = new MainMenuControlls(MainMenuControlls.ControlType.UP_ARROW, "Music Volume up", false, 440, 370);
        settingControls[15] = new MainMenuControlls(MainMenuControlls.ControlType.DOWN_ARROW, "Music Volume down", false, 472, 370);

        settingControls[16] = new MainMenuControlls(MainMenuControlls.ControlType.TEXT, "SFX", false, 290, 410);
        settingControls[17] = new MainMenuControlls(MainMenuControlls.ControlType.TEXT, selectedSFXVolume + "", false, 380, 410);
        settingControls[18] = new MainMenuControlls(MainMenuControlls.ControlType.UP_ARROW, "SFX Volume up", false, 440, 410);
        settingControls[19] = new MainMenuControlls(MainMenuControlls.ControlType.DOWN_ARROW, "SFX Volume down", false, 472, 410);

        // ############ PRZYCISKI
        menuButtons = new MainMenuButton[5];
        menuButtons[0] = new MainMenuButton("Podróżuj Dalej", 320, 280);
        menuButtons[1] = new MainMenuButton("Nowa Przygoda", 320, 320);
        menuButtons[2] = new MainMenuButton("Kostnica", 320, 360);
        menuButtons[3] = new MainMenuButton("Ustawienia", 320, 400);
        menuButtons[4] = new MainMenuButton("Wyjdź", 320, 440);


        // PARTICLES
//        torch = new Image("particles/torch.png");
//
//        Image image = new Image("particles/particle.png", false);
//        system = new ParticleSystem(image,1500);
//
//        try {
//            File xmlFile = new File(MainClass.RES + "particles/torch.xml");
//            ConfigurableEmitter emitter = ParticleIO.loadEmitter(xmlFile);
//            emitter.setPosition(295, 358);
//            system.addEmitter(emitter);
//            emitter = ParticleIO.loadEmitter(xmlFile);
//            emitter.setPosition(485, 358);
//            system.addEmitter(emitter);
//        } catch (Exception e) {
//            System.out.println("Exception: " +e.getMessage());
//            e.printStackTrace();
//            System.exit(0);
//        }
//
//        system.setBlendingMode(ParticleSystem.BLEND_ADDITIVE);

    }

    public void update(GameContainer gc, int delta) throws SlickException {

        if (offsetX > maxOffsetX && offsetY > maxOffsetY) {
            offsetX = initialOffsetX;
            offsetY = initialOffsetY;
        }

        offsetX += 0.08f * delta;
        offsetY += 0.08f * delta;

        // INITIAL RESOLUTION CHANGE
        if (!settingsLoaded) {
            AppGameContainer gameContainer = (AppGameContainer) gc;
            gameContainer.setDisplayMode(selectedGameWidth, selectedGameHeight, selectedFullScreen);
            settingsLoaded = true;
            objectManager = forGoldAndSweetrolls.getFGASGame().getObjectManager();
            mainMenuMovingBackground = new Ground[objectManager.getGrounds().length][objectManager.getGrounds()[0].length];
            for (int i = 0; i < objectManager.getGrounds().length; i++) {
                for (int j = 0; j < objectManager.getGrounds()[0].length; j++) {
                    mainMenuMovingBackground[i][j] = objectManager.getGrounds()[i][j];
                }
            }
        }

        mouseCursor.update(delta, 0, 0);

        if (!showSettings) {
            for(int i = 0; i < menuButtons.length; i++) {
                if (mouseCursor.intersects(menuButtons[i])) {
                    menuButtons[i].setHover(true);
                    if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                        menuButtons[i].setActive(true);
                        switch (i) {
                            case 0: {
                                loadGame(true);
                                break;
                            }
                            case 1: {
                                forGoldAndSweetrolls.setGameState(GameState.CREATION_MENU);
                                break;
                            }
                            case 2: {
                                System.out.println("KOSTNICA...");
                                forGoldAndSweetrolls.getSfx().play(forGoldAndSweetrolls.getSfxVolume());
                                break;
                            }
                            case 3: {
                                settingControls[5].setText(config.gameWidth+"x"+config.gameHeight);
                                settingControls[7].setChecked(config.fullScreen);
                                settingControls[9].setChecked(config.vsync);
                                settingControls[11].setChecked(config.showFps);
                                settingControls[13].setText(config.musicVolume+"");
                                settingControls[17].setText(config.sfxVolume+"");

                                showSettings = true;
                                break;
                            }
                            case 4: {
                                input.clearKeyPressedRecord();
                                forGoldAndSweetrolls.setGameState(GameState.EXIT);
                                break;
                            }
                        }
                    } else menuButtons[i].setActive(false);
                } else menuButtons[i].setHover(false);
            }
        } else {
            for(int i = 0; i < settingControls.length; i++) {
                if (mouseCursor.intersects(settingControls[i])) {
                    settingControls[i].setHover(true);
                    if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                        settingControls[i].setActive(true);
                        switch (i) {
                            case 0: {
                                config.gameWidth = selectedGameWidth;
                                config.gameHeight = selectedGameHeight;
                                config.fullScreen = selectedFullScreen;
                                config.vsync = selectedVSync;
                                config.showFps = selectedShowFPS;
                                config.musicVolume = selectedMusicVolume;
                                config.sfxVolume = selectedSFXVolume;

                                AppGameContainer gameContainer = (AppGameContainer) gc;
                                gameContainer.setDisplayMode(config.gameWidth, config.gameHeight, config.fullScreen);
                                gameContainer.setVSync(config.vsync);
                                gameContainer.setShowFPS(config.showFps);

                                ConfigManager configManager = new ConfigManager();
                                configManager.saveToFile(config);

                                showSettings = false;
                                break;
                            }
                            case 1: {
                                setSettingsToDeclared();
                                showSettings = false;
                                break;
                            }
                            case 2: {
                                if (selectedMode > 0) selectedMode--;
                                else selectedMode = modes.size()-1;
                                settingControls[5].setText(modes.get(selectedMode).getWidth()+"x"+modes.get(selectedMode).getHeight());
                                selectedGameWidth = modes.get(selectedMode).getWidth();
                                selectedGameHeight = modes.get(selectedMode).getHeight();
                                break;
                            }
                            case 3: {
                                if (selectedMode < modes.size()-1) selectedMode++;
                                else selectedMode = 0;
                                settingControls[5].setText(modes.get(selectedMode).getWidth()+"x"+modes.get(selectedMode).getHeight());
                                selectedGameWidth = modes.get(selectedMode).getWidth();
                                selectedGameHeight = modes.get(selectedMode).getHeight();
                                break;
                            }
                            case 7: {
                                settingControls[i].setChecked(!settingControls[i].isChecked());
                                selectedFullScreen = settingControls[i].isChecked();
                                break;
                            }
                            case 9: {
                                settingControls[i].setChecked(!settingControls[i].isChecked());
                                selectedVSync = settingControls[i].isChecked();
                                break;
                            }
                            case 11: {
                                settingControls[i].setChecked(!settingControls[i].isChecked());
                                selectedShowFPS = settingControls[i].isChecked();
                                break;
                            }
                            case 14: {
                                if (selectedMusicVolume < 100) {
                                    selectedMusicVolume += 10;
                                }
                                settingControls[13].setText(selectedMusicVolume+"");
                                forGoldAndSweetrolls.getMainMenuMusic().setVolume(selectedMusicVolume / 100f);
                                break;
                            }
                            case 15: {
                                if (selectedMusicVolume > 0) {
                                    selectedMusicVolume -= 10;
                                }
                                settingControls[13].setText(selectedMusicVolume+"");
                                forGoldAndSweetrolls.getMainMenuMusic().setVolume(selectedMusicVolume / 100f);
                                break;
                            }
                            case 18: {
                                if (selectedSFXVolume < 100) {
                                    selectedSFXVolume += 10;
                                }
                                settingControls[17].setText(selectedSFXVolume+"");
                                forGoldAndSweetrolls.setSfxVolume(selectedSFXVolume);
                                break;
                            }
                            case 19: {
                                if (selectedSFXVolume > 0) {
                                    selectedSFXVolume -= 10;
                                }
                                settingControls[17].setText(selectedSFXVolume+"");
                                forGoldAndSweetrolls.setSfxVolume(selectedSFXVolume);
                                break;
                            }
                        }
                    } settingControls[i].setActive(false);
                } else settingControls[i].setHover(false);
            }
        }

        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            if (!showSettings) {
                forGoldAndSweetrolls.setGameState(GameState.EXIT);
            } else {
                showSettings = false;
                input.clearKeyPressedRecord();
            }

        }

        //system.update(delta);

    }

    public void render(GameContainer gc, Graphics g) throws SlickException {

        if (mainMenuMovingBackground != null) {
            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < 20; j++) {
                    mainMenuMovingBackground[i + ((int) (offsetX / 32))][j + ((int) (offsetY / 32))].render(g, offsetX, offsetY);
                }
            }
        }

        // #### LITTLE SHADE ON MAP
        mainMenuBackgroundShade.draw(0, 0);

        mainMenuBackground.draw(0, 0);

        logoTitle.draw(245, 60);

        for(MainMenuButton m: menuButtons) {
            m.render(g, 0 ,0);
        }

        //torch.draw(280,350);
        //torch.draw(470,350);

        //system.render();

        if (showSettings) {
            optionsGui.draw(230, 160);
            for(MainMenuControlls s: settingControls) {
                s.render(g, 0 ,0);
            }
        }

    }


    void loadGame(boolean load) {
        forGoldAndSweetrolls.resetGame();
        forGoldAndSweetrolls.setGameState(GameState.GAME);
        if (load) {
            SaveGameData saveGameData = SaveGameManager.load("player.sav");

            // DATA
            forGoldAndSweetrolls.getFGASGame().getPlayer().setX(saveGameData.getPlayerX());
            forGoldAndSweetrolls.getFGASGame().getPlayer().setY(saveGameData.getPlayerY());
            forGoldAndSweetrolls.getFGASGame().getPlayer().setWorldMapTileX(saveGameData.getPlayerWorldMapTileX());
            forGoldAndSweetrolls.getFGASGame().getPlayer().setWorldMapTileY(saveGameData.getPlayerWorldMapTileY());
            forGoldAndSweetrolls.getFGASGame().getPlayer().statistics = saveGameData.getPlayerStatistics();
            forGoldAndSweetrolls.getFGASGame().getTimeCounter().setTimeStamp(saveGameData.getTimeCounterTimeStamp());
            forGoldAndSweetrolls.getFGASGame().getTimeCounter().setDayCounter(saveGameData.getTimeCounterDayCounter());
            forGoldAndSweetrolls.getFGASGame().getTimeCounter().setHourCounter(saveGameData.getTimeCounterHourCounter());
            forGoldAndSweetrolls.getFGASGame().getTimeCounter().setMinuteCounter(saveGameData.getTimeCounterMinuteCounter());
            forGoldAndSweetrolls.getFGASGame().getObjectManager().getCurrentMap().setLevelType(saveGameData.getLevelType());
            forGoldAndSweetrolls.getFGASGame().setActionHistory(saveGameData.getActionHistory());
            forGoldAndSweetrolls.getFGASGame().getPlayer().setName(saveGameData.getPlayerStatistics().P_Name);

            forGoldAndSweetrolls.getFGASGame().getHud().turn(saveGameData.getTimeCounterTimeStamp());
        }

        forGoldAndSweetrolls.getFGASGame().calculateOffset();
    }
}
