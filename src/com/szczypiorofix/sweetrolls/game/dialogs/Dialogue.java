/*
 * Developed by szczypiorofix on 24.08.18 13:26.
 * Copyright (c) 2018. All rights reserved.
 *
 */

package com.szczypiorofix.sweetrolls.game.dialogs;

import com.szczypiorofix.sweetrolls.game.objects.characters.NPC;

import java.util.HashMap;

public class Dialogue {

    protected HashMap<Integer, DialoguePart> dialogues;
    protected int currentDialogueState;
    protected NPC npc;
    protected final int BX = 30;
    protected int BY = 530;
    protected final int BWIDTH = 250;
    protected final int BHEIGHT = 40;

    public Dialogue() {
        dialogues = new HashMap<>();
    }

    public Dialogue(NPC npc) {
        this();
        dialogues = new HashMap<>();
        this.npc = npc;
    }

    public HashMap<Integer, DialoguePart> getDialogues() {
        return dialogues;
    }

    public void setDialogues(HashMap<Integer, DialoguePart> dialogues) {
        this.dialogues = dialogues;
    }

    public int getCurrentDialogueState() {
        return currentDialogueState;
    }

    public void setCurrentDialogueState(int currentDialogueState) {
        this.currentDialogueState = currentDialogueState;
    }

    public NPC getNpc() {
        return npc;
    }

    public void setNpc(NPC npc) {
        this.npc = npc;
    }
}
