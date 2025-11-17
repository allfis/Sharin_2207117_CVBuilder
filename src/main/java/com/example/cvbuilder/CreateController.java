package com.example.cvbuilder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;

public class CreateController {

    @FXML private TextField fullNameField;
    @FXML private TextField emailField;
    @FXML private TextField phoneField;
    @FXML private TextField addressField;
    @FXML private TextArea educationArea;
    @FXML private TextArea skillsArea;
    @FXML private TextArea experienceArea;
    @FXML private TextArea projectsArea;

    @FXML
    private void onGenerateCV(ActionEvent event) {

        if(fullNameField.getText().isEmpty() || emailField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Missing Information");
            alert.setHeaderText(null);
            alert.setContentText("Please enter your Full Name and Email!");
            alert.showAndWait();
            return;
        }

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/preview.fxml"));
            Parent root = loader.load();


            //PreviewController previewController = loader.getController();
//           previewController.setCVData(
//                    fullNameField.getText(),
//                    emailField.getText(),
//                    phoneField.getText(),
//                    addressField.getText(),
//                    educationArea.getText(),
//                    skillsArea.getText(),
//                    experienceArea.getText(),
//                    projectsArea.getText()
//            );


            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("CV Preview");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
