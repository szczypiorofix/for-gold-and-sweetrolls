package com.szczypiorofix.racoon.game.main;


import org.newdawn.slick.*;

public class RacoonGame extends BasicGame {

    private GameManager gameManager;

    RacoonGame(String title) {
        super(title);
        gameManager = new GameManager();
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        gameManager.init(gc);
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
        gameManager.handleInputs(gc, delta);
        gameManager.handleLogic(gc, delta);
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        gameManager.render(gc, g);
    }

}
