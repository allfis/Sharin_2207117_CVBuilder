package com.example.cvbuilder;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateController {

    @FXML
    private TextField fullNameField, emailField, phoneField, addressField,
            educationField, skillsField, experienceField, projectsField;

    @FXML
    public void onGenerate() throws Exception {
        CV cv = new CV();
        cv.fullName = fullNameField.getText();
        cv.email = emailField.getText();
        cv.phone = phoneField.getText();
        cv.address = addressField.getText();
        cv.education = java.util.Arrays.asList(educationField.getText().split(","));
        cv.skills = java.util.Arrays.asList(skillsField.getText().split(","));
        cv.experience = java.util.Arrays.asList(experienceField.getText().split(","));
        cv.projects = java.util.Arrays.asList(projectsField.getText().split(","));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/preview.fxml"));
        Parent root = loader.load();

        PreviewController controller = loader.getController();
        controller.setCV(cv);

        Stage stage = (Stage) fullNameField.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
}
