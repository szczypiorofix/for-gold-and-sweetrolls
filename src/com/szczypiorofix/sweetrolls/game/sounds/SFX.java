package com.szczypiorofix.sweetrolls.game.sounds;

import com.szczypiorofix.sweetrolls.game.main.MainClass;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class SFX {

    private Sound s;

    public SFX(String fileName) {
        try {
            s = new Sound(MainClass.RES + "sfx/"+fileName);
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }


    public void play() {
        s.play();
    }
}
