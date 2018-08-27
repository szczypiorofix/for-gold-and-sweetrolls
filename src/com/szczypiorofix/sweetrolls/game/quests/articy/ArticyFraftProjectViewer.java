/*
 * Developed by szczypiorofix on 24.08.18 13:35.
 * Copyright (c) 2018. All rights reserved.
 *
 */

package com.szczypiorofix.sweetrolls.game.quests.articy;


import java.awt.*;

public class ArticyFraftProjectViewer {

    private ArticyFraftProjectViewer() {
        EditorWindow editorWindow = new EditorWindow("Articy Draft Viewer");
        editorWindow.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(ArticyFraftProjectViewer::new);
    }
}
