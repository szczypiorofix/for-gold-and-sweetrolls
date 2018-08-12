package com.szczypiorofix.sweetrolls.game.main.states;

import com.szczypiorofix.sweetrolls.game.enums.GameState;
import com.szczypiorofix.sweetrolls.game.enums.ObjectType;
import com.szczypiorofix.sweetrolls.game.gui.MainMenuButton;
import com.szczypiorofix.sweetrolls.game.gui.MouseCursor;

import com.szczypiorofix.sweetrolls.game.main.fonts.BitMapFont;
import com.szczypiorofix.sweetrolls.game.main.fonts.FontParser;

import org.newdawn.slick.*;



public class GameMainMenu {

    private Input input;
    private Image mainMenuBackground;
    private MainMenuButton[] menuButtons;
    private MouseCursor mouseCursor;
    private BitMapFont titleFont;
    private MainGame mainGame;
    private int windowWidth, windowHeight;
    private boolean showSettings = false;


    GameMainMenu(MainGame mainGame) {
        this.mainGame = mainGame;
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
        titleFont = FontParser.getFont("Immortal Bitmap Title Font", "immortal-bitmap.xml", "immortal-bitmap.png");
        titleFont.setSize(15f);

        menuButtons = new MainMenuButton[4];

        // ##################### FONT #####################
        // https://www.fontsquirrel.com/fonts/Immortal


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

        for(int i = 0; i < menuButtons.length; i++) {
            if (mouseCursor.intersects(menuButtons[i])) {
                menuButtons[i].setHover(true);
                if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                    menuButtons[i].setActive(true);
                    switch (i) {
                        case 0: {
                            input.clearKeyPressedRecord();
                            mainGame.setGameState(GameState.GAME);
                            break;
                        }
                        case 3: {
                            input.clearKeyPressedRecord();
                            mainGame.setGameState(GameState.EXIT);
                            break;
                        }
                    }
                } else menuButtons[i].setActive(false);
            } else menuButtons[i].setHover(false);
        }

        if (input.isKeyPressed(Input.KEY_SPACE)) {
            mainGame.setGameState(GameState.GAME);
        }

        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            mainGame.setGameState(GameState.EXIT);
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

        }
    }

}
