/*
 * Developed by szczypiorofix on 30.08.18 14:41.
 * Copyright (c) 2018. All rights reserved.
 *
 */

package com.szczypiorofix.sweetrolls.game.main.states;

import com.szczypiorofix.sweetrolls.game.enums.CharacterRace;
import com.szczypiorofix.sweetrolls.game.enums.CharacterSex;
import com.szczypiorofix.sweetrolls.game.enums.GameState;
import com.szczypiorofix.sweetrolls.game.gui.MainMenuButton;
import com.szczypiorofix.sweetrolls.game.gui.MainMenuControlls;
import com.szczypiorofix.sweetrolls.game.gui.MouseCursor;
import com.szczypiorofix.sweetrolls.game.main.core.NameGenerator;
import com.szczypiorofix.sweetrolls.game.main.graphics.Textures;
import org.newdawn.slick.*;

public class FGASCreatePlayer {

    private Input input;
    private MouseCursor mouseCursor;
    private Image hudImage;
    private FGASMainMenu fgasMainMenu;
    private ForGoldAndSweetrolls forGoldAndSweetrolls;
    private MainMenuControlls[] controlls;
    private Image currentPortraitImage = null;
    private int currentAvatarSpriteSheetId = 0;
    private int currentAvatarSpriteSheetColumns = 0;
    private SpriteSheet currentSpriteSheet = null;
    private CharacterSex currentSex = CharacterSex.MALE;
    private CharacterRace currentRace = CharacterRace.HUMAN;
    private MainMenuButton generateRandomName;
    private NameGenerator nameGenerator;
    private String currentName;

    public FGASCreatePlayer(ForGoldAndSweetrolls forGoldAndSweetrolls, FGASMainMenu fgasMainMenu) {
        this.forGoldAndSweetrolls = forGoldAndSweetrolls;
        this.fgasMainMenu = fgasMainMenu;
        nameGenerator = new NameGenerator();
        currentName = nameGenerator.getRandomName(currentSex, currentRace);
    }


