package com.example.cvbuilder;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class PreviewController {

    @FXML
    private Text fullNamePreview, emailPreview, phonePreview, addressPreview;

    @FXML
    private TextArea educationPreview, skillsPreview, experiencePreview, projectsPreview;

    public void setCVData(String fullName, String email, String phone, String address,
                          String education, String skills, String experience, String projects) {
        fullNamePreview.setText(fullName);
        emailPreview.setText(email);
        phonePreview.setText(phone);
        addressPreview.setText(address);

        educationPreview.setText(education);
        skillsPreview.setText(skills);
        experiencePreview.setText(experience);
        projectsPreview.setText(projects);

        educationPreview.setEditable(false);
        skillsPreview.setEditable(false);
        experiencePreview.setEditable(false);
        projectsPreview.setEditable(false);
    }
}
