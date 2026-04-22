package org.example.quantumapp.dao;

import org.example.quantumapp.database.DBConnection;
import org.example.quantumapp.database.SchemDB;
import org.example.quantumapp.model.Pedido;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {
    private Connection connection;

    public PedidoDAO() throws SQLException {
        connection = DBConnection.getConnection();
    }

    public int insertarPedido(Pedido pedido) throws SQLException {
        String query = String.format("INSERT INTO %s (%s, %s, %s, %s,%s) VALUES (?, ?, ?, ?,?)",
                SchemDB.TAB_PEDIDOS, SchemDB.COL_DATEPEDIDO,
                SchemDB.COL_PAYMENT, SchemDB.COL_STATUS,SchemDB.COL_FKPEDIDO,SchemDB.COL_IDE);
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setTimestamp(1, pedido.getFecha());
            preparedStatement.setString(2, pedido.getMetodoPago());
            preparedStatement.setString(3, pedido.getEstado());
            preparedStatement.setInt(4, pedido.getIdCliente());
            preparedStatement.setInt(5,pedido.getIdEmpleado());
                    preparedStatement.executeUpdate();
                    try(ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                        if (resultSet.next()){
                            return resultSet.getInt(1);
                        }

                    }
        }
        return -1;
    }

    public int actualizarPedido(Pedido pedido) throws SQLException {
        String query = String.format("UPDATE %s SET %s = ?, %s = ?, %s = ?, %s = ?, %s = ? WHERE %s = ?",
                SchemDB.TAB_PEDIDOS, SchemDB.COL_FKPEDIDO,SchemDB.COL_IDE, SchemDB.COL_DATEPEDIDO,
                SchemDB.COL_PAYMENT, SchemDB.COL_STATUS, SchemDB.COL_IDPEDIDO);
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, pedido.getIdCliente());
            preparedStatement.setInt(2,pedido.getIdEmpleado());
            preparedStatement.setTimestamp(3, pedido.getFecha());
            preparedStatement.setString(4, pedido.getMetodoPago());
            preparedStatement.setString(5, pedido.getEstado());
            preparedStatement.setInt(6, pedido.getId());
             int filasAfectadas = preparedStatement.executeUpdate();
             return filasAfectadas;
        }
    }

    public Pedido buscarPedido(int idPedido) throws SQLException {
        Pedido pedido = null;
        String query = String.format("SELECT * FROM %s WHERE %s = ?",
                SchemDB.TAB_PEDIDOS, SchemDB.COL_IDPEDIDO);
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idPedido);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {

                        int fkPedido =    resultSet.getInt(SchemDB.COL_FKPEDIDO);
                         int idEmpleado = resultSet.getInt(SchemDB.COL_IDE);

                          Timestamp datePedido =   resultSet.getTimestamp(SchemDB.COL_DATEPEDIDO);
                          String payment =  resultSet.getString(SchemDB.COL_PAYMENT);
                           String estado = resultSet.getString(SchemDB.COL_STATUS);

                    pedido = new Pedido(idPedido,idEmpleado,fkPedido,datePedido,payment,estado);
                }
            }
        }
        return pedido;
    }

    public List<Pedido> buscarTodosLosPedidos() throws SQLException {
        List<Pedido> listaPedidos = new ArrayList<>();
        String query = "SELECT * FROM " + SchemDB.TAB_PEDIDOS;
        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet resultSet = ps.executeQuery()) {
            while (resultSet.next()) {


                      int idPedido =  resultSet.getInt(SchemDB.COL_IDPEDIDO);
                int idEmpleado = resultSet.getInt(SchemDB.COL_IDE);
                    int fkPedido =      resultSet.getInt(SchemDB.COL_FKPEDIDO);
                      Timestamp datePedido =   resultSet.getTimestamp(SchemDB.COL_DATEPEDIDO);
                     String payment =   resultSet.getString(SchemDB.COL_PAYMENT);
                      String estado =  resultSet.getString(SchemDB.COL_STATUS);


                listaPedidos.add(new Pedido(idPedido, idEmpleado,fkPedido,datePedido,payment,estado));
            }
        }
        return listaPedidos;
    }

    public int eliminarPedido(int idPedido) throws SQLException {
        String query = String.format("DELETE FROM %s WHERE %s = ?",
                SchemDB.TAB_PEDIDOS, SchemDB.COL_IDPEDIDO);
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idPedido);
            int filasAfectadas = preparedStatement.executeUpdate();
            return filasAfectadas;
        }
    }
}
