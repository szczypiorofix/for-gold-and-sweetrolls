package com.szczypiorofix.sweetrolls.game.quests.content;

public class A_Pin extends ArticyObject {

    private int index;
    private Semantic semantic;
    private String expression;

    public A_Pin(String id) {
        super(id);
    }

    public A_Pin(String id, int index, Semantic semantic, String expression) {
        this(id);
        this.index = index;
        this.semantic = semantic;
        this.expression = expression;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Semantic getSemantic() {
        return semantic;
    }

    public void setSemantic(Semantic semantic) {
        this.semantic = semantic;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
}
