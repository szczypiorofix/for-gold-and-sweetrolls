/*
 * Developed by szczypiorofix on 01.09.18 00:49.
 * Copyright (c) 2018. All rights reserved.
 *
 */

package com.szczypiorofix.sweetrolls.deditor;

import javax.swing.*;
import java.awt.*;

public class FunctionalButtonsPanel extends JPanel {

    private JButton addDialoguePartButton;

    public FunctionalButtonsPanel() {
        super(new FlowLayout(), true);

        addDialoguePartButton = new JButton("Add dialogue part");


        add(addDialoguePartButton);
    }

    public JButton getAddDialoguePartButton() {
        return addDialoguePartButton;
    }

}
