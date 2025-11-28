package com.example.cvbuilder.database;

import java.sql.*;

public class DatabaseHelper {

    private static final String DB_URL = "jdbc:sqlite:cvbuilder.db";

    // Create CV table if it doesn't exist
    public DatabaseHelper() {
        createTable();
    }

    // Connect to SQLite database
    private Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    // Create the CV table
    public void createTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS cv (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL,
                    email TEXT UNIQUE NOT NULL,
                    phone TEXT,
                    address TEXT,
                    education TEXT,
                    skills TEXT,
                    experience TEXT,
                    projects TEXT
                );
                """;

        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Failed to create table: " + e.getMessage());
        }
    }

    // Insert a new CV
    public void insertCV(String name, String email, String phone, String address,
                         String education, String skills, String experience, String projects) throws SQLException {

        String sql = "INSERT INTO cv (name, email, phone, address, education, skills, experience, projects) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, phone);
            pstmt.setString(4, address);
            pstmt.setString(5, education);
            pstmt.setString(6, skills);
            pstmt.setString(7, experience);
            pstmt.setString(8, projects);

            pstmt.executeUpdate();
        }
    }

    // Search CV by email
    public CV getCVByEmail(String email) {
        String sql = "SELECT * FROM cv WHERE email = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new CV(
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("phone"),
                            rs.getString("address"),
                            rs.getString("education"),
                            rs.getString("skills"),
                            rs.getString("experience"),
                            rs.getString("projects")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    // Update CV
    public int updateCV(String name, String email, String phone, String address,
                        String education, String skills, String experience, String projects) throws SQLException {

        String sql = "UPDATE cv SET name = ?, phone = ?, address = ?, education = ?, skills = ?, experience = ?, projects = ? " +
                "WHERE email = ?";

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, phone);
            pstmt.setString(3, address);
            pstmt.setString(4, education);
            pstmt.setString(5, skills);
            pstmt.setString(6, experience);
            pstmt.setString(7, projects);
            pstmt.setString(8, email);

            return pstmt.executeUpdate();
        }
    }

    // Delete CV by email
    public boolean deleteCVByEmail(String email) throws SQLException {
        String sql = "DELETE FROM cv WHERE email = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            int rows = pstmt.executeUpdate();
            return rows > 0;
        }
    }

    // Preview all CVs
    public ResultSet getAllCVs() throws SQLException {
        String sql = "SELECT * FROM cv ORDER BY id ASC";
        Connection conn = connect();
        Statement stmt = conn.createStatement();
        return stmt.executeQuery(sql);
    }
}
