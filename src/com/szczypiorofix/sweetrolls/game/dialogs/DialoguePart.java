package com.szczypiorofix.sweetrolls.game.dialogs;

import com.szczypiorofix.sweetrolls.game.gui.DialogueButton;

import java.util.ArrayList;

public class DialoguePart {

    private int id;
    private String name;
    private ArrayList<DialogueButton> buttons;

    public DialoguePart(int id, String name) {
        this.id = id;
        this.name = name;
        buttons = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setButtons(ArrayList<DialogueButton> buttons) {
        this.buttons = buttons;
    }

    public ArrayList<DialogueButton> getButtons() {
        return buttons;
    }

    public String getName() {
        return name;
    }


}
