package com.szczypiorofix.sweetrolls.game.gui;

import com.szczypiorofix.sweetrolls.game.main.graphics.Fonts;
import com.szczypiorofix.sweetrolls.game.main.MainClass;
import com.szczypiorofix.sweetrolls.game.objects.characters.Player;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

public class HUD {

    private Image image;
    private Player player;
    private Fonts smallHUDFont;

    public HUD(Player player) {
        this.player = player;
        try {
            image = new Image(MainClass.RES + "assets/hud.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        smallHUDFont = new Fonts("immortal.ttf", java.awt.Font.BOLD, 14f);
    }

    public void render(GameContainer gc, StateBasedGame sgb, Graphics g) {
        int levelMaxContainer = 156;
        int currentLevel = (int) (player.statistics.currentLevelBar * levelMaxContainer) / player.statistics.currentLevelMaxBar;
        if (currentLevel > levelMaxContainer) currentLevel = levelMaxContainer;

        image.draw(0, 0);


        smallHUDFont.draw("Gracz: " + player.getName(), 600, 50, Color.white);
        smallHUDFont.draw("Poziom: " +player.statistics.level, 600, 65, Color.white);
        g.drawRect(600, 85, 160, 11);
        g.fillRect(602, 87, currentLevel, 8);
        smallHUDFont.draw("Zdrowie: " +player.statistics.health +"/"+player.statistics.maxHealth, 600, 100, Color.white);
        smallHUDFont.draw("Runda: " +player.getPlayerTurn(), 600, 115, Color.white);
        smallHUDFont.draw("Teren: " +player.getTerrainType().getName(), 600, 130, Color.white);
        smallHUDFont.draw("Tiles current: " +player.getTileX(0)+":"+player.getTileY(0), 600, 145, Color.white);
        smallHUDFont.draw("Tiles world: " +player.getWorldMapTileX()+":"+player.getWorldMapTileY(), 600, 160, Color.white);
        smallHUDFont.draw("Location: " +player.getCurrentLevelName(), 600, 175, Color.white);
    }

}
