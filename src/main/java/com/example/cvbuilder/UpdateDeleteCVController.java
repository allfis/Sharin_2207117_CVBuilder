package com.example.cvbuilder;

import com.example.cvbuilder.database.DatabaseHelper;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.util.regex.Pattern;

public class UpdateDeleteCVController {

    @FXML private TextField searchEmailField;
    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private TextField phoneField;
    @FXML private TextField addressField;

    @FXML private TextArea educationField;
    @FXML private TextArea skillsField;
    @FXML private TextArea experienceField;
    @FXML private TextArea projectsField;

    private final DatabaseHelper dbHelper = new DatabaseHelper();

    private final Pattern emailPattern = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    private final Pattern phonePattern = Pattern.compile("^\\d{7,15}$");

    @FXML
    private void initialize() {
        // Ensure phoneField only accepts digits
        phoneField.textProperty().addListener((obs, oldText, newText) -> {
            if (!newText.matches("\\d*")) {
                phoneField.setText(newText.replaceAll("[^\\d]", ""));
            }
        });
    }

    // Search CV by email and populate fields
    @FXML
    private void onSearchCV() {
        String email = searchEmailField.getText().trim();
        if (email.isEmpty() || !emailPattern.matcher(email).matches()) {
            showAlert("Validation Error", "Enter a valid email to search.");
            return;
        }

        try {
            ResultSet rs = dbHelper.getCVByEmail(email);
            if (rs.next()) {
                // Populate fields
                nameField.setText(rs.getString("name"));
                emailField.setText(rs.getString("email"));
                phoneField.setText(rs.getString("phone"));
                addressField.setText(rs.getString("address"));
                educationField.setText(rs.getString("education"));
                skillsField.setText(rs.getString("skills"));
                experienceField.setText(rs.getString("experience"));
                projectsField.setText(rs.getString("projects"));
            } else {
                showAlert("Not Found", "No CV found for the given email.");
            }
        } catch (Exception e) {
            showAlert("Error", "Failed to fetch CV: " + e.getMessage());
        }
    }

    // Update the CV in the database
    @FXML
    private void onUpdateCV() {
        try {
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String phone = phoneField.getText().trim();
            String address = addressField.getText().trim();
            String education = educationField.getText().trim();
            String skills = skillsField.getText().trim();
            String experience = experienceField.getText().trim();
            String projects = projectsField.getText().trim();

            if (name.isEmpty()) {
                showAlert("Validation Error", "Full Name is required.");
                return;
            }
            if (email.isEmpty() || !emailPattern.matcher(email).matches()) {
                showAlert("Validation Error", "Invalid Email Address.");
                return;
            }
            if (!phone.isEmpty() && !phonePattern.matcher(phone).matches()) {
                showAlert("Validation Error", "Phone number must be digits.");
                return;
            }

            int rows = dbHelper.updateCV(name, email, phone, address, education, skills, experience, projects);
            if (rows > 0) {
                showAlert("Success", "CV updated successfully.");
            } else {
                showAlert("Error", "No CV found to update.");
            }

        } catch (Exception e) {
            showAlert("Error", "Failed to update CV: " + e.getMessage());
        }
    }

    // Delete the CV from the database
    @FXML
    private void onDeleteCV() {
        String email = emailField.getText().trim();
        if (email.isEmpty()) {
            showAlert("Validation Error", "Email is required to delete CV.");
            return;
        }

        try {
            boolean deleted = dbHelper.deleteCVByEmail(email);
            if (deleted) {
                showAlert("Success", "CV deleted successfully.");
                clearFields();
            } else {
                showAlert("Error", "No CV found to delete.");
            }
        } catch (Exception e) {
            showAlert("Error", "Failed to delete CV: " + e.getMessage());
        }
    }

    // Clear all form fields
    private void clearFields() {
        nameField.clear();
        emailField.clear();
        phoneField.clear();
        addressField.clear();
        educationField.clear();
        skillsField.clear();
        experienceField.clear();
        projectsField.clear();
        searchEmailField.clear();
    }

    // Show alert helper
    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
