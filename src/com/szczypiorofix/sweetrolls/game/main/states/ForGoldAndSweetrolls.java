package com.szczypiorofix.sweetrolls.game.main.states;


import com.szczypiorofix.sweetrolls.game.enums.GameState;
import com.szczypiorofix.sweetrolls.game.enums.ObjectType;
import com.szczypiorofix.sweetrolls.game.gui.MouseCursor;
import com.szczypiorofix.sweetrolls.game.main.core.Configuration;
import com.szczypiorofix.sweetrolls.game.main.fonts.BitMapFont;
import com.szczypiorofix.sweetrolls.game.main.fonts.FontParser;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.*;

import java.util.ArrayList;


public final class ForGoldAndSweetrolls extends BasicGame {

    private Input input;
    private FGAS_Game FGASGame;
    private MouseCursor mouseCursor;
    private BitMapFont titleFont;
    private FGAS_MainMenu FGASMainMenu;

    private GameState gameState;
    private Music gameMusic, mainMenuMusic;
    private float musicVolume = 1f;

    public ForGoldAndSweetrolls(String title, ArrayList<DisplayMode> modes, Configuration config) {
        super(title);
        FGASMainMenu = new FGAS_MainMenu(this, config, modes);
        FGASGame = new FGAS_Game(this);
        gameState = GameState.MAIN_MENU;
    }


    @Override
    public void init(GameContainer gc) throws SlickException {
        input = gc.getInput();

        gameMusic = new Music("music/ex-11-towards_heaven_2017.xm");

        // https://modarchive.org/index.php?request=view_by_moduleid&query=59256  - 0rigin.xm
        mainMenuMusic = new Music("music/mm-0rigin.xm");

        mainMenuMusic.loop(1f, musicVolume);

        titleFont = FontParser.getFont("Immortal Bitmap Title Font", "immortal-bitmap.xml", "immortal-bitmap.png");
        titleFont.setSize(4.1f);

        // ##################### FONT #####################
        // https://www.fontsquirrel.com/fonts/Immortal

        gc.setMouseCursor(new Image("mouse_cursor.png"), 0, 0);
        mouseCursor = new MouseCursor("Mouse Cursor Main Menu", input.getMouseX(), input.getMouseY(), 32, 32, ObjectType.MOUSECURSOR, input);

        FGASMainMenu.init(gc, input, mouseCursor);
        FGASGame.init(gc, input, mouseCursor);
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {

        if (gameState == GameState.MAIN_MENU) {
            FGASMainMenu.update(gc, delta);
        } else if (gameState == GameState.GAME) {
            FGASGame.handleInputs(gc, delta);
            FGASGame.handleLogic(gc, delta);
        }

        if (gameState == GameState.EXIT) {
            gc.exit();
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

    public void setGameState(GameState gameState){
        switch (gameState) {
            case MAIN_MENU: {
                gameMusic.stop();
                mainMenuMusic.loop(1f, musicVolume);
                break;
            }
            case GAME: {
                mainMenuMusic.stop();
                gameMusic.loop(1f, musicVolume);
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

    public FGAS_Game getFGASGame() {
        return FGASGame;
    }

    public FGAS_MainMenu getFGASMainMenu() {
        return FGASMainMenu;
    }

    public float getMusicVolume() {
        return musicVolume;
    }

    public void setMusicVolume(float musicVolume) {
        this.musicVolume = musicVolume;
    }
}
