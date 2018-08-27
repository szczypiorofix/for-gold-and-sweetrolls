/*
 * Developed by szczypiorofix on 24.08.18 13:28.
 * Copyright (c) 2018. All rights reserved.
 *
 */

package com.szczypiorofix.sweetrolls.game.gui;

import java.io.Serializable;
import java.util.ArrayList;

public class ActionHistory implements Serializable {

    private ArrayList<String> wholeHistory;
    public String[] history;

    public ActionHistory() {
        history = new String[]{
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                ""};
        wholeHistory = new ArrayList<>();
    }

    public void addValue(String v) {
        if (history.length - 1 >= 0) System.arraycopy(history, 1, history, 0, history.length - 1);

        history[history.length-1] = v;

        wholeHistory.add(v);
    }
}
