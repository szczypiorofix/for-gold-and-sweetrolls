package com.szczypiorofix.sweetrolls.game.gui;

import com.szczypiorofix.sweetrolls.game.main.MainClass;
import com.szczypiorofix.sweetrolls.game.objects.characters.Player;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

public class HUD {

    private Image image;
    private Player player;
    private MouseCursor mouseCursor;

    public HUD(Player player, MouseCursor mouseCursor) {
        this.player = player;
        this.mouseCursor = mouseCursor;
        try {
            image = new Image(MainClass.RES + "assets/hud.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public void render(GameContainer gc, StateBasedGame sgb, Graphics g) {
        int levelMaxContainer = 156;
        int currentLevel = (int) (player.statistics.currentLevelBar * levelMaxContainer) / player.statistics.currentLevelMaxBar;
        if (currentLevel > levelMaxContainer) currentLevel = levelMaxContainer;

        image.draw(0, 0);


        g.drawString("Gracz: " + player.getName(), 600, 50);
        g.drawString("Poziom: " +player.statistics.level, 600, 65);
        g.drawRect(600, 85, 160, 11);
        g.fillRect(602, 87, currentLevel, 8);
        g.drawString("Zdrowie: " +player.statistics.health +"/"+player.statistics.maxHealth, 600, 100);
        g.drawString("Runda: " +player.getPlayerTurn(), 600, 115);
        g.drawString("Teren: " +player.getTerrainType().getName(), 600, 130);
        g.drawString("Tiles current: " +player.getTileX(0)+":"+player.getTileY(0), 600, 145);
        g.drawString("Tiles world: " +player.getWorldMapTileX()+":"+player.getWorldMapTileY(), 600, 160);
        g.drawString("Location: " +player.getCurrentLevelName(), 600, 175);
        g.drawString("Mouse tile: " +mouseCursor.getTileX()+":"+mouseCursor.getTileY(), 600, 190);
    }

}
