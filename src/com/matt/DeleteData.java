package com.matt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteData {

    public static final String PATH_TO_DATABASE = "jdbc:sqlite:/Users/matt/Documents/development/CAPSTONE_1/capstone_server-master/server.db";

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

    public void delete(int id) {
        String sql = "DELETE FROM troop";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteEA(int id) {
        String sql = "DELETE FROM exercise_area ";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
