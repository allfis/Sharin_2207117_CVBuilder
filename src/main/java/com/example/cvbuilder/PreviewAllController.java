package com.example.cvbuilder;

import com.example.cvbuilder.database.DatabaseHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class PreviewAllController {

    @FXML private TableView<CV> cvTable;
    @FXML private TableColumn<CV, Integer> idColumn;
    @FXML private TableColumn<CV, String> nameColumn;
    @FXML private TableColumn<CV, String> emailColumn;
    @FXML private TableColumn<CV, String> phoneColumn;
    @FXML private TableColumn<CV, String> addressColumn;
    @FXML private TableColumn<CV, String> educationColumn;
    @FXML private TableColumn<CV, String> skillsColumn;
    @FXML private TableColumn<CV, String> experienceColumn;
    @FXML private TableColumn<CV, String> projectsColumn;

    private final DatabaseHelper dbHelper = new DatabaseHelper();

    @FXML
    private void initialize() {
        // Bind columns to CV properties
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        educationColumn.setCellValueFactory(new PropertyValueFactory<>("education"));
        skillsColumn.setCellValueFactory(new PropertyValueFactory<>("skills"));
        experienceColumn.setCellValueFactory(new PropertyValueFactory<>("experience"));
        projectsColumn.setCellValueFactory(new PropertyValueFactory<>("projects"));

        loadCVs();
    }

    @FXML
    private void onRefreshCVs() {
        loadCVs();
    }

    private void loadCVs() {
        try {
            List<CV> cvList = dbHelper.getAllCVs();  // Now returns List<CV>
            ObservableList<CV> list = FXCollections.observableArrayList(cvList);
            cvTable.setItems(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
