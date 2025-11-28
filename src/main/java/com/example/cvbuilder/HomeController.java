package com.example.cvbuilder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HomeController {

    @FXML
    private void onCreateCV(ActionEvent event) {
        openPage("/fxml/CreateCV.fxml", "Create CV");
    }

    @FXML
    private void onUpdateDeleteCV(ActionEvent event) {
        openPage("/fxml/UpdateDeleteCV.fxml", "Update/Delete CV");
    }

    @FXML
    private void onPreviewCVs(ActionEvent event) {
        openPage("/fxml/PreviewAll.fxml", "Preview CVs");
    }

    private void openPage(String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
