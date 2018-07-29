package com.szczypiorofix.sweetrolls.game.quests.content;


public abstract class A_Object {

    public String id;
    public String displayName;


    public A_Object(String id) {
        this.id = id;
    }

    public A_Object(String id, String displayName) {
        this(id);
        this.displayName = displayName;
    }

}
