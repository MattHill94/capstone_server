package com.matt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class EchoerOld extends Thread {

    private Socket socket;

    public EchoerOld(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            int echoString;
            while (true) {
                echoString = Integer.parseInt(input.readLine());
                System.out.println("Received client input: " + echoString);

                if(echoString == 1) {
                    System.out.println("youve started the sim");

                    Database.showStatus();
                    Database.updateLocation();




                }

                try {


                }
                catch (Exception e) {
                    System.err.println(e.getClass().getName() + ": " + e.getMessage());
                    System.exit(0);
                }
                if (echoString == 0) {
                    break;
                }
//                try {
//                    Thread.sleep(15000);
//
//                } catch (InterruptedException e ) {
//                    System.out.println("Thread interrupted");
//                }
                output.println(echoString);
            }
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("Oops: " + e.getMessage());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}