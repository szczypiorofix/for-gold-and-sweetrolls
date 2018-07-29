package com.szczypiorofix.sweetrolls.game.quests.content;

public class A_Pin extends A_Object {

    public int index;
    public Semantic semantic;
    public String expression;

    public A_Pin(String id) {
        super(id);
    }

    public A_Pin(String id, int index, Semantic semantic, String expression) {
        this(id);
        this.index = index;
        this.semantic = semantic;
        this.expression = expression;
    }


    @Override
    public String toString() {
        return "A_Pin{" +
                "index=" + index +
                ", semantic=" + semantic +
                ", id='" + id + "\'}, \n";
    }
}
