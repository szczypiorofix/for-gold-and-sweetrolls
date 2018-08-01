package com.szczypiorofix.sweetrolls.game.quests.articy.content;

import java.util.ArrayList;

public class A_FeatureDefinition extends A_Object {


    public int propertyDefinitionsCount;
    public ArrayList<String> propertyDefinitionsRef;

    public A_FeatureDefinition(String id) {
        super(id);
        propertyDefinitionsRef = new ArrayList<>();
    }


}
