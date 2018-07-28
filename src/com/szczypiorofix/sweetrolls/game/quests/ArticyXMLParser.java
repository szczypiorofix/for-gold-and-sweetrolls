package com.szczypiorofix.sweetrolls.game.quests;

import com.szczypiorofix.sweetrolls.game.main.MainClass;
import com.szczypiorofix.sweetrolls.game.quests.content.A_Entity;
import com.szczypiorofix.sweetrolls.game.quests.content.A_FlowFragment;
import com.szczypiorofix.sweetrolls.game.quests.content.A_Pin;
import com.szczypiorofix.sweetrolls.game.quests.content.Semantic;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ArticyXMLParser {

    private List<A_Entity> entitiesList;
    private List<A_FlowFragment> flowFragments;

    public ArticyXMLParser(String name) {

        entitiesList = new ArrayList<>();
        flowFragments = new ArrayList<>();

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
                                                    a_entity.setDisplayName(lsElement.getTextContent().trim());
                                                }
                                            }
                                        }
                                    }
                                    entitiesList.add(a_entity);
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
                                                    a_flowFragment.setDisplayName(lsElement.getTextContent().trim());
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
                                                    a_flowFragment.setText(lsElement.getTextContent().trim());
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

                                           a_flowFragment.setPinCount(Integer.parseInt(pinsElement.getAttribute("Count")));

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

                                    flowFragments.add(a_flowFragment);
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
        for (int i = 0; i < entitiesList.size(); i++) {
            System.out.println(entitiesList.get(i).getDisplayName() + ", "+entitiesList.get(i).getId());
        }
        System.out.println();

        System.out.println("FlowFragments: ");
        for (int i = 0; i < flowFragments.size(); i++) {
            System.out.println(flowFragments.get(i).getDisplayName() + ", "+flowFragments.get(i).getId());
        }
        System.out.println();

    }

}
