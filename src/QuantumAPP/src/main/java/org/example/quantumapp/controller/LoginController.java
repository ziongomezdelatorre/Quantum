package org.example.quantumapp.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.quantumapp.HelloApplication;
import org.example.quantumapp.dao.EmpleadoDAO;
import org.example.quantumapp.model.Empleado;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Button btnAcceder;

    @FXML
    private TextField editCorreo;

    @FXML
    private PasswordField editPass;


    private EmpleadoDAO empleadoDAO;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            empleadoDAO = new EmpleadoDAO();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error ");
            alert.setContentText("No se ha podido acceder a la base de datos");
            alert.showAndWait();
        }
        actions();
    }


    public void actions(){
        btnAcceder.setOnAction(event -> {
            String correo = editCorreo.getText();
            String contrasenia = editPass.getText();
            try {
                Empleado empleadoCorrecto = empleadoDAO.buscarEmpleadoPorCorreoYContrasena(correo,contrasenia);
                if (empleadoCorrecto!=null && empleadoCorrecto.getRol().contains("empleado")){
                    FXMLLoader loader = null;
                    try {
                        loader =new FXMLLoader(HelloApplication.class.getResource("empleado-view.fxml"));
                        Parent parent = loader.load();
                       EmpleadoController controller= loader.getController();
                       controller.setEmpleado(empleadoCorrecto);
                        Scene scene = new Scene(parent,1024,720);

                        Stage empleadoView = (Stage) btnAcceder.getScene().getWindow();
                        empleadoView.setScene(scene);
                        empleadoView.setTitle("Panel de Control de "+empleadoCorrecto.getNombre());
                        empleadoView.centerOnScreen();
                    }catch (IOException e){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("Error");
                        alert.setHeaderText("No se ha podido acceder al panel de control");
                        alert.showAndWait();
                    }
                } else if (empleadoCorrecto!=null && empleadoCorrecto.getRol().contains("admin")){
                    FXMLLoader loader = null;
                    try {
                        loader =new FXMLLoader(HelloApplication.class.getResource("admin-view.fxml"));
                        Parent parent = loader.load();
                        AdminController controller= loader.getController();
                        controller.setAdmin(empleadoCorrecto);
                        Scene scene = new Scene(parent,1024,720);

                        Stage empleadoView = (Stage) btnAcceder.getScene().getWindow();
                        empleadoView.setScene(scene);
                        empleadoView.setTitle("Panel de Control de "+empleadoCorrecto.getNombre());
                        empleadoView.centerOnScreen();
                    }catch (IOException e){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("Error");
                        alert.setHeaderText("No se ha podido acceder al panel de control");
                        alert.showAndWait();
                    }

                }
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error");
                alert.setContentText("No se ha podido acceder a los datos necesarios");
                alert.showAndWait();
            }


        });

    }
}
