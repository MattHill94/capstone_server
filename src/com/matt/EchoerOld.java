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
    static int instanceCounter = 0;

    @Override
    public void run() {

        int counter = 0;
        try {
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            int echoString;
            while (true) {
                echoString = Integer.parseInt(input.readLine());
                System.out.println("Received client input: " + echoString);
                DeleteData app = new DeleteData();

                // have a counter to gamify this app least count wins if any errors are at the end you lose


                counter = instanceCounter;

                if(echoString == 1) {
                    instanceCounter++;
                    System.out.println("You've started the sim and have been issued your initial kit");

                    Database db = new Database();

                    Database.updateAmmo();
                    Database.updateRations();
                    Database.updateWater();

                    Database.showStatus();

                    System.out.println("Your Objective is to travel 1Km in the least moves possible.. Good Luck!");


                }
                if(echoString == 2) {
                    instanceCounter++;

                    Database.showAmmo();
                }
                if(echoString == 3) {
                    instanceCounter++;

                    Database.showRations();
                }
                if(echoString == 4) {
                    instanceCounter++;

                    Database.showWater();
                }
                if(echoString == 5) {
                    instanceCounter++;

                    Database.showLocation();
                }
                if(echoString == 6) {
                    instanceCounter++;

                    Database.updateLocation();
                    DatabaseExerciseArea.updateExerciseLocation();
                    Database.removeRations();
                    Database.removeWater();
                    Database.showLocation();
                    System.out.println(" Game counter " + instanceCounter);
                        // rations at 0 need rations to move

                }
                if(echoString == 7) {
                    instanceCounter++;
                    Database.updateRations();
                }
                if(echoString == 8) {
                    instanceCounter++;
                    Database.updateWater();
                }

                if(instanceCounter >= 20 ) {
                    System.out.println("You lost try again");
                    app.delete(1);
                    app.deleteEA(1);


                    System.exit(0);
                }

                if (echoString == 0) {
                    app.delete(1);
//                    app.delete(2);
//                    app.delete(3);
//                    app.delete(4);
                    app.deleteEA(1);
//                    app.deleteEA(2);
//                    app.deleteEA(3);
//                    app.deleteEA(4);
                    System.exit(0);

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
