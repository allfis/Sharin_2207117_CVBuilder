package com.example.cvbuilder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

public class CreateController {

    @FXML private TextField fullNameField;
    @FXML private TextField emailField;
    @FXML private TextField phoneField;
    @FXML private TextField addressField;
    @FXML private TextArea educationArea;
    @FXML private TextArea skillsArea;
    @FXML private TextArea experienceArea;
    @FXML private TextArea projectsArea;
    @FXML private ImageView profileImageView;

    private File selectedImageFile;

    // STEP 1: Upload photo
    @FXML
    private void onUploadPhoto(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Profile Picture");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );

        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            selectedImageFile = file;
            Image image = new Image(file.toURI().toString());
            profileImageView.setImage(image);
        }
    }

    // STEP 2: Generate CV with validations
    @FXML
    private void onGenerateCV(ActionEvent event) {

        String fullName = fullNameField.getText().trim();
        String email = emailField.getText().trim();
        String phone = phoneField.getText().trim();

        // Basic validations
        if(fullName.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            showAlert("Missing Information", "Full Name, Email and Phone are required!");
            return;
        }

        if(!isValidEmail(email)) {
            showAlert("Invalid Email", "Please enter a valid email in format name@gmail.com");
            return;
        }

        if(!isInteger(phone)) {
            showAlert("Invalid Phone", "Phone number must contain only digits!");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/preview.fxml"));
            Parent root = loader.load();

            PreviewController previewController = loader.getController();
            previewController.setCVData(
                    fullName,
                    email,
                    phone,
                    addressField.getText().trim(),
                    educationArea.getText(),
                    skillsArea.getText(),
                    experienceArea.getText(),
                    projectsArea.getText(),
                    selectedImageFile // pass selected image
            );

            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("CV Preview");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // HELPER: Alert
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // HELPER: Validate email
    private boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return Pattern.matches(regex, email);
    }

    // HELPER: Check integer
    private boolean isInteger(String str) {
        return str.matches("\\d+");
    }
}
