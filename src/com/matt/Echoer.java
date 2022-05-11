package com.matt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Echoer extends Thread {

    private Socket socket;

    public Echoer(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            String echoString;
            while (true) {
                echoString = input.readLine();
                System.out.println("Received client input: " + echoString);


                Connection c = null;
                Statement stmt = null;
                try {
                    Class.forName("org.sqlite.JDBC");
                    c = DriverManager.getConnection("jdbc:sqlite:/Users/matt/Documents/development/EchoClient/troop.db");
                    c.setAutoCommit(false);
                    System.out.println("Opened database successfully");

                    stmt = c.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM TROOP;");

                    while (rs.next()) {
                        int id = rs.getInt("id");
                        int ammo = rs.getInt("ammo");
                        double rations = rs.getFloat("rations");
                        double water = rs.getDouble("water");
                        int location = rs.getInt("location");

                        System.out.println("ID = " + id);
                        System.out.println("AMMO = " + ammo);
                        System.out.println("RATIONS = " + rations);
                        System.out.println("WATER = " + water);
                        System.out.println("LOCATION = " + location);
                        System.out.println();
                    }
                    rs.close();
                    stmt.close();
                    c.close();
                } catch (Exception e) {
                    System.err.println(e.getClass().getName() + ": " + e.getMessage());
                    System.exit(0);
                }
                System.out.println("Operation done successfully");

                if (echoString.equals("exit")) {
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
