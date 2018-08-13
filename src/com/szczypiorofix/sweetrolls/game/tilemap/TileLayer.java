package com.szczypiorofix.sweetrolls.game.tilemap;


import java.nio.ByteBuffer;
import java.util.Arrays;

public class TileLayer {

    private String name;
    private int width;
    private int height;
    private boolean locked = false;
    private boolean visible = true;
    private TileObject[][] tileObjects;
    /** The code used to decode Base64 encoding */
    private static byte[] baseCodes = new byte[256];

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

    private static byte[] intToByte(int value) {
        return ByteBuffer.allocate(4).putInt(value).array();
    }

    public static int getUInt32(byte[] src, int offset) {
        final int v0 = src[offset + 0] & 0xFF;
        final int v1 = src[offset + 1] & 0xFF;
        final int v2 = src[offset + 2] & 0xFF;
        final int v3 = src[offset + 3] & 0xFF;
        return ((v3 << 24) | (v2 << 16) | (v1 << 8) | v0);
    }

    private byte[] decodeBase64(char[] data) {
        int temp = data.length;
        for (int ix = 0; ix < data.length; ix++) {
            if ((data[ix] > 255) || baseCodes[data[ix]] < 0) {
                --temp;
            }
        }

        int len = (temp / 4) * 3;
        if ((temp % 4) == 3)
            len += 2;
        if ((temp % 4) == 2)
            len += 1;

        byte[] out = new byte[len];

        int shift = 0;
        int accum = 0;
        int index = 0;

        for (int ix = 0; ix < data.length; ix++) {
            int value = (data[ix] > 255) ? -1 : baseCodes[data[ix]];

            if (value >= 0) {
                accum <<= 6;
                shift += 6;
                accum |= value;
                if (shift >= 8) {
                    shift -= 8;
                    out[index++] = (byte) ((accum >> shift) & 0xff);
                }
            }
        }

        if (index != out.length) {
            throw new RuntimeException(
                    "Data length appears to be wrong (wrote " + index
                            + " should be " + out.length + ")");
        }

        return out;
    }

    public void setDataCSVFromString(String dataCSV) {
        //dataCSV = "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";

        String[] data = dataCSV.replace("\n", "").replace("\r", "").trim().split(",");

//        char[] enc = dataCSV.trim().toCharArray();
//        byte[] dec = decodeBase64(enc);
//
//        try {
//            GZIPInputStream is = new GZIPInputStream(new ByteArrayInputStream(dec));
//
//            for (int y = 0; y < height; y++) {
//                for (int x = 0; x < width; x++) {
//                    int tileId = 0;
//                    tileId |= is.read();
//                    tileId |= is.read() << 8;
//                    tileId |= is.read() << 16;
//                    tileId |= is.read() << 24;
//                    System.out.println(tileId);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//
//        int[] d = new int[dec.length/4];
//        for (int i = 0; i < dec.length; i+=4) {
//            d[i/4] = getUInt32(dec, i);
//        }
//        System.out.println(Arrays.toString(d));
//        System.out.println();
//        System.out.println();


//        byte[] byteArray = new byte[0];
//        try {
//            byteArray = Base64.decodeBase64(dataCSV.getBytes("UTF-8"));
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        //System.out.println(byteArray);
//
//        int[] d = new int[byteArray.length/4];
//        for (int i = 0; i < byteArray.length; i+=4) {
//            d[i/4] = getUInt32(byteArray, i);
//        }
//        System.out.println(Arrays.toString(d));
//
//
//
//
//        try {
//            System.out.println(GZIPCompression.decompress(dataCSV.getBytes("UTF-8")));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//
//        ByteBuffer byteBuffer = ByteBuffer.allocate(d.length * 4);
//        IntBuffer intBuffer = byteBuffer.asIntBuffer();
//        intBuffer.put(d);
//
//        byte[] array = byteBuffer.array();
//
//        for (int i=0; i < array.length; i++)
//        {
//            System.out.println(i + ": " + array[i]);
//        }


        //final byte[] bytes = intToByte(length);
        //System.out.println(Arrays.toString(byteArray));
        //String decodedString = new String(bytes);
        //System.out.println(dataCSV + " = " + decodedString);

        //System.exit(0);

        int[] dataArray= Arrays.stream(data).mapToInt(Integer::parseInt).toArray();
        this.tileObjects = new TileObject[width][height];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                this.tileObjects[j][i] = new TileObject(dataArray[i * width + j]);
            }
        }
    }

}
