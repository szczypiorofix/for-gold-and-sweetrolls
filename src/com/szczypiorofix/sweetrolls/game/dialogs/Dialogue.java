/*
 * Developed by szczypiorofix on 24.08.18 13:26.
 * Copyright (c) 2018. All rights reserved.
 *
 */

package com.szczypiorofix.sweetrolls.game.dialogs;


import java.util.ArrayList;

public class Dialogue {


    private String name;
    private ArrayList<DialoguePart> dialogueParts;
    private int currentDialogueState = 0;

    public Dialogue() {
        name = "";
        dialogueParts = new ArrayList<>();
    }

    public Dialogue(String name) {
        this();
        this.name = name;
    }

    public DialoguePart getCurrentDialoguePart() {
        return dialogueParts.get(currentDialogueState);
    }

    public int getCurrentDialogueState() {
        return currentDialogueState;
    }

    public void setCurrentDialogueState(int currentDialogueState) {
        this.currentDialogueState = currentDialogueState;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addDialoguePart(DialoguePart dialoguePart) {
        dialogueParts.add(dialoguePart);
        dialoguePart.finalizeButtons();
    }

    public ArrayList<DialoguePart> getDialogueParts() {
        return dialogueParts;
    }

    public void setDialogueParts(ArrayList<DialoguePart> dialogueParts) {
        this.dialogueParts = dialogueParts;
    }
}
