package com.szczypiorofix.sweetrolls.game.main.states;

import com.szczypiorofix.sweetrolls.game.enums.GameState;
import com.szczypiorofix.sweetrolls.game.gui.MainMenuButton;
import com.szczypiorofix.sweetrolls.game.gui.MainMenuControlls;
import com.szczypiorofix.sweetrolls.game.gui.MouseCursor;
import com.szczypiorofix.sweetrolls.game.main.core.ConfigManager;
import com.szczypiorofix.sweetrolls.game.main.core.Configuration;
import com.szczypiorofix.sweetrolls.game.main.core.ObjectManager;
import com.szczypiorofix.sweetrolls.game.objects.terrain.Ground;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import java.util.ArrayList;


public class FGAS_MainMenu {

    private Input input;
    private Image mainMenuBackground;
    private Image mainMenuBackgroundShade;
    private Image optionsGui;
    private Image logoTitle;
    private MainMenuButton[] menuButtons;
    private MainMenuControlls[] settingControlls;
    private MouseCursor mouseCursor;
    private ForGoldAndSweetrolls forGoldAndSweetrolls;
    private ObjectManager objectManager;
    private Ground[][] mainMenuMovingBackground;

    private int windowWidth, windowHeight;
    private int selectedGameWidth, selectedGameHeight;
    private float selectedMusicVolume;
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


    FGAS_MainMenu(ForGoldAndSweetrolls forGoldAndSweetrolls, Configuration config, ArrayList<DisplayMode> modes) {
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
        selectedShowFPS = config.showFps;
        for(int i = 0; i < modes.size(); i++) {
            if (modes.get(i).getWidth() == selectedGameWidth && modes.get(i).getHeight() == selectedGameHeight) {
                selectedMode = i;
                break;
            }
        }
    }

