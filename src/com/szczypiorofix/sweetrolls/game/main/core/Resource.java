/*
 * Developed by szczypiorofix on 24.08.18 13:38.
 * Copyright (c) 2018. All rights reserved.
 *
 */

package com.szczypiorofix.sweetrolls.game.main.core;

import com.szczypiorofix.sweetrolls.game.enums.ResourceType;

public class Resource {

    private int amount;
    private int initialAmount;
    private ResourceType type;

    public Resource(ResourceType type, int amount) {
        this.amount = amount;
        this.type = type;
        initialAmount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public ResourceType getType() {
        return type;
    }

    public void setType(ResourceType type) {
        this.type = type;
    }

    public int collect() {
        return 0;
    }

    public void turn(long lastStamp) {
        System.out.println("Setting last stamp..." +lastStamp);
    }
}
