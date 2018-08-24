/*
 * Developed by szczypiorofix on 24.08.18 13:28.
 * Copyright (c) 2018. All rights reserved.
 *
 */

package com.szczypiorofix.sweetrolls.game.enums;

public enum ObjectType {

    DEFAULT("domyślny"),

    // ###### CHARACTERS
    PLAYER("gracz"),
    NPC("npc"),
    MONSTER("wróg"),

    // ###### TERRAINS
    PLACE("miejsce"),
    WATER("woda"),
    PLAINS("równiny"),
    HILLS("wzgórza"),
    FOREST("lasy"),
    MOUNTAINS("góry"),
    SETTLEMENT("osada"),
    SWAMP("bagna"),
    SWAMP_TREE("las na bagnach"),
    DESERT("pustynia"),
    DESERT_TREE("drzewka na pustyni"),
    WALL("ściana"),

    // ###### ITEMS
    ITEM("przedmiot"),
    ARMOR("zbroja"),
    WEAPON("broń"),
    POTION("napój"),
    SPECIAL("specjalny"),

    // ###### GUI
    GUI("gui"),
    MOUSECURSOR("kursor myszy"),
    INVENTORY_CONTAINER("kontener inwentarza");


    private String name;

    ObjectType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
