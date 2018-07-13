package com.szczypiorofix.sweetrolls.game.main;

import com.szczypiorofix.sweetrolls.game.def.ObjectType;
import com.szczypiorofix.sweetrolls.game.graphics.Fonts;
import com.szczypiorofix.sweetrolls.game.gui.MainMenuButton;
import com.szczypiorofix.sweetrolls.game.gui.MouseCursor;
import com.szczypiorofix.sweetrolls.game.sounds.SFX;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;

import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.EmptyTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class GameMainMenu extends BasicGameState {

    private Input input;
    private Image background;
    private boolean serverStatus = false;
    private String serverStatusMsg = "offline";
    private SFX sfx1;
    private MainMenuButton[] menuButtons;

    private Music mainMenuMusic;

    private Fonts fontImmortal;

    private MouseCursor mouseCursor;


    @Override
    public int getID() {
        return MainClass.MAINMENU;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        background = new Image("res/background.png");

        sfx1 = new SFX("sword-unsheathe.ogg");

        // https://opengameart.org/content/heroic-minority
        mainMenuMusic = new Music("res/music/menu-music.ogg");
        mainMenuMusic.play();

        menuButtons = new MainMenuButton[4];

        // https://www.fontsquirrel.com/fonts/Immortal
        fontImmortal = new Fonts("immortal.ttf", java.awt.Font.BOLD, 52.f);

        Fonts menuFont = new Fonts("immortal.ttf", java.awt.Font.BOLD, 13f);


        menuButtons[0] = new MainMenuButton("NOWA GRA", (gc.getWidth() / 2) - (128 / 2), 200, 128, 32, menuFont);
        menuButtons[1] = new MainMenuButton("USTAWIENIA", (gc.getWidth() / 2) - (128 / 2), 240, 128, 32, menuFont);
        menuButtons[2] = new MainMenuButton("POMOC", (gc.getWidth() / 2) - (128 / 2), 280, 128, 32, menuFont);
        menuButtons[3] = new MainMenuButton("KONIEC", (gc.getWidth() / 2) - (128 / 2), 320, 128, 32, menuFont);

        input = gc.getInput();

        // https://opengameart.org/content/dwarven-cursor
        gc.setMouseCursor(new Image("res/mouse_cursor.png"), 0, 0);
        mouseCursor = new MouseCursor("Mouse Cursor Main Menu", input.getMouseX(), input.getMouseY(), 1, 1, ObjectType.MOUSECURSOR);

        gc.setTargetFrameRate(60);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

        serverStatus = MainClass.serverOnline;

        mouseCursor.update(gc, sbg, delta);


        for(int i = 0; i < menuButtons.length; i++) {
            if (mouseCursor.intersects(menuButtons[i])) {
                menuButtons[i].setHover(true);
                if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                    menuButtons[i].setActive(true);
                    switch (i) {
                        case 0: {
                            input.clearKeyPressedRecord();
                            sbg.enterState(MainClass.GAME, new FadeOutTransition(Color.black), new EmptyTransition());
                            break;
                        }
                        case 3: {
                            input.clearKeyPressedRecord();
                            sbg.enterState(MainClass.EXIT, new FadeOutTransition(Color.black), new EmptyTransition());
                            break;
                        }
                    }
                } else menuButtons[i].setActive(false);
            } else menuButtons[i].setHover(false);
        }




        if (input.isKeyPressed(Input.KEY_SPACE)) {
            input.clearKeyPressedRecord();
            sbg.enterState(MainClass.GAME, new FadeOutTransition(Color.black), new EmptyTransition());
        }

        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            input.clearKeyPressedRecord();


            //NetworkClient.closeConnection();


            sbg.enterState(MainClass.EXIT, new FadeOutTransition(Color.black), new EmptyTransition());
        }

        if (input.isKeyPressed(Input.KEY_ENTER)) {
            sfx1.play();
        }

        //if (serverStatus) serverStatusMsg = "online";
        //else serverStatusMsg = "offline";
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

        background.draw(0, 0, gc.getWidth(), gc.getHeight());

        //g.drawString("Server status: "+serverStatusMsg, 10, 30);
        g.drawString("MX: "+mouseCursor.getX(), 10, 50);
        g.drawString("MY: "+mouseCursor.getY(), 10, 60);


        fontImmortal.draw("For Gold and Sweetrolls", 85, 60, Color.white);

        for(MainMenuButton m: menuButtons) {
            m.render(gc, sbg, g);
        }
    }

}
