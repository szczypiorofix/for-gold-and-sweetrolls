package com.szczypiorofix.sweetrolls.game.tilemap;


import com.szczypiorofix.sweetrolls.game.main.MainClass;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;


public class TileSet {

    private int firstGid;
    private String name;
    private String sourceFile;
    private String imageSource;
    private int tileWidth;
    private int tileHeight;
    private int tileCount;
    private int columns;
    private int sourceWidth;
    private int sourceHeight;
    private SpriteSheet image;

    public TileSet(int firstGid, String sourceFile) {
        this.firstGid = firstGid;
        this.sourceFile = sourceFile;
        try {
            File inputFile = new File(MainClass.RES + "map/" + sourceFile);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList tilesetList = doc.getElementsByTagName("tileset");

            for (int tileset = 0; tileset < tilesetList.getLength(); tileset++) {
                Node tilesetNode = tilesetList.item(tileset);

                if (tilesetNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element tilesetElement = (Element) tilesetNode;

                    name = tilesetElement.getAttribute("name");
                    tileWidth = Integer.parseInt(tilesetElement.getAttribute("tilewidth"));
                    tileHeight = Integer.parseInt(tilesetElement.getAttribute("tileheight"));
                    tileCount = Integer.parseInt(tilesetElement.getAttribute("tilecount"));
                    columns = Integer.parseInt(tilesetElement.getAttribute("columns"));


                    NodeList imageList = tilesetElement.getElementsByTagName("image");
                    Node imageNode = imageList.item(0);
                    if (imageNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element imageElement = (Element) imageNode;
                        imageSource = imageElement.getAttribute("source");
                        sourceWidth = Integer.parseInt(imageElement.getAttribute("width"));
                        sourceHeight = Integer.parseInt(imageElement.getAttribute("height"));
                        image = null;

                        try {
                            image = new SpriteSheet(MainClass.RES +"map/" +imageSource, tileWidth, tileHeight);
                        } catch (SlickException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }


        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }

    public TileSet(int firstGid, String name, String imageSource, int tileWidth, int tileHeight, int tileCount, int columns, int sourceWidth, int sourceHeight, SpriteSheet image) {
        this.firstGid = firstGid;
        this.name = name;
        this.imageSource = imageSource;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.tileCount = tileCount;
        this.columns = columns;
        this.sourceWidth = sourceWidth;
        this.sourceHeight = sourceHeight;
        this.image = image;
        this.sourceFile = "";
    }

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public void setTileWidth(int tileWidth) {
        this.tileWidth = tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public void setTileHeight(int tileHeight) {
        this.tileHeight = tileHeight;
    }

    public int getTileCount() {
        return tileCount;
    }

    public void setTileCount(int tileCount) {
        this.tileCount = tileCount;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getSourceWidth() {
        return sourceWidth;
    }

    public void setSourceWidth(int sourceWidth) {
        this.sourceWidth = sourceWidth;
    }

    public int getSourceHeight() {
        return sourceHeight;
    }

    public void setSourceHeight(int sourceHeight) {
        this.sourceHeight = sourceHeight;
    }

    public int getFirstGid() {
        return firstGid;
    }

    public void setFirstGid(int firstGid) {
        this.firstGid = firstGid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSourceFile() {
        return sourceFile;
    }

    public Image getImageSprite(int id) {
        return image.getSprite(id % columns, id / columns);
    }

}
