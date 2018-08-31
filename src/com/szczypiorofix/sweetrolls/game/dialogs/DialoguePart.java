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
    private String requiredItem;
    private String requireAmount;
    private int failNextId;
    private ArrayList<DialoguePartButton> dialoguePartButtons;

    public DialoguePart(String id, String text, String requiredItem, String requireAmount, String failNextId) {
        this.id = Integer.parseInt(id);
        this.requiredItem = requiredItem;
        this.requireAmount = requireAmount;
        this.failNextId = Integer.parseInt(failNextId);
        this.text = text.replace("\\n", "\n");
        dialoguePartButtons = new ArrayList<>();

        System.out.println("Required item: " + requiredItem);
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

    public String getRequiredItem() {
        return requiredItem;
    }

    public void setRequiredItem(String requiredItem) {
        this.requiredItem = requiredItem;
    }

    public String getRequireAmount() {
        return requireAmount;
    }

    public void setRequireAmount(String requireAmount) {
        this.requireAmount = requireAmount;
    }
}
