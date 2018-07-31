package com.szczypiorofix.sweetrolls.game.dialogs.npc;

import com.szczypiorofix.sweetrolls.game.dialogs.Dialogue;
import com.szczypiorofix.sweetrolls.game.dialogs.DialoguePart;
import com.szczypiorofix.sweetrolls.game.gui.DialogueButton;
import com.szczypiorofix.sweetrolls.game.objects.characters.NPC;

import java.util.ArrayList;

public class DialoguePete extends Dialogue {

    public DialoguePete(NPC npc) {
        super(npc);

        DialoguePart dialoguePart0 = new DialoguePart(0, "Beta Fallouta 76 będzie kiedy będzie gotowa.");
        ArrayList<DialogueButton> buttons = new ArrayList<>();
        buttons.add(new DialogueButton("OK", BX, BY + (BHEIGHT * 2), BWIDTH, BHEIGHT, true, 0));
        dialoguePart0.setButtons(buttons);

        dialogues.put(dialoguePart0.getId(), dialoguePart0);
    }

}
