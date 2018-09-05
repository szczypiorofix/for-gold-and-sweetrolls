/*
 * Developed by szczypiorofix on 24.08.18 13:29.
 * Copyright (c) 2018. All rights reserved.
 *
 */

package com.szczypiorofix.sweetrolls.game.gui;

import com.szczypiorofix.sweetrolls.game.main.core.TimeCounter;
import com.szczypiorofix.sweetrolls.game.main.fonts.BitMapFont;
import com.szczypiorofix.sweetrolls.game.main.fonts.FontParser;
import com.szczypiorofix.sweetrolls.game.main.graphics.Textures;
import com.szczypiorofix.sweetrolls.game.objects.Statistics;
import com.szczypiorofix.sweetrolls.game.objects.characters.Player;
import org.newdawn.slick.*;


public class HUD {

    private final Color levelBarColor = new Color(104, 103, 29, 255);
    private final Color healthBarColor = new Color(181, 5, 25, 255);
    private final Color foodBarColor = new Color(5, 109, 2, 255);
    private final Color waterBarColor = new Color(9, 28, 155, 255);
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
        image = Textures.getInstance().hudImage;
        clockImage = Textures.getInstance().clockImage;
        font = FontParser.getFont();
    }

    public void turn() {
        rotateFactor = (timeCounter.getCurrentTurnTime() * 360f) / 1440f; // 1440 minut = 24h * 60min
        clockImage.rotate(rotateFactor);
    }

    public void turn(long minutes) {
        rotateFactor = ((minutes * 360f) / 1440f) % 360f;
        clockImage.rotate(rotateFactor);
    }

    public TimeCounter getTimeCounter() {
        return timeCounter;
    }

    public void setTimeCounter(TimeCounter timeCounter) {
        this.timeCounter = timeCounter;
    }

    public ActionHistory getActionHistory() {
        return actionHistory;
    }

    public void setActionHistory(ActionHistory actionHistory) {
        this.actionHistory = actionHistory;
    }


    private void featureBar(Graphics g, int x, int y, int width, int height, int currentValue, int maxValue, Color color, BitMapFont font, String text) {
        Color c = g.getColor();
        g.setColor(color);
        int currentLevel = (currentValue * width) / maxValue;
        if (currentLevel > width) currentLevel = width;
        //int fillWidth = currentLevel > 2 ? currentLevel - 4 : 0;
        g.fillRect(x, y, currentLevel, height);
        g.setColor(c);
        g.drawRect(x, y, width, height);
        font.draw(text, x + 2, y);
    }

    private void featureBar(Graphics g, int x, int y, int width, int height, float currentValue, float maxValue, Color color, BitMapFont font, String text) {
        Color c = g.getColor();
        g.setColor(color);
        float currentLevel = (currentValue * width) / maxValue;
        if (currentLevel > width) currentLevel = width;
        //float fillWidth = currentLevel > 2 ? currentLevel - 4 : 0;
        g.fillRect(x, y, currentLevel, height);
        g.setColor(c);
        g.drawRect(x, y, width, height);
        font.draw(text, x + 2, y);
    }

    public void render(GameContainer gc, Graphics g) {

        clockImage.draw(617, 8);
        image.draw(0, 0);

        featureBar(g,
                590,
                150,
                160,
                16,
                player.statistics.P_CurrentLevelBar,
                player.statistics.P_CurrentLevelMaxBar,
                levelBarColor,
                font,
                "Poziom "+player.statistics.P_Level
                );

        font.draw("Dzień: " + timeCounter.getDayCounter() + ", godz: " + timeCounter.getHourCounter() + ":" + (
                timeCounter.getMinuteCounter() < 10 ? timeCounter.getMinuteCounter() + "0"
                        : timeCounter.getMinuteCounter()
        ), 590, 80);

        font.draw(player.statistics.P_Name, 590, 100);
        font.draw("Poziom: " +player.statistics.P_Level, 590, 120);
        //font.draw("Złoto: " + player.statistics.P_Gold, 590, 170);
        //font.draw("Teren: " +player.getTerrainType().getName(), 590, 190);

        featureBar(g,
                590,
                180,
                160,
                20,
                player.statistics.P_Health,
                player.statistics.P_MaxHealth,
                healthBarColor,
                font,
                " Zdrowie: " +player.statistics.P_Health +"/"+player.statistics.P_MaxHealth);
        //font.draw(" Zdrowie: " +player.statistics.P_Health +"/"+player.statistics.P_MaxHealth, 125, 20);

        featureBar(g,
                590,
                200,
                160,
                20,
                player.statistics.P_Food,
                Statistics.P_MAX_FOOD,
                foodBarColor,
                font,
                "Jedzenie: " + String.format("%.2f", player.statistics.P_Food));

        featureBar(g,
                590,
                220,
                160,
                20,
                player.statistics.P_Water,
                Statistics.P_MAX_WATER,
                waterBarColor,
                font,
                "Woda: " + String.format("%.2f", player.statistics.P_Water));

        //font.draw("Jedzenie: " + String.format("%.2f", player.statistics.P_Food), 590, 230);
        //font.draw("Woda: " + String.format("%.2f", player.statistics.P_Water), 590, 250);

        for (int i = 0; i < actionHistory.history.length; i++) {
            font.draw(actionHistory.history[i], 580, 380 + (i * 20));
        }

        //font.draw("Tiles c: " +player.getTileX()+":"+player.getTileY(), 590, 240);
        //font.draw("PS: " +player.getLevelState(), 590, 270);
//        font.draw("Tiles w: " +player.getWorldMapTileX()+":"+player.getWorldMapTileY(), 590, 260);
//        font.draw("Location: " +player.getCurrentLevelName(), 590, 280);
//        font.draw("Mouse tile: " +mouseCursor.getTileX()+":"+mouseCursor.getTileY(), 590, 300);
//        font.draw("Player offset: " +player.getOffsetX()+":"+player.getOffsetY(), 590, 340);

        font.draw("Pancerz : " +player.statistics.P_ArmorClass, 590, 320);
        font.draw("Atak: " +player.statistics.P_Damage, 590, 340);

        font.draw("Picked: " +player.statistics.W_PickedUpItems, 590, 360);
        font.draw("Sex: " +player.statistics.P_Sex, 590, 380);
        font.draw("Race: " +player.statistics.P_Race, 590, 400);

    }

}