    public void init(GameContainer gc, Input input, MouseCursor mouseCursor) throws SlickException {
        this.input = input;
        this.mouseCursor = mouseCursor;
        hudImage = Textures.getInstance().creationGUI;

        currentSpriteSheet = Textures.getInstance().avatarsHumanMales;

        currentAvatarSpriteSheetColumns = currentSpriteSheet.getHorizontalCount();
        currentPortraitImage = currentSpriteSheet.getSprite(currentAvatarSpriteSheetId % currentAvatarSpriteSheetColumns, currentAvatarSpriteSheetId / currentAvatarSpriteSheetColumns);

        // Avatars
        // https://www.deviantart.com/artastrophe/art/BG-Portrait-Package-145216289

        generateRandomName = new MainMenuButton("Losowe imię", 540, 50);

        controlls = new MainMenuControlls[13];
        controlls[0] = new MainMenuControlls(MainMenuControlls.ControlType.CANCEL, "", false, 330, 540);
        controlls[1] = new MainMenuControlls(MainMenuControlls.ControlType.OK, "", false, 380, 540);

        // portrait controlls
        controlls[2] = new MainMenuControlls(MainMenuControlls.ControlType.LEF_ARROW, "", false, 80, 320);
        controlls[3] = new MainMenuControlls(MainMenuControlls.ControlType.RIGHT_ARROW, "", false, 220, 320);

        // character sex
        controlls[4] = new MainMenuControlls(MainMenuControlls.ControlType.TEXT, "Mężczyzna", false, 420, 100);
        controlls[5] = new MainMenuControlls(MainMenuControlls.ControlType.RADIO_BUTTON, "", currentSex.equals(CharacterSex.MALE), 450, 130);
        controlls[6] = new MainMenuControlls(MainMenuControlls.ControlType.TEXT, "Kobieta", false, 600, 100);
        controlls[7] = new MainMenuControlls(MainMenuControlls.ControlType.RADIO_BUTTON, "", currentSex.equals(CharacterSex.FEMALE), 625, 130);

        // character name
        controlls[8] = new MainMenuControlls(MainMenuControlls.ControlType.TEXT, currentName, false, 420, 20);

        // character race
        controlls[9] = new MainMenuControlls(MainMenuControlls.ControlType.TEXT, "Człowiek", false, 420, 180);
        controlls[10] = new MainMenuControlls(MainMenuControlls.ControlType.RADIO_BUTTON, "", currentRace.equals(CharacterRace.HUMAN), 450, 210);
        controlls[11] = new MainMenuControlls(MainMenuControlls.ControlType.TEXT, "Elf", false, 625, 180);
        controlls[12] = new MainMenuControlls(MainMenuControlls.ControlType.RADIO_BUTTON, "", currentRace.equals(CharacterRace.ELF), 625, 210);

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

        if (mouseCursor.intersects(generateRandomName)) {
            generateRandomName.setHover(true);
            if (mouseCursor.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                generateRandomName.setActive(true);
                currentName = nameGenerator.getRandomName(currentSex, currentRace);
                controlls[8].setText(currentName);

            } else generateRandomName.setActive(false);
        } else generateRandomName.setHover(false);

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
                            //fgasMainMenu.loadGame(false);
                            forGoldAndSweetrolls.getFGASGame().restartGame();
                            forGoldAndSweetrolls.getFGASGame().setPlayerName(currentName);
                            forGoldAndSweetrolls.setGameState(GameState.GAME);
                            break;
                        }
                        case 2: {
                            currentAvatarSpriteSheetId--;
                            if (currentAvatarSpriteSheetId < 0) {
                                currentAvatarSpriteSheetId = (currentSpriteSheet.getHorizontalCount() * currentSpriteSheet.getVerticalCount()) - 1;
                            }
                            currentPortraitImage = currentSpriteSheet.getSprite(currentAvatarSpriteSheetId % currentAvatarSpriteSheetColumns, currentAvatarSpriteSheetId / currentAvatarSpriteSheetColumns);
                            break;
                        }
                        case 3: {
                            currentAvatarSpriteSheetId++;
                            if (currentAvatarSpriteSheetId >= currentSpriteSheet.getHorizontalCount() * currentSpriteSheet.getVerticalCount()) {
                                currentAvatarSpriteSheetId = 0;
                            }
                            currentPortraitImage = currentSpriteSheet.getSprite(currentAvatarSpriteSheetId % currentAvatarSpriteSheetColumns, currentAvatarSpriteSheetId / currentAvatarSpriteSheetColumns);
                            break;
                        }
                        case 5: {
                            currentSex = CharacterSex.MALE;
                            controlls[7].setChecked(false);
                            controlls[5].setChecked(true);
                            if (currentRace == CharacterRace.ELF) currentSpriteSheet = Textures.getInstance().avatarsElfMales;
                            else currentSpriteSheet = Textures.getInstance().avatarsHumanMales;
                            currentAvatarSpriteSheetId = 0;
                            currentPortraitImage = currentSpriteSheet.getSprite(currentAvatarSpriteSheetId % currentAvatarSpriteSheetColumns, currentAvatarSpriteSheetId / currentAvatarSpriteSheetColumns);
                            break;
                        }
                        case 7: {
                            currentSex = CharacterSex.FEMALE;
                            controlls[7].setChecked(true);
                            controlls[5].setChecked(false);
                            if (currentRace == CharacterRace.ELF) currentSpriteSheet = Textures.getInstance().avatarsElfFemales;
                            else currentSpriteSheet = Textures.getInstance().avatarsHumanFemales;
                            currentAvatarSpriteSheetId = 0;
                            currentPortraitImage = currentSpriteSheet.getSprite(currentAvatarSpriteSheetId % currentAvatarSpriteSheetColumns, currentAvatarSpriteSheetId / currentAvatarSpriteSheetColumns);
                            break;
                        }
                        case 10: {
                            currentRace = CharacterRace.HUMAN;
                            controlls[10].setChecked(true);
                            controlls[12].setChecked(false);
                            if (currentSex == CharacterSex.FEMALE) currentSpriteSheet = Textures.getInstance().avatarsHumanFemales;
                            else currentSpriteSheet = Textures.getInstance().avatarsHumanMales;
                            currentAvatarSpriteSheetId = 0;
                            currentPortraitImage = currentSpriteSheet.getSprite(currentAvatarSpriteSheetId % currentAvatarSpriteSheetColumns, currentAvatarSpriteSheetId / currentAvatarSpriteSheetColumns);
                            break;
                        }
                        case 12: {
                            currentRace = CharacterRace.ELF;
                            controlls[10].setChecked(false);
                            controlls[12].setChecked(false);
                            if (currentSex == CharacterSex.FEMALE) currentSpriteSheet = Textures.getInstance().avatarsElfFemales;
                            else currentSpriteSheet = Textures.getInstance().avatarsElfMales;
                            currentAvatarSpriteSheetId = 0;
                            currentPortraitImage = currentSpriteSheet.getSprite(currentAvatarSpriteSheetId % currentAvatarSpriteSheetColumns, currentAvatarSpriteSheetId / currentAvatarSpriteSheetColumns);
                            break;
                        }
                    }
                } else controlls[i].setActive(false);
            } else controlls[i].setHover(false);
        }
    }

    public void render(GameContainer gc, Graphics g) throws SlickException {
        currentPortraitImage.draw(80, 40);
        hudImage.draw(0, 0);
        for (int i = 0; i < controlls.length; i++) {
            controlls[i].render(g, 0, 0);
        }
        generateRandomName.render(g, 0, 0);
    }

    public String getCurrentName() {
        return currentName;
    }
}
