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
        String query = String.format("INSERT INTO %s (%s, %s, %s, %s) VALUES (?, ?, ?, ?)",
                SchemDB.TAB_PEDIDOS, SchemDB.COL_FKPEDIDO, SchemDB.COL_DATEPEDIDO,
                SchemDB.COL_PAYMENT, SchemDB.COL_STATUS);
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, pedido.getIdCliente());
            preparedStatement.setTimestamp(2, pedido.getFecha());
            preparedStatement.setString(3, pedido.getMetodoPago());
            preparedStatement.setString(4, pedido.getEstado());
                    int filasAfectadas =preparedStatement.executeUpdate();
                    return filasAfectadas;
        }
    }

    public int actualizarPedido(Pedido pedido) throws SQLException {
        String query = String.format("UPDATE %s SET %s = ?, %s = ?, %s = ?, %s = ? WHERE %s = ?",
                SchemDB.TAB_PEDIDOS, SchemDB.COL_FKPEDIDO, SchemDB.COL_DATEPEDIDO,
                SchemDB.COL_PAYMENT, SchemDB.COL_STATUS, SchemDB.COL_IDPEDIDO);
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, pedido.getIdCliente());
            preparedStatement.setTimestamp(2, pedido.getFecha());
            preparedStatement.setString(3, pedido.getMetodoPago());
            preparedStatement.setString(4, pedido.getEstado());
            preparedStatement.setInt(5, pedido.getId());
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
                    pedido = new Pedido(
                            resultSet.getInt(SchemDB.COL_IDPEDIDO),
                            resultSet.getInt(SchemDB.COL_FKPEDIDO),
                            resultSet.getTimestamp(SchemDB.COL_DATEPEDIDO),
                            resultSet.getString(SchemDB.COL_PAYMENT),
                            resultSet.getString(SchemDB.COL_STATUS)
                    );
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
                listaPedidos.add(new Pedido(
                        resultSet.getInt(SchemDB.COL_IDPEDIDO),
                        resultSet.getInt(SchemDB.COL_FKPEDIDO),
                        resultSet.getTimestamp(SchemDB.COL_DATEPEDIDO),
                        resultSet.getString(SchemDB.COL_PAYMENT),
                        resultSet.getString(SchemDB.COL_STATUS)
                ));
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
