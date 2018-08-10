package com.szczypiorofix.sweetrolls.game.gui;

import com.szczypiorofix.sweetrolls.game.enums.InventorySlotType;
import com.szczypiorofix.sweetrolls.game.main.fonts.BitMapFont;
import com.szczypiorofix.sweetrolls.game.main.fonts.FontParser;
import com.szczypiorofix.sweetrolls.game.objects.characters.Player;
import com.szczypiorofix.sweetrolls.game.objects.item.Item;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

import java.util.HashMap;
import java.util.Map;

public class Inventory {

    private Player player;
    private MouseCursor mouseCursor;
    private BitMapFont font;
    private Image image;
    private boolean show;
    private int rows = 5;
    private int cols = 8;
    private InventoryContainer[][] inventoryContainers = new InventoryContainer[cols][rows+1];
    private Item dragItem, itemForDrop;
    private InventoryContainer currentContainer;
    private int dropX, dropY;
    private int dragOriginX, dragOriginY;
    private boolean dropping;
    private boolean drag;
    private boolean using;

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

        // INVENTORY CONTAINERS
        int id = 0;
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                inventoryContainers[x][y] = new InventoryContainer(id, InventorySlotType.INVENTORY, 167 + x * 34, 359 + y * 34, null);
                id++;
            }
        }

        inventoryContainers[0][rows] = new InventoryContainer(id, InventorySlotType.HEAD, 284, 144, null);
        inventoryContainers[1][rows] = new InventoryContainer(id+1, InventorySlotType.CHEST, 284, 186, null);
        inventoryContainers[2][rows] = new InventoryContainer(id+2, InventorySlotType.LEGS, 284, 263, null);
        inventoryContainers[3][rows] = new InventoryContainer(id+3, InventorySlotType.LEFT_HAND, 214, 203, null);
        inventoryContainers[4][rows] = new InventoryContainer(id+4, InventorySlotType.RIGHT_HAND, 354, 203, null);
        inventoryContainers[5][rows] = new InventoryContainer(id+5, InventorySlotType.LEFT_RING, 218, 247, null);
        inventoryContainers[6][rows] = new InventoryContainer(id+6, InventorySlotType.RIGHT_RING, 350, 247, null);
    }

    public void update(GameContainer gc, StateBasedGame sgb, int delta) throws SlickException {

        for (int x = 0; x < inventoryContainers.length; x++) {
            for (int y = 0; y < inventoryContainers[0].length; y++) {

                if (inventoryContainers[x][y] != null) {
                    inventoryContainers[x][y].update(gc, sgb, delta, 0 , 0);
                    currentContainer = inventoryContainers[x][y];

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

                        // USE
                        if (mouseCursor.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                            using = true;
                        }

                        // DRAG
                        if (mouseCursor.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
                            if (!drag) {
                                if (inventoryContainers[x][y].getItem() != null) {
                                    dragOriginX = x;
                                    dragOriginY = y;
                                    dragItem = inventoryContainers[x][y].getItem();
                                    drag = true;
                                }
                            } else {
                                dragItem.setX(mouseCursor.getX() - 16);
                                dragItem.setY(mouseCursor.getY() - 16);
                            }
                        } else {
                            if (drag) {
                                dragItem.setX(dragOriginX);
                                dragItem.setY(dragOriginY);
                                currentContainer.item = dragItem;
                                currentContainer.item.setX(currentContainer.getX());
                                currentContainer.item.setY(currentContainer.getY());
                                if (!(dragOriginX == x && dragOriginY == y)) {
                                    inventoryContainers[dragOriginX][dragOriginY].item = null;

                                    if (currentContainer.getInventorySlotType() == InventorySlotType.HEAD) {
                                        player.statistics.armorClass += dragItem.getArmorRatio();
                                    }

                                    if (currentContainer.getInventorySlotType() == InventorySlotType.CHEST) {
                                        player.statistics.armorClass += dragItem.getArmorRatio();
                                    }

                                    if (currentContainer.getInventorySlotType() == InventorySlotType.INVENTORY) {
                                        //if (dragItem.getType().equalsIgnoreCase())
                                        player.statistics.armorClass -= dragItem.getArmorRatio();
                                        player.statistics.damage -= dragItem.getDamageRatio();
                                    }


                                }
                                dragItem = null;
                                drag = false;
                            }
                        }
                    }
                }
            }
        }
    }

    public void render(GameContainer gc, StateBasedGame sgb, Graphics g) throws SlickException {
        if (show) {
            image.draw(150, 90);
            for (int x = 0; x < inventoryContainers.length; x++) {
                for (int y = 0; y < inventoryContainers[0].length; y++) {
                    if (inventoryContainers[x][y] != null) {
                        inventoryContainers[x][y].render(gc, sgb, g, 0, 0);
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
        inventoryContainers[dropX][dropY].item = null;
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
            if (r >= rows) return false;

            if (inventoryContainers[c][r].getItem() == null) {
                inventoryContainers[c][r].setItem(item);
                done = true;
            }

            c++;
            if (c >= cols) {
                c = 0;
                r++;
            }
        } while (!done);
        return true;
    }

    public boolean isDrag() {
        return drag;
    }
}
