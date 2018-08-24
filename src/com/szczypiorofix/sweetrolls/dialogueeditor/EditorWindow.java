/*
 * Developed by szczypiorofix on 24.08.18 13:26.
 * Copyright (c) 2018. All rights reserved.
 *
 */

package com.szczypiorofix.sweetrolls.dialogueeditor;

import javax.swing.*;
import java.awt.*;

public class EditorWindow extends JFrame {

    public EditorWindow(String title) throws HeadlessException {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);

        MainPanel mainPanel = new MainPanel(new BorderLayout(), true);
        JScrollPane scrollMainPanel = new JScrollPane(mainPanel);
        this.add(scrollMainPanel);
    }


}
