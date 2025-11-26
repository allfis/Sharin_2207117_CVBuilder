module com.example.cvbuilder {
    // JavaFX modules
    requires javafx.controls;
    requires javafx.fxml;

    // JDBC API
    requires java.sql;

    // SQLite JDBC driver (from org.xerial)
    requires org.xerial.sqlitejdbc;

    // Allow FXML to access your controllers
    opens com.example.cvbuilder to javafx.fxml;

    // Export your base package
    exports com.example.cvbuilder;
}