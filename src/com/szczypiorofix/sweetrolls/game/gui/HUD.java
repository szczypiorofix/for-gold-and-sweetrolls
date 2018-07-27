package com.szczypiorofix.sweetrolls.game.gui;

import com.szczypiorofix.sweetrolls.game.main.MainClass;
import com.szczypiorofix.sweetrolls.game.main.fonts.BitMapFont;
import com.szczypiorofix.sweetrolls.game.main.fonts.FontParser;
import com.szczypiorofix.sweetrolls.game.objects.characters.Player;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

public class HUD {

    private Image image;
    private Player player;
    private MouseCursor mouseCursor;
    private BitMapFont font;

    public HUD(Player player, MouseCursor mouseCursor) {
        this.player = player;
        this.mouseCursor = mouseCursor;
        try {
            image = new Image(MainClass.RES + "assets/hud.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        font = FontParser.getFont("Immortal HUD Bitmap Font", "immortal-bitmap.xml", "immortal-bitmap.png");
        font.setSize(4.5f);
    }

    public void render(GameContainer gc, StateBasedGame sgb, Graphics g) {
        int levelMaxContainer = 156;
        int currentLevel = (int) (player.statistics.currentLevelBar * levelMaxContainer) / player.statistics.currentLevelMaxBar;
        if (currentLevel > levelMaxContainer) currentLevel = levelMaxContainer;

        image.draw(0, 0);


        font.draw("Gracz: " + player.getName(), 600, 50);
        font.draw("Poziom: " +player.statistics.level, 600, 65);
        g.drawRect(600, 85, 160, 11);
        g.fillRect(602, 87, currentLevel, 8);
        font.draw("Zdrowie: " +player.statistics.health +"/"+player.statistics.maxHealth, 600, 100);
        font.draw("Runda: " +player.getPlayerTurn(), 600, 115);
        font.draw("Teren: " +player.getTerrainType().getName(), 600, 130);
        font.draw("Tiles current: " +player.getTileX(0)+":"+player.getTileY(0), 600, 145);
        font.draw("Tiles world: " +player.getWorldMapTileX()+":"+player.getWorldMapTileY(), 600, 160);
        font.draw("Location: " +player.getCurrentLevelName(), 600, 175);
        font.draw("Mouse tile: " +mouseCursor.getTileX()+":"+mouseCursor.getTileY(), 600, 190);
    }

}
