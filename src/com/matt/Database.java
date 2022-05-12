package com.matt;

import java.sql.*;

public class Database {

    public static final String TABLE_TROOP = "troop";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_AMMO = "ammo";
    public static final String COLUMN_RATIONS = "rations";
    public static final String COLUMN_WATER = "water";
    public static final String COLUMN_LOCATION = "location";


    private Connection connect() {
        // SQLite connection string
        // where the database lives on my system
        String url = "jdbc:sqlite:/Users/matt/Documents/development/EchoServer/server.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    // this is method that sets the table headers setting int and doubles for the table
    public void insert(int id, int ammo, double rations, double water, int location) {
        String sql = "INSERT INTO troop(id, ammo, rations, water, location) VALUES(?,?,?,?,?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setInt(2, ammo);
            pstmt.setDouble(3, rations);
            pstmt.setDouble(4, water);
            pstmt.setInt(5, location);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void showStatus(){
        try {
            // show status will print the whole database with all of the columns
            Connection conn = DriverManager.getConnection("jdbc:sqlite:/Users/matt/Documents/development/EchoServer/server.db");
            Statement statement = conn.createStatement();
            statement.execute("SELECT * FROM troop");
            ResultSet results = statement.getResultSet();
            while (results.next()) {
                System.out.println(results.getInt("id") + " " +
                        results.getInt("ammo") + " " +
                        results.getDouble("rations") + " " +
                        results.getDouble("water") + " " +
                        results.getInt("location"));
            }
            results.close();
            statement.close();
            conn.close();
        }
        catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static void showId() {
        try {
            // show status will print the whole database with all of the columns
            Connection conn = DriverManager.getConnection("jdbc:sqlite:/Users/matt/Documents/development/EchoServer/server.db");
            Statement statement = conn.createStatement();
            statement.execute("SELECT id FROM troop");
            ResultSet results = statement.getResultSet();
            while (results.next()) {
                System.out.println(results.getInt("id") + " ");
            }
            results.close();
            statement.close();
            conn.close();
        }
        catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }


    public static void showAmmo() {
        try {
            // show status will print the whole database with all of the columns
            Connection conn = DriverManager.getConnection("jdbc:sqlite:/Users/matt/Documents/development/EchoServer/server.db");
            Statement statement = conn.createStatement();
            statement.execute("SELECT id, ammo FROM troop");
            ResultSet results = statement.getResultSet();
            while (results.next()) {
                System.out.println(" AMMO COUNT FOR TROOP ID " +
                        results.getInt("id") + " IS " +
                        results.getInt("ammo") + " ROUNDS ");
            }
            results.close();
            statement.close();
            conn.close();
        }
        catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }


    public static void showRations(){
        try {
            // show status will print the whole database with all of the columns
            Connection conn = DriverManager.getConnection("jdbc:sqlite:/Users/matt/Documents/development/EchoServer/server.db");
            Statement statement = conn.createStatement();
            statement.execute("SELECT id, rations FROM troop");
            ResultSet results = statement.getResultSet();
            while (results.next()) {
                System.out.println(" RATIONS FOR TROOP ID " +
                        results.getInt("id") + " WILL LAST " +
                        results.getInt("rations") + " DAYS ");
            }
            results.close();
            statement.close();
            conn.close();
        }
        catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static void showWater() {
        try {
            // show status will print the whole database with all of the columns
            Connection conn = DriverManager.getConnection("jdbc:sqlite:/Users/matt/Documents/development/EchoServer/server.db");
            Statement statement = conn.createStatement();
            statement.execute("SELECT id, water FROM troop");
            ResultSet results = statement.getResultSet();
            while (results.next()) {
                System.out.println(" WATER COUNT FOR TROOP ID " +
                        results.getInt("id") + " WILL LAST " +
                        results.getInt("water") + " DAYS ");
            }
            results.close();
            statement.close();
            conn.close();
        }
        catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static void showLocation(){
        try {
            // show status will print the whole database with all of the columns
            Connection conn = DriverManager.getConnection("jdbc:sqlite:/Users/matt/Documents/development/EchoServer/server.db");
            Statement statement = conn.createStatement();
            statement.execute("SELECT id, location FROM troop");
            ResultSet results = statement.getResultSet();
            while (results.next()) {
                System.out.println(" Troop with id " +
                        results.getInt("id") + " Has Travelled " +
                        results.getInt("location") + " Meters ");
            }
            results.close();
            statement.close();
            conn.close();
        }
        catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }



    public static void updateLocation() {
        try {
            // SQL statement to update location of troop at the minute it is set to 100
            Connection conn = DriverManager.getConnection("jdbc:sqlite:/Users/matt/Documents/development/EchoServer/server.db");
            Statement statement = conn.createStatement();
            statement.execute("UPDATE " + TABLE_TROOP + " SET " + COLUMN_LOCATION + "=100 + location");
            statement.close();
            conn.close();
        }
        catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static void updateAmmo() {
        try {
            // SQL statement to update location of troop at the minute it is set to 100
            Connection conn = DriverManager.getConnection("jdbc:sqlite:/Users/matt/Documents/development/EchoServer/server.db");
            Statement statement = conn.createStatement();
            statement.execute("UPDATE " + TABLE_TROOP + " SET " + COLUMN_AMMO + "=180 + ammo");
            statement.close();
            conn.close();
        }
        catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
    public static void updateRations() {
        try {
            // SQL statement to update location of troop at the minute it is set to 100
            Connection conn = DriverManager.getConnection("jdbc:sqlite:/Users/matt/Documents/development/EchoServer/server.db");
            Statement statement = conn.createStatement();
            statement.execute("UPDATE " + TABLE_TROOP + " SET " + COLUMN_RATIONS + "=1 + rations");
            statement.close();
            conn.close();
        }
        catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static void updateWater() {
        try {
            // SQL statement to update location of troop at the minute it is set to 100
            Connection conn = DriverManager.getConnection("jdbc:sqlite:/Users/matt/Documents/development/EchoServer/server.db");
            Statement statement = conn.createStatement();
            statement.execute("UPDATE " + TABLE_TROOP + " SET " + COLUMN_WATER + "=1 + water");
            statement.close();
            conn.close();
        }
        catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }














}



