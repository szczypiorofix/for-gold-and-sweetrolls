package com.szczypiorofix.sweetrolls.game.main.core;

import com.szczypiorofix.sweetrolls.game.enums.ResourceType;

public class Resource {

    private int timeStamp;
    private int nextStamp;
    private int amount;
    private int initialAmount;
    private ResourceType type;

    public Resource(ResourceType type, int amount) {
        this.amount = amount;
        this.type = type;
        initialAmount = amount;
        timeStamp = 0;
        nextStamp = 10;
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
        if (timeStamp >= nextStamp) timeStamp = 0;
        int temp = amount;
        amount = 0;
        return temp;
    }

    public void round() {
        timeStamp++;
        if (timeStamp >= nextStamp) {
            amount = initialAmount;
            timeStamp = nextStamp;
        }
    }
}
