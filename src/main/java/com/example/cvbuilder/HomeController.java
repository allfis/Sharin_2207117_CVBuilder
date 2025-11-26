package com.example.cvbuilder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomeController {

    @FXML
    public void onCreate(ActionEvent event) throws Exception {

        // Load create screen
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/create.fxml"));

        // Switch scene
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Create CV");
        stage.show();
    }
}
