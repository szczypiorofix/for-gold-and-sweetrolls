/*
 * Developed by szczypiorofix on 04.09.18 11:55.
 * Copyright (c) 2018. All rights reserved.
 *
 */

package com.szczypiorofix.sweetrolls.game.main.core;

import com.szczypiorofix.sweetrolls.game.dialogs.DialoguesXMLParser;
import com.szczypiorofix.sweetrolls.game.enums.CharacterRace;
import com.szczypiorofix.sweetrolls.game.enums.CharacterSex;
import com.szczypiorofix.sweetrolls.game.main.MainClass;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;


public class NameGenerator {


    private String[] humanFemaleNames, humanMaleNames, humanSurnames;
    private String[] elfFemaleNames, elfMaleNames, elfSurnames;
    private String[] dwarfFemaleNames, dwarfMaleNames, dwardSurnames;

    public NameGenerator() {
        parseXML();
    }

    public String getRandomName(CharacterSex sex, CharacterRace race) {
        String name;
        switch (race) {
            case ELF: {
                if (sex == CharacterSex.MALE)
                    name = elfMaleNames[MainClass.RANDOM.nextInt(elfMaleNames.length)]+" "+elfSurnames[MainClass.RANDOM.nextInt(elfSurnames.length)];
                else
                    name = elfFemaleNames[MainClass.RANDOM.nextInt(elfFemaleNames.length)]+" "+elfSurnames[MainClass.RANDOM.nextInt(elfSurnames.length)];
                break;
            }
            case DWARF: {
                if (sex == CharacterSex.MALE)
                    name = dwarfMaleNames[MainClass.RANDOM.nextInt(dwarfMaleNames.length)]+" "+dwardSurnames[MainClass.RANDOM.nextInt(dwardSurnames.length)];
                else
                    name = dwarfFemaleNames[MainClass.RANDOM.nextInt(dwarfFemaleNames.length)]+" "+dwardSurnames[MainClass.RANDOM.nextInt(dwardSurnames.length)];
                break;
            }
            default: { // HUMAN
                if (sex == CharacterSex.MALE)
                    name = humanMaleNames[MainClass.RANDOM.nextInt(humanMaleNames.length)]+" "+humanSurnames[MainClass.RANDOM.nextInt(humanSurnames.length)];
                else
                    name = humanFemaleNames[MainClass.RANDOM.nextInt(humanFemaleNames.length)]+" "+humanSurnames[MainClass.RANDOM.nextInt(humanSurnames.length)];
                break;
            }
        }

        return name;
    }

    private void parseXML() {
        InputStream inputFile = null;
        try {
            inputFile = DialoguesXMLParser.class.getResourceAsStream("/names/names.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList namesAllList = doc.getElementsByTagName("names_all");
            Node namesAllNode = namesAllList.item(0);
            Element namesAllElement = (Element) namesAllNode;

            NodeList namesList = namesAllElement.getChildNodes();
            for (int i = 0; i < namesList.getLength(); i++) {
                Node namesNode = namesList.item(i);
                if (namesNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element namesElement = (Element) namesNode;

                    // MALE NAMES
                    NodeList maleNamesList = namesElement.getElementsByTagName("male_names");
                    for (int j = 0; j < maleNamesList.getLength(); j++) {
                        Node maleNamesNode = maleNamesList.item(j);
                        if (maleNamesNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element maleNamesElement = (Element) maleNamesNode;
                            addToArray(namesElement.getAttribute("race"), maleNamesElement, 1);
                        }
                    }

                    // FEMALE NAMES
                    NodeList femaleNamesList = namesElement.getElementsByTagName("female_names");
                    for (int j = 0; j < femaleNamesList.getLength(); j++) {
                        Node femaleNamesNode = femaleNamesList.item(j);
                        if (femaleNamesNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element femaleNamesElement = (Element) femaleNamesNode;
                            addToArray(namesElement.getAttribute("race"), femaleNamesElement, 2);
                        }
                    }

                    // SURNAMES
                    NodeList surnamesList = namesElement.getElementsByTagName("surnames");
                    for (int j = 0; j < surnamesList.getLength(); j++) {
                        Node surnameNode = surnamesList.item(j);
                        if (surnameNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element surnamesElement = (Element) surnameNode;
                            addToArray(namesElement.getAttribute("race"), surnamesElement, 3);
                        }
                    }
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            if (inputFile != null) {
                try {
                    inputFile.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }

    }

    private void addToArray(String race, Element e, int type) {
        switch (race) {
            case "human": {
                switch (type) {
                    case 1: {
                        humanMaleNames = e
                                .getFirstChild()
                                .getNodeValue()
                                .trim()
                                .split(",");
                        break;
                    }
                    case 2: {
                        humanFemaleNames = e
                                .getFirstChild()
                                .getNodeValue()
                                .trim()
                                .split(",");
                        break;
                    }
                    default: {
                        humanSurnames = e
                                .getFirstChild()
                                .getNodeValue()
                                .trim()
                                .split(",");
                        break;
                    }
                }
                break;
            }
            case "dwarf": {
                switch (type) {
                    case 1: {
                        dwarfMaleNames = e
                                .getFirstChild()
                                .getNodeValue()
                                .trim()
                                .split(",");
                        break;
                    }
                    case 2: {
                        dwarfFemaleNames = e
                                .getFirstChild()
                                .getNodeValue()
                                .trim()
                                .split(",");
                        break;
                    }
                    default: {
                        dwardSurnames = e
                                .getFirstChild()
                                .getNodeValue()
                                .trim()
                                .split(",");
                        break;
                    }
                }
                break;
            }
            case "elf": {
                switch (type) {
                    case 1: {
                        elfMaleNames = e
                                .getFirstChild()
                                .getNodeValue()
                                .trim()
                                .split(",");
                        break;
                    }
                    case 2: {
                        elfFemaleNames = e
                                .getFirstChild()
                                .getNodeValue()
                                .trim()
                                .split(",");
                        break;
                    }
                    default: {
                        elfSurnames = e
                                .getFirstChild()
                                .getNodeValue()
                                .trim()
                                .split(",");
                        break;
                    }
                }
                break;
            }
            default: {
                //System.out.println("Podano niewłaściwą rasę !!!");
                //System.exit(0);
            }
        }
    }
}
