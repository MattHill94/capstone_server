package com.matt;

import java.io.IOException;
import java.net.ServerSocket;

public class Main {
    public static void main(String[] args) {
        // Server will start on port 6000 can be changed if this port is in use
        try(ServerSocket serverSocket = new ServerSocket(6000)) {
            while(true) {
                // server started waiting for client to connect when connected prints new client has connected
                new Echoer(serverSocket.accept()).start();
                System.out.println("A new client has connected!");
            }

        } catch (IOException e ) {
            System.out.println("Server exception " + e.getMessage());
        }
    }
}