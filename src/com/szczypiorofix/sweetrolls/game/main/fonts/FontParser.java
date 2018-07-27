package com.szczypiorofix.sweetrolls.game.main.fonts;

import com.szczypiorofix.sweetrolls.game.main.MainClass;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class FontParser {

    private static FontParser instance;
    private static BitMapFont bitMapFont;
    private static HashSet<String> fonts;

    private FontParser(String fontName, String xmlFontName, String pngFontName) {
        bitMapFont = new BitMapFont(fontName);
        bitMapFont.setFontImage(pngFontName);
        System.out.println("Creating font "+fontName +" ...");
        try {
            File inputFile = new File(MainClass.RES + "fonts/" + xmlFontName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList fontList = doc.getElementsByTagName("font");
            for (int font = 0; font < fontList.getLength(); font++) {
                Node fontNode = fontList.item(font);
                if (fontNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element fontElement = (Element) fontNode;
                    bitMapFont.setFontWidth(Integer.parseInt(fontElement.getAttribute("width")));
                    bitMapFont.setFontHeight(Integer.parseInt(fontElement.getAttribute("height")));
                    bitMapFont.setFontSpace(Integer.parseInt(fontElement.getAttribute("space")));
                    NodeList itemList = fontElement.getElementsByTagName("item");
                    for (int item = 0; item < itemList.getLength(); item++) {
                        Node itemNode = itemList.item(item);
                        if (itemNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element itemElement = (Element) itemNode;
                            bitMapFont.addChar(new FontChar(
                                    itemElement.getAttribute("ascii").equalsIgnoreCase("") ? -1 : Integer.parseInt(itemElement.getAttribute("ascii")),
                                    itemElement.getAttribute("ucode").equalsIgnoreCase("") ? -1 : Integer.parseInt(itemElement.getAttribute("ucode")),
                                    itemElement.getAttribute("raw").length() > 0 ? itemElement.getAttribute("raw").charAt(0) : ' ',
                                    itemElement.getAttribute("bottom").equalsIgnoreCase("") ? 0 : Integer.parseInt(itemElement.getAttribute("bottom")),
                                    itemElement.getAttribute("top").equalsIgnoreCase("") ? 0 : Integer.parseInt(itemElement.getAttribute("top")),
                                    itemElement.getAttribute("x").equalsIgnoreCase("") ? 0 : Integer.parseInt(itemElement.getAttribute("x")),
                                    itemElement.getAttribute("y").equalsIgnoreCase("") ? 0 : Integer.parseInt(itemElement.getAttribute("y")),
                                    itemElement.getAttribute("width").equalsIgnoreCase("") ? 0 : Integer.parseInt(itemElement.getAttribute("width")),
                                    itemElement.getAttribute("height").equalsIgnoreCase("") ? 0 : Integer.parseInt(itemElement.getAttribute("height")),
                                    itemElement.getAttribute("leading").equalsIgnoreCase("") ? 0 : Integer.parseInt(itemElement.getAttribute("leading")),
                                    itemElement.getAttribute("trailing").equalsIgnoreCase("") ? 0 : Integer.parseInt(itemElement.getAttribute("trailing"))
                            ));
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static BitMapFont getFont(String fontName, String xmlFontName, String pngFontName) {
        if (fonts == null) {
            fonts = new HashSet<>();
        }
        if (!fonts.contains(fontName)) {
            fonts.add(fontName);
            instance = new FontParser(fontName, xmlFontName, pngFontName);
        }
        return bitMapFont;
    }

}
