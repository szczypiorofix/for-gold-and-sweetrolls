/*
 * Developed by szczypiorofix on 28.08.18 14:44.
 * Copyright (c) 2018. All rights reserved.
 *
 */

package com.szczypiorofix.sweetrolls.game.quests.articy;

import com.szczypiorofix.sweetrolls.game.quests.articy.content.A_Dialogue;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class SimulatePanel extends JPanel {


    public SimulatePanel() {
        super( true);
    }

    public void updateData(ArticyXMLParser parser) {

        HashMap<String, A_Dialogue> dialogues = parser.getDialogues();
        setLayout(new GridLayout(dialogues.size(), 1, 5, 5));



        for (Map.Entry<String, A_Dialogue> map : dialogues.entrySet()) {
            add(new JButton(map.getValue().id +" " +map.getValue().displayName));
        }

    }
}
