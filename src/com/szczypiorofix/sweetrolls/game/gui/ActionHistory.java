package com.szczypiorofix.sweetrolls.game.gui;

public class ActionHistory {

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
    }

    public void addValue(String v) {
        if (history.length - 1 >= 0) System.arraycopy(history, 1, history, 0, history.length - 1);

        history[history.length-1] = v;
    }
}
