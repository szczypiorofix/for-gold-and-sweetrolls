package com.szczypiorofix.sweetrolls.game.gui;

import com.szczypiorofix.sweetrolls.game.main.fonts.BitMapFont;
import com.szczypiorofix.sweetrolls.game.main.fonts.FontParser;
import com.szczypiorofix.sweetrolls.game.objects.characters.Player;
import com.szczypiorofix.sweetrolls.game.objects.item.Item;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

public class Inventory {

    private Player player;
    private MouseCursor mouseCursor;
    private BitMapFont font;
    private Image image;
    private boolean show;
    private int rows = 8;
    private int cols = 5;
    private InventoryContainer[][] items = new InventoryContainer[cols][rows];
    private Item dragItem, itemForDrop;
    private InventoryContainer currentContainer;
    private int dropX, dropY;
    private int dragOriginX, dragOriginY;
    private boolean dropping;
    private boolean drag;

    public Inventory(Player player, MouseCursor mouseCursor) {
        this.player = player;
        this.mouseCursor = mouseCursor;

        try {
            image = new Image("assets/inventory.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }

        font = FontParser.getFont("Immortal HUD Bitmap Font", "immortal-bitmap.xml", "immortal-bitmap.png");
        font.setSize(4.5f);
        int id = 0;
        for (int y = 0; y < cols; y++) {
            for (int x = 0; x < rows; x++) {
                items[y][x] = new InventoryContainer(id, 167 + x * 34, 359 + y * 34, null);
                id++;
            }
        }
    }

    public void update(GameContainer gc, StateBasedGame sgb, int delta) throws SlickException {

        for (int x = 0; x < cols; x++) {
            for (int y = 0; y < rows; y++) {
                items[x][y].update(gc, sgb, delta, 0 , 0);
                currentContainer = items[x][y];

                if (mouseCursor.intersects(currentContainer)) {
                    currentContainer.setHover(true);

                    // DROP
                    if (currentContainer.getItem() != null) {
                        if (mouseCursor.getInput().isMousePressed(Input.MOUSE_RIGHT_BUTTON)) {
                            itemForDrop = currentContainer.getItem();
                            dropX = x;
                            dropY = y;
                            dropping = true;
                        }
                    }


                    // DRAG
                    if (mouseCursor.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
                        if (!drag) {
                            if (items[x][y].getItem() != null && !drag) {
                                dragItem = items[x][y].getItem();
                                dragOriginX = x;
                                dragOriginY = y;
                                drag = true;
                            }
                        } else {
                            currentContainer.setHover(true);
                            //items[x][y].setItem(currentItem);
                            //items[x][y].getItem().setX(currentItem.getX());
                            //items[x][y].getItem().setY(currentItem.getY());
                            dragItem.setX(mouseCursor.getX() - 16);
                            dragItem.setY(mouseCursor.getY() - 16);
                        }
                    } else {
                        if (drag) {
                            currentContainer.item = dragItem;
                            currentContainer.item.setX(currentContainer.getX());
                            currentContainer.item.setY(currentContainer.getY());
                            dragItem = null;
                            items[dragOriginX][dragOriginY].item = null;
                        }
                        drag = false;

                    }

                }
            }
        }
    }

    public void render(GameContainer gc, StateBasedGame sgb, Graphics g) throws SlickException {
        if (show) {
            image.draw(150, 90);

            for (int x = 0; x < cols; x++) {
                for (int y = 0; y < rows; y++) {
                    if (items[x][y] != null) {
                        items[x][y].render(gc, sgb, g, 0, 0);
                    }
                }
            }

        }
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public Item dropFromInventory() {
        return itemForDrop;
    }

    public void removeItem() {
        items[dropX][dropY].item = null;
    }

    public boolean isDropping() {
        return dropping;
    }

    public void setDropping(boolean dropping) {
        this.dropping = dropping;
    }

    public boolean putToInventory(Item item) {
        boolean done = false;
        int c = 0;
        int r = 0;
        do {
            if (c >= cols) return false;
            if (items[c][r].getItem() == null) {
                items[c][r].setItem(item);
                done = true;
            }
            r++;
            if (r >= rows) {
                r = 0;
                c++;
            }
        } while (!done);
        return true;
    }

    public boolean isDrag() {
        return drag;
    }
}
