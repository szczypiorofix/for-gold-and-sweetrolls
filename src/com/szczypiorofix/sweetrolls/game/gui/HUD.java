package com.szczypiorofix.sweetrolls.game.gui;

import com.szczypiorofix.sweetrolls.game.main.core.TimeCounter;
import com.szczypiorofix.sweetrolls.game.main.fonts.BitMapFont;
import com.szczypiorofix.sweetrolls.game.main.fonts.FontParser;
import com.szczypiorofix.sweetrolls.game.objects.characters.Player;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class HUD {

    private Image image;
    private Image clockImage;
    private Player player;
    private MouseCursor mouseCursor;
    private BitMapFont font;
    private TimeCounter timeCounter;
    private ActionHistory actionHistory;
    private float rotateFactor = 0;

    public HUD(Player player, TimeCounter timeCounter, ActionHistory actionHistory, MouseCursor mouseCursor) {
        this.player = player;
        this.mouseCursor = mouseCursor;
        this.actionHistory = actionHistory;
        this.timeCounter = timeCounter;
        try {
            image = new Image("assets/hud.png");
            clockImage = new Image("assets/time-counter.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        font = FontParser.getFont("HUD Font", "immortal-bitmap.xml", "immortal-bitmap.png");
        font.setSize(4.2f);
    }

    public void turn() {
        rotateFactor = (timeCounter.getCurrentTurnTime() * 360f) / 1440f; // 1440 minut = 24h * 60min
        clockImage.rotate(rotateFactor);
    }

    public void render(GameContainer gc, Graphics g) {
        int levelMaxContainer = 156;
        int currentLevel = (player.statistics.currentLevelBar * levelMaxContainer) / player.statistics.currentLevelMaxBar;
        if (currentLevel > levelMaxContainer) currentLevel = levelMaxContainer;

        clockImage.draw(617, 8);
        image.draw(0, 0);

        font.draw("Dzień: " + timeCounter.getDayCounter() + ", godz: " + timeCounter.getHourCounter() + ":" + (
                timeCounter.getMinuteCounter() < 10 ? timeCounter.getMinuteCounter() + "0"
                        : timeCounter.getMinuteCounter()
        ), 590, 80);

        font.draw("Gracz: " + player.getName(), 590, 100);
        font.draw("Poziom: " +player.statistics.level, 590, 120);
        font.draw("Złoto: " + player.statistics.gold, 590, 170);
        g.drawRect(590, 150, 160, 11);
        g.fillRect(592, 152, currentLevel, 8);
        font.draw("Zdrowie: " +player.statistics.health +"/"+player.statistics.maxHealth, 590, 190);

        font.draw("Teren: " +player.getTerrainType().getName(), 590, 210);
        font.draw("Jedzenie: " + String.format("%.2f", player.statistics.foodRations), 590, 230);
        font.draw("Woda: " + String.format("%.2f", player.statistics.water), 590, 250);

        for (int i = 0; i < actionHistory.history.length; i++) {
            font.draw(actionHistory.history[i], 580, 380 + (i * 20));
        }

        //font.draw("Tiles c: " +player.getTileX()+":"+player.getTileY(), 590, 240);
        font.draw("PS: " +player.getLevelState(), 590, 270);
//        font.draw("Tiles w: " +player.getWorldMapTileX()+":"+player.getWorldMapTileY(), 590, 260);
//        font.draw("Location: " +player.getCurrentLevelName(), 590, 280);
//        font.draw("Mouse tile: " +mouseCursor.getTileX()+":"+mouseCursor.getTileY(), 590, 300);
//        font.draw("Player offset: " +player.getOffsetX()+":"+player.getOffsetY(), 590, 340);

        font.draw("Pancerz : " +player.statistics.armorClass, 590, 320);
        font.draw("Atak: " +player.statistics.damage, 590, 340);

        font.draw("PA: " +timeCounter.getTimeStamp(), 590, 360);
    }

}
