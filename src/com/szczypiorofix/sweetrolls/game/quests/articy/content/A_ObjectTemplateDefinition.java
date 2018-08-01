package com.szczypiorofix.sweetrolls.game.quests.articy.content;

import java.util.ArrayList;

public class A_ObjectTemplateDefinition extends A_Object {

    public int featureDefinitionsCount;
    public ArrayList<String> featureDefinitionRef;

    public A_ObjectTemplateDefinition(String id) {
        super(id);
        featureDefinitionRef = new ArrayList<>();
    }

}
