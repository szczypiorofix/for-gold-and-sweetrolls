package com.szczypiorofix.sweetrolls.game.gui;

import com.szczypiorofix.sweetrolls.game.main.fonts.BitMapFont;
import com.szczypiorofix.sweetrolls.game.main.fonts.FontParser;
import com.szczypiorofix.sweetrolls.game.objects.characters.NPC;
import com.szczypiorofix.sweetrolls.game.objects.characters.Player;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

public class DialogueFrame {

    private Player player;
    private NPC npc;
    private MouseCursor mouseCursor;
    private boolean showDialog = false;
    private BitMapFont font;

    public DialogueFrame() {
    }

    public DialogueFrame(Player player, NPC npc, MouseCursor mouseCursor) {
        this.player = player;
        this.npc = npc;
        this.mouseCursor = mouseCursor;
        font = FontParser.getFont("Immortal NPC Bitmap Font", "immortal-bitmap.xml", "immortal-bitmap.png");
        font.setSize(5.5f);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta, float offsetX, float offsetY) throws SlickException {
        if (npc != null && showDialog) {
            for (int i = 0; i < npc.getDialog().getDialogues().get(npc.getDialog().getCurrentDialogueState()).getButtons().size(); i++) {

                DialogueButton currentButton = npc.getDialog().getDialogues().get(npc.getDialog().getCurrentDialogueState()).getButtons().get(i);
                if (mouseCursor.intersects(currentButton)
                        && gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {

                    npc.getDialog().setCurrentDialogueState(currentButton.getNextDialogueState());

                    System.out.println(currentButton.getName());
                    System.out.println(npc.getDialog().getCurrentDialogueState());

                    if (currentButton.isEndButton()) {

                        showDialog = false;

                    }
//                    else {
//                        npc.getDialog().setDialogueOption(currentButton.getNextDialogueState());
//                    }

                }

            }
        }

    }

    public void render(GameContainer gc, StateBasedGame sgb, Graphics g) throws SlickException {
        if (showDialog) {
            Color c = g.getColor();
            g.setColor(new Color(0.1f, 0.1f, 0.1f, 0.9f));
            g.fillRect(0, 300, 560, 290);
            font.draw(npc.getDialog().getDialogues().get(npc.getDialog().getCurrentDialogueState()).getName(), 30, 310);
            for (int i = 0; i < npc.getDialog().getDialogues().get(npc.getDialog().getCurrentDialogueState()).getButtons().size(); i++) {
                npc.getDialog().getDialogues().get(npc.getDialog().getCurrentDialogueState()).getButtons().get(i).render(gc, sgb, g, 0, 0);
            }
            g.setColor(c);
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
}
