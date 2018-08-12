package com.szczypiorofix.sweetrolls.game.objects.terrain;

import com.szczypiorofix.sweetrolls.game.enums.ObjectType;
import com.szczypiorofix.sweetrolls.game.objects.GameObject;
import org.newdawn.slick.*;


public class Ground extends GameObject {

    private Image image;
    private Image imageThumbnail;
    private Color highlighColor = new Color(1f, 1f, 1f, 0.65f);

    public Ground(String name, float x, float y, float width, float height, ObjectType objectType, Image image, boolean visible) {
        super(name, x, y, width, height, objectType);
        this.image = image;
        this.visible = visible;
        imageThumbnail = image.getScaledCopy( 0.1f);
        //this.image.setFilter(Image.FILTER_LINEAR);
    }

//    public Ground(String name, float x, float y, float width, float height, ObjectType objectType, Image image) {
//        super(name, x, y, width, height, objectType);
//        this.image = image;
//        imageThumbnail = image.getScaledCopy(0.1f);
//    }

    @Override
    public void update(int delta, float offsetX, float offsetY) {

        hover = false;
    }

    @Override
    public void render(Graphics g, float offsetX, float offsetY) {
        if (visible) {
            if (hover) {
                g.drawImage(image, - offsetX + x, - offsetY + y, highlighColor);
            } else
                g.drawImage(image, - offsetX + x, - offsetY + y);
        }
    }

    @Override
    public void turn() {}

    public Image getThumbnail() {
        return imageThumbnail;
    }
}
