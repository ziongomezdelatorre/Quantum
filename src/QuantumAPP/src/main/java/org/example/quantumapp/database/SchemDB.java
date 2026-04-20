package org.example.quantumapp.database;

public interface SchemDB {
    public static final String TAB_CLIENTES ="cliente";
    String COL_ID = "id_cliente";
    String COL_NAME = "nombre";
    String COL_SURNAME = "apellido";
    String COL_DNI = "dni";
    String COL_DATE = "fecha_nacimiento";
    String COL_EMAIL = "correo";
    String COL_PASS = "contraseña";
    String COL_ADDRESS = "direccion";


    String TAB_EMPLEADO="empleado";
    String COL_IDE = "id_empleado";


    String TAB_PRODUCTOS ="producto";
    String COL_IDPRODUCT ="id_producto";
    String COL_PRODUCTNAME ="nombre_producto";
    String COL_DESCRIPTION = "descripcion";
    String COL_STOCK = "stock";
    String COL_AVAILABILITY = "disponibilidad";
    String COL_PRICE = "precio";
    String COL_FKPRODUCT = "id_categoria";

    String TAB_PEDIDOS = "pedido";
    String COL_IDPEDIDO="id_pedido";
    String COL_DATEPEDIDO ="fecha";
    String COL_PAYMENT = "metodo_pago";
    String COL_STATUS = "estado";
    String COL_FKPEDIDO ="id_cliente";



}
