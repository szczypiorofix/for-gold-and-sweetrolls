package com.szczypiorofix.sweetrolls.game.quests.content;

import com.szczypiorofix.sweetrolls.game.gui.DialogueButton;
import com.szczypiorofix.sweetrolls.game.objects.characters.NPC;
import com.szczypiorofix.sweetrolls.game.quests.DialoguePart;

import java.util.ArrayList;
import java.util.HashMap;

public class Dialogs {

    private HashMap<Integer, DialoguePart> dialogues;
    private int currentDialogueState;
    private NPC npc;
    private final int BX = 30;
    private int BY = 460;
    private final int BWIDTH = 250;
    private final int BHEIGHT = 40;

    public Dialogs(NPC npc) {

        this.npc = npc;
        dialogues = new HashMap<>();
        currentDialogueState = 0;

        if (npc.getName().equalsIgnoreCase("Todd")) {

            DialoguePart dialoguePart0 = new DialoguePart(0, "Witaj nieznajomy!");
            ArrayList<DialogueButton> buttons = new ArrayList<>();
            buttons.add(new DialogueButton("Witaj", BX, BY, BWIDTH, BHEIGHT, false, 1));
            buttons.add(new DialogueButton("Witaj. Co słychać?", BX, BY + (BHEIGHT), BWIDTH, BHEIGHT, false, 2));
            buttons.add(new DialogueButton("Wyjście", BX, BY + (BHEIGHT * 2), BWIDTH, BHEIGHT, true, 0));
            dialoguePart0.setButtons(buttons);

            DialoguePart dialoguePart1 = new DialoguePart(1, "Witaj. Co Cię do nas sprowadza?");
            buttons = new ArrayList<>();
            buttons.add(new DialogueButton("Szukam pracy.", BX, BY, BWIDTH, BHEIGHT, false, 3));
            buttons.add(new DialogueButton("Kręcę się po okolicy.", BX, BY + BHEIGHT, BWIDTH, BHEIGHT, false, 4));
            buttons.add(new DialogueButton("Wyjście", BX, BY + (BHEIGHT * 2), BY + (BHEIGHT * 2), BHEIGHT, true, 1));
            dialoguePart1.setButtons(buttons);

            DialoguePart dialoguePart2 = new DialoguePart(2, "Dziękuję. Wszystko dobrze.");
            buttons = new ArrayList<>();
            buttons.add(new DialogueButton("Wyjście", BX, BY, BWIDTH, BHEIGHT, true, 1));
            dialoguePart2.setButtons(buttons);

            DialoguePart dialoguePart3 = new DialoguePart(3, "Nic dla ciebie nie mam.\nPrzyjdź jutro.");
            buttons = new ArrayList<>();
            buttons.add(new DialogueButton("Wyjście", BX, BY, BWIDTH, BHEIGHT, true, 1));
            dialoguePart3.setButtons(buttons);

            DialoguePart dialoguePart4 = new DialoguePart(4, "Tylko nie przeszkadzaj.\nNikt nie lubi jak jakiś przybłęda wchodzi mu\nna posesję.");
            buttons = new ArrayList<>();
            buttons.add(new DialogueButton("Wyjście", BX, BY, BWIDTH, BHEIGHT, true, 1));
            dialoguePart4.setButtons(buttons);

            dialogues.put(dialoguePart0.getId(), dialoguePart0);
            dialogues.put(dialoguePart1.getId(), dialoguePart1);
            dialogues.put(dialoguePart2.getId(), dialoguePart2);
            dialogues.put(dialoguePart3.getId(), dialoguePart3);
            dialogues.put(dialoguePart4.getId(), dialoguePart4);

        }

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
