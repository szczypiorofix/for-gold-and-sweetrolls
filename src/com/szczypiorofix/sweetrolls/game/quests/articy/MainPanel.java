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
    private JPanel flowFragmentsPanel, entitiesPanel, dialoguesPanel, dialoguesFragmentsPanel;
    private JTabbedPane tabbedPane = new JTabbedPane();
    private JList<String> entityList;
    private DefaultListModel<String> defaultListModel;



    public MainPanel(LayoutManager layout, boolean isDoubleBuffered) {
        super(layout, isDoubleBuffered);

        northPanel = new JPanel();
        eastPanel = new JPanel();
        westPanel = new JPanel();
        southPanel = new JPanel();
        centerPanel = new JPanel();



        flowFragmentsPanel = new JPanel();

        entitiesPanel = new JPanel();

        dialoguesPanel = new JPanel();

        dialoguesFragmentsPanel = new JPanel();



        defaultListModel = new DefaultListModel<>();
        entityList = new JList<>(defaultListModel);
        //entityList.setBounds(100,100, 75,75);

        entitiesPanel.add(entityList);

        tabbedPane.addTab("Flows", flowFragmentsPanel);
        tabbedPane.addTab("Entities", entitiesPanel);
        tabbedPane.addTab("Dialogues", dialoguesPanel);
        tabbedPane.addTab("Dialogues Fragmens", dialoguesFragmentsPanel);


        centerPanel.add(tabbedPane);

        this.add(northPanel, BorderLayout.NORTH);
        this.add(southPanel, BorderLayout.SOUTH);
        this.add(westPanel, BorderLayout.WEST);
        this.add(eastPanel, BorderLayout.EAST);
        this.add(centerPanel, BorderLayout.CENTER);


    }


    public void updateEntitiesPanel(ArticyXMLParser parser) {
        for (Map.Entry<String, A_Entity> entry : parser.getEntitiesList().entrySet()) {

            defaultListModel.addElement(entry.getValue().displayName);


        }
    }

}
