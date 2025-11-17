package com.example.cvbuilder;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class PreviewController {

    @FXML
    private Text nameText, emailText, phoneText, addressText;

    @FXML
    private TextArea educationArea, skillsArea, experienceArea, projectsArea;

    public void setCVData(String fullName, String email, String phone, String address,
                          String education, String skills, String experience, String projects) {
        nameText.setText(fullName);
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
    }
}