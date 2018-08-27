/*
 * Developed by szczypiorofix on 26.08.18 00:42.
 * Copyright (c) 2018. All rights reserved.
 *
 */

package com.szczypiorofix.sweetrolls.game.quests.articy;

import com.szczypiorofix.sweetrolls.game.quests.articy.content.A_Entity;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class MainPanel extends JPanel {

    private JPanel northPanel, westPanel, eastPanel, southPanel, centerPanel;
    private JPanel flowsPanel, entitiesPanel;
    private JTabbedPane tabbedPane = new JTabbedPane();



    public MainPanel(LayoutManager layout, boolean isDoubleBuffered) {
        super(layout, isDoubleBuffered);

        northPanel = new JPanel();
        eastPanel = new JPanel();
        westPanel = new JPanel();
        southPanel = new JPanel();
        centerPanel = new JPanel();

        flowsPanel = new JPanel();
        flowsPanel.add(new JButton("OK"));
        flowsPanel.add(new JTextArea(25, 50));

        entitiesPanel = new JPanel();

        tabbedPane.addTab("Flows", flowsPanel);
        tabbedPane.addTab("Entities", entitiesPanel);

        centerPanel.add(tabbedPane);

        this.add(northPanel, BorderLayout.NORTH);
        this.add(southPanel, BorderLayout.SOUTH);
        this.add(westPanel, BorderLayout.WEST);
        this.add(eastPanel, BorderLayout.EAST);
        this.add(centerPanel, BorderLayout.CENTER);


    }


    public void updateEntitiesPanel(ArticyXMLParser parser) {
        for (Map.Entry<String, A_Entity> entry : parser.getEntitiesList().entrySet()) {
            entitiesPanel.add(new JButton(entry.getValue().displayName));
        }
    }

}
