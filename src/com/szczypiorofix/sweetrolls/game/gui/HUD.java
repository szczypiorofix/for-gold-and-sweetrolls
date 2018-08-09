package com.szczypiorofix.sweetrolls.game.gui;

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
            image = new Image("assets/hud.png");
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

        font.draw("D: " +player.getTimeCounter().getDayCounter()+", H: "+player.getTimeCounter().getHourCounter()+":" + (
                player.getTimeCounter().getMinuteCounter() < 10 ? player.getTimeCounter().getMinuteCounter()+"0"
                        : player.getTimeCounter().getMinuteCounter()
        ), 590, 15);

        font.draw("Gracz: " + player.getName(), 590, 50);
        font.draw("Poziom: " +player.statistics.level, 590, 70);
        font.draw("Złoto: " + player.statistics.gold, 590, 120);
        g.drawRect(590, 100, 160, 11);
        g.fillRect(592, 102, currentLevel, 8);
        font.draw("Zdrowie: " +player.statistics.health +"/"+player.statistics.maxHealth, 590, 140);

        font.draw("Teren: " +player.getTerrainType().getName(), 590, 180);
        font.draw("Jedzenie: " + String.format("%.2f", player.statistics.foodRations), 590, 200);
        font.draw("Woda: " + String.format("%.2f", player.statistics.water), 590, 220);

        for (int i = 0; i < player.getActionHistory().history.length; i++) {
            font.draw(player.getActionHistory().history[i], 580, 380 + (i * 20));
        }

//        font.draw("Tiles c: " +player.getTileX(0)+":"+player.getTileY(0), 590, 240);
//        font.draw("Tiles w: " +player.getWorldMapTileX()+":"+player.getWorldMapTileY(), 590, 260);
//        font.draw("Location: " +player.getCurrentLevelName(), 590, 280);
//        font.draw("Mouse tile: " +mouseCursor.getTileX()+":"+mouseCursor.getTileY(), 590, 300);
//        font.draw("Player : " +player.getWidth()+":"+player.getHeight(), 590, 320);
//        font.draw("Player offset: " +player.getOffsetX()+":"+player.getOffsetY(), 590, 340);
        font.draw("Mouse tile: " +mouseCursor.getX()+":"+mouseCursor.getY(), 590, 300);

    }

}
