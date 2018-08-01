package com.szczypiorofix.sweetrolls.game.quests.articy.content;


public abstract class A_Object {

    public String id;
    public String displayName;
    public String technicalName;
    public String color;


    public A_Object(String id) {
        this.id = id;
    }

    public A_Object(String id, String displayName) {
        this(id);
        this.displayName = displayName;
        this.technicalName = "";
        this.color = "";
    }

}
