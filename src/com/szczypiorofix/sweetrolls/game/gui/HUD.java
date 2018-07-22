package com.szczypiorofix.sweetrolls.game.gui;

import com.szczypiorofix.sweetrolls.game.main.MainClass;
import com.szczypiorofix.sweetrolls.game.objects.characters.Player;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class HUD {

    private Image image;
    private Player player;

    public HUD(Player player) {
        this.player = player;
        try {
            image = new Image(MainClass.RES + "assets/hud.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public void render(GameContainer gc, StateBasedGame sgb, Graphics g) throws SlickException {
        image.draw(0, 0);
        g.drawString("Gracz: " + player.getName(), 600, 50);
        g.drawString(player.getPlayerClass() + " poziom: " +player.statistics.level, 600, 70);
        g.drawString("Exp: " +player.statistics.exp, 600, 90);
        g.drawString("Zdrowie: " +player.statistics.health +"/"+player.statistics.maxHealth, 600, 110);
    }
}
