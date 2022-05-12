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
                    System.out.println("You've started the sim and have been issued your initial kit");
                    Database db = new Database();

                    Database.updateAmmo();
                    Database.updateRations();
                    Database.updateWater();

                    Database.showStatus();


                }

                if(echoString == 2) {
                    Database.showAmmo();
                }
                if(echoString == 3) {
                    Database.showRations();
                }
                if(echoString == 4) {
                    Database.showWater();
                }
                if(echoString == 5) {
                    Database.showLocation();

                }
                if(echoString == 6) {
                    Database.updateLocation();
                    DatabaseExerciseArea.updateExerciseLocation();
                    Database.showLocation();
                }
                if (echoString == 0) {
                    break;
                }

                try {

                }
                catch (Exception e) {
                    System.err.println(e.getClass().getName() + ": " + e.getMessage());
                    System.exit(0);
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