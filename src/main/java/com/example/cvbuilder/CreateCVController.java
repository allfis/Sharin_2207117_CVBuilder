package com.example.cvbuilder;

import com.example.cvbuilder.database.DatabaseHelper;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.regex.Pattern;

public class CreateCVController {

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
        // Restrict phoneField to digits only
        phoneField.textProperty().addListener((obs, oldText, newText) -> {
            if (!newText.matches("\\d*")) {
                phoneField.setText(newText.replaceAll("[^\\d]", ""));
            }
        });
    }

    @FXML
    private void onSaveCV() {
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

            if (email.isEmpty()) {
                showAlert("Validation Error", "Email Address is required.");
                return;
            }

            if (!emailPattern.matcher(email).matches()) {
                showAlert("Validation Error", "Invalid Email Address.");
                return;
            }

            if (!phone.isEmpty() && !phonePattern.matcher(phone).matches()) {
                showAlert("Validation Error", "Invalid Phone Number");
                return;
            }

            dbHelper.insertCV(name, email, phone, address, education, skills, experience, projects);

            showAlert("Success", "CV inserted Successfully");

            clearFields();

        } catch (Exception e) {
            showAlert("Error", "Failed to insert CV: " + e.getMessage());
        }
    }

    private void clearFields() {
        nameField.clear();
        emailField.clear();
        phoneField.clear();
        addressField.clear();
        educationField.clear();
        skillsField.clear();
        experienceField.clear();
        projectsField.clear();
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
