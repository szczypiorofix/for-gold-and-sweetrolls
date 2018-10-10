/*
 * Developed by szczypiorofix on 10.10.18 23:10.
 * Copyright (c) 2018. All rights reserved.
 *
 */

package com.szczypiorofix.sweetrolls.deditor;

import com.szczypiorofix.sweetrolls.game.dialogs.Dialogue;
import com.szczypiorofix.sweetrolls.game.dialogs.DialoguesXMLParser;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;

public class EditorWindow extends JFrame {

    private MainPanel mainPanel;
    private JScrollPane mainScrollPanel;
    private FunctionalButtonsPanel functionalButtonsPanel;
    private JMenuBar menuBar;
    private JMenu menuFile;
    private JMenuItem menuFileOpen, menuFileSave, menuFileExit;
    private JFileChooser fc;
    private JButton addDialoguePartButton;
    private AddNewDialoguePartDialog addNewDialoguePartDialog;

    public EditorWindow(String title) throws HeadlessException {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(600, 450));
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        mainPanel = new MainPanel();
        functionalButtonsPanel = new FunctionalButtonsPanel();
        mainScrollPanel = new JScrollPane(mainPanel);
        add(mainScrollPanel, BorderLayout.CENTER);
        add(functionalButtonsPanel, BorderLayout.NORTH);

        addNewDialoguePartDialog = new AddNewDialoguePartDialog(this);

        addDialoguePartButton = functionalButtonsPanel.getAddDialoguePartButton();
        addDialoguePartButton.addActionListener(e -> {
            addNewDialoguePartDialog.setVisible(true);
        });

        menuBar = new JMenuBar();
        menuFile = new JMenu("File");

        menuFileOpen = new JMenuItem("Open", KeyEvent.VK_O);
        menuFileOpen.addActionListener(e -> {

            fc = new JFileChooser(FileSystemView.getFileSystemView().getDefaultDirectory());
            fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
            fc.setDialogTitle("XML dialogue file ");
            fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fc.setMultiSelectionEnabled(false);
            fc.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter filter = new FileNameExtensionFilter("XML file (FGAS dialogue)", "xml");
            fc.addChoosableFileFilter(filter);

            int returnValue = fc.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fc.getSelectedFile();
                Dialogue dialogue = DialoguesXMLParser.parseDialogueXML(selectedFile.getAbsolutePath(), true);
                System.out.println(dialogue.getName());
                mainPanel.updateMainPanel(dialogue);
                menuFileOpen.setEnabled(false);
            }
        });


        menuFileSave = new JMenuItem("Save", KeyEvent.VK_S);


        menuFileExit = new JMenuItem("Exit", KeyEvent.VK_X);
        menuFileExit.addActionListener(e -> System.exit(0));

        menuFile.add(menuFileOpen);
        menuFile.add(menuFileSave);
        menuFile.add(menuFileExit);

        menuBar.add(menuFile);
        setJMenuBar(menuBar);

    }


}
