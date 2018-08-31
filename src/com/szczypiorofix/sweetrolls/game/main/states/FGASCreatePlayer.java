/*
 * Developed by szczypiorofix on 30.08.18 14:41.
 * Copyright (c) 2018. All rights reserved.
 *
 */

package com.szczypiorofix.sweetrolls.game.main.states;

import com.szczypiorofix.sweetrolls.game.enums.CharacterSex;
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
    private int currentAvatarSpriteSheetId = 0;
    private int currentAvatarSpriteSheetColumns = 0;
    private SpriteSheet currentSpriteSheet = null;
    private CharacterSex currentSex = CharacterSex.MALE;

    public FGASCreatePlayer(ForGoldAndSweetrolls forGoldAndSweetrolls, FGASMainMenu fgasMainMenu) {
        this.forGoldAndSweetrolls = forGoldAndSweetrolls;
        this.fgasMainMenu = fgasMainMenu;
    }


    public void init(GameContainer gc, Input input, MouseCursor mouseCursor) throws SlickException {
        this.input = input;
        this.mouseCursor = mouseCursor;
        hudImage = Textures.getInstance().creationGUI;

        if (currentSex == CharacterSex.MALE) currentSpriteSheet = Textures.getInstance().avatarsMales;
        else currentSpriteSheet = Textures.getInstance().avatarsFemales;

        currentAvatarSpriteSheetColumns = currentSpriteSheet.getHorizontalCount();
        currentImage = currentSpriteSheet.getSprite(currentAvatarSpriteSheetId % currentAvatarSpriteSheetColumns, currentAvatarSpriteSheetId / currentAvatarSpriteSheetColumns);

        // Avatars
        // https://www.deviantart.com/artastrophe/art/BG-Portrait-Package-145216289

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
                        case 2: {
                            currentAvatarSpriteSheetId--;
                            if (currentAvatarSpriteSheetId < 0) {
                                currentAvatarSpriteSheetId = (currentSpriteSheet.getHorizontalCount() * currentSpriteSheet.getVerticalCount()) - 1;
                            }
                            currentImage = currentSpriteSheet.getSprite(currentAvatarSpriteSheetId % currentAvatarSpriteSheetColumns, currentAvatarSpriteSheetId / currentAvatarSpriteSheetColumns);
                            break;
                        }
                        case 3: {
                            currentAvatarSpriteSheetId++;
                            if (currentAvatarSpriteSheetId >= currentSpriteSheet.getHorizontalCount() * currentSpriteSheet.getVerticalCount()) {
                                currentAvatarSpriteSheetId = 0;
                            }
                            currentImage = currentSpriteSheet.getSprite(currentAvatarSpriteSheetId % currentAvatarSpriteSheetColumns, currentAvatarSpriteSheetId / currentAvatarSpriteSheetColumns);
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