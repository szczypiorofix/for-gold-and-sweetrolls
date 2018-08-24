/*
 * Developed by szczypiorofix on 24.08.18 13:28.
 * Copyright (c) 2018. All rights reserved.
 *
 */

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
