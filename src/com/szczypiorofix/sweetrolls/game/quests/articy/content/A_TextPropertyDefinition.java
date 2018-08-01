package com.szczypiorofix.sweetrolls.game.quests.articy.content;

public class A_TextPropertyDefinition extends A_Object {

    /**
     * Based on parent property;
     */
    public String basedOn;

    /**
     *
     */
    public boolean isMandatory;

    /**
     *
     */
    public boolean isLocalized;

    /**
     *
     */
    public boolean allowLineBreaks;

    public A_TextPropertyDefinition(String id) {
        super(id);
    }

}
