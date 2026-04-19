package org.example.quantumapp.model;

import java.io.Serializable;

public class Producto {


    private String nombreProducto, descripcion;
    private int idCategoria;
    private int stock;
    private boolean disponibilidad;
    private double precio;

    public Producto() {
    }

    public Producto(String nombreProducto, String descripcion, int idCategoria, int stock, boolean disponibilidad, double precio) {
        this.nombreProducto = nombreProducto;
        this.descripcion = descripcion;
        this.idCategoria = idCategoria;
        this.stock = stock;
        this.disponibilidad = disponibilidad;
        this.precio = precio;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
