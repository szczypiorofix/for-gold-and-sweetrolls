/*
 * Developed by szczypiorofix on 30.08.18 11:22.
 * Copyright (c) 2018. All rights reserved.
 *
 */

package com.szczypiorofix.sweetrolls.deditor;


import com.szczypiorofix.sweetrolls.game.dialogs.Dialogue;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    private Dialogue dialogue;
    private JButton[] dialoguePartsButtons;

    public MainPanel() {
        super(new BorderLayout(), true);
    }


    public void updateMainPanel(Dialogue dialogue) {
        this.dialogue = dialogue;
        dialoguePartsButtons = new JButton[dialogue.getDialogueParts().size()];


    }


}
