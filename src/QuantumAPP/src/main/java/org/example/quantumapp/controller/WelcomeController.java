package org.example.quantumapp.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class WelcomeController implements Initializable {
    @FXML
private WebView vistaBienvenida;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        WebEngine webEngine = vistaBienvenida.getEngine();
        URL url2 = getClass().getResource("/org/example/quantumapp/welcome.html");
        if (url2!=null){
            webEngine.load(url2.toExternalForm());
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de Recursos");
            alert.setHeaderText("Archivo no encontrado");
            alert.setContentText("No se pudo hallar welcome.html en el classpath.");
            alert.showAndWait();
        }
    }
}
