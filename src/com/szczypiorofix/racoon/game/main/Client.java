package com.szczypiorofix.racoon.game.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;


class Client {

    private Socket socket = null;
    private boolean conneted = false;

    Client() {
        String ip = "127.0.0.1";
        int port = 6969;

        System.out.println("[Connecting to socket...]");
        try {
            socket = new Socket(ip, port);

            String message = "IT JUST WORKS !!!";
            System.out.println("Sending: " + message);
            String returnStr = echo(message);
            System.out.println("Receiving: " + returnStr);
            socket.close();

        } catch (IOException e) {
            //e.printStackTrace();
            MainClass.logging(false, Level.INFO, "Błąd połączenia z serwerem. "+MainClass.getStackTrace(e));
            System.out.println("Brak połączenia z serwerem...");
        }


    }

    private String echo(String message) {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out.println(message);
            return in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}

