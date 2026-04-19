package org.example.quantumapp.model;

import java.sql.Date;
import java.sql.Timestamp;

public class Pedido {
    private Timestamp fecha;
    private String metodoPago;
    private String estado;

    public Pedido() {
    }

    public Pedido(Timestamp fecha, String metodoPago, String estado) {
        this.fecha = fecha;
        this.metodoPago = metodoPago;
        this.estado = estado;
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
