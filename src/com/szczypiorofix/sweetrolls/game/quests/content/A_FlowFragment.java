package com.szczypiorofix.sweetrolls.game.quests.content;

import java.util.ArrayList;

public class A_FlowFragment extends ArticyObject {

    private String displayName;
    private String technicalName;
    private String shortId;
    private ArrayList<A_Pin> pins;
    private int pinCount;
    private String text;

    public A_FlowFragment(String id) {
        super(id);
        pins = new ArrayList<>();
        pinCount = 0;
    }

    public A_FlowFragment(String id, String displayName, String technicalName, String shortId) {
        this(id);
        this.displayName = displayName;
        this.technicalName = technicalName;
        this.shortId = shortId;
    }

    public void addPin(A_Pin pin) {
        pins.add(pin);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getTechnicalName() {
        return technicalName;
    }

    public void setTechnicalName(String technicalName) {
        this.technicalName = technicalName;
    }

    public String getShortId() {
        return shortId;
    }

    public void setShortId(String shortId) {
        this.shortId = shortId;
    }

    public ArrayList<A_Pin> getPins() {
        return pins;
    }

    public void setPins(ArrayList<A_Pin> pins) {
        this.pins = pins;
    }

    public int getPinCount() {
        return pinCount;
    }

    public void setPinCount(int pinCount) {
        this.pinCount = pinCount;
    }
}
