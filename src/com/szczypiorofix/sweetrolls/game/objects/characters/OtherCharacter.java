package com.szczypiorofix.sweetrolls.game.objects.characters;

import com.szczypiorofix.sweetrolls.game.enums.ObjectType;
import com.szczypiorofix.sweetrolls.game.tilemap.Property;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;

public class OtherCharacter extends Character {


    OtherCharacter(String name, int x, int y, int width, int height, ObjectType objectType, ArrayList<Property> properties) {
        super(name, x, y, width, height, objectType, properties);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta, int offsetX, int offsetY) {

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g, int offsetX, int offsetY) {

    }

    @Override
    public void turn() {

    }
}
