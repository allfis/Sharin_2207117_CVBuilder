package com.example.cvbuilder;

import com.example.cvbuilder.database.DatabaseHelper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class HelloApplication extends Application {



    @Override
    public void start(Stage stage) throws Exception {
        // Ensure database table exists
        DatabaseHelper dbHelper = new DatabaseHelper(); // table is automatically created here

        // Load Home.fxml from classpath
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Home.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);

        stage.setTitle("CV Builder");

        // Optional icon
        try {
            Image icon = new Image(getClass().getResourceAsStream("/icons/app_icon.png"));
            stage.getIcons().add(icon);
        } catch (Exception e) {
            System.out.println("Icon not found — continuing without it.");
        }

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
