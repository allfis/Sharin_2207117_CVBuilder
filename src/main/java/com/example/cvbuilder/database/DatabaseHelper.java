package com.example.cvbuilder.database;

import java.sql.*;

public class DatabaseHelper {

    private static final String DB_URL = "jdbc:sqlite:cvbuilder.db";

    public DatabaseHelper() {
        createTable();
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public void createTable() {
        String sql = """
            CREATE TABLE IF NOT EXISTS cv (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                full_name TEXT NOT NULL,
                email TEXT UNIQUE NOT NULL,
                phone TEXT NOT NULL,
                address TEXT,
                education TEXT,
                skills TEXT,
                experience TEXT,
                projects TEXT,
                image_path TEXT
            );
        """;

        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Table creation failed: " + e.getMessage());
        }
    }

    public boolean cvExists(String email) {
        String sql = "SELECT email FROM cv WHERE email = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean insertCV(String fullName, String email, String phone, String address,
                            String education, String skills, String experience,
                            String projects, String imagePath) {
        String sql = """
            INSERT INTO cv(full_name,email,phone,address,education,skills,experience,projects,image_path)
            VALUES (?,?,?,?,?,?,?,?,?)
        """;
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, fullName);
            pstmt.setString(2, email);
            pstmt.setString(3, phone);
            pstmt.setString(4, address);
            pstmt.setString(5, education);
            pstmt.setString(6, skills);
            pstmt.setString(7, experience);
            pstmt.setString(8, projects);
            pstmt.setString(9, imagePath);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean updateCV(String fullName, String email, String phone, String address,
                            String education, String skills, String experience,
                            String projects, String imagePath) {
        String sql = """
            UPDATE cv SET full_name=?, phone=?, address=?, education=?, skills=?, experience=?, projects=?, image_path=? 
            WHERE email=?
        """;
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, fullName);
            pstmt.setString(2, phone);
            pstmt.setString(3, address);
            pstmt.setString(4, education);
            pstmt.setString(5, skills);
            pstmt.setString(6, experience);
            pstmt.setString(7, projects);
            pstmt.setString(8, imagePath);
            pstmt.setString(9, email);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean deleteCV(String email) {
        String sql = "DELETE FROM cv WHERE email=?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public ResultSet getCV(String email) {
        String sql = "SELECT * FROM cv WHERE email=?";
        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            return pstmt.executeQuery();
        } catch (SQLException e) {
            return null;
        }
    }
}
