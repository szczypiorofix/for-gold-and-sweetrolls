package com.szczypiorofix.sweetrolls.game.main.states;


import com.szczypiorofix.sweetrolls.game.enums.GameState;
import com.szczypiorofix.sweetrolls.game.enums.ObjectType;
import com.szczypiorofix.sweetrolls.game.gui.MainMenuButton;
import com.szczypiorofix.sweetrolls.game.gui.MouseCursor;
import com.szczypiorofix.sweetrolls.game.main.core.Resolution;
import com.szczypiorofix.sweetrolls.game.main.fonts.BitMapFont;
import com.szczypiorofix.sweetrolls.game.main.fonts.FontParser;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.*;

import java.util.ArrayList;


public final class ForGoldAndSweetrolls extends BasicGame {

    private final DisplayMode[] modes;
    private Input input;
    private FGAS_Game FGASGame;
    private ArrayList<Resolution> resolutions;
    private MouseCursor mouseCursor;
    private Image mainMenuBackground;
    private MainMenuButton[] menuButtons;
    private BitMapFont titleFont;
    private FGAS_MainMenu FGASMainMenu;

    private int windowWidth, windowHeight;
    private GameState gameState;
    private int resolutionIndex = 0;
    private boolean fullScreen = false;

    public ForGoldAndSweetrolls(String title, DisplayMode[] modes) {
        super(title);
        this.modes = modes;
        FGASMainMenu = new FGAS_MainMenu(this);
        FGASGame = new FGAS_Game(this);
        gameState = GameState.MAIN_MENU;
    }


    @Override
    public void init(GameContainer gc) throws SlickException {
        input = gc.getInput();
        windowWidth = gc.getWidth();
        windowHeight = gc.getHeight();
        fullScreen = gc.isFullscreen();

        resolutions = new ArrayList<>();
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

        FGASMainMenu.init(gc, input);

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



        gc.setMouseCursor(new Image("mouse_cursor.png"), 0, 0);
        mouseCursor = new MouseCursor("Mouse Cursor Main Menu", input.getMouseX(), input.getMouseY(), 32, 32, ObjectType.MOUSECURSOR, input);

        FGASGame.init(gc, input, mouseCursor);
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {

        if (gameState == GameState.MAIN_MENU) {
            FGASMainMenu.update(gc, delta);
        }

        if (gameState == GameState.GAME) {
            FGASGame.handleInputs(gc, delta);
            FGASGame.handleLogic(gc, delta);
        }

        if (gameState == GameState.EXIT) {
            gc.exit();
        }



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

        input.clearKeyPressedRecord();
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        if (gameState == GameState.MAIN_MENU) {
            FGASMainMenu.render(gc, g);
        }
        if (gameState == GameState.GAME) {
            FGASGame.render(gc, g);
        }

    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }


}
