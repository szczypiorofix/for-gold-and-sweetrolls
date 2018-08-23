package com.szczypiorofix.sweetrolls.game.enums;

import java.io.Serializable;

public enum LevelType implements Serializable {
    WORLD_MAP,
    INNER_MAP,
    INNER_RANDOM_MAP;

    public static LevelType getInitialLevelType() {
        return WORLD_MAP;
    }
}