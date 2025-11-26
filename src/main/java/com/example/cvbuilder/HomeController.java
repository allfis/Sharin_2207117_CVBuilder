package com.example.cvbuilder;

import com.example.cvbuilder.database.DatabaseHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HomeController {

    @FXML private TextField fullNameField, emailField, phoneField, addressField;
    @FXML private TextArea educationArea, skillsArea, experienceArea, projectsArea;

    private final DatabaseHelper dbHelper = new DatabaseHelper();

    // CREATE or UPDATE CV
    @FXML
    private void onCreateUpdateCV(ActionEvent event) {
        String fullName = fullNameField.getText().trim();
        String email = emailField.getText().trim();
        String phone = phoneField.getText().trim();

        if(fullName.isEmpty() || email.isEmpty() || phone.isEmpty()){
            showAlert("Error", "Full Name, Email, and Phone are required!");
            return;
        }

        String address = addressField.getText().trim();
        String education = educationArea.getText();
        String skills = skillsArea.getText();
        String experience = experienceArea.getText();
        String projects = projectsArea.getText();

        if(dbHelper.cvExists(email)) {
            dbHelper.updateCV(fullName, email, phone, address, education, skills, experience, projects, "");
        } else {
            dbHelper.insertCV(fullName, email, phone, address, education, skills, experience, projects, "");
        }

        showAlert("Success", "CV Created/Updated Successfully!");

        // Preview automatically
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/preview.fxml"));
            Parent root = loader.load();

            PreviewController previewController = loader.getController();
            ResultSet rs = dbHelper.getCV(email);
            if(rs != null && rs.next()){
                previewController.setCVData(
                        rs.getString("full_name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getString("education"),
                        rs.getString("skills"),
                        rs.getString("experience"),
                        rs.getString("projects"),
                        rs.getString("image_path")
                );
            }

            Stage stage = (Stage) fullNameField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("CV Preview");
            stage.show();

        } catch (IOException | SQLException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to open Preview page!");
        }
    }

    // DELETE CV
    @FXML
    private void onDeleteCV(ActionEvent event) {
        String email = emailField.getText().trim();
        if(email.isEmpty()){
            showAlert("Error", "Please enter email to delete CV!");
            return;
        }

        if(!dbHelper.cvExists(email)){
            showAlert("Error", "CV not found for this email!");
            return;
        }

        boolean success = dbHelper.deleteCV(email);
        if(success){
            showAlert("Success", "CV deleted successfully!");
            clearFields();
        } else {
            showAlert("Error", "Failed to delete CV!");
        }
    }

    private void clearFields(){
        fullNameField.clear();
        emailField.clear();
        phoneField.clear();
        addressField.clear();
        educationArea.clear();
        skillsArea.clear();
        experienceArea.clear();
        projectsArea.clear();
    }

    private void showAlert(String title, String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
