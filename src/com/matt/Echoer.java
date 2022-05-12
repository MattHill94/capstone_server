package com.matt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

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

            int inputNum;

            while (true) {
                inputNum = Integer.parseInt(input.readLine());
                System.out.println("Received client input: " + inputNum);

                boolean quit = false;
                boolean forward = true;
                inputNum = Integer.parseInt(input.readLine());

                while(!quit) {
                    switch(inputNum) {
                        case 0:
                            if(inputNum == 1) {
                                System.out.println("num 1");
                            }
                            break;
                        case 1:
                            if(inputNum == 2) {
                                System.out.println("num 2");
                            }
                            break;
                        case 2:
                            if(inputNum == 3) {
                                System.out.println("num 3");
                            }
                            break;

                    }
                }
                try {


                } catch (Exception e) {
                    System.err.println(e.getClass().getName() + ": " + e.getMessage());
                    System.exit(0);
                }
                System.out.println("Operation done successfully");

                if (inputNum == 0) {
                    break;
                }
                output.println(inputNum);
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





//    private static void printMenu() {
//        System.out.println("Available actions:\npress");
//        System.out.println("0 - to quit\n" +
//                "1 - to play next song\n" +
//                "2 - to play previous song\n" +
//                "3 - to replay the current song\n" +
//                "4 - list songs in the playlist\n" +
//                "5 - print available actions.\n" +
//                "6 - delete current song from playlist");
//    }

//    private static void printList(LinkedList<Song> playList) {
//        Iterator<Song> iterator = playList.iterator();
//        System.out.println("================================");
//        while(iterator.hasNext()) {
//            System.out.println(iterator.next());
//        }
//        System.out.println("================================");
//    }


}
