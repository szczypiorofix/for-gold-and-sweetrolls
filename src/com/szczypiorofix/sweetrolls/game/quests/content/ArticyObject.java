package com.szczypiorofix.sweetrolls.game.quests.content;

import java.util.ArrayList;
import java.util.List;

public abstract class ArticyObject {

    public String id;
    public String displayName;
    public List<String>pinsInput = new ArrayList<String>();
    public List<String> pinsOutput = new ArrayList<String>();

    public ArticyObject(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public List<String> getPinsInput() {
        return pinsInput;
    }

    public void setPinsInput(List<String> pinsInput) {
        this.pinsInput = pinsInput;
    }

    public List<String> getPinsOutput() {
        return pinsOutput;
    }

    public void setPinsOutput(List<String> pinsOutput) {
        this.pinsOutput = pinsOutput;
    }
}
