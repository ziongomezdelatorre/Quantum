package org.example.quantumapp.model;

import java.sql.Date;
import java.sql.Timestamp;

public class Pedido {
    private int id;
    private int idCliente;
    private Timestamp fecha;
    private String metodoPago;
    private String estado;

    public Pedido() {
    }

    public Pedido(int idCliente, Timestamp fecha, String metodoPago, String estado) {
        this.idCliente = idCliente;
        this.fecha = fecha;
        this.metodoPago = metodoPago;
        this.estado = estado;
    }

    public Pedido(int id, int idCliente, Timestamp fecha, String metodoPago, String estado) {
        this.id = id;
        this.idCliente = idCliente;
        this.fecha = fecha;
        this.metodoPago = metodoPago;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
