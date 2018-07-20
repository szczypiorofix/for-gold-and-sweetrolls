package com.szczypiorofix.sweetrolls.game.objects.character;

import com.szczypiorofix.sweetrolls.game.def.ObjectType;
import com.szczypiorofix.sweetrolls.game.graphics.Textures;
import com.szczypiorofix.sweetrolls.game.objects.GameObject;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class NPC extends GameObject {

    private Image npcImage;

    public NPC(String name, float x, float y, float width, float height, Image image) {
        super(name, x, y, width, height, ObjectType.NPC);
        if (image == null) {
            npcImage = Textures.getInstance().classm32.getSprite(0, 0);
        } else npcImage = image;
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta, float offsetX, float offsetY) throws SlickException {

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g, float offsetX, float offsetY) throws SlickException {
        npcImage.draw(- offsetX + x, - offsetY + y);
        if (hover) {
            g.drawString(name, - offsetX + x, - offsetY + y - 15);
        }
    }
}
