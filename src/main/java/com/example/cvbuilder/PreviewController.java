package com.example.cvbuilder;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class PreviewController {

    @FXML
    private Text fullNameText, emailText, phoneText, addressText,
            educationText, skillsText, experienceText, projectsText;

    public void setCV(CV cv) {
        fullNameText.setText("Full Name: " + cv.fullName);
        emailText.setText("Email: " + cv.email);
        phoneText.setText("Phone: " + cv.phone);
        addressText.setText("Address: " + cv.address);
        educationText.setText("Education: " + String.join(", ", cv.education));
        skillsText.setText("Skills: " + String.join(", ", cv.skills));
        experienceText.setText("Experience: " + String.join(", ", cv.experience));
        projectsText.setText("Projects: " + String.join(", ", cv.projects));
    }
}
