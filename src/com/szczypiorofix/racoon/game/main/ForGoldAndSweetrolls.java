package com.szczypiorofix.racoon.game.main;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class ForGoldAndSweetrolls extends StateBasedGame {

    ForGoldAndSweetrolls(String name) {
        super(name);
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        this.addState(new GameMainMenu());
        this.addState(new ExitGame());
        this.addState(new MainGame());
    }

}
