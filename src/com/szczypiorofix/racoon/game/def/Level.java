package com.szczypiorofix.racoon.game.def;

public enum Level {

    WORLD_MAP(1),

    TOWN1(2),

    TOWN2(3),

    TOWN3(4);

    private final int id;

    Level(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }
}
