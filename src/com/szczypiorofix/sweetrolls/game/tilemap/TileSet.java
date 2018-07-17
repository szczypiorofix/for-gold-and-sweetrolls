package com.szczypiorofix.sweetrolls.game.tilemap;


import com.szczypiorofix.sweetrolls.game.main.MainClass;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class TileSet {

    private int firstGrid;
    private String source;
    private String imageSource;
    private int tileWidth;
    private int tileHeight;
    private int tileCount;
    private int columns;
    private int sourceWidth;
    private int sourceHeight;
    private SpriteSheet image;

    public TileSet(int firstGrid, String source) {
        this.firstGrid = firstGrid;
        this.source = source;

        try {
            File inputFile = new File(source);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList tilesetList = doc.getElementsByTagName("tileset");

            for (int temp = 0; temp < tilesetList.getLength(); temp++) {
                Node tilesetNode = tilesetList.item(temp);
                if (tilesetNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element tilesetElement = (Element) tilesetNode;
                    tileWidth = Integer.parseInt(tilesetElement.getAttribute("tilewidth"));
                    tileHeight = Integer.parseInt(tilesetElement.getAttribute("tileheight"));
                    tileCount = Integer.parseInt(tilesetElement.getAttribute("tilecount"));
                    columns = Integer.parseInt(tilesetElement.getAttribute("columns"));

                    NodeList imageList = doc.getElementsByTagName("image");
                    for (int i = 0; i < imageList.getLength(); i++) {
                        Node imageNode = imageList.item(i);
                        if (imageNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element imageElement = (Element) imageNode;
                            imageSource = imageElement.getAttribute("source");
                            sourceWidth = Integer.parseInt(imageElement.getAttribute("width"));
                            sourceHeight = Integer.parseInt(imageElement.getAttribute("height"));
                        }
                    }
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        try {
            image = new SpriteSheet(MainClass.RES +"map/" +imageSource, tileWidth, tileHeight);
        } catch (SlickException e) {
            e.printStackTrace();
        }
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

    public int getFirstGrid() {
        return firstGrid;
    }

    public void setFirstGrid(int firstGrid) {
        this.firstGrid = firstGrid;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Image getImageSprite(int id) {
        return image.getSprite((int) ((id  - firstGrid) % columns), (int) ((id - firstGrid) / columns) );
    }

}
