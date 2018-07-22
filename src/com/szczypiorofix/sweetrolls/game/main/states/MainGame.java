package com.szczypiorofix.sweetrolls.game.main.states;


import com.szczypiorofix.sweetrolls.game.main.core.GameManager;
import com.szczypiorofix.sweetrolls.game.main.MainClass;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public final class MainGame extends BasicGameState {

    private GameManager gameManager;


    @Override
    public int getID() {
        return MainClass.GAME;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) {
        gameManager = new GameManager();
        gameManager.init(gc);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        gameManager.handleInputs(gc, sbg, delta);
        gameManager.handleLogic(gc, sbg, delta);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        gameManager.render(gc, sbg, g);
    }

}
