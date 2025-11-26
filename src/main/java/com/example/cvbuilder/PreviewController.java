package com.example.cvbuilder;

import com.example.cvbuilder.database.DatabaseHelper;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.File;

public class PreviewController {

    @FXML private Text fullNameText, emailText, phoneText, addressText;
    @FXML private TextArea educationArea, skillsArea, experienceArea, projectsArea;
    @FXML private ImageView profileImageView;

    private final DatabaseHelper dbHelper = new DatabaseHelper();

    // Set data from Create page
    public void setCVData(String fullName, String email, String phone, String address,
                          String education, String skills, String experience, String projects,
                          String imagePath) {

        fullNameText.setText(fullName);
        emailText.setText(email);
        phoneText.setText(phone);
        addressText.setText(address);

        educationArea.setText(education);
        skillsArea.setText(skills);
        experienceArea.setText(experience);
        projectsArea.setText(projects);

        educationArea.setEditable(false);
        skillsArea.setEditable(false);
        experienceArea.setEditable(false);
        projectsArea.setEditable(false);

        // Display image from path
        if (imagePath != null && !imagePath.isEmpty()) {
            File file = new File(imagePath);
            if (file.exists()) {
                profileImageView.setImage(new Image(file.toURI().toString()));
            }
        }
    }

    // Delete CV
    @FXML
    private void onDeleteCV() {
        String email = emailText.getText();
        dbHelper.deleteCV(email);

        fullNameText.setText("");
        emailText.setText("");
        phoneText.setText("");
        addressText.setText("");
        educationArea.clear();
        skillsArea.clear();
        experienceArea.clear();
        projectsArea.clear();
        profileImageView.setImage(null);
    }
}
