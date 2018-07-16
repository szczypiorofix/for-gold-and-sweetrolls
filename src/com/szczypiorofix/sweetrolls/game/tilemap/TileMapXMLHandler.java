package com.szczypiorofix.sweetrolls.game.tilemap;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class TileMapXMLHandler extends DefaultHandler{


    private TileMap tileMap;
    private Layer layer;
    private String CSV;
    private boolean readCSV;

    TileMap getTileMap() {
        return tileMap;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("map")) {

            tileMap = new TileMap(
                    Integer.parseInt(attributes.getValue("width")),
                    Integer.parseInt(attributes.getValue("height")),
                    Integer.parseInt(attributes.getValue("tilewidth")),
                    Integer.parseInt(attributes.getValue("tileheight"))
            );

        } else if (qName.equalsIgnoreCase("tileset")) {

            tileMap.addTileSet(new TileSet(
                    Integer.parseInt(attributes.getValue("firstgid")),
                    attributes.getValue("source")
            ));

        } else if (qName.equalsIgnoreCase("layer")) {

            layer = new Layer(
                    attributes.getValue("name"),
                    Integer.parseInt(attributes.getValue("width")),
                    Integer.parseInt(attributes.getValue("height"))
            );
            tileMap.addLayer(layer);

        } else if (qName.equalsIgnoreCase("data")) {
            CSV = null;
            readCSV = true;
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        if (CSV == null && readCSV) {
            CSV = new String(ch, start, length);
        } else {
            CSV += new String(ch, start, length);
        }
        if (readCSV) System.out.println(CSV);
        readCSV = false;
    }

}
