package com.szczypiorofix.sweetrolls.game.tilemap;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class TileMapXMLHandler extends DefaultHandler{


    private TileMap tileMap;
    private Layer layer;

    public TileMap getTileMap() {
        return tileMap;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("tilemap")) {

            tileMap = new TileMap();

            String tileswide = attributes.getValue("tileswide");
            String tileshigh = attributes.getValue("tileshigh");
            String tilewidth = attributes.getValue("tilewidth");
            String tileheight = attributes.getValue("tileheight");

            System.out.println("LAYER OK: tileswide:"+tileswide +", tileshigh:"+tileshigh +", tilewidth:"+tilewidth+", tileheight:"+tileheight);

        } else if (qName.equalsIgnoreCase("layer")) {

            layer = new Layer(
                    Integer.parseInt(attributes.getValue("number")),
                    attributes.getValue("name")
            );
            tileMap.addLayer(layer);

        } else if (qName.equalsIgnoreCase("tile")) {

            Tile tile = new Tile(
                    Integer.parseInt(attributes.getValue("x")),
                    Integer.parseInt(attributes.getValue("y")),
                    Integer.parseInt(attributes.getValue("tile")),
                    Integer.parseInt(attributes.getValue("rot")),
                    Boolean.parseBoolean(attributes.getValue("flipX"))
            );

            layer.addTile(tile);

        }
    }

}
