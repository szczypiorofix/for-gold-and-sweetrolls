/*
 * Developed by szczypiorofix on 24.08.18 13:45.
 * Copyright (c) 2018. All rights reserved.
 *
 */

package com.szczypiorofix.sweetrolls.dialogueeditor;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    private JPanel northPanel, westPanel, eastPanel, southPanel, centerPanel;

    public MainPanel(LayoutManager layout, boolean isDoubleBuffered) {
        super(layout, isDoubleBuffered);

//        northPanel = new JPanel(new FlowLayout());
//        eastPanel = new JPanel();
//        westPanel = new JPanel();
//        southPanel = new JPanel();
//        centerPanel = new JPanel();
//
//        northPanel.add(new JTextArea(10, 40));
//        northPanel.add(new JButton("OK"));
//
//
//        this.add(northPanel, BorderLayout.NORTH);
//        this.add(southPanel, BorderLayout.SOUTH);
//        this.add(westPanel, BorderLayout.WEST);
//        this.add(eastPanel, BorderLayout.EAST);
//        this.add(centerPanel, BorderLayout.CENTER);

        JButton button;
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        button = new JButton("Button 1");
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        this.add(button, c);

        button = new JButton("Button 2");
        c.gridx = 1;
        c.gridy = 0;
        this.add(button, c);

        button = new JButton("Button 3");
        c.gridx = 2;
        c.gridy = 0;
        this.add(button, c);

        button = new JButton("Long-Named Button 4");
        c.ipady = 40;      //make this component tall
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
        this.add(button, c);

        button = new JButton("5");
        c.ipady = 0;       //reset to default
        c.weighty = 1.0;   //request any extra vertical space
        c.anchor = GridBagConstraints.PAGE_END; //bottom of space
        c.insets = new Insets(10,0,0,0);  //top padding
        c.gridx = 1;       //aligned with button 2
        c.gridwidth = 2;   //2 columns wide
        c.gridy = 2;       //third row
        this.add(button, c);

    }
}
