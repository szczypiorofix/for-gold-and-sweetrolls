package com.szczypiorofix.racoon.game.sounds;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class SFX {

    private String fileName;
    private Sound s;

    public SFX(String fileName) {
        this.fileName = fileName;
        try {
            s = new Sound("res/sfx/"+fileName);
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }


    public void play() {
        s.play();
    }
}
