/*
 * Developed by szczypiorofix on 24.08.18 13:36.
 * Copyright (c) 2018. All rights reserved.
 *
 */

package com.szczypiorofix.sweetrolls.game.quests.articy.content;

public class A_Entity extends A_Object {



    public A_Entity(String id) {
        super(id);
    }

    public A_Entity (String id, String name) {
        super(id, name);
    }


    @Override
    public String toString() {
        return "A_Entity{" +
                "id='" + id + '\'' +
                ", displayName='" + displayName + '\'' +
                '}';
    }
}
