package com.szczypiorofix.sweetrolls.game.tilemap;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class Level {

    private TileMap tileMap;

    public Level() {
    }

    public void load(String name) {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            TileMapXMLHandler handler = new TileMapXMLHandler();
            saxParser.parse(new File("src/res/map/"+name), handler);

            tileMap = handler.getTileMap();

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    public TileMap getTileMap() {
        return tileMap;
    }

}
