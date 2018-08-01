package com.szczypiorofix.sweetrolls.game.main.core;

import org.newdawn.slick.Game;
import org.newdawn.slick.ScalableGame;


/**
 * Main game class, extends ScalableGame for scalling game sceen (different resolutions support).
 */
public final class ForGoldAndSweetrolls extends ScalableGame {

    /**
     * Main and the only constructor of this class.
     * @param held (GameStatesContainer) - name and display modes from MainClass.
     * @param normalWidth (int) initial width of the game screen.
     * @param normalHeight (int) initial height of the game screen.
     * @param maintainAspect (Boolean) true if game need to keep aspect ratio.
     */
    public ForGoldAndSweetrolls(Game held, int normalWidth, int normalHeight, boolean maintainAspect) {
        super(held, normalWidth, normalHeight, maintainAspect);
    }

}
