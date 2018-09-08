/*
 * Developed by szczypiorofix on 09.09.18 00:04.
 * Copyright (c) 2018. All rights reserved.
 *
 */

package com.szczypiorofix.sweetrolls.game.tilemap;


import org.apache.commons.codec.binary.Base64;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.zip.GZIPInputStream;

public class TileLayer implements Serializable {

    private String name;
    private int width;
    private int height;
    private boolean locked = false;
    private boolean visible = true;
    private TileObject[][] tileObjects;

    public TileLayer(String name, int width, int height) {
        this.name = name;
        this.width = width;
        this.height = height;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TileObject[][] getData() {
        return tileObjects;
    }

    public TileObject getTile(int x, int y) {
        return tileObjects[x][y];
    }

    public void setData(TileObject[][] tileObjects) {
        this.tileObjects = tileObjects;
    }


    /**
     * Read data from TiledMap Editor layer.
     * Decode it and the decompress it. Then twu-dimensional array of TiledObject is created.
     * This method use Apache Common Coded library.
     * url: https://commons.apache.org/proper/commons-codec/
     * ######### THIS METHOD ONLY SUPPORTS BASE64 GZIP COMPRESSED DATA !!!
     * @param compressedData (String) data from tilem map file.
     */
    public void readEncodedAndGZipCompressedData(String compressedData) {
        byte[] byteArray = Base64.decodeBase64(compressedData.getBytes(StandardCharsets.UTF_8));
        GZIPInputStream is = null;
        try {
            is = new GZIPInputStream(new ByteArrayInputStream(byteArray));
        } catch (IOException e) {
            e.printStackTrace();
        }
        tileObjects = new TileObject[width][height];
        try {
            for(int y = 0; y < height; ++y) {
                for(int x = 0; x < width; ++x) {
                    int tileId = 0;
                    tileId = tileId | is.read();
                    tileId |= is.read() << 8;
                    tileId |= is.read() << 16;
                    tileId |= is.read() << 24;
                    tileObjects[x][y] = new TileObject(tileId);
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Read data from TiledMap Editor layer.
     * Only CSV data are supported in this method.
     * @param dataCSV (String) data from tilem map file.
     */
    public void readCSVDataFromString(String dataCSV) {
        String[] data = dataCSV.replace("\n", "").replace("\r", "").trim().split(",");
        int[] dataArray= Arrays.stream(data).mapToInt(Integer::parseInt).toArray();
        this.tileObjects = new TileObject[width][height];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                this.tileObjects[j][i] = new TileObject(dataArray[i * width + j]);
            }
        }
    }

}
