/*
 * Developed by szczypiorofix on 24.08.18 13:37.
 * Copyright (c) 2018. All rights reserved.
 *
 */

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

    public void play(int volume) {
        s.play(1f, (float) (volume) / 100);
    }
}
