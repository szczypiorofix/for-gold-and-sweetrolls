package com.szczypiorofix.sweetrolls.game.quests.articy.content;

import java.util.HashMap;

public class A_Dialogue extends A_Object {

    public String text;
    public HashMap<String, A_Pin> pins;

    public A_Dialogue(String id) {
        super(id);
        pins = new HashMap<>();
    }
}
