/*
 * Developed by szczypiorofix on 09.09.18 00:04.
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
import com.szczypiorofix.sweetrolls.game.main.sounds.SFX;
import org.newdawn.slick.*;

public class FGASCreatePlayer {

    private final int MIN_STRENGTH = 10, MIN_DEXTERITY = 10, MIN_CONSTITUTION = 10, MIN_INTELLIGENCE = 10;
    private final int MAX_STAT_POINTS = 20;
    private final int MAX_SKILLS_POINTS = 6;
    private Input input;
    private MouseCursor mouseCursor;
    private Image hudImage;
    private FGASMainMenu fgasMainMenu;
    private ForGoldAndSweetrolls forGoldAndSweetrolls;
    private MainMenuControlls[] mainConstrolls, raceControlls, sexControlls, statisticsControlls, skillsControlls;
    private Image currentPortraitImage = null;
    private int currentAvatarSpriteSheetId = 0;
    private int currentAvatarSpriteSheetColumns = 0;
    private SpriteSheet currentSpriteSheet = null;
    private CharacterSex currentSex = CharacterSex.MALE;
    private CharacterRace currentRace = CharacterRace.HUMAN;
    private MainMenuButton generateRandomName;
    private NameGenerator nameGenerator;
    private String currentName;
    private int currentStr, currentDex, currentCon, currentInt, leftStatsPoints, leftSkillsPoints;
    private int currentAlchemy, currentLockpicking, currentSpeech, currentArchery, currentSurvival, currentSword, currentShield, currentArmor;
    private SFX sfx;

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

        sfx = new SFX("hit.ogg");

        leftStatsPoints = MAX_STAT_POINTS;
        currentStr = MIN_STRENGTH;
        currentDex = MIN_DEXTERITY;
        currentCon = MIN_CONSTITUTION;
        currentInt = MIN_INTELLIGENCE;

        leftSkillsPoints = MAX_SKILLS_POINTS;
        currentAlchemy = 0;
        currentArchery = 0;
        currentArmor = 0;
        currentLockpicking = 0;
        currentSpeech = 0;
        currentShield = 0;
        currentSurvival = 0;
        currentSword = 0;

        currentSpriteSheet = Textures.getInstance().avatarsMales;

        currentAvatarSpriteSheetColumns = currentSpriteSheet.getHorizontalCount();
        currentPortraitImage = currentSpriteSheet.getSprite(currentAvatarSpriteSheetId % currentAvatarSpriteSheetColumns, currentAvatarSpriteSheetId / currentAvatarSpriteSheetColumns);

        generateRandomName = new MainMenuButton("Losowe imię", 620, 15);

        // ########## MAIN CONTROLLS
        mainConstrolls = new MainMenuControlls[5];
        mainConstrolls[0] = new MainMenuControlls(MainMenuControlls.ControlType.CANCEL, "", false, 530, 550);
        mainConstrolls[1] = new MainMenuControlls(MainMenuControlls.ControlType.OK, "", false, 580, 550);
        mainConstrolls[2] = new MainMenuControlls(MainMenuControlls.ControlType.LEF_ARROW, "", false, 40, 320);
        mainConstrolls[3] = new MainMenuControlls(MainMenuControlls.ControlType.RIGHT_ARROW, "", false, 180, 320);
        mainConstrolls[4] = new MainMenuControlls(MainMenuControlls.ControlType.TEXT, currentName, false, 420, 20);

        // ########## CHARACTER SEX CONTROLLS
        sexControlls = new MainMenuControlls[4];
        sexControlls[0] = new MainMenuControlls(MainMenuControlls.ControlType.TEXT, "Mężczyzna", false, 420, 70);
        sexControlls[1] = new MainMenuControlls(MainMenuControlls.ControlType.RADIO_BUTTON, "", currentSex.equals(CharacterSex.MALE), 450, 100);
        sexControlls[2] = new MainMenuControlls(MainMenuControlls.ControlType.TEXT, "Kobieta", false, 600, 70);
        sexControlls[3] = new MainMenuControlls(MainMenuControlls.ControlType.RADIO_BUTTON, "", currentSex.equals(CharacterSex.FEMALE), 625, 100);

        // ########## CHARACTER RACE CONTROLLS
        raceControlls = new MainMenuControlls[10];
        raceControlls[0] = new MainMenuControlls(MainMenuControlls.ControlType.TEXT, "Człowiek", false, 260, 165);
        raceControlls[1] = new MainMenuControlls(MainMenuControlls.ControlType.RADIO_BUTTON, "", currentRace.equals(CharacterRace.HUMAN), 290, 195);
        raceControlls[2] = new MainMenuControlls(MainMenuControlls.ControlType.TEXT, "Elf", false, 400, 165);
        raceControlls[3] = new MainMenuControlls(MainMenuControlls.ControlType.RADIO_BUTTON, "", currentRace.equals(CharacterRace.ELF), 400, 195);
        raceControlls[4] = new MainMenuControlls(MainMenuControlls.ControlType.TEXT, "Krasnolud", false, 475, 165);
        raceControlls[5] = new MainMenuControlls(MainMenuControlls.ControlType.RADIO_BUTTON, "", currentRace.equals(CharacterRace.DWARF), 510, 195);
        raceControlls[6] = new MainMenuControlls(MainMenuControlls.ControlType.TEXT, "Niziołek", false, 595, 165);
        raceControlls[7] = new MainMenuControlls(MainMenuControlls.ControlType.RADIO_BUTTON, "", currentRace.equals(CharacterRace.HALFLING), 620, 195);
        raceControlls[8] = new MainMenuControlls(MainMenuControlls.ControlType.TEXT, "Gnom", false, 715, 165);
        raceControlls[9] = new MainMenuControlls(MainMenuControlls.ControlType.RADIO_BUTTON, "", currentRace.equals(CharacterRace.GNOME), 730, 195);

        // ########## STATISTICS CONTROLLS
        statisticsControlls = new MainMenuControlls[18];
        statisticsControlls[0] = new MainMenuControlls(MainMenuControlls.ControlType.TEXT, "Punkty:", false, 30, 380);
        statisticsControlls[1] = new MainMenuControlls(MainMenuControlls.ControlType.TEXT, leftStatsPoints +"", false, 190, 380);

        statisticsControlls[2] = new MainMenuControlls(MainMenuControlls.ControlType.TEXT, "Siła", false, 30, 420);
        statisticsControlls[3] = new MainMenuControlls(MainMenuControlls.ControlType.TEXT, currentStr + "", false, 190, 420);
        statisticsControlls[4] = new MainMenuControlls(MainMenuControlls.ControlType.UP_ARROW, "", false, 230, 415);
        statisticsControlls[5] = new MainMenuControlls(MainMenuControlls.ControlType.DOWN_ARROW, "", false, 270, 415);

        statisticsControlls[6] = new MainMenuControlls(MainMenuControlls.ControlType.TEXT, "Zręczność", false, 30, 450);
        statisticsControlls[7] = new MainMenuControlls(MainMenuControlls.ControlType.TEXT, currentDex + "", false, 190, 450);
        statisticsControlls[8] = new MainMenuControlls(MainMenuControlls.ControlType.UP_ARROW, "", false, 230, 445);
        statisticsControlls[9] = new MainMenuControlls(MainMenuControlls.ControlType.DOWN_ARROW, "", false, 270, 445);

        statisticsControlls[10] = new MainMenuControlls(MainMenuControlls.ControlType.TEXT, "Wytrzymałość", false, 30, 480);
        statisticsControlls[11] = new MainMenuControlls(MainMenuControlls.ControlType.TEXT, currentCon + "", false, 190, 480);
        statisticsControlls[12] = new MainMenuControlls(MainMenuControlls.ControlType.UP_ARROW, "", false, 230, 475);
        statisticsControlls[13] = new MainMenuControlls(MainMenuControlls.ControlType.DOWN_ARROW, "", false, 270, 475);

        statisticsControlls[14] = new MainMenuControlls(MainMenuControlls.ControlType.TEXT, "Inteligencja", false, 30, 510);
        statisticsControlls[15] = new MainMenuControlls(MainMenuControlls.ControlType.TEXT, currentInt + "", false, 190, 510);
        statisticsControlls[16] = new MainMenuControlls(MainMenuControlls.ControlType.UP_ARROW, "", false, 230, 505);
        statisticsControlls[17] = new MainMenuControlls(MainMenuControlls.ControlType.DOWN_ARROW, "", false, 270, 505);

        // ########## SKILLS CONTROLLS
        skillsControlls = new MainMenuControlls[34];
        skillsControlls[0] = new MainMenuControlls(MainMenuControlls.ControlType.TEXT, "Punkty:", false, 460, 260);
        skillsControlls[1] = new MainMenuControlls(MainMenuControlls.ControlType.TEXT, leftSkillsPoints +"", false, 620, 260);

        skillsControlls[2] = new MainMenuControlls(MainMenuControlls.ControlType.TEXT, "Alchemia", false, 460, 300);
        skillsControlls[3] = new MainMenuControlls(MainMenuControlls.ControlType.TEXT, currentAlchemy + "", false, 620, 300);
        skillsControlls[4] = new MainMenuControlls(MainMenuControlls.ControlType.UP_ARROW, "", false, 660, 295);
        skillsControlls[5] = new MainMenuControlls(MainMenuControlls.ControlType.DOWN_ARROW, "", false, 700, 295);

        skillsControlls[6] = new MainMenuControlls(MainMenuControlls.ControlType.TEXT, "Otw. zamków", false, 460, 330);
        skillsControlls[7] = new MainMenuControlls(MainMenuControlls.ControlType.TEXT, currentLockpicking + "", false, 620, 330);
        skillsControlls[8] = new MainMenuControlls(MainMenuControlls.ControlType.UP_ARROW, "", false, 660, 325);
        skillsControlls[9] = new MainMenuControlls(MainMenuControlls.ControlType.DOWN_ARROW, "", false, 700, 325);

        skillsControlls[10] = new MainMenuControlls(MainMenuControlls.ControlType.TEXT, "Miecze", false, 460, 360);
        skillsControlls[11] = new MainMenuControlls(MainMenuControlls.ControlType.TEXT, currentSword + "", false, 620, 360);
        skillsControlls[12] = new MainMenuControlls(MainMenuControlls.ControlType.UP_ARROW, "", false, 660, 355);
        skillsControlls[13] = new MainMenuControlls(MainMenuControlls.ControlType.DOWN_ARROW, "", false, 700, 355);

        skillsControlls[14] = new MainMenuControlls(MainMenuControlls.ControlType.TEXT, "Zbroja", false, 460, 390);
        skillsControlls[15] = new MainMenuControlls(MainMenuControlls.ControlType.TEXT, currentArmor + "", false, 620, 390);
        skillsControlls[16] = new MainMenuControlls(MainMenuControlls.ControlType.UP_ARROW, "", false, 660, 385);
        skillsControlls[17] = new MainMenuControlls(MainMenuControlls.ControlType.DOWN_ARROW, "", false, 700, 385);

        skillsControlls[18] = new MainMenuControlls(MainMenuControlls.ControlType.TEXT, "Tarcze", false, 460, 420);
        skillsControlls[19] = new MainMenuControlls(MainMenuControlls.ControlType.TEXT, currentShield + "", false, 620, 420);
        skillsControlls[20] = new MainMenuControlls(MainMenuControlls.ControlType.UP_ARROW, "", false, 660, 415);
        skillsControlls[21] = new MainMenuControlls(MainMenuControlls.ControlType.DOWN_ARROW, "", false, 700, 415);

        skillsControlls[22] = new MainMenuControlls(MainMenuControlls.ControlType.TEXT, "Surwiwal", false, 460, 450);
        skillsControlls[23] = new MainMenuControlls(MainMenuControlls.ControlType.TEXT, currentSurvival + "", false, 620, 450);
        skillsControlls[24] = new MainMenuControlls(MainMenuControlls.ControlType.UP_ARROW, "", false, 660, 445);
        skillsControlls[25] = new MainMenuControlls(MainMenuControlls.ControlType.DOWN_ARROW, "", false, 700, 445);

        skillsControlls[26] = new MainMenuControlls(MainMenuControlls.ControlType.TEXT, "Retoryka", false, 460, 480);
        skillsControlls[27] = new MainMenuControlls(MainMenuControlls.ControlType.TEXT, currentSpeech + "", false, 620, 480);
        skillsControlls[28] = new MainMenuControlls(MainMenuControlls.ControlType.UP_ARROW, "", false, 660, 475);
        skillsControlls[29] = new MainMenuControlls(MainMenuControlls.ControlType.DOWN_ARROW, "", false, 700, 475);

        skillsControlls[30] = new MainMenuControlls(MainMenuControlls.ControlType.TEXT, "Łucznictwo", false, 460, 510);
        skillsControlls[31] = new MainMenuControlls(MainMenuControlls.ControlType.TEXT, currentArchery + "", false, 620, 510);
        skillsControlls[32] = new MainMenuControlls(MainMenuControlls.ControlType.UP_ARROW, "", false, 660, 505);
        skillsControlls[33] = new MainMenuControlls(MainMenuControlls.ControlType.DOWN_ARROW, "", false, 700, 505);
    }

    public void update(GameContainer gc, int delta) throws SlickException {
        mouseCursor.update(delta, 0, 0);

        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            forGoldAndSweetrolls.setGameState(GameState.MAIN_MENU);
        }
//        if (input.isKeyPressed(Input.KEY_ENTER)) {
//            fgasMainMenu.loadGame(false);
//            forGoldAndSweetrolls.setGameState(GameState.GAME);
//        }

        if (mouseCursor.intersects(generateRandomName)) {
            generateRandomName.setHover(true);
            if (mouseCursor.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                generateRandomName.setActive(true);
                currentName = nameGenerator.getRandomName(currentSex, currentRace);
                mainConstrolls[4].setText(currentName);

            } else generateRandomName.setActive(false);
        } else generateRandomName.setHover(false);

        for (int i = 0; i < mainConstrolls.length; i++) {
            if (mouseCursor.intersects(mainConstrolls[i])) {
                mainConstrolls[i].setHover(true);
                if (mouseCursor.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                    mainConstrolls[i].setActive(true);
                    switch (i) {
                        case 0: {
                            forGoldAndSweetrolls.setGameState(GameState.MAIN_MENU);
                            break;
                        }
                        case 1: {
                            //if (leftStatsPoints == 0 && leftSkillsPoints == 0) {
                                //fgasMainMenu.loadGame(false);


                            // TODO Coś z tym trzeba zeobić


                                forGoldAndSweetrolls.getFGASGame().restartGame();
                                forGoldAndSweetrolls.getFGASGame().getPlayer().statistics.p_Name = currentName;
                                forGoldAndSweetrolls.getFGASGame().getPlayer().statistics.p_Sex = currentSex;
                                forGoldAndSweetrolls.getFGASGame().getPlayer().statistics.p_Race = currentRace;
                                forGoldAndSweetrolls.getFGASGame().getPlayer().statistics.p_Stat_Strength = currentStr;
                                forGoldAndSweetrolls.getFGASGame().getPlayer().statistics.p_Stat_Dexterity = currentDex;
                                forGoldAndSweetrolls.getFGASGame().getPlayer().statistics.p_Stat_Constitution = currentCon;
                                forGoldAndSweetrolls.getFGASGame().getPlayer().statistics.p_Stat_Intelligence = currentInt;
                                forGoldAndSweetrolls.setGameState(GameState.GAME);
//                            } else {
//                                sfx.play(forGoldAndSweetrolls.getSfxVolume());
//                            }
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
                    }
                } else mainConstrolls[i].setActive(false);
            } else mainConstrolls[i].setHover(false);
        }

        for (int i = 0; i < sexControlls.length; i++) {
            if (mouseCursor.intersects(sexControlls[i])) {
                sexControlls[i].setHover(true);
                if (mouseCursor.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                    sexControlls[i].setActive(true);
                    switch (i) {
                        case 1: {
                            currentSex = CharacterSex.MALE;
                            sexControlls[3].setChecked(false);
                            sexControlls[1].setChecked(true);
                            currentSpriteSheet = Textures.getInstance().avatarsMales;
                            currentAvatarSpriteSheetColumns = currentSpriteSheet.getHorizontalCount();
                            currentAvatarSpriteSheetId = 0;
                            currentPortraitImage = currentSpriteSheet.getSprite(currentAvatarSpriteSheetId % currentAvatarSpriteSheetColumns, currentAvatarSpriteSheetId / currentAvatarSpriteSheetColumns);
                            break;
                        }
                        case 3: {
                            currentSex = CharacterSex.FEMALE;
                            sexControlls[3].setChecked(true);
                            sexControlls[1].setChecked(false);
                            currentSpriteSheet = Textures.getInstance().avatarsFemales;
                            currentAvatarSpriteSheetColumns = currentSpriteSheet.getHorizontalCount();
                            currentAvatarSpriteSheetId = 0;
                            currentPortraitImage = currentSpriteSheet.getSprite(currentAvatarSpriteSheetId % currentAvatarSpriteSheetColumns, currentAvatarSpriteSheetId / currentAvatarSpriteSheetColumns);
                            break;
                        }
                    }
                } else sexControlls[i].setActive(false);
            } else sexControlls[i].setHover(false);
        }

        for (int i = 0; i < raceControlls.length; i++) {
            if (mouseCursor.intersects(raceControlls[i])) {
                raceControlls[i].setHover(true);
                if (mouseCursor.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                    raceControlls[i].setActive(true);
                    switch (i) {
                        case 1: {
                            currentRace = CharacterRace.HUMAN;
                            raceControlls[1].setChecked(true);
                            raceControlls[3].setChecked(false);
                            raceControlls[5].setChecked(false);
                            raceControlls[7].setChecked(false);
                            raceControlls[9].setChecked(false);
                            break;
                        }
                        case 3: {
                            currentRace = CharacterRace.ELF;
                            raceControlls[1].setChecked(false);
                            raceControlls[3].setChecked(true);
                            raceControlls[5].setChecked(false);
                            raceControlls[7].setChecked(false);
                            raceControlls[9].setChecked(false);
                            break;
                        }
                        case 5: {
                            currentRace = CharacterRace.DWARF;
                            raceControlls[1].setChecked(false);
                            raceControlls[3].setChecked(false);
                            raceControlls[5].setChecked(true);
                            raceControlls[7].setChecked(false);
                            raceControlls[9].setChecked(false);
                            break;
                        }
                        case 7: {
                            currentRace = CharacterRace.HALFLING;
                            raceControlls[1].setChecked(false);
                            raceControlls[3].setChecked(false);
                            raceControlls[5].setChecked(false);
                            raceControlls[7].setChecked(true);
                            raceControlls[9].setChecked(false);
                            break;
                        }
                        case 9: {
                            currentRace = CharacterRace.GNOME;
                            raceControlls[1].setChecked(false);
                            raceControlls[3].setChecked(false);
                            raceControlls[5].setChecked(false);
                            raceControlls[7].setChecked(false);
                            raceControlls[9].setChecked(true);
                            break;
                        }
                    }
                } else raceControlls[i].setActive(false);
            } else raceControlls[i].setHover(false);
        }

        for (int i = 0; i < statisticsControlls.length; i++) {
            if (mouseCursor.intersects(statisticsControlls[i])) {
                statisticsControlls[i].setHover(true);
                if (mouseCursor.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                    statisticsControlls[i].setActive(true);
                    switch (i) {
                        // STRENGTH
                        case 4: {
                            if (leftStatsPoints > 0) {
                                currentStr++;
                                leftStatsPoints--;
                                statisticsControlls[1].setText(leftStatsPoints +"");
                                statisticsControlls[3].setText(currentStr+"");
                            }
                            break;
                        }
                        case 5: {
                            if (currentStr > MIN_STRENGTH) {
                                currentStr--;
                                leftStatsPoints++;
                                statisticsControlls[1].setText(leftStatsPoints +"");
                                statisticsControlls[3].setText(currentStr+"");
                            }
                            break;
                        }
                        // DEXTERITY
                        case 8: {
                            if (leftStatsPoints > 0) {
                                currentDex++;
                                leftStatsPoints--;
                                statisticsControlls[1].setText(leftStatsPoints +"");
                                statisticsControlls[7].setText(currentDex+"");
                            }
                            break;
                        }
                        case 9: {
                            if (currentDex > MIN_DEXTERITY) {
                                currentDex--;
                                leftStatsPoints++;
                                statisticsControlls[1].setText(leftStatsPoints +"");
                                statisticsControlls[7].setText(currentDex+"");
                            }
                            break;
                        }
                        // CONSTITUTION
                        case 12: {
                            if (leftStatsPoints > 0) {
                                currentCon++;
                                leftStatsPoints--;
                                statisticsControlls[1].setText(leftStatsPoints +"");
                                statisticsControlls[11].setText(currentCon+"");
                            }
                            break;
                        }
                        case 13: {
                            if (currentCon > MIN_CONSTITUTION) {
                                currentCon--;
                                leftStatsPoints++;
                                statisticsControlls[1].setText(leftStatsPoints +"");
                                statisticsControlls[11].setText(currentCon+"");
                            }
                            break;
                        }
                        // INTELLIGENCE
                        case 16: {
                            if (leftStatsPoints > 0) {
                                currentInt++;
                                leftStatsPoints--;
                                statisticsControlls[1].setText(leftStatsPoints +"");
                                statisticsControlls[15].setText(currentInt+"");
                            }
                            break;
                        }
                        case 17: {
                            if (currentInt > MIN_INTELLIGENCE) {
                                currentInt--;
                                leftStatsPoints++;
                                statisticsControlls[1].setText(leftStatsPoints +"");
                                statisticsControlls[15].setText(currentInt+"");
                            }
                            break;
                        }
                    }
                } else statisticsControlls[i].setActive(false);
            } else statisticsControlls[i].setHover(false);
        }

        for (int i = 0; i < skillsControlls.length; i++) {
            if (mouseCursor.intersects(skillsControlls[i])) {
                skillsControlls[i].setHover(true);
                if (mouseCursor.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                    skillsControlls[i].setActive(true);
                    switch (i) {
                        // ALCHEMY
                        case 4: {
                            if (leftSkillsPoints > 0) {
                                currentAlchemy++;
                                leftSkillsPoints--;
                                skillsControlls[1].setText(leftSkillsPoints +"");
                                skillsControlls[3].setText(currentAlchemy+"");
                            }
                            break;
                        }
                        case 5: {
                            if (currentAlchemy > 0) {
                                currentAlchemy--;
                                leftSkillsPoints++;
                                skillsControlls[1].setText(leftSkillsPoints +"");
                                skillsControlls[3].setText(currentAlchemy+"");
                            }
                            break;
                        }
                        // LOCKPICKING
                        case 8: {
                            if (leftSkillsPoints > 0) {
                                currentLockpicking++;
                                leftSkillsPoints--;
                                skillsControlls[1].setText(leftSkillsPoints +"");
                                skillsControlls[7].setText(currentLockpicking+"");
                            }
                            break;
                        }
                        case 9: {
                            if (currentLockpicking > 0) {
                                currentLockpicking--;
                                leftSkillsPoints++;
                                skillsControlls[1].setText(leftSkillsPoints +"");
                                skillsControlls[7].setText(currentLockpicking+"");
                            }
                            break;
                        }
                        // SWORDS
                        case 12: {
                            if (leftSkillsPoints > 0) {
                                currentSword++;
                                leftSkillsPoints--;
                                skillsControlls[1].setText(leftSkillsPoints +"");
                                skillsControlls[11].setText(currentSword+"");
                            }
                            break;
                        }
                        case 13: {
                            if (currentSword > 0) {
                                currentSword--;
                                leftSkillsPoints++;
                                skillsControlls[1].setText(leftSkillsPoints +"");
                                skillsControlls[11].setText(currentSword+"");
                            }
                            break;
                        }
                        // ARMOR
                        case 16: {
                            if (leftSkillsPoints > 0) {
                                currentArmor++;
                                leftSkillsPoints--;
                                skillsControlls[1].setText(leftSkillsPoints +"");
                                skillsControlls[15].setText(currentArmor+"");
                            }
                            break;
                        }
                        case 17: {
                            if (currentArmor > 0) {
                                currentArmor--;
                                leftSkillsPoints++;
                                skillsControlls[1].setText(leftSkillsPoints +"");
                                skillsControlls[15].setText(currentArmor+"");
                            }
                            break;
                        }
                        // SHIELD
                        case 20: {
                            if (leftSkillsPoints > 0) {
                                currentShield++;
                                leftSkillsPoints--;
                                skillsControlls[1].setText(leftSkillsPoints +"");
                                skillsControlls[19].setText(currentShield+"");
                            }
                            break;
                        }
                        case 21: {
                            if (currentShield > 0) {
                                currentShield--;
                                leftSkillsPoints++;
                                skillsControlls[1].setText(leftSkillsPoints +"");
                                skillsControlls[19].setText(currentShield+"");
                            }
                            break;
                        }
                        // SURVIVAL
                        case 24: {
                            if (leftSkillsPoints > 0) {
                                currentSurvival++;
                                leftSkillsPoints--;
                                skillsControlls[1].setText(leftSkillsPoints +"");
                                skillsControlls[23].setText(currentSurvival+"");
                            }
                            break;
                        }
                        case 25: {
                            if (currentSurvival > 0) {
                                currentSurvival--;
                                leftSkillsPoints++;
                                skillsControlls[1].setText(leftSkillsPoints +"");
                                skillsControlls[23].setText(currentSurvival+"");
                            }
                            break;
                        }
                        // SPEECH
                        case 28: {
                            if (leftSkillsPoints > 0) {
                                currentSpeech++;
                                leftSkillsPoints--;
                                skillsControlls[1].setText(leftSkillsPoints +"");
                                skillsControlls[27].setText(currentSpeech+"");
                            }
                            break;
                        }
                        case 29: {
                            if (currentSpeech > 0) {
                                currentSpeech--;
                                leftSkillsPoints++;
                                skillsControlls[1].setText(leftSkillsPoints +"");
                                skillsControlls[27].setText(currentSpeech+"");
                            }
                            break;
                        }
                        // ARCHARY
                        case 32: {
                            if (leftSkillsPoints > 0) {
                                currentArchery++;
                                leftSkillsPoints--;
                                skillsControlls[1].setText(leftSkillsPoints +"");
                                skillsControlls[31].setText(currentArchery+"");
                            }
                            break;
                        }
                        case 33: {
                            if (currentArchery > 0) {
                                currentArchery--;
                                leftSkillsPoints++;
                                skillsControlls[1].setText(leftSkillsPoints +"");
                                skillsControlls[31].setText(currentArchery+"");
                            }
                            break;
                        }

                    }
                } else skillsControlls[i].setActive(false);
            } else skillsControlls[i].setHover(false);
        }
    }

    public void render(GameContainer gc, Graphics g) throws SlickException {
        currentPortraitImage.draw(40, 40);
        hudImage.draw(0, 0);
        for (int i = 0; i < mainConstrolls.length; i++) {
            mainConstrolls[i].render(g, 0, 0);
        }
        for (int i = 0; i < sexControlls.length; i++) {
            sexControlls[i].render(g, 0, 0);
        }
        for (int i = 0; i < raceControlls.length; i++) {
            raceControlls[i].render(g, 0, 0);
        }
        for (int i = 0; i < statisticsControlls.length; i++) {
            statisticsControlls[i].render(g, 0, 0);
        }
        for (int i = 0; i < skillsControlls.length; i++) {
            skillsControlls[i].render(g, 0, 0);
        }
        generateRandomName.render(g, 0, 0);
    }


}
