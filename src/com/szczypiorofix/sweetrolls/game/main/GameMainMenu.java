package com.szczypiorofix.sweetrolls.game.main;

import com.szczypiorofix.sweetrolls.game.gui.MainMenuButton;
import com.szczypiorofix.sweetrolls.game.sounds.SFX;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import java.awt.Font;

import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.util.ResourceLoader;
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

    private java.awt.Font UIFont1;
    private org.newdawn.slick.UnicodeFont uniFont;
    private Music mainMenuMusic;


    @Override
    public int getID() {
        return MainClass.MAINMENU;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        background = new Image("res/background.png");

        sfx1 = new SFX("sword-unsheathe.ogg");

        mainMenuMusic = new Music("res/music/menu-music.ogg");
        mainMenuMusic.play();

        menuButtons = new MainMenuButton[4];

        gc.setMouseCursor(new Image("res/mouse_cursor.png"), 0, 0);

        try{
            UIFont1 = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT,
                    org.newdawn.slick.util.ResourceLoader.getResourceAsStream("fonts/Berylium.ttf"));
            UIFont1 = UIFont1.deriveFont(Font.BOLD, 52.f);
            uniFont = new org.newdawn.slick.UnicodeFont(UIFont1);
            uniFont.addAsciiGlyphs();
            uniFont.getEffects().add(new ColorEffect(java.awt.Color.white));
            uniFont.addAsciiGlyphs();
            uniFont.loadGlyphs();
        }catch(Exception e){
            e.printStackTrace();
        }


        menuButtons[0] = new MainMenuButton("NOWA GRA", (gc.getWidth() / 2) - (128 / 2), 200, 128, 32);
        menuButtons[1] = new MainMenuButton("USTAWIENIA", (gc.getWidth() / 2) - (128 / 2), 240, 128, 32);
        menuButtons[2] = new MainMenuButton("POMOC", (gc.getWidth() / 2) - (128 / 2), 280, 128, 32);
        menuButtons[3] = new MainMenuButton("KONIEC", (gc.getWidth() / 2) - (128 / 2), 320, 128, 32);

        input = gc.getInput();

        gc.setTargetFrameRate(60);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

        serverStatus = MainClass.serverOnline;

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

        if (serverStatus) serverStatusMsg = "online";
        else serverStatusMsg = "offline";
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

        background.draw(0, 0, gc.getWidth(), gc.getHeight());

        g.drawString("Server status: "+serverStatusMsg, 10, 30);

        //font.drawString(32.0f, 32.0f, "Your words here", Color.green);

        uniFont.drawString(145, 60, "For Gold and Sweetrolls", Color.white);

        for(MainMenuButton m: menuButtons) {
            m.render(gc, g);
        }
    }

}
