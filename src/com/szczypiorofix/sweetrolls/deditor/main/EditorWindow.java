/*
 * Developed by szczypiorofix on 30.08.18 07:54.
 * Copyright (c) 2018. All rights reserved.
 *
 */

package com.szczypiorofix.sweetrolls.deditor.main;

import javax.swing.*;
import java.awt.*;

public class EditorWindow extends JFrame {

    private MainPanel mainPanel;

    public EditorWindow(String title) throws HeadlessException {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(600, 450));
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        mainPanel = new MainPanel();
        add(mainPanel, BorderLayout.CENTER);

    }
}
