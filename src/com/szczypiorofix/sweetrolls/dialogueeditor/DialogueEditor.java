/*
 * Developed by szczypiorofix on 24.08.18 13:26.
 * Copyright (c) 2018. All rights reserved.
 *
 */

package com.szczypiorofix.sweetrolls.dialogueeditor;

public class DialogueEditor {

    private DialogueEditor() {
        EditorWindow editorWindow = new EditorWindow("Dialogue Editor");
        editorWindow.setVisible(true);
    }

    public static void main(String[] args) {
        new DialogueEditor();
    }

}
