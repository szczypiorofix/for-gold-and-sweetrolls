/*
 * Developed by szczypiorofix on 30.08.18 11:22.
 * Copyright (c) 2018. All rights reserved.
 *
 */

package com.szczypiorofix.sweetrolls.deditor;

import java.awt.*;

public class DialogueEditor {

    public DialogueEditor() {
        EditorWindow editorWindow = new EditorWindow("Dialogue Editor");
        editorWindow.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(DialogueEditor::new);
    }

}
