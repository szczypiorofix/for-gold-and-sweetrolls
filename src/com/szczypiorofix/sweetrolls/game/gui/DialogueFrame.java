/*
 * Developed by szczypiorofix on 24.08.18 13:29.
 * Copyright (c) 2018. All rights reserved.
 *
 */

package com.szczypiorofix.sweetrolls.game.gui;

import com.szczypiorofix.sweetrolls.game.dialogs.DialoguePartButton;
import com.szczypiorofix.sweetrolls.game.enums.PlayerAction;
import com.szczypiorofix.sweetrolls.game.interfaces.CloseableFrameListener;
import com.szczypiorofix.sweetrolls.game.main.MainClass;
import com.szczypiorofix.sweetrolls.game.main.fonts.BitMapFont;
import com.szczypiorofix.sweetrolls.game.main.fonts.FontParser;
import com.szczypiorofix.sweetrolls.game.main.graphics.Textures;
import com.szczypiorofix.sweetrolls.game.objects.characters.NPC;
import com.szczypiorofix.sweetrolls.game.objects.characters.Player;
import org.newdawn.slick.*;

public class DialogueFrame implements CloseableFrameListener {

    private Player player;
    private NPC npc;
    private MouseCursor mouseCursor;
    private boolean showDialog = false;
    private BitMapFont fontS, fontL;
    private Image dialogueFrameImage;


    public DialogueFrame(Player player, MouseCursor mouseCursor) {
        this.player = player;
        this.mouseCursor = mouseCursor;
        fontS = FontParser.getFont("Immortal NPC S Bitmap Font", "immortal-bitmap.xml", "immortal-bitmap.png");
        fontS.setSize(4.5f);
        fontL = FontParser.getFont("Immortal NPC L Bitmap Font", "immortal-bitmap.xml", "immortal-bitmap.png");
        fontL.setSize(5.5f);
        dialogueFrameImage = Textures.getInstance().dialogueFrame;
    }

    public void setNpc(NPC npc) {
        this.npc = npc;
    }

    public void update(GameContainer gc, int delta, float offsetX, float offsetY) {
        if (npc != null && showDialog) {
            for (int i = 0; i < npc.getDialogue().getCurrentDialoguePart().getDialoguePartButtons().size(); i++) {
                DialoguePartButton currentButton = npc.getDialogue().getCurrentDialoguePart().getDialoguePartButtons().get(i);
                if (mouseCursor.intersects(currentButton)
                        && gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)
                    ) {

                    if (currentButton.isRandom())
                        if (currentButton.getRangeFrom() == 0 && currentButton.getRangeTo() == 0)
                            npc.getDialogue().setCurrentDialogueState(MainClass.RANDOM.nextInt(npc.getDialogue().getDialogueParts().size()));
                        else {
                            System.out.println("Range from: " +currentButton.getRangeFrom()+ " to: " +currentButton.getRangeTo());
                            int r = MainClass.RANDOM.nextInt(
                                    (currentButton.getRangeTo() - currentButton.getRangeFrom()) + 1
                            ) + currentButton.getRangeFrom();
                            System.out.println(r);
                            npc.getDialogue().setCurrentDialogueState(r);
                        }

                    else
                        npc.getDialogue().setCurrentDialogueState(currentButton.getNextId());

                    if (currentButton.isEndButton()) {
                        showDialog = false;
                        player.setPlayerAction(PlayerAction.MOVE);
                    }
                }
            }
        }
    }

    public void render(Graphics g) throws SlickException {
        if (showDialog) {
            dialogueFrameImage.draw(10, 300);
            fontS.draw(npc.getName()+" :", 30, 310);
            fontL.draw(npc.getDialogue().getCurrentDialoguePart().getText(), 30, 345);
            for (int i = 0; i < npc.getDialogue().getCurrentDialoguePart().getDialoguePartButtons().size(); i++) {
                npc.getDialogue().getCurrentDialoguePart().getDialoguePartButtons().get(i).render(g, 0, 0);
            }

        }
    }

    public boolean isShowDialog() {
        return showDialog;
    }

    public void setShowDialog(boolean showDialog) {
        this.showDialog = showDialog;
    }

    public NPC getNpc() {
        return npc;
    }

    @Override
    public void closeFrame() {
        showDialog = false;
    }
}
