package com.szczypiorofix.sweetrolls.game.quests;

import com.szczypiorofix.sweetrolls.game.main.MainClass;
import com.szczypiorofix.sweetrolls.game.quests.content.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.*;

public class ArticyXMLParser {

    private HashMap<String, A_Entity> entitiesList;
    private HashMap<String, A_FlowFragment> flowFragments;
    private HashMap<String, A_Connection> connections;
    private HashMap<String, A_Dialogue> dialogues;
    private HashMap<String, A_DialogueFragment> dialoguesFragmens;

    private String startId;
    private String stopId;
    private String currentId;
    private State state;

    private enum State {
        FLOW_FRAGMENT,
        CONNECTION,
        DIALOGUE,
        DIALOGUE_FRAGMENT,
        PIN
    }

    public ArticyXMLParser(String name) {

        startId = "-1";

        entitiesList = new HashMap<>();
        flowFragments = new HashMap<>();
        connections = new HashMap<>();
        dialogues = new HashMap<>();
        dialoguesFragmens = new HashMap<>();

        try {
            File inputFile = new File(MainClass.RES + "quests/" + name);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList exportList = doc.getElementsByTagName("Export");
            for (int export = 0; export < exportList.getLength(); export++) {
                Node exportNode = exportList.item(export);
                if (exportNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element exportElement = (Element) exportNode;

                    NodeList contentList = exportElement.getElementsByTagName("Content");
                    for (int content = 0; content < contentList.getLength(); content++) {
                        Node contentNode = exportList.item(content);
                        if (contentNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element contentElement = (Element) contentNode;

                            // ################ ENTITY ################
                            NodeList entityList = contentElement.getElementsByTagName("Entity");
                            for (int entity = 0; entity < entityList.getLength(); entity++) {
                                Node entityNode = entityList.item(entity);
                                if (entityNode.getNodeType() == Node.ELEMENT_NODE) {
                                    Element entityElement = (Element) entityNode;

                                    A_Entity a_entity = new A_Entity(entityElement.getAttribute("Id"));

                                    NodeList displayNameList = entityElement.getElementsByTagName("DisplayName");
                                    for (int dn = 0; dn < displayNameList.getLength(); dn++) {
                                        Node dnNode = displayNameList.item(dn);
                                        if (dnNode.getNodeType() == Node.ELEMENT_NODE) {
                                            Element dnElement = (Element) dnNode;
                                            NodeList localizedStringNameList = dnElement.getElementsByTagName("LocalizedString");
                                            for (int ls = 0; ls < localizedStringNameList.getLength(); ls++) {
                                                Node lsNode = displayNameList.item(ls);
                                                if (lsNode.getNodeType() == Node.ELEMENT_NODE) {
                                                    Element lsElement = (Element) lsNode;
                                                    a_entity.displayName = lsElement.getTextContent().trim();
                                                }
                                            }
                                        }
                                    }
                                    entitiesList.put(a_entity.id, a_entity);
                                }
                            }


                            // ################ FLOW FRAGMENT ################
                            NodeList flowFragmentList = contentElement.getElementsByTagName("FlowFragment");
                            for (int ff = 0; ff < flowFragmentList.getLength(); ff++) {
                                Node ffNode = flowFragmentList.item(ff);
                                if (ffNode.getNodeType() == Node.ELEMENT_NODE) {
                                    Element ffElement = (Element) ffNode;

                                    A_FlowFragment a_flowFragment = new A_FlowFragment(ffElement.getAttribute("Id"));

                                    // DisplayName
                                    NodeList displayNameList = ffElement.getElementsByTagName("DisplayName");
                                    for (int dn = 0; dn < displayNameList.getLength(); dn++) {
                                        Node dnNode = displayNameList.item(dn);
                                        if (dnNode.getNodeType() == Node.ELEMENT_NODE) {
                                            Element dnElement = (Element) dnNode;
                                            NodeList localizedStringNameList = dnElement.getElementsByTagName("LocalizedString");
                                            for (int ls = 0; ls < localizedStringNameList.getLength(); ls++) {
                                                Node lsNode = localizedStringNameList.item(ls);
                                                if (lsNode.getNodeType() == Node.ELEMENT_NODE) {
                                                    Element lsElement = (Element) lsNode;
                                                    a_flowFragment.displayName = lsElement.getTextContent().trim();

                                                    if (a_flowFragment.displayName.equalsIgnoreCase("START")) {
                                                        startId = ffElement.getAttribute("Id");
                                                    }

                                                    if (a_flowFragment.displayName.equalsIgnoreCase("STOP")) {
                                                        stopId = ffElement.getAttribute("Id");
                                                    }
                                                }
                                            }
                                        }
                                    }

                                    // Text
                                    NodeList textNameList = ffElement.getElementsByTagName("Text");
                                    for (int tn = 0; tn < textNameList.getLength(); tn++) {
                                        Node tNode = textNameList.item(tn);
                                        if (tNode.getNodeType() == Node.ELEMENT_NODE) {
                                            Element tElement = (Element) tNode;
                                            NodeList localizedStringNameList = tElement.getElementsByTagName("LocalizedString");
                                            for (int ls = 0; ls < localizedStringNameList.getLength(); ls++) {
                                                Node lsNode = localizedStringNameList.item(ls);
                                                if (lsNode.getNodeType() == Node.ELEMENT_NODE) {
                                                    Element lsElement = (Element) lsNode;
                                                    a_flowFragment.text = lsElement.getTextContent().trim();
                                                }
                                            }
                                        }
                                    }

                                    // Pins
                                    NodeList pinsList = ffElement.getElementsByTagName("Pins");
                                    for (int pn = 0; pn < pinsList.getLength(); pn++) {
                                        Node pinsNode = pinsList.item(pn);
                                        if (pinsNode.getNodeType() == Node.ELEMENT_NODE) {
                                            Element pinsElement = (Element) pinsNode;

                                           a_flowFragment.pinCount = Integer.parseInt(pinsElement.getAttribute("Count"));

                                           NodeList pinList = pinsElement.getElementsByTagName("Pin");
                                           for (int p = 0; p < pinList.getLength(); p++) {
                                               Node pNode = pinList.item(p);
                                               if (pNode.getNodeType() == Node.ELEMENT_NODE) {
                                                   Element pinElement = (Element) pNode;

                                                   Semantic s;
                                                   if (pinElement.getAttribute("Semantic").equalsIgnoreCase("Input"))
                                                       s = Semantic.INPUT;
                                                   else s = Semantic.OUTPUT;

                                                   A_Pin a_pin = new A_Pin(
                                                           pinElement.getAttribute("Id"),
                                                           Integer.parseInt(pinElement.getAttribute("Index")),
                                                           s,
                                                           pinElement.getAttribute("Expression")
                                                   );
                                                   a_flowFragment.addPin(a_pin);
                                               }
                                           }
                                        }
                                    }

                                    flowFragments.put(a_flowFragment.id, a_flowFragment);
                                }
                            }


                            // ################ CONNECTION ################
                            NodeList connectionsList = contentElement.getElementsByTagName("Connection");
                            for (int cn = 0; cn < connectionsList.getLength(); cn++) {
                                Node connectionsNode = connectionsList.item(cn);
                                if (connectionsNode.getNodeType() == Node.ELEMENT_NODE) {
                                    Element connectionsElement = (Element) connectionsNode;

                                    A_Connection a_connection = new A_Connection(connectionsElement.getAttribute("Id"));

                                    NodeList sourceList = connectionsElement.getElementsByTagName("Source");
                                    for (int sl = 0; sl < sourceList.getLength(); sl++) {
                                        Node sourceNode = sourceList.item(sl);
                                        if (sourceNode.getNodeType() == Node.ELEMENT_NODE) {
                                            Element sourceElement = (Element) sourceNode;
                                            a_connection.sourceIdRef = sourceElement.getAttribute("IdRef");
                                            a_connection.sourcePinRef = sourceElement.getAttribute("PinRef");
                                        }
                                    }

                                    NodeList targetList = connectionsElement.getElementsByTagName("Target");
                                    for (int tl = 0; tl < targetList.getLength(); tl++) {
                                        Node targetNode = targetList.item(tl);
                                        if (targetNode.getNodeType() == Node.ELEMENT_NODE) {
                                            Element targetElement = (Element) targetNode;
                                            a_connection.targetIdRef = targetElement.getAttribute("IdRef");
                                            a_connection.targetPinRef = targetElement.getAttribute("PinRef");
                                        }
                                    }
                                    connections.put(a_connection.id, a_connection);
                                }
                            }



                            // ################ DIALOGUE ################
                            NodeList dialogueList = contentElement.getElementsByTagName("Dialogue");
                            for (int dl = 0; dl < dialogueList.getLength(); dl++) {
                                Node connectionsNode = dialogueList.item(dl);
                                if (connectionsNode.getNodeType() == Node.ELEMENT_NODE) {
                                    Element connectionsElement = (Element) connectionsNode;

                                    A_Dialogue a_dialogue = new A_Dialogue(connectionsElement.getAttribute("Id"));

                                    // DisplayName
                                    NodeList displayNameList = connectionsElement.getElementsByTagName("DisplayName");
                                    for (int dn = 0; dn < displayNameList.getLength(); dn++) {
                                        Node dnNode = displayNameList.item(dn);
                                        if (dnNode.getNodeType() == Node.ELEMENT_NODE) {
                                            Element dnElement = (Element) dnNode;
                                            NodeList localizedStringNameList = dnElement.getElementsByTagName("LocalizedString");
                                            for (int ls = 0; ls < localizedStringNameList.getLength(); ls++) {
                                                Node lsNode = localizedStringNameList.item(ls);
                                                if (lsNode.getNodeType() == Node.ELEMENT_NODE) {
                                                    Element lsElement = (Element) lsNode;
                                                    a_dialogue.displayName = lsElement.getTextContent().trim();
                                                }
                                            }
                                        }
                                    }


                                    dialogues.put(a_dialogue.id, a_dialogue);
                                }
                            }


                            // ################ DIALOGUE FRAGMENTS ################
                            NodeList dialogueFragmentsList = contentElement.getElementsByTagName("DialogueFragment");
                            for (int df = 0; df < dialogueFragmentsList.getLength(); df++) {
                                Node dialogueFragmentsNode = dialogueFragmentsList.item(df);
                                if (dialogueFragmentsNode.getNodeType() == Node.ELEMENT_NODE) {
                                    Element dialogueFragmentsNodeElement = (Element) dialogueFragmentsNode;

                                    A_DialogueFragment a_dialogueFragment = new A_DialogueFragment(dialogueFragmentsNodeElement.getAttribute("Id"));

                                    // Text
                                    NodeList displayNameList = dialogueFragmentsNodeElement.getElementsByTagName("Text");
                                    for (int dn = 0; dn < displayNameList.getLength(); dn++) {
                                        Node dnNode = displayNameList.item(dn);
                                        if (dnNode.getNodeType() == Node.ELEMENT_NODE) {
                                            Element dnElement = (Element) dnNode;
                                            NodeList localizedStringNameList = dnElement.getElementsByTagName("LocalizedString");
                                            for (int ls = 0; ls < localizedStringNameList.getLength(); ls++) {
                                                Node lsNode = localizedStringNameList.item(ls);
                                                if (lsNode.getNodeType() == Node.ELEMENT_NODE) {
                                                    Element lsElement = (Element) lsNode;
                                                    a_dialogueFragment.text = lsElement.getTextContent().trim();
                                                }
                                            }
                                        }
                                    }


                                    // Menu text
                                    NodeList menuTextList = dialogueFragmentsNodeElement.getElementsByTagName("MenuText");
                                    for (int dn = 0; dn < menuTextList.getLength(); dn++) {
                                        Node mtNode = menuTextList.item(dn);
                                        if (mtNode.getNodeType() == Node.ELEMENT_NODE) {
                                            Element mtElement = (Element) mtNode;
                                            NodeList localizedStringNameList = mtElement.getElementsByTagName("LocalizedString");
                                            for (int ls = 0; ls < localizedStringNameList.getLength(); ls++) {
                                                Node lsNode = localizedStringNameList.item(ls);
                                                if (lsNode.getNodeType() == Node.ELEMENT_NODE) {
                                                    Element lsElement = (Element) lsNode;
                                                    a_dialogueFragment.menuText = lsElement.getTextContent().trim();
                                                }
                                            }
                                        }
                                    }

                                    // Speaker IdRef
                                    NodeList speakerList = dialogueFragmentsNodeElement.getElementsByTagName("Speaker");
                                    for (int sl = 0; sl < speakerList.getLength(); sl++) {
                                        Node sNode = speakerList.item(sl);
                                        if (sNode.getNodeType() == Node.ELEMENT_NODE) {
                                            Element sElement = (Element) sNode;
                                            a_dialogueFragment.speakerIdRef = sElement.getAttribute("IdRef");
                                        }
                                    }

                                    // Pins
                                    NodeList pinsList = dialogueFragmentsNodeElement.getElementsByTagName("Pins");
                                    for (int pn = 0; pn < pinsList.getLength(); pn++) {
                                        Node pinsNode = pinsList.item(pn);
                                        if (pinsNode.getNodeType() == Node.ELEMENT_NODE) {
                                            Element pinsElement = (Element) pinsNode;

                                            a_dialogueFragment.pinCount = Integer.parseInt(pinsElement.getAttribute("Count"));

                                            NodeList pinList = pinsElement.getElementsByTagName("Pin");
                                            for (int p = 0; p < pinList.getLength(); p++) {
                                                Node pNode = pinList.item(p);
                                                if (pNode.getNodeType() == Node.ELEMENT_NODE) {
                                                    Element pinElement = (Element) pNode;

                                                    Semantic s;
                                                    if (pinElement.getAttribute("Semantic").equalsIgnoreCase("Input"))
                                                        s = Semantic.INPUT;
                                                    else s = Semantic.OUTPUT;

                                                    A_Pin a_pin = new A_Pin(
                                                            pinElement.getAttribute("Id"),
                                                            Integer.parseInt(pinElement.getAttribute("Index")),
                                                            s,
                                                            pinElement.getAttribute("Expression")
                                                    );
                                                    a_dialogueFragment.addPin(a_pin);
                                                }
                                            }
                                        }
                                    }

                                    dialoguesFragmens.put(a_dialogueFragment.id, a_dialogueFragment);
                                }
                            }

                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }



        System.out.println("Entities: ");
        entitiesList.forEach((key, value)
                -> System.out.println(key + " => " + value));
        System.out.println();


        System.out.println("Flow Fragments: ");
        flowFragments.forEach((key, value)
                -> System.out.println(key + " => " + value));
        System.out.println();


        System.out.println("Connections: ");
        connections.forEach((key, value)
                -> System.out.println(
                        key + " => sourceIdRef: " + value.sourceIdRef + " - sourcePinRef: " + value.sourcePinRef
                                + " => targetIdRef: " + value.targetIdRef + " - targetPinRef: " + value.targetPinRef
        ));
        System.out.println();


        System.out.println("Dialogues: ");
        dialogues.forEach((key, value)
                -> System.out.println(key + " => " + value.displayName));
        System.out.println();


        System.out.println("Dialogue Fragments: ");
        dialoguesFragmens.forEach((key, value)
                -> System.out.println(key + " => " + value.text +", menu text: "+value.menuText));
        System.out.println();


        System.out.println();
        System.out.println();
        System.out.println();

        System.out.println("Flow from "+startId +" to "+stopId);

        currentId = startId;
        boolean end = false;
        state = State.FLOW_FRAGMENT;

        Scanner in = new Scanner(System.in);

        String[] ids;

        do {

            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Piny: ");
            int c = 0;

            switch (state) {
                case FLOW_FRAGMENT: {
                    Set<Map.Entry<String, A_Pin>> employeeSalaryEntries = flowFragments.get(currentId).pins.entrySet();
                    Iterator<Map.Entry<String, A_Pin>> employeeSalaryIterator = employeeSalaryEntries.iterator();

                    ids = new String[flowFragments.get(currentId).pins.size()];

                    while (employeeSalaryIterator.hasNext()) {
                        Map.Entry<String, A_Pin> entry = employeeSalaryIterator.next();
                        System.out.println("[" +c+"] " + entry.getKey() + " => " + entry.getValue());
                        ids[c] = entry.getKey();
                        c++;
                    }

//                    for (int i = 0; i < ids.length; i++) {
//                        System.out.println(ids[i]);
//                    }

                    Integer i = in.nextInt();
                    if (i == -1 || currentId.equalsIgnoreCase(stopId)) end = true;

                    if (i < ids.length && i >= 0) {
                        currentId = ids[i];
                        state = State.CONNECTION;
                    }

                    break;
                }
                case DIALOGUE: {
                    break;
                }
                case CONNECTION: {

                    System.out.println("Current id: "+currentId);
                    String prevId = currentId;
//                    connections.forEach((key, value)
//                            -> {
//                        if (currentId.equalsIgnoreCase(value.sourcePinRef)) {
//                            currentId = value.targetIdRef;
//                        }
//                    });
                    System.out.println("Zmiana ID z "+prevId +" na " +currentId);
                    connections.forEach((key, value)
                            -> {
                        if (currentId.equalsIgnoreCase(value.targetPinRef)) {
                            currentId = value.targetIdRef;
                            state = State.FLOW_FRAGMENT;
                            return;
                        }
                    });

                    break;
                }
                case DIALOGUE_FRAGMENT: {

                }
                case PIN: {

                }
            }


        } while (!end);


    }

}
