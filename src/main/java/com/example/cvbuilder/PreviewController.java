package com.example.cvbuilder;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.File;

public class PreviewController {

    @FXML private Text fullNameText;
    @FXML private Text emailText;
    @FXML private Text phoneText;
    @FXML private Text addressText;

    @FXML private TextArea educationArea;
    @FXML private TextArea skillsArea;
    @FXML private TextArea experienceArea;
    @FXML private TextArea projectsArea;

    @FXML private ImageView profileImageView;

    public void setCVData(String fullName, String email, String phone, String address,
                          String education, String skills, String experience, String projects,
                          File profileImage) {

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

        if (profileImage != null) {
            Image image = new Image(profileImage.toURI().toString());
            profileImageView.setImage(image);
        }
    }
}
