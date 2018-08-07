package com.szczypiorofix.sweetrolls.game.main.sounds;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class SFX {

    private Sound s;

    public SFX(String fileName) {
        try {
            s = new Sound("sfx/"+fileName);
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }


    public void play() {
        s.play();
    }
}
