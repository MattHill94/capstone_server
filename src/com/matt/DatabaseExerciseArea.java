package com.matt;

import java.sql.*;

public class DatabaseExerciseArea {

    public static final String TABLE_EXERCISE_AREA = "exercise_area";

    public static final String COLUMN_TROOP_ID = "troop_id";

    public static final String COLUMN_EXERCISE_LOCATION = "exercise_location";

    public static final String PATH_TO_DATABASE = "jdbc:sqlite:/Users/matt/Desktop/CAPSTONE_1/capstone_server-master/server.db";


    private Connection connect() {
        // SQLite connection string
        // where the database lives on my system
        String url = PATH_TO_DATABASE;
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
            Connection conn = DriverManager.getConnection(PATH_TO_DATABASE);
            Statement statement = conn.createStatement();
            statement.execute("UPDATE " + TABLE_EXERCISE_AREA + " SET " + COLUMN_EXERCISE_LOCATION + "=100 + exercise_location");

            statement.close();
            conn.close();
        }
        catch (SQLException e) {
            int SQLErrorCode = e.getErrorCode();
            if(SQLErrorCode == 19) {
                System.out.println("You've completed the game "  + "in " + EchoerOld.instanceCounter + " moves " + "Exiting...");
                System.exit(0);
            }
        }
    }
}
