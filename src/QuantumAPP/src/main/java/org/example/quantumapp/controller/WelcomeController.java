package org.example.quantumapp.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.example.quantumapp.HelloApplication;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WelcomeController implements Initializable {
    @FXML
private WebView vistaBienvenida;
    @FXML
    private Button btnLogin;


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

        actions();
    }

    public void actions(){
        btnLogin.setOnAction(event -> {

                FXMLLoader loader = null;
                try {
                    loader =new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));
                    Parent parent = loader.load();
                    LoginController loginController = loader.getController();
                    Scene scene = new Scene(parent,1024,720);

                    Stage loginView = (Stage) btnLogin.getScene().getWindow();
                    loginView.setScene(scene);
                    loginView.setTitle("Log in");
                    loginView.centerOnScreen();
                }catch (IOException e){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Error");
                    alert.setHeaderText("No se ha podido acceder al panel de login");
                    alert.showAndWait();
                }

        });
    }
}