    public void init(GameContainer gc, Input input, MouseCursor mouseCursor) throws SlickException {
        windowWidth = gc.getWidth();
        windowHeight = gc.getHeight();

        this.mouseCursor = mouseCursor;
        this.input = input;

        // https://opengameart.org/content/heroic-minority
//        Music mainMenuMusic = new Music("music/menu-music.ogg");
//        mainMenuMusic.play();


        mainMenuBackground = new Image("assets/mm-gui-background.png");
        mainMenuBackgroundShade = new Image("assets/mm-background-shade.png");

        logoTitle = new Image("assets/logo-title.png");

        optionsGui = new Image("assets/mm-gui-options.png");

        menuButtons = new MainMenuButton[4];

        // ##################### FONT #####################
        // https://www.fontsquirrel.com/fonts/Immortal


        // ############ USTAWIENIA
        settingControlls = new MainMenuControlls[12];

        settingControlls[0] = new MainMenuControlls(MainMenuControlls.ControlType.OK, "OK", false,385, 400, 32, 32);
        settingControlls[1] = new MainMenuControlls(MainMenuControlls.ControlType.CANCEL, "Cancel", false,430, 400, 32, 32);
        settingControlls[2] = new MainMenuControlls(MainMenuControlls.ControlType.LEF_ARROW,  "Left arrow", false,310, 190, 32, 32);
        settingControlls[3] = new MainMenuControlls(MainMenuControlls.ControlType.RIGHT_ARROW, "Right arrow", false, 490, 190, 32, 32);

        settingControlls[4] = new MainMenuControlls(MainMenuControlls.ControlType.TEXT, "Rozdzielczość", false, 355, 170, 32, 32);
        settingControlls[5] = new MainMenuControlls(MainMenuControlls.ControlType.TEXT, selectedGameWidth+"x"+selectedGameHeight, false, 380, 195, 32, 32);

        settingControlls[6] = new MainMenuControlls(MainMenuControlls.ControlType.TEXT, "Full screen", false, 360, 234, 32, 32);
        settingControlls[7] = new MainMenuControlls(MainMenuControlls.ControlType.CHECK_BOX, "Full screen checkbox", selectedFullScreen, 470, 230, 32, 32);

        settingControlls[8] = new MainMenuControlls(MainMenuControlls.ControlType.TEXT, "v-sync", false, 360, 274, 32, 32);
        settingControlls[9] = new MainMenuControlls(MainMenuControlls.ControlType.CHECK_BOX, "V-Sync checkbox", selectedVSync, 470, 270, 32, 32);

        settingControlls[10] = new MainMenuControlls(MainMenuControlls.ControlType.TEXT, "FPS", false, 360, 314, 32, 32);
        settingControlls[11] = new MainMenuControlls(MainMenuControlls.ControlType.CHECK_BOX, "FPS show checkbox", selectedShowFPS, 470, 310, 32, 32);


        // ############ PRZYCISKI
        menuButtons[0] = new MainMenuButton("KONTYNUUJ", 140, 520, 128, 32);
        menuButtons[1] = new MainMenuButton("NOWA GRA", 280, 520, 128, 32);
        menuButtons[2] = new MainMenuButton("USTAWIENIA", 420, 520, 128, 32);
        menuButtons[3] = new MainMenuButton("KONIEC", 560, 520, 128, 32);

        // https://opengameart.org/content/dwarven-cursor
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
                                input.clearKeyPressedRecord();
                                forGoldAndSweetrolls.resetGame();
                                forGoldAndSweetrolls.setGameState(GameState.GAME);
                                break;
                            }
                            case 2: {
                                settingControlls[5].setText(config.gameWidth+"x"+config.gameHeight);
                                settingControlls[7].setChecked(config.fullScreen);
                                settingControlls[9].setChecked(config.vsync);
                                settingControlls[11].setChecked(config.showFps);

                                showSettings = true;
                                break;
                            }
                            case 3: {
                                input.clearKeyPressedRecord();
                                forGoldAndSweetrolls.setGameState(GameState.EXIT);
                                break;
                            }
                        }
                    } else menuButtons[i].setActive(false);
                } else menuButtons[i].setHover(false);
            }
        } else {
            for(int i = 0; i < settingControlls.length; i++) {
                if (mouseCursor.intersects(settingControlls[i])) {
                    settingControlls[i].setHover(true);
                    if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                        settingControlls[i].setActive(true);
                        switch (i) {
                            case 0: {
                                config.gameWidth = selectedGameWidth;
                                config.gameHeight = selectedGameHeight;
                                config.fullScreen = selectedFullScreen;
                                config.vsync = selectedVSync;
                                config.showFps = selectedShowFPS;
                                config.musicVolume = selectedMusicVolume;

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
                                // RESET CONTROLLS
                                setSettingsToDeclared();

                                showSettings = false;
                                break;
                            }
                            case 2: {
                                if (selectedMode > 0) selectedMode--;
                                else selectedMode = modes.size()-1;
                                settingControlls[5].setText(modes.get(selectedMode).getWidth()+"x"+modes.get(selectedMode).getHeight());
                                selectedGameWidth = modes.get(selectedMode).getWidth();
                                selectedGameHeight = modes.get(selectedMode).getHeight();
                                break;
                            }
                            case 3: {
                                if (selectedMode < modes.size()-1) selectedMode++;
                                else selectedMode = 0;
                                settingControlls[5].setText(modes.get(selectedMode).getWidth()+"x"+modes.get(selectedMode).getHeight());
                                selectedGameWidth = modes.get(selectedMode).getWidth();
                                selectedGameHeight = modes.get(selectedMode).getHeight();
                                break;
                            }
                            case 7: {
                                settingControlls[i].setChecked(!settingControlls[i].isChecked());
                                selectedFullScreen = settingControlls[i].isChecked();
                                break;
                            }
                            case 9: {
                                settingControlls[i].setChecked(!settingControlls[i].isChecked());
                                selectedVSync = settingControlls[i].isChecked();
                                break;
                            }
                            case 11: {
                                settingControlls[i].setChecked(!settingControlls[i].isChecked());
                                selectedShowFPS = settingControlls[i].isChecked();
                                break;
                            }
                        }
                    } settingControlls[i].setActive(false);
                } else settingControlls[i].setHover(false);
            }
        }

        if (input.isKeyPressed(Input.KEY_SPACE)) {
            forGoldAndSweetrolls.resetGame();
            forGoldAndSweetrolls.setGameState(GameState.GAME);
        }

        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            if (!showSettings) {
                forGoldAndSweetrolls.setGameState(GameState.EXIT);
            } else {
                showSettings = false;
                input.clearKeyPressedRecord();
            }

        }

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

        if (showSettings) {
            optionsGui.draw(250, 150);
            for(MainMenuControlls s: settingControlls) {
                s.render(g, 0 ,0);
            }
        }
    }


}
