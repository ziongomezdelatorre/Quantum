package org.example.quantumapp.model;

import java.sql.Date;

public abstract class Persona {
    private String nombre,apellido,direccion,dni,correo,pass;
    private Date fechaNacimiento;

    public Persona(String nombre, String apellido, String direccion, String dni, String correo, String pass, Date fechaNacimiento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.dni = dni;
        this.correo = correo;
        this.pass = pass;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
