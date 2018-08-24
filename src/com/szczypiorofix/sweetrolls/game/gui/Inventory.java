/*
 * Developed by szczypiorofix on 24.08.18 13:29.
 * Copyright (c) 2018. All rights reserved.
 *
 */

package com.szczypiorofix.sweetrolls.game.gui;

import com.szczypiorofix.sweetrolls.game.enums.InventorySlotType;
import com.szczypiorofix.sweetrolls.game.enums.ItemType;
import com.szczypiorofix.sweetrolls.game.enums.PlayerAction;
import com.szczypiorofix.sweetrolls.game.interfaces.CloseableFrameListener;
import com.szczypiorofix.sweetrolls.game.interfaces.ConsumableListener;
import com.szczypiorofix.sweetrolls.game.interfaces.DroppableListener;
import com.szczypiorofix.sweetrolls.game.objects.characters.Player;
import com.szczypiorofix.sweetrolls.game.objects.item.Item;
import org.newdawn.slick.*;

import java.util.ArrayList;


public class Inventory implements CloseableFrameListener {

    private Player player;
    private MouseCursor mouseCursor;
    private MainMenuControlls inventoryExit;
    private Image image;
    private boolean show;
    private int rows = 5;
    private int cols = 8;
    private InventoryContainer[][] inventoryContainers = new InventoryContainer[cols][rows+1];
    private Item dragItem, itemForDrop;
    private InventoryContainer currentContainer;
    private int dropX, dropY;
    private int dragOriginX, dragOriginY;
    private boolean drag;
    private DroppableListener droppableListener;
    private ConsumableListener consumableListener;
    private ArrayList<InventoryOptionsButton> optionsFrameButtons = new ArrayList<>();
    private boolean showOptionsFrame = false;
    private int lockedX, lockedY;

    public Inventory(Player player, MouseCursor mouseCursor) {
        this.player = player;
        this.mouseCursor = mouseCursor;

        try {
            image = new Image("assets/inventory.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }

        // INVENTORY CONTAINERS
        int id = 0;
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                inventoryContainers[x][y] = new InventoryContainer(id, InventorySlotType.INVENTORY, 167 + x * 34, 359 + y * 34, null);
                id++;
            }
        }

        inventoryExit = new MainMenuControlls(
                MainMenuControlls.ControlType.CANCEL,
                "",
                false,
                409,
                100,
                32,
                32
                );
        inventoryExit.setCloseableFrameListener(this);

        inventoryContainers[0][rows] = new InventoryContainer(id, InventorySlotType.HEAD, 284, 144, null);
        inventoryContainers[1][rows] = new InventoryContainer(id+1, InventorySlotType.CHEST, 284, 186, null);
        inventoryContainers[2][rows] = new InventoryContainer(id+2, InventorySlotType.LEGS, 284, 263, null);
        inventoryContainers[3][rows] = new InventoryContainer(id+3, InventorySlotType.LEFT_HAND, 214, 203, null);
        inventoryContainers[4][rows] = new InventoryContainer(id+4, InventorySlotType.RIGHT_HAND, 354, 203, null);
        inventoryContainers[5][rows] = new InventoryContainer(id+5, InventorySlotType.LEFT_RING, 218, 247, null);
        inventoryContainers[6][rows] = new InventoryContainer(id+6, InventorySlotType.RIGHT_RING, 350, 247, null);
    }

    public void setDroppableListener(DroppableListener droppableListener) {
        this.droppableListener = droppableListener;
    }

    public void setConsumableListener(ConsumableListener consumableListener) {
        this.consumableListener = consumableListener;
    }

