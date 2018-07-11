package com.szczypiorofix.racoon.game.main;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.EmptyTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class GameMainMenu extends BasicGameState {

    private Input input;
    private Image background;
    private boolean serverStatus = false;
    private String serverStatusMsg = "offline";


    @Override
    public int getID() {
        return MainClass.MAINMENU;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        background = new Image("res/background.png");
        input = gc.getInput();

        gc.setTargetFrameRate(60);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

        if (input.isKeyPressed(Input.KEY_SPACE)) {
            input.clearKeyPressedRecord();
            sbg.enterState(MainClass.GAME, new FadeOutTransition(Color.black), new EmptyTransition());
        }

        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            input.clearKeyPressedRecord();
            sbg.enterState(MainClass.EXIT, new FadeOutTransition(Color.black), new EmptyTransition());
        }

        if (serverStatus) serverStatusMsg = "online";
        else serverStatusMsg = "offline";
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

        background.draw(0, 0, gc.getWidth(), gc.getHeight());

        g.drawString("Server status: "+serverStatusMsg, 10, 30);
    }

}
