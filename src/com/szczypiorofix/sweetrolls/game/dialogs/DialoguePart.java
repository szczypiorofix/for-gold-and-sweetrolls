/*
 * Developed by szczypiorofix on 24.08.18 13:27.
 * Copyright (c) 2018. All rights reserved.
 *
 */

package com.szczypiorofix.sweetrolls.game.dialogs;



import java.util.ArrayList;

public class DialoguePart {


    private int id;
    private String text;
    private ArrayList<DialoguePartButton> dialoguePartButtons;

    public DialoguePart(String id, String text) {
        this.id = Integer.parseInt(id);
        this.text = text.replace("\\n", "\n");
        dialoguePartButtons = new ArrayList<>();
    }

    public void addDialogueButton(DialoguePartButton dialoguePartButton) {
        dialoguePartButtons.add(dialoguePartButton);
    }

    public ArrayList<DialoguePartButton> getDialoguePartButtons() {
        return dialoguePartButtons;
    }

    public void setDialoguePartButtons(ArrayList<DialoguePartButton> dialoguePartButtons) {
        this.dialoguePartButtons = dialoguePartButtons;
    }

    public void finalizeButtons() {
        // You can change the order of dialogue buttons
        for (int i = 0; i < dialoguePartButtons.size(); i++) {
            dialoguePartButtons.get(i).setY(DialoguePartButton.BY - ((dialoguePartButtons.size() - dialoguePartButtons.get(i).getOrder()) * DialoguePartButton.BHEIGHT));
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
