module com.example.cvbuilder {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.cvbuilder to javafx.fxml;
    exports com.example.cvbuilder;
}