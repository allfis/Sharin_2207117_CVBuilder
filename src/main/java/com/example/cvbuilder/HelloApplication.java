package com.example.cvbuilder;

import com.example.cvbuilder.database.DatabaseHelper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    private final DatabaseHelper dbHelper = new DatabaseHelper();

    @Override
    public void start(Stage stage) throws Exception {
        // Initialize database tables at launch
        dbHelper.createTable();  // ensures table is ready

        // Load the Home FXML page
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/home.fxml"));

        Scene scene = new Scene(root);
        stage.setTitle("CV Builder");
        stage.setWidth(800);
        stage.setHeight(600);

        // Set application icon (optional)
        Image icon = new Image(getClass().getResource("/images/cv_icon.jpg").toString());
        stage.getIcons().add(icon);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
