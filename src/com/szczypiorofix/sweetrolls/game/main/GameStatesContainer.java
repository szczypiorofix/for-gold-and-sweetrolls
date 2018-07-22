package com.szczypiorofix.sweetrolls.game.main;

import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class GameStatesContainer extends StateBasedGame {

    private DisplayMode[] modes;

    public GameStatesContainer(DisplayMode[] modes, String name) {
        super(name);
        this.modes = modes;
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        this.addState(new GameMainMenu(modes));
        this.addState(new ExitGame());
        this.addState(new MainGame());
    }
}
