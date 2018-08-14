package com.szczypiorofix.sweetrolls.game.main.states;

import com.szczypiorofix.sweetrolls.game.enums.GameState;
import com.szczypiorofix.sweetrolls.game.enums.ObjectType;
import com.szczypiorofix.sweetrolls.game.gui.MainMenuButton;
import com.szczypiorofix.sweetrolls.game.gui.MainMenuControlls;
import com.szczypiorofix.sweetrolls.game.gui.MouseCursor;
import com.szczypiorofix.sweetrolls.game.main.core.Configuration;
import com.szczypiorofix.sweetrolls.game.main.fonts.BitMapFont;
import com.szczypiorofix.sweetrolls.game.main.fonts.FontParser;
import org.newdawn.slick.*;



public class FGAS_MainMenu {

    private Input input;
    private Image mainMenuBackground;
    private Image optionsGui;
    private MainMenuButton[] menuButtons;
    private MainMenuControlls[] settingControlls;
    private MouseCursor mouseCursor;
    private BitMapFont titleFont;
    private ForGoldAndSweetrolls forGoldAndSweetrolls;
    private int windowWidth, windowHeight;
    private int selectedGameWidth, selectedGameHeight;
    private boolean selectedFullScreen;
    private boolean showSettings = false;
    private Configuration config;


    FGAS_MainMenu(ForGoldAndSweetrolls forGoldAndSweetrolls, Configuration config) {
        this.forGoldAndSweetrolls = forGoldAndSweetrolls;
        this.config = config;
    }


    public void init(GameContainer gc, Input input) throws SlickException {
        windowWidth = gc.getWidth();
        windowHeight = gc.getHeight();


        this.input = input;

        //sfx1 = new SFX("sword-unsheathe.ogg");

        // https://opengameart.org/content/heroic-minority
//        Music mainMenuMusic = new Music("music/menu-music.ogg");
//        mainMenuMusic.play();



        mainMenuBackground = new Image("assets/mm-gui-background.png");
        optionsGui = new Image("assets/mm-gui-options.png");
        titleFont = FontParser.getFont("Immortal Bitmap Title Font", "immortal-bitmap.xml", "immortal-bitmap.png");
        titleFont.setSize(15f);

        menuButtons = new MainMenuButton[4];

        // ##################### FONT #####################
        // https://www.fontsquirrel.com/fonts/Immortal


        // ############ USTAWIENIA
        settingControlls = new MainMenuControlls[6];

        settingControlls[0] = new MainMenuControlls(MainMenuControlls.ControlType.OK, "", 365, 350, 32, 32);
        settingControlls[1] = new MainMenuControlls(MainMenuControlls.ControlType.CANCEL, "", 410, 350, 32, 32);
        settingControlls[2] = new MainMenuControlls(MainMenuControlls.ControlType.LEF_ARROW,  "",300, 190, 32, 32);
        settingControlls[3] = new MainMenuControlls(MainMenuControlls.ControlType.RIGHT_ARROW, "", 480, 190, 32, 32);
        settingControlls[4] = new MainMenuControlls(MainMenuControlls.ControlType.TEXT, "Rozdzielczość", 345, 170, 32, 32);
        settingControlls[5] = new MainMenuControlls(MainMenuControlls.ControlType.TEXT, "800x600", 360, 195, 32, 32);



        // ############ PRZYCISKI
        menuButtons[0] = new MainMenuButton("NOWA GRA", (gc.getWidth() / 2) - (128 / 2), 200, 128, 32);
        menuButtons[1] = new MainMenuButton("USTAWIENIA", (gc.getWidth() / 2) - (128 / 2), 240, 128, 32);
        menuButtons[2] = new MainMenuButton("POMOC", (gc.getWidth() / 2) - (128 / 2), 280, 128, 32);
        menuButtons[3] = new MainMenuButton("KONIEC", (gc.getWidth() / 2) - (128 / 2), 320, 128, 32);

        // https://opengameart.org/content/dwarven-cursor
        gc.setMouseCursor(new Image("mouse_cursor.png"), 0, 0);
        mouseCursor = new MouseCursor("Mouse Cursor Main Menu", input.getMouseX(), input.getMouseY(), 1, 1, ObjectType.MOUSECURSOR, input);
    }

    public void update(GameContainer gc, int delta) throws SlickException {

        mouseCursor.update(delta, 0, 0);

        if (!showSettings) {

            selectedFullScreen = config.fullScreen;
            selectedGameWidth = config.gameWidth;
            selectedGameHeight = config.gameHeight;

            for(int i = 0; i < menuButtons.length; i++) {
                if (mouseCursor.intersects(menuButtons[i])) {
                    menuButtons[i].setHover(true);
                    if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                        menuButtons[i].setActive(true);
                        switch (i) {
                            case 0: {
                                input.clearKeyPressedRecord();
                                forGoldAndSweetrolls.setGameState(GameState.GAME);
                                break;
                            }
                            case 1: {
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
                                AppGameContainer gameContainer = (AppGameContainer) gc;
                                gameContainer.setDisplayMode(config.gameWidth, config.gameHeight, config.fullScreen);
                                showSettings = false;
                                break;
                            }
                            case 1: {
                                showSettings = false;
                                break;
                            }

                        }
                    } else settingControlls[i].setActive(false);
                } else settingControlls[i].setHover(false);
            }
        }

        if (input.isKeyPressed(Input.KEY_SPACE)) {
            forGoldAndSweetrolls.setGameState(GameState.GAME);
        }

        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            if (!showSettings) {
                forGoldAndSweetrolls.setGameState(GameState.EXIT);
            } else {
                showSettings = false;
            }

        }

//        if (input.isKeyPressed(Input.KEY_ENTER)) {
//            sfx1.play();
//        }


    }

    public void render(GameContainer gc, Graphics g) {

        mainMenuBackground.draw(0, 0);
        titleFont.draw("For Fold and Sweetrolls", 70, 50);

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
