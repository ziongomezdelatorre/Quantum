package org.example.quantumapp.controller;

import javafx.fxml.Initializable;
import org.example.quantumapp.model.Empleado;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {


    private Empleado admin;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void setAdmin(Empleado admin){
        this.admin=admin;

    }
}
