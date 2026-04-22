package org.example.quantumapp.model;

import java.sql.Date;

public class Empleado extends Persona {
    private String rol;
    public Empleado(String nombre, String apellido, String direccion, String dni, String correo, String pass, Date fechaNacimiento,String rol) {
        super(nombre, apellido, direccion, dni, correo, pass, fechaNacimiento);
        this.rol = rol;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
