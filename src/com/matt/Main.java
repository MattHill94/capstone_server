
package com.matt;

import java.io.IOException;
import java.net.ServerSocket;

public class Main {
    public static void main(String[] args) {

        // Initializing troop database on startup
        Database db = new Database();
        db.insert(1, 0, 0, 0, 0);
        db.insert(2, 0, 0, 0, 0);
        db.insert(3, 0, 0, 0, 0);
        db.insert(4, 0, 0, 0, 0);

        // Initializing exercise_area database on startup
        DatabaseExerciseArea dbea = new DatabaseExerciseArea();
        dbea.insert(1,0);
        dbea.insert(2,0);
        dbea.insert(3,0);
        dbea.insert(4,0);

        // Server will start on port 6000 can be changed if this port is in use
        try(ServerSocket serverSocket = new ServerSocket(6002)) {
            while(true) {
                // server started waiting for client to connect when connected prints new client has connected
                new EchoerOld(serverSocket.accept()).start();
                System.out.println("A new client has connected!" +
                        " press '1' to ready the troops " );
            }

        } catch (IOException e ) {
            System.out.println("Server exception " + e.getMessage());
        }
    }
}