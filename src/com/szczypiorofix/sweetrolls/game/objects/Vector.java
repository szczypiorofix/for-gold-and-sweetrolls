package com.szczypiorofix.sweetrolls.game.objects;

public class Vector {

    public int x = 0;
    public int y = 0;

    public Vector() {}

    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void multiply(int value) {
        this.x *= value;
        this.y *= value;
    }

    public void multiply(int valueX, int valueY) {
        this.x *= valueX;
        this.y *= valueY;
    }

}
