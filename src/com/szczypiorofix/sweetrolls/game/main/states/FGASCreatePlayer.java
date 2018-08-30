/*
 * Developed by szczypiorofix on 30.08.18 14:41.
 * Copyright (c) 2018. All rights reserved.
 *
 */

package com.szczypiorofix.sweetrolls.game.main.states;

import com.szczypiorofix.sweetrolls.game.enums.GameState;
import com.szczypiorofix.sweetrolls.game.gui.MainMenuButton;
import com.szczypiorofix.sweetrolls.game.gui.MouseCursor;
import com.szczypiorofix.sweetrolls.game.main.graphics.Textures;
import org.newdawn.slick.*;

public class FGASCreatePlayer {

    private Input input;
    private MouseCursor mouseCursor;
    private Image hudImage;
    private FGASMainMenu fgasMainMenu;
    private ForGoldAndSweetrolls forGoldAndSweetrolls;
    private MainMenuButton[] buttons;

    public FGASCreatePlayer(ForGoldAndSweetrolls forGoldAndSweetrolls, FGASMainMenu fgasMainMenu) {
        this.forGoldAndSweetrolls = forGoldAndSweetrolls;
        this.fgasMainMenu = fgasMainMenu;
    }


    public void init(GameContainer gc, Input input, MouseCursor mouseCursor) throws SlickException {
        this.input = input;
        this.mouseCursor = mouseCursor;
        hudImage = Textures.getInstance().creationGUI;

        buttons = new MainMenuButton[2];
        buttons[0] = new MainMenuButton("Anuluj", 60, 540);
        buttons[1] = new MainMenuButton("OK", 600, 540);
    }

    public void update(GameContainer gc, int delta) throws SlickException {
        mouseCursor.update(delta, 0, 0);

        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            forGoldAndSweetrolls.setGameState(GameState.MAIN_MENU);
        }
        if (input.isKeyPressed(Input.KEY_ENTER)) {
            fgasMainMenu.loadGame(false);
            forGoldAndSweetrolls.setGameState(GameState.GAME);
        }

        for (int i = 0; i < buttons.length; i++) {
            if (mouseCursor.intersects(buttons[i])) {
                buttons[i].setHover(true);
                if (mouseCursor.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                    buttons[i].setActive(true);
                    switch (i) {
                        case 0: {
                            forGoldAndSweetrolls.setGameState(GameState.MAIN_MENU);
                            break;
                        }
                        case 1: {
                            fgasMainMenu.loadGame(false);
                            forGoldAndSweetrolls.setGameState(GameState.GAME);
                            break;
                        }
                    }
                } else buttons[i].setActive(false);
            } else buttons[i].setHover(false);
        }
    }

    public void render(GameContainer gc, Graphics g) throws SlickException {
        hudImage.draw(0, 0);
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].render(g, 0, 0);
        }
    }

}
