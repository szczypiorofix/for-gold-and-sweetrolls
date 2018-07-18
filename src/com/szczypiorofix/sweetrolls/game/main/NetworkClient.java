package com.szczypiorofix.sweetrolls.game.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;


class NetworkClient {

    private static Socket socket = null;
    private static boolean connected = false;
    private static final String HOST_NAME = "wroblewskipiotr.pl";
    private static final int PORT = 3000;

    private static PrintWriter out;
    private static BufferedReader in;


    public static void startConnection() {
        try {
            socket = new Socket(InetAddress.getByName("localhost"), PORT);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            connected = true;
        } catch (IOException e) {
            connected = false;
            System.out.println("Wykryto błąd połączenia !!!");
            //e.printStackTrace();
            //System.exit(0);
        }

        System.out.println("Socket?: " +connected);
    }

    public static boolean isConnected() {
        // IF NOT CONNECTED TRY TO CONNECT...
        if (!connected) {
            startConnection();
        }

        // IF CONNECTED CHECK IF SERVER STATUS IS OK
        if (sendMessage("STATUS") == null) connected = false;
        return connected;
    }

    private static String sendMessage(String msg) {
        String result = "";
        if (connected) {
            out.println(msg);
            try {
                if (!socket.isClosed()) result = in.readLine();
            } catch (IOException e) {
                //e.printStackTrace();
                result = "ERROR !!!";
                connected = false;
            }
        }
        return result;
    }

    public static void closeConnection() {

        if (connected) {
            try {
                in.close();
                out.close();
                if (!socket.isClosed()) socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        connected = false;
    }

}

