package com.szczypiorofix.sweetrolls.game.main.fonts;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;


public class FontParser {

    private static HashMap<String, BitMapFont> fonts;


    private static BitMapFont getBitMapFont(String fontName, String xmlFontName, String pngFontName) {
        BitMapFont bitMapFont = new BitMapFont(fontName);
        bitMapFont.setFontImage(pngFontName);

        System.out.println("Rejestracja nowego fontu: "+fontName+", "+xmlFontName);

        try {
            InputStream in = FontParser.class.getResourceAsStream("/fonts/"+xmlFontName);
            //File inputFile = new File(MainClass.RES + "fonts/" + xmlFontName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(in);
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
        return bitMapFont;
    }

    public static BitMapFont getFont(String fontName, String xmlFontName, String pngFontName) {
        BitMapFont font;
        if (fonts == null) {
            fonts = new HashMap<>();
        }
        if (!fonts.containsKey(fontName)) {
            font = getBitMapFont(fontName, xmlFontName,pngFontName);
            fonts.put(fontName, font);
        } else font = fonts.get(fontName);
        return font;
    }

}
