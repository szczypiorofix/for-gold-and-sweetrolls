package com.szczypiorofix.sweetrolls.game.main.core;

import com.szczypiorofix.sweetrolls.game.objects.characters.Player;
import com.szczypiorofix.sweetrolls.game.tilemap.TileMap;



public class LevelManager {


    public LevelManager() {}

    public TileMap loadGeneratedLevel(String name, String type, Player player) {

        if (type.equalsIgnoreCase("plains")) {
            player.getActionHistory().addValue("Obszar: równiny");
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
                    false).getTileMap();

        } else if (type.equalsIgnoreCase("forest")) {
            player.getActionHistory().addValue("Obszar: lasy");
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
                    false).getTileMap();

        } else if (type.equalsIgnoreCase("mountains")) {
            player.getActionHistory().addValue("Obszar: góry");
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
                    false).getTileMap();
        }

        // DUNGEON
        player.getActionHistory().addValue("Obszar: podziemia");
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
                    true).getTileMap();
    }

    public TileMap loadLevel(String levelName) {
        return new TileMap(levelName);
    }

}
