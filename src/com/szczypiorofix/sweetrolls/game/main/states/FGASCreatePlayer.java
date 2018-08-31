/*
 * Developed by szczypiorofix on 30.08.18 14:41.
 * Copyright (c) 2018. All rights reserved.
 *
 */

package com.szczypiorofix.sweetrolls.game.main.states;

import com.szczypiorofix.sweetrolls.game.enums.GameState;
import com.szczypiorofix.sweetrolls.game.gui.MainMenuControlls;
import com.szczypiorofix.sweetrolls.game.gui.MouseCursor;
import com.szczypiorofix.sweetrolls.game.main.graphics.Textures;
import org.newdawn.slick.*;

public class FGASCreatePlayer {

    private Input input;
    private MouseCursor mouseCursor;
    private Image hudImage;
    private FGASMainMenu fgasMainMenu;
    private ForGoldAndSweetrolls forGoldAndSweetrolls;
    private MainMenuControlls[] controlls;
    private Image currentImage = null;
    private int avatarSpriteSheetY = 0;

    public FGASCreatePlayer(ForGoldAndSweetrolls forGoldAndSweetrolls, FGASMainMenu fgasMainMenu) {
        this.forGoldAndSweetrolls = forGoldAndSweetrolls;
        this.fgasMainMenu = fgasMainMenu;
    }


    public void init(GameContainer gc, Input input, MouseCursor mouseCursor) throws SlickException {
        this.input = input;
        this.mouseCursor = mouseCursor;
        hudImage = Textures.getInstance().creationGUI;

        currentImage = Textures.getInstance().avatartsMales.getSprite(0, avatarSpriteSheetY);

        // Avatars
        // https://opengameart.org/content/60-terrible-character-portraits

        controlls = new MainMenuControlls[4];
        controlls[0] = new MainMenuControlls(MainMenuControlls.ControlType.CANCEL, "", false, 330, 540);
        controlls[1] = new MainMenuControlls(MainMenuControlls.ControlType.OK, "", false, 380, 540);
        controlls[2] = new MainMenuControlls(MainMenuControlls.ControlType.LEF_ARROW, "", false, 120, 260);
        controlls[3] = new MainMenuControlls(MainMenuControlls.ControlType.RIGHT_ARROW, "", false, 270, 260);
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

        for (int i = 0; i < controlls.length; i++) {
            if (mouseCursor.intersects(controlls[i])) {
                controlls[i].setHover(true);
                if (mouseCursor.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                    controlls[i].setActive(true);
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
                } else controlls[i].setActive(false);
            } else controlls[i].setHover(false);
        }
    }

    public void render(GameContainer gc, Graphics g) throws SlickException {
        hudImage.draw(0, 0);
        for (int i = 0; i < controlls.length; i++) {
            controlls[i].render(g, 0, 0);
        }
        currentImage.draw(170, 150);
    }

}
