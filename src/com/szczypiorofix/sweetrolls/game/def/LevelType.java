package com.szczypiorofix.sweetrolls.game.def;

public enum LevelType {

    WORLD_MAP(1),

    TOWN1(2),

    TOWN2(3),

    TOWN3(4);

    private final int id;

    LevelType(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }
}
