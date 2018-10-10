/*
 * Developed by szczypiorofix on 10.10.18 23:10.
 * Copyright (c) 2018. All rights reserved.
 *
 */

package com.szczypiorofix.sweetrolls.deditor;

import com.szczypiorofix.sweetrolls.game.dialogs.DialoguePartButton;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AddNewDialoguePartDialog extends JDialog {

    private JTextField idField;
    private JTextArea textArea;
    private JButton saveButton, addButton;
    private JPanel northPanel, centerPanel, southPanel;
    private JList buttonsList;
    private DefaultListModel<String> listModel;
    private String textData;
    private int idData;
    private AddNewDialoguePartButtonDIalog addNewDialoguePartButtonDIalog;

    public AddNewDialoguePartDialog(Frame owner) {
        super(owner, true);
        setSize(new Dimension(400, 400));
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout());
        setResizable(false);

        addNewDialoguePartButtonDIalog = new AddNewDialoguePartButtonDIalog(owner, this);

        // NORTH PANEL
        northPanel = new JPanel();
        idField = new JTextField(6);
        idField.setToolTipText("DialoguePart id");
        textArea = new JTextArea(5, 30);
        textArea.setToolTipText("DialoguePart text");
        northPanel.add(idField);
        northPanel.add(textArea);



        // CENTER PANEL
        centerPanel = new JPanel();

        listModel = new DefaultListModel<>();
        buttonsList = new JList<>(listModel);
        centerPanel.add(buttonsList);

        // SOUTH PANEL
        southPanel = new JPanel();

        saveButton = new JButton("Zapisz");
        addButton = new JButton("Dodaj opcję dialogową");
        addButton.addActionListener(e -> {
            addNewDialoguePartButtonDIalog.setVisible(true);
        });

        southPanel.add(saveButton);
        southPanel.add(addButton);


        // Dialog
        add(northPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);

    }

    public void addNewDialoguePartButton(DialoguePartButton button) {
        System.out.println(button);

        listModel.addElement(button.getResponse());
        centerPanel.revalidate();

        centerPanel.revalidate();

    }


}
