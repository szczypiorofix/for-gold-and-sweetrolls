/*
 * Developed by szczypiorofix on 24.08.18 13:28.
 * Copyright (c) 2018. All rights reserved.
 *
 */

package com.szczypiorofix.sweetrolls.game.enums;

public enum ResourceType {

    FOOD("żywność", 1440), // 24h * 60 min = 1440 min
    WATER("woda", 1440),
    WOOD("drewno", 1920), // 32h * 60 min = 1920 min
    IRON("żelazo", 2880); // 48h * 60 min = 2880 min

    public String name;
    public int timeToRenewResource;

    ResourceType(String name, int timeToRenewResource) {
        this.name = name;
        this.timeToRenewResource = timeToRenewResource;
    }
}
