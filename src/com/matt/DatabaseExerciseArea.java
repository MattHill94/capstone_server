package com.matt;

import java.sql.*;

public class DatabaseExerciseArea {

    public static final String TABLE_EXERCISE_AREA = "exercise_area";

    public static final String COLUMN_TROOP_ID = "troop_id";

    public static final String COLUMN_EXERCISE_LOCATION = "exercise_location";



    private Connection connect() {
        // SQLite connection string
        // where the database lives on my system
        String url = "jdbc:sqlite:/Users/matt/Downloads/capstone_server-main 2/server.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void insert(int troop_id, int exercise_location) {
        String sql = "INSERT INTO exercise_area(troop_id, exercise_location) VALUES(?,?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, troop_id);
            pstmt.setInt(2, exercise_location);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateExerciseLocation() {
        try {
            // SQL statement to update location of troop at the minute it is set to 100
            Connection conn = DriverManager.getConnection("jdbc:sqlite:/Users/matt/Downloads/capstone_server-main 2/server.db");
            Statement statement = conn.createStatement();
            statement.execute("UPDATE " + TABLE_EXERCISE_AREA + " SET " + COLUMN_EXERCISE_LOCATION + "=100 + exercise_location");

            statement.close();
            conn.close();
        }
        catch (SQLException e) {
            int SQLErrorCode = e.getErrorCode();
            if(SQLErrorCode == 19) {
                System.out.println("YOU'VE WON THE GAME YOU FUCKING LEGEND"  + " In " + EchoerOld.instanceCounter + " moves " + ": Press 0 to EXIT NOW");
                System.exit(0);
            }
        }
    }
}
