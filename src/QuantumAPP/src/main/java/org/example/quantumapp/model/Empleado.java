package org.example.quantumapp.model;

import java.sql.Date;

public class Empleado extends Persona {
    public Empleado(String nombre, String apellido, String direccion, String dni, String correo, String pass, Date fechaNacimiento) {
        super(nombre, apellido, direccion, dni, correo, pass, fechaNacimiento);
    }
}
