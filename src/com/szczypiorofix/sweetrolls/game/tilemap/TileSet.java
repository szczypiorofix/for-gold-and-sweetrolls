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

    public TileSet(int firstGrid, String source, String imageSource, int tileWidth, int tileHeight, int tileCount, int columns, int sourceWidth, int sourceHeight, SpriteSheet image) {
        this.firstGrid = firstGrid;
        this.source = source;
        this.imageSource = imageSource;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.tileCount = tileCount;
        this.columns = columns;
        this.sourceWidth = sourceWidth;
        this.sourceHeight = sourceHeight;
        this.image = image;
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
        return image.getSprite((id  - firstGrid) % columns, (id - firstGrid) / columns);
    }

}
