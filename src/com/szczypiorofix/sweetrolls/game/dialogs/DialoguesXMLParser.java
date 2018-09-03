/*
 * Developed by szczypiorofix on 29.08.18 10:49.
 * Copyright (c) 2018. All rights reserved.
 *
 */

package com.szczypiorofix.sweetrolls.game.dialogs;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DialoguesXMLParser {

    private DialoguesXMLParser() {}

    public static Dialogue parseDialogueXML(String filename, boolean editorContext) {
        Dialogue dialogue = new Dialogue();
        InputStream inputFile = null;
        try {

            if (!editorContext)
                inputFile = DialoguesXMLParser.class.getResourceAsStream("/dialogues/"+filename);
            else
                inputFile = new FileInputStream(filename);

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList dialogueList = doc.getElementsByTagName("dialogue");
            for (int dl = 0; dl < dialogueList.getLength(); dl++) {
                Node dialogueNode = dialogueList.item(dl);
                if (dialogueNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element dialogueElement = (Element) dialogueNode;

                    dialogue = new Dialogue(dialogueElement.getAttribute("name"));

                    NodeList dialoguePartList = dialogueElement.getChildNodes(); // dialogue_part
                    for (int dpl = 0; dpl < dialoguePartList.getLength(); dpl++) {
                        Node dialoguePartNode = dialoguePartList.item(dpl);
                        if (dialoguePartNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element dialoguePartElement = (Element) dialoguePartNode;

                            DialoguePart dialoguePart = new DialoguePart(
                                    dialoguePartElement.getAttribute("id"),
                                    dialoguePartElement.getAttribute("text")
                            );

                            NodeList dialogueButtonsList = dialoguePartElement.getChildNodes();
                            for (int dbl = 0; dbl < dialogueButtonsList.getLength(); dbl++) {
                                Node dialogueButtonNode = dialogueButtonsList.item(dbl);
                                if (dialogueButtonNode.getNodeType() == Node.ELEMENT_NODE) {
                                    Element dialogueButtonElement = (Element) dialogueButtonNode;

                                    DialoguePartButton dialoguePartButton = new DialoguePartButton(
                                            dialogueButtonElement.getAttribute("id").equalsIgnoreCase("")
                                                    ? "-1"
                                                    : dialogueButtonElement.getAttribute("id"),
                                            dialogueButtonElement.getAttribute("response"),
                                            dialogueButtonElement.getAttribute("endbutton"),
                                            dialogueButtonElement.getAttribute("nextId"),
                                            dialogueButtonElement.getAttribute("random").equalsIgnoreCase("")
                                                    ? "false"
                                                    : dialogueButtonElement.getAttribute("random"),
                                            dialogueButtonElement.getAttribute("rangeFrom").equalsIgnoreCase("")
                                                    ? "-1"
                                                    : dialogueButtonElement.getAttribute("rangeFrom"),
                                            dialogueButtonElement.getAttribute("rangeTo").equalsIgnoreCase("")
                                                    ? "-1"
                                                    : dialogueButtonElement.getAttribute("rangeTo"),
                                            dialogueButtonElement.getAttribute("requiredItem"),
                                            dialogueButtonElement.getAttribute("requiredAmount").equalsIgnoreCase("")
                                                    ? "0"
                                                    : dialogueButtonElement.getAttribute("requiredAmount"),
                                            dialogueButtonElement.getAttribute("failNextId").equalsIgnoreCase("")
                                                    ? "-1"
                                                    : dialogueButtonElement.getAttribute("failNextId"),
                                            dialogueButtonElement.getAttribute("locked").equalsIgnoreCase("")
                                                    ? "false"
                                                    : dialogueButtonElement.getAttribute("locked"),
                                            dialogueButtonElement.getAttribute("unlockId").equalsIgnoreCase("")
                                                    ? "-1"
                                                    : dialogueButtonElement.getAttribute("unlockId")
                                    );
                                    if (!editorContext) dialoguePartButton.setFontForGame();
                                    dialoguePart.addDialogueButton(dialoguePartButton);
                                }

                            }
                            dialogue.addDialoguePart(dialoguePart);
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputFile != null) {
                try {
                    inputFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return dialogue;
    }

}
