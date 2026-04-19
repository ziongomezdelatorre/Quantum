package org.example.quantumapp.model;


import java.sql.Date;

public class Cliente extends Persona{


    public Cliente(String nombre, String apellido, String direccion, String dni, String correo, String pass, Date fechaNacimiento) {
        super(nombre, apellido, direccion, dni, correo, pass, fechaNacimiento);
    }
}
