/*
 * Developed by szczypiorofix on 26.08.18 00:42.
 * Copyright (c) 2018. All rights reserved.
 *
 */

package com.szczypiorofix.sweetrolls.game.quests.articy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class EditorWindow extends JFrame {

    private JMenuBar menuBar = new JMenuBar();
    private JMenu plikMenu = new JMenu("Projekt");
    private JMenu infoMenu = new JMenu("Info");
    private JMenuItem plikMenuOtworz = new JMenuItem("Otwórz", KeyEvent.VK_O);
    private JMenuItem plikMenuZakoncz = new JMenuItem("Zakończ", KeyEvent.VK_Z);
    private JMenuItem infoMenuInfo = new JMenuItem("Informacje", KeyEvent.VK_I);
    final JFileChooser fc = new JFileChooser();

    public EditorWindow(String title) throws HeadlessException {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);

        MainPanel mainPanel = new MainPanel(new BorderLayout(), true);
        JScrollPane scrollMainPanel = new JScrollPane(mainPanel);
        this.add(scrollMainPanel);

        plikMenuOtworz.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        plikMenuZakoncz.addActionListener(e -> {

        });

        plikMenuZakoncz.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
        plikMenuZakoncz.addActionListener(e -> System.exit(0));

        plikMenu.setMnemonic(KeyEvent.VK_P);
        plikMenu.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");
        plikMenu.add(plikMenuOtworz);
        plikMenu.add(plikMenuZakoncz);

        infoMenuInfo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK));
        infoMenu.add(infoMenuInfo);
        infoMenu.setMnemonic(KeyEvent.VK_I);

        menuBar.add(plikMenu);
        menuBar.add(infoMenu);
        this.setJMenuBar(menuBar);

        new ArticyXMLParser("TestProject.xml");
    }


}
