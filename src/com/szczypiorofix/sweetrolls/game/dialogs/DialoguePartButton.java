/*
 * Developed by szczypiorofix on 29.08.18 11:38.
 * Copyright (c) 2018. All rights reserved.
 *
 */

package com.szczypiorofix.sweetrolls.game.dialogs;

import com.szczypiorofix.sweetrolls.game.enums.ObjectType;
import com.szczypiorofix.sweetrolls.game.main.fonts.BitMapFont;
import com.szczypiorofix.sweetrolls.game.main.fonts.FontParser;
import com.szczypiorofix.sweetrolls.game.objects.GameObject;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class DialoguePartButton extends GameObject {

    private BitMapFont font;

    private String response;
    private boolean endButton;
    private boolean random;
    private int nextId;
    private int rangeFrom, rangeTo;
    private String requiredItem;
    private int requiredAmount;
    private int failNextId;
    private int bId;
    private int unlockId;
    private boolean locked;
    public static final int BX = 30;
    public static int BY = 570;
    public static final int BWIDTH = 360;
    public static final int BHEIGHT = 40;

    public DialoguePartButton(String id,
                              String response,
                              String endButton,
                              String nextId,
                              String random,
                              String rangeFrom,
                              String rangeTo,
                              String requiredItem,
                              String requiredAmount,
                              String failNextId,
                              String locked,
                              String unlockId) {
        super(response, BX, BY, BWIDTH, BHEIGHT, ObjectType.GUI);
        this.bId = Integer.parseInt(id);
        this.response = response;
        this.endButton = Boolean.parseBoolean(endButton);
        this.random = Boolean.parseBoolean(random);
        this.nextId = Integer.parseInt(nextId);
        this.rangeFrom = Integer.parseInt(rangeFrom);
        this.rangeTo = Integer.parseInt(rangeTo);
        this.requiredItem = requiredItem;
        this.requiredAmount = Integer.parseInt(requiredAmount);
        this.failNextId = Integer.parseInt(failNextId);
        this.locked = Boolean.parseBoolean(locked);
        this.unlockId = Integer.parseInt(unlockId);
    }

    public DialoguePartButton(String id,
                              String response,
                              boolean endButton,
                              String nextId,
                              boolean random,
                              String rangeFrom,
                              String rangeTo,
                              String requiredItem,
                              String requiredAmount,
                              String failNextId,
                              boolean locked,
                              String unlockId) {
        super(response, BX, BY, BWIDTH, BHEIGHT, ObjectType.GUI);
        this.bId = Integer.parseInt(id);
        this.response = response;
        this.endButton = endButton;
        this.random = random;
        this.nextId = Integer.parseInt(nextId);
        this.rangeFrom = Integer.parseInt(rangeFrom);
        this.rangeTo = Integer.parseInt(rangeTo);
        this.requiredItem = requiredItem;
        this.requiredAmount = Integer.parseInt(requiredAmount);
        this.failNextId = Integer.parseInt(failNextId);
        this.locked = locked;
        this.unlockId = Integer.parseInt(unlockId);
    }

    public void setFontForGame() {
        font = FontParser.getFont();
    }

    public int getRangeFrom() {
        return rangeFrom;
    }

    public void setRangeFrom(int rangeFrom) {
        this.rangeFrom = rangeFrom;
    }

    public int getRangeTo() {
        return rangeTo;
    }

    public void setRangeTo(int rangeTo) {
        this.rangeTo = rangeTo;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public boolean isEndButton() {
        return endButton;
    }

    public void setEndButton(boolean endButton) {
        this.endButton = endButton;
    }

    public int getNextId() {
        return nextId;
    }

    public void setNextId(int nextId) {
        this.nextId = nextId;
    }

    public boolean isRandom() {
        return random;
    }

    public void setRandom(boolean random) {
        this.random = random;
    }

    public String getRequiredItem() {
        return requiredItem;
    }

    public void setRequiredItem(String requiredItem) {
        this.requiredItem = requiredItem;
    }

    public int getRequiredAmount() {
        return requiredAmount;
    }

    public void setRequiredAmount(int requiredAmount) {
        this.requiredAmount = requiredAmount;
    }

    public int getFailNextId() {
        return failNextId;
    }

    public void setFailNextId(int failNextId) {
        this.failNextId = failNextId;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public int getbId() {
        return bId;
    }

    public void setbId(int bId) {
        this.bId = bId;
    }

    public int getUnlockId() {
        return unlockId;
    }

    public void setUnlockId(int unlockId) {
        this.unlockId = unlockId;
    }

    @Override
    public void update(int delta, float offsetX, float offsetY) throws SlickException {}

    @Override
    public void render(Graphics g, float offsetX, float offsetY) throws SlickException {
        g.drawRect(x - 5, y - 2, width + 5, height + 4);
        font.draw(response, x, y + 10);
    }

    public void render(Graphics g, float offsetX, float offsetY, int x, int y) throws SlickException {
        this.x = x;
        this.y = y;
        g.drawRect(x - 5, y - 2, width + 5, height + 4);
        font.draw(response, x, y + 10);
    }

    @Override
    public String toString() {
        return "DialoguePartButton{" +
                "response='" + response + '\'' +
                ", endButton=" + endButton +
                ", random=" + random +
                ", nextId=" + nextId +
                ", rangeFrom=" + rangeFrom +
                ", rangeTo=" + rangeTo +
                ", requiredItem='" + requiredItem + '\'' +
                ", requiredAmount=" + requiredAmount +
                ", failNextId=" + failNextId +
                ", bId=" + bId +
                ", unlockId=" + unlockId +
                ", locked=" + locked +
                '}';
    }
}
