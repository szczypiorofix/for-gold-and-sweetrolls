package com.szczypiorofix.sweetrolls.game.main.states;


import com.szczypiorofix.sweetrolls.game.enums.GameState;
import com.szczypiorofix.sweetrolls.game.enums.ObjectType;
import com.szczypiorofix.sweetrolls.game.gui.MainMenuButton;
import com.szczypiorofix.sweetrolls.game.gui.MouseCursor;
import com.szczypiorofix.sweetrolls.game.main.core.GameManager;
import com.szczypiorofix.sweetrolls.game.main.core.Resolution;
import com.szczypiorofix.sweetrolls.game.main.fonts.BitMapFont;
import com.szczypiorofix.sweetrolls.game.main.fonts.FontParser;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.*;

import java.util.ArrayList;


public final class MainGame extends BasicGame {

    private final DisplayMode[] modes;
    private Input input;
    private GameManager gameManager;
    private ArrayList<Resolution> resolutions;
    private MouseCursor mouseCursor;
    private Image mainMenuBackground;
    private MainMenuButton[] menuButtons;
    private BitMapFont titleFont;
    private GameMainMenu gameMainMenu;

    private int windowWidth, windowHeight;
    private GameState gameState;
    private int resolutionIndex = 0;
    private boolean fullScreen = false;

    public MainGame(String title, DisplayMode[] modes) {
        super(title);
        this.modes = modes;
        gameMainMenu = new GameMainMenu(this);
        gameManager = new GameManager(this);
        gameState = GameState.MAIN_MENU;
    }


    @Override
    public void init(GameContainer gc) throws SlickException {
        //gc.setVSync(false);
        //gc.setTargetFrameRate(0);

        input = gc.getInput();
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

        gameMainMenu.init(gc, input);

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

        gameManager.init(gc, input, mouseCursor);
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {

        if (gameState == GameState.MAIN_MENU) {
            gameMainMenu.update(gc, delta);
        }

        if (gameState == GameState.GAME) {
            gameManager.handleInputs(gc, delta);
            gameManager.handleLogic(gc, delta);
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
            gameMainMenu.render(gc, g);
        }
        if (gameState == GameState.GAME) {
            gameManager.render(gc, g);
        }

    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }


}
