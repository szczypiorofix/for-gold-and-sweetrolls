package com.szczypiorofix.sweetrolls.game.enums;

public enum ResourceType {

    FOOD("żywność"),
    WATER("woda"),
    WOOD("drewno"),
    IRON("żelazo");

    public String name;

    ResourceType(String name) {
        this.name = name;
    }
}
