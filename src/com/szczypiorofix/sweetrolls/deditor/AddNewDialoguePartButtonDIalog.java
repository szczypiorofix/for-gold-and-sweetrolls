/*
 * Developed by szczypiorofix on 01.09.18 22:07.
 * Copyright (c) 2018. All rights reserved.
 *
 */

package com.szczypiorofix.sweetrolls.deditor;

import com.szczypiorofix.sweetrolls.game.dialogs.DialoguePartButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AddNewDialoguePartButtonDIalog extends JDialog {

    private JButton saveButton;
    private JPanel mainPanel, southPanel;
    private AddNewDialoguePartDialog addNewDialoguePartDialog;
    private JTextField idField, responseField, nextIdField, rangeFromField, rangeToField, requiredItemField, requiredAmountField, failNextIdField, unlockIdField;
    private JCheckBox endButtonCheckBox, randomCheckBox, lockedCheckBox;

    public AddNewDialoguePartButtonDIalog(Frame owner, AddNewDialoguePartDialog addNewDialoguePartDialog) {
        super(owner, true);
        this.addNewDialoguePartDialog = addNewDialoguePartDialog;
        setSize(new Dimension(400, 400));
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout());
        setResizable(false);

        mainPanel = new JPanel(new GridLayout(12, 2));
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        idField = new JTextField(5);
        responseField = new JTextField(5);
        nextIdField = new JTextField(5);
        rangeFromField = new JTextField(5);
        rangeToField = new JTextField(5);
        requiredItemField = new JTextField(5);
        requiredAmountField = new JTextField(5);
        failNextIdField = new JTextField(5);
        unlockIdField = new JTextField(5);

        endButtonCheckBox = new JCheckBox();
        randomCheckBox = new JCheckBox();
        lockedCheckBox = new JCheckBox();

        mainPanel.add(new JLabel("id: "));
        mainPanel.add(idField);
        mainPanel.add(new JLabel("Response:"));
        mainPanel.add(responseField);
        mainPanel.add(new JLabel("Next id: "));
        mainPanel.add(nextIdField);
        mainPanel.add(new JLabel("Range from: "));
        mainPanel.add(rangeFromField);
        mainPanel.add(new JLabel("Range to: "));
        mainPanel.add(rangeToField);
        mainPanel.add(new JLabel("Required item: "));
        mainPanel.add(requiredItemField);
        mainPanel.add(new JLabel("Required amount:"));
        mainPanel.add(requiredAmountField);
        mainPanel.add(new JLabel("Fail next field:"));
        mainPanel.add(failNextIdField);
        mainPanel.add(new JLabel("Unlock id:"));
        mainPanel.add(unlockIdField);
        mainPanel.add(new JLabel("End Button?"));
        mainPanel.add(endButtonCheckBox);
        mainPanel.add(new JLabel("Random?"));
        mainPanel.add(randomCheckBox);
        mainPanel.add(new JLabel("Locked?"));
        mainPanel.add(lockedCheckBox);

        clearFields();

        southPanel = new JPanel();

        saveButton = new JButton("Dodaj");
        saveButton.addActionListener(e -> {
            addNewDialoguePartDialog.addNewDialoguePartButton(new DialoguePartButton(
                    idField.getText(),
                    responseField.getText(),
                    endButtonCheckBox.isSelected(),
                    nextIdField.getText(),
                    randomCheckBox.isSelected(),
                    rangeFromField.getText(),
                    rangeToField.getText(),
                    requiredItemField.getText(),
                    requiredAmountField.getText(),
                    failNextIdField.getText(),
                    lockedCheckBox.isSelected(),
                    unlockIdField.getText()
            ));
            clearFields();
            dispose();
        });

        southPanel.add(saveButton);

        add(mainPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);
    }

    public void clearFields() {
        idField.setText("0");
        responseField.setText("<response>");
        endButtonCheckBox.setSelected(false);
        nextIdField.setText("0");
        randomCheckBox.setSelected(false);
        rangeFromField.setText("0");
        rangeToField.setText("0");
        requiredItemField.setText("<item>");
        requiredAmountField.setText("0");
        failNextIdField.setText("0");
        lockedCheckBox.setSelected(false);
        unlockIdField.setText("0");
    }

}
