module org.example.quantumapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.example.quantumapp;


    opens org.example.quantumapp to javafx.fxml;
    exports org.example.quantumapp;
}