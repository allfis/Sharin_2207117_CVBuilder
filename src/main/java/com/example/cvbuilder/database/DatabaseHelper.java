package com.example.cvbuilder.database;

import com.example.cvbuilder.CV;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {

    private static final String DB_URL = "jdbc:sqlite:cvbuilder.db";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    // Get CV by email
    public CV getCVByEmail(String email) {
        String sql = "SELECT * FROM cv WHERE email = ?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new CV(
                        rs.getInt("id"),
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

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Insert a new CV
    public boolean insertCV(CV cv) {
        String sql = "INSERT INTO cv(name, email, phone, address, education, skills, experience, projects) VALUES(?,?,?,?,?,?,?,?)";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cv.getName());
            stmt.setString(2, cv.getEmail());
            stmt.setString(3, cv.getPhone());
            stmt.setString(4, cv.getAddress());
            stmt.setString(5, cv.getEducation());
            stmt.setString(6, cv.getSkills());
            stmt.setString(7, cv.getExperience());
            stmt.setString(8, cv.getProjects());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    // Update CV
    public boolean updateCV(CV cv) {
        String sql = "UPDATE cv SET name = ?, phone = ?, address = ?, education = ?, skills = ?, experience = ?, projects = ? WHERE email = ?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cv.getName());
            stmt.setString(2, cv.getPhone());
            stmt.setString(3, cv.getAddress());
            stmt.setString(4, cv.getEducation());
            stmt.setString(5, cv.getSkills());
            stmt.setString(6, cv.getExperience());
            stmt.setString(7, cv.getProjects());
            stmt.setString(8, cv.getEmail());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //  Delete CV by email
    public boolean deleteCVByEmail(String email) {
        String sql = "DELETE FROM cv WHERE email = ?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // In DatabaseHelper.java
    public List<CV> getAllCVs() {
        List<CV> cvList = new ArrayList<>();
        String sql = "SELECT * FROM cv"; // or your table name

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                CV cv = new CV(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getString("education"),
                        rs.getString("skills"),
                        rs.getString("experience"),
                        rs.getString("projects")
                );
                cvList.add(cv);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cvList;
    }

}
