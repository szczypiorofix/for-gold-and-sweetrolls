package com.szczypiorofix.sweetrolls.game.main.states;

import com.szczypiorofix.sweetrolls.game.enums.ObjectType;
import com.szczypiorofix.sweetrolls.game.gui.MainMenuButton;
import com.szczypiorofix.sweetrolls.game.gui.MouseCursor;
import com.szczypiorofix.sweetrolls.game.main.MainClass;

import com.szczypiorofix.sweetrolls.game.main.fonts.BitMapFont;
import com.szczypiorofix.sweetrolls.game.main.fonts.FontParser;
import org.lwjgl.opengl.DisplayMode;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.EmptyTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import java.util.ArrayList;


public class GameMainMenu extends BasicGameState {

    private Input input;
    private Image background;
    private MainMenuButton[] menuButtons;
    private MouseCursor mouseCursor;
    private BitMapFont titleFont;
    private int windowWidth, windowHeight;
    private final DisplayMode[] modes;
    private ArrayList<Resolution> resolutions;
    private int resolutionIndex = 0;
    private boolean fullScreen = false;

    class Resolution {
        int width, height;

        Resolution(int width, int height) {
            this.width = width;
            this.height = height;
        }
    }

    GameMainMenu(DisplayMode[] modes) {
        this.modes = modes;

    }

    @Override
    public int getID() {
        return MainClass.MAINMENU;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        windowWidth = gc.getWidth();
        windowHeight = gc.getHeight();
        fullScreen = gc.isFullscreen();

        resolutions = new ArrayList<>(1);
        int c = 0;
        for(DisplayMode d: modes) {
            if (d.getFrequency() == 60 && d.getBitsPerPixel() == 32) {
                resolutions.add(new Resolution(d.getWidth(), d.getHeight()));
                if (d.getWidth() == windowWidth && d.getHeight() == windowHeight) {
                    resolutionIndex = c;
                }
                c++;
            }
        }

        background = new Image(MainClass.RES+"background.png");

        //sfx1 = new SFX("sword-unsheathe.ogg");

        // https://opengameart.org/content/heroic-minority
//        Music mainMenuMusic = new Music(MainClass.RES+"music/menu-music.ogg");
//        mainMenuMusic.play();




        titleFont = FontParser.getFont("Immortal Bitmap Title Font", "immortal-bitmap.xml", "immortal-bitmap.png");
        titleFont.setSize(15f);

        menuButtons = new MainMenuButton[4];

        // ##################### FONT #####################
        // https://www.fontsquirrel.com/fonts/Immortal


        menuButtons[0] = new MainMenuButton("NOWA GRA", (gc.getWidth() / 2) - (128 / 2), 200, 128, 32);
        menuButtons[1] = new MainMenuButton("USTAWIENIA", (gc.getWidth() / 2) - (128 / 2), 240, 128, 32);
        menuButtons[2] = new MainMenuButton("POMOC", (gc.getWidth() / 2) - (128 / 2), 280, 128, 32);
        menuButtons[3] = new MainMenuButton("KONIEC", (gc.getWidth() / 2) - (128 / 2), 320, 128, 32);

        input = gc.getInput();

        // https://opengameart.org/content/dwarven-cursor
        gc.setMouseCursor(new Image(MainClass.RES + "mouse_cursor.png"), 0, 0);
        mouseCursor = new MouseCursor("Mouse Cursor Main Menu", input.getMouseX(), input.getMouseY(), 1, 1, ObjectType.MOUSECURSOR, input);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

        mouseCursor.update(gc, sbg, delta, 0, 0);

        for(int i = 0; i < menuButtons.length; i++) {
            if (mouseCursor.intersects(menuButtons[i])) {
                menuButtons[i].setHover(true);
                if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                    menuButtons[i].setActive(true);
                    switch (i) {
                        case 0: {
                            input.clearKeyPressedRecord();
                            sbg.enterState(MainClass.GAME, new FadeOutTransition(Color.black), new EmptyTransition());
                            break;
                        }
                        case 3: {
                            input.clearKeyPressedRecord();
                            sbg.enterState(MainClass.EXIT, new FadeOutTransition(Color.black), new EmptyTransition());
                            break;
                        }
                    }
                } else menuButtons[i].setActive(false);
            } else menuButtons[i].setHover(false);
        }

        if (input.isKeyPressed(Input.KEY_SPACE)) {
            input.clearKeyPressedRecord();
            sbg.enterState(MainClass.GAME, new FadeOutTransition(Color.black), new EmptyTransition());
        }

        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            input.clearKeyPressedRecord();
            sbg.enterState(MainClass.EXIT, new FadeOutTransition(Color.black), new EmptyTransition());
        }

//        if (input.isKeyPressed(Input.KEY_ENTER)) {
//            sfx1.play();
//        }

        if (input.isKeyPressed(Input.KEY_F1)) {
            AppGameContainer gameContainer = (AppGameContainer) gc;
            if (resolutionIndex > 0) resolutionIndex--;
            gameContainer.setDisplayMode(resolutions.get(resolutionIndex).width, resolutions.get(resolutionIndex).height, fullScreen);
            System.out.println(resolutions.get(resolutionIndex).width+":"+resolutions.get(resolutionIndex).height);
        }

        if (input.isKeyPressed(Input.KEY_F2)) {
            AppGameContainer gameContainer = (AppGameContainer) gc;
            if (resolutionIndex < resolutions.size()-1) resolutionIndex++;
            gameContainer.setDisplayMode(resolutions.get(resolutionIndex).width, resolutions.get(resolutionIndex).height, fullScreen);
            System.out.println(resolutions.get(resolutionIndex).width+":"+resolutions.get(resolutionIndex).height);
        }

        if (input.isKeyPressed(Input.KEY_F3)) {
            fullScreen = !fullScreen;
            gc.setFullscreen(fullScreen);
        }

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {

        background.draw(0, 0, windowWidth, windowHeight);
        titleFont.draw("For Fold and Sweetrolls", 70, 50);

        //titleFont.draw("AĄBCĆDEĘFGHIJKLŁMNŃOÓPRSŚTUWXYZŻŹ", 5, 5);
        //titleFont.draw("aąbcćdeęfghijklłmnńoóprsśtuwxyzżź", 5, 40);
        //titleFont.draw("1234567890~!@#$%^&*()_+-=][{}'|?/;:,.", 5, 80);

        for(MainMenuButton m: menuButtons) {
            m.render(gc, sbg, g, 0 ,0);
        }
    }

}
