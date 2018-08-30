/*
 * Developed by szczypiorofix on 29.08.18 10:49.
 * Copyright (c) 2018. All rights reserved.
 *
 */

package com.szczypiorofix.sweetrolls.game.dialogs;


import com.szczypiorofix.sweetrolls.game.main.MainClass;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class DialoguesXMLParser {


    public static Dialogue parseDialogueXML(String filename) {
        Dialogue dialogue = new Dialogue();
        try {

            File inputFile = new File(MainClass.RES + "dialogues/" + filename);
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
                                            dialogueButtonElement.getAttribute("order"),
                                            dialogueButtonElement.getAttribute("response"),
                                            dialogueButtonElement.getAttribute("endbutton"),
                                            dialogueButtonElement.getAttribute("nextId")
                                    );
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
        }
        return dialogue;
    }

}