    public void update(GameContainer gc, int delta) throws SlickException {

        if (mouseCursor.intersects(inventoryExit)) {
            inventoryExit.setHover(true);
            if (mouseCursor.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                inventoryExit.close();
                setShow(false);
            } else inventoryExit.setActive(false);
        } else inventoryExit.setHover(false);

        for (int x = 0; x < inventoryContainers.length; x++) {
            for (int y = 0; y < inventoryContainers[0].length; y++) {

                if (showOptionsFrame && mouseCursor.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
                    for (int i = 0; i < optionsFrameButtons.size(); i++) {
                        InventoryOptionsButton currentOption = optionsFrameButtons.get(i);

                        if (mouseCursor.intersects(currentOption)) {

                            if (currentOption.getCommand().equalsIgnoreCase("drop")) {
                                itemForDrop = currentContainer.getItem();
                                dropX = lockedX;
                                dropY = lockedY;
                                droppableListener.drop(itemForDrop);
                            } else if (currentOption.getCommand().equalsIgnoreCase("consume")) {
                                consumableListener.consume(currentContainer.item);
                                currentContainer.item = null;
                            }

                        }

                        showOptionsFrame = false;
                    }

                }

                if (inventoryContainers[x][y] != null && !showOptionsFrame) {
                    inventoryContainers[x][y].update(delta, 0 , 0);
                    currentContainer = inventoryContainers[x][y];
                    lockedX = x;
                    lockedY = y;

                    if (mouseCursor.intersects(currentContainer)) {
                        currentContainer.setHover(true);


                        // DROP
                        if (currentContainer.getItem() != null) {

                            if (mouseCursor.getInput().isMousePressed(Input.MOUSE_RIGHT_BUTTON)) {
                                optionsFrameButtons = new ArrayList<>();

                                if (currentContainer.getItem().getItemType() == ItemType.FOOD)
                                    optionsFrameButtons.add(new InventoryOptionsButton("Zjedz", (int) currentContainer.getX() + 20, (int) currentContainer.getY() - 55, "consume"));

                                if (currentContainer.getItem().getItemType() == ItemType.POTION)
                                    optionsFrameButtons.add(new InventoryOptionsButton("Wypij", (int) currentContainer.getX() + 20, (int) currentContainer.getY() - 55, "consume"));

                                optionsFrameButtons.add(new InventoryOptionsButton("Wyrzuć", (int) currentContainer.getX() + 20, (int) currentContainer.getY() - 30, "drop"));

                                showOptionsFrame = true;
                            }
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
                                        player.statistics.P_ArmorClass += dragItem.getArmorRatio();
                                    }

                                    if (currentContainer.getInventorySlotType() == InventorySlotType.CHEST) {
                                        player.statistics.P_ArmorClass += dragItem.getArmorRatio();
                                    }

                                    if (currentContainer.getInventorySlotType() == InventorySlotType.INVENTORY) {
                                        player.statistics.P_ArmorClass -= dragItem.getArmorRatio();
                                        player.statistics.P_Damage -= dragItem.getDamageRatio();
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

    public void render(Graphics g) throws SlickException {
        if (show) {
            image.draw(150, 90);
            for (int x = 0; x < inventoryContainers.length; x++) {
                for (int y = 0; y < inventoryContainers[0].length; y++) {
                    if (inventoryContainers[x][y] != null) {
                        inventoryContainers[x][y].render(g, 0, 0);
                    }
                }
            }
            if (showOptionsFrame) {
                for (int i = 0; i < optionsFrameButtons.size(); i++) {
                    optionsFrameButtons.get(i).render(g, 0, 0);
                }
            }
            inventoryExit.render(g, 0, 0);
        }
    }


    public void setShow(boolean show) {
        this.show = show;
        showOptionsFrame = false;
    }

    public Item getItemForDrop() {
        return itemForDrop;
    }

    public void removeDroppedItemFromInventory() {
        inventoryContainers[dropX][dropY].item = null;
    }

    public boolean putToInventory(Item item) {
        boolean done = false;
        int c = 0;
        int r = 0;
        do {
            if (r >= rows) return false;

            if (inventoryContainers[c][r].getItem() == null) {
                inventoryContainers[c][r].setItem(item);
                inventoryContainers[c][r].getItem().setFound(true);
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


    @Override
    public void closeFrame() {
        player.setPlayerAction(PlayerAction.MOVE);
    }
}
