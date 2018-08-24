/*
 * Developed by szczypiorofix on 24.08.18 13:38.
 * Copyright (c) 2018. All rights reserved.
 *
 */

package com.szczypiorofix.sweetrolls.game.main.core;

import com.szczypiorofix.sweetrolls.game.gui.ActionHistory;
import com.szczypiorofix.sweetrolls.game.objects.characters.Player;
import com.szczypiorofix.sweetrolls.game.tilemap.TileMap;



public class LevelManager {


    public LevelManager() {}

    public TileMap loadGeneratedLevel(String name, String type, ActionHistory actionHistory) {

        if (type.equalsIgnoreCase("plains")) {
            actionHistory.addValue("Obszar: równiny");
            return new LevelGenerator(
                    name,
                    50,
                    50,
                    10,
                    11,
                    4,
                    4,
                    0.43f,
                    4,
                    true).getTileMap();

        } else if (type.equalsIgnoreCase("forest")) {
            actionHistory.addValue("Obszar: lasy");
            return new LevelGenerator(
                    name,
                    50,
                    50,
                    55,
                    10,
                    4,
                    4,
                    0.40f,
                    4,
                    true).getTileMap();

        } else if (type.equalsIgnoreCase("mountains")) {
            actionHistory.addValue("Obszar: góry");
            return new LevelGenerator(
                    name,
                    50,
                    50,
                    118,
                    10,
                    4,
                    4,
                    0.44f,
                    4,
                    true).getTileMap();
        }

        // DUNGEON
        actionHistory.addValue("Obszar: podziemia");
        return new LevelGenerator(
                    name,
                    50,
                    50,
                    31,
                    6,
                    4,
                    4,
                    0.43f,
                    8,
                    false).getTileMap();
    }

    public TileMap loadLevel(String levelName) {
        return new TileMap(levelName);
    }

}
