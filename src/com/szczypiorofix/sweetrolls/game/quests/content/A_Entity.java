package com.szczypiorofix.sweetrolls.game.quests.content;

public class A_Entity extends ArticyObject {

    private String displayName;
    private String technicalName;
    private String shortId;

    public A_Entity(String id) {
        super(id);
    }

    public A_Entity (String id, String name, String technicalName, String shortId) {
        super(id);
        this.displayName = name;
        this.technicalName = technicalName;
        this.shortId = shortId;
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
}
