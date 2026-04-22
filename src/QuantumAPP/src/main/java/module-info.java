module org.example.quantumapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.web;


    opens org.example.quantumapp.controller to javafx.fxml;

    opens org.example.quantumapp to javafx.fxml;
    exports org.example.quantumapp;
}