package com.szczypiorofix.sweetrolls.game.main;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class ExitGame extends BasicGameState {


    @Override
    public int getID() {
        return MainClass.EXIT;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) {

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {

    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) {
        gc.exit();
    }
}
