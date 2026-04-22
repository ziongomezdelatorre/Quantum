package org.example.quantumapp.dao;

import org.example.quantumapp.database.DBConnection;
import org.example.quantumapp.database.SchemDB;
import org.example.quantumapp.model.DetallePedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DetallesPedidoDAO {
    private Connection connection;

    public DetallesPedidoDAO() throws SQLException{
        connection= DBConnection.getConnection();
    }


    public int insertarDetallePedido(DetallePedido detallePedido) throws SQLException {
        String query = String.format("INSERT INTO %s (%s, %s, %s, %s ) VALUES (?,?,?,?)", SchemDB.TAB_DETALLESPEDIDOS,SchemDB.COL_IDPRODUCT,SchemDB.COL_IDPEDIDO,SchemDB.COL_UNITPRICE, SchemDB.COL_QUANTITY);
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
                preparedStatement.setInt(1,detallePedido.getIdProducto());
                preparedStatement.setInt(2,detallePedido.getIdPedido());
                preparedStatement.setDouble(3,detallePedido.getPrecioUnitario());
                preparedStatement.setInt(4,detallePedido.getCantidad());

                int filasAfectadas =  preparedStatement.executeUpdate();
                return filasAfectadas;


        }


    }

    public int actualizarDetallePedido(DetallePedido detallePedido) throws SQLException{
        String query = String.format("UPDATE %s SET %s = ?, %s = ? WHERE %s = ? AND %s = ?",
                SchemDB.TAB_DETALLESPEDIDOS, SchemDB.COL_QUANTITY, SchemDB.COL_UNITPRICE,
                SchemDB.COL_IDPRODUCT, SchemDB.COL_IDPEDIDO);
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1,detallePedido.getCantidad());
            preparedStatement.setDouble(2, detallePedido.getPrecioUnitario());
            preparedStatement.setInt(3, detallePedido.getIdProducto());
            preparedStatement.setInt(4, detallePedido.getIdPedido());

            int filasAfectadas = preparedStatement.executeUpdate();
            return filasAfectadas;
    }
}
    public DetallePedido buscarDetallePedido(int idProducto, int idPedido)  throws SQLException {
        DetallePedido detallePedido = null;
        String query = String.format("SELECT * FROM %s WHERE %s = ? AND %s = ?",SchemDB.TAB_DETALLESPEDIDOS,
                SchemDB.COL_IDPRODUCT,SchemDB.COL_IDPEDIDO) ;
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
               preparedStatement.setInt(1,idProducto);
               preparedStatement.setInt(2,idPedido);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()){
                        double precioUnitario = resultSet.getDouble(SchemDB.COL_UNITPRICE);
                        int cantidad =   resultSet.getInt(SchemDB.COL_QUANTITY);
                        detallePedido = new DetallePedido(idProducto,idPedido,cantidad,precioUnitario);


                    }




            }
            return detallePedido;

        }

    }


    public List<DetallePedido> buscarTodosLosDetalles() throws SQLException{
        List<DetallePedido> listaDetalles = new ArrayList<>();
        String query = String.format("SELECT * FROM %s",SchemDB.TAB_DETALLESPEDIDOS);
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
               try (ResultSet resultSet = preparedStatement.executeQuery()){
                   while (resultSet.next()){
                       int idProducto = resultSet.getInt(SchemDB.COL_IDPRODUCT);
                       int idPedido = resultSet.getInt(SchemDB.COL_IDPEDIDO);
                       double precioUnitario = resultSet.getDouble(SchemDB.COL_UNITPRICE);
                       int cantidad = resultSet.getInt(SchemDB.COL_QUANTITY);
                       listaDetalles.add(new DetallePedido(idProducto,idPedido,cantidad,precioUnitario));
                   }
               }

        }
        return listaDetalles;

    }


    public int eliminarDetallesPorPedido(int idPedido) throws  SQLException{
        String query = String.format("DELETE FROM %s WHERE %s = ?",
                SchemDB.TAB_DETALLESPEDIDOS, SchemDB.COL_IDPEDIDO);
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idPedido);
            int filasAfectadas = preparedStatement.executeUpdate();
            return filasAfectadas;
        }
    }

    public int eliminarDetallesPorProducto(int idProducto) throws  SQLException{
        String query = String.format("DELETE FROM %s WHERE %s = ?",
                SchemDB.TAB_DETALLESPEDIDOS, SchemDB.COL_IDPRODUCT);
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idProducto);
            int filasAfectadas = preparedStatement.executeUpdate();
            return filasAfectadas;
        }
    }


    public int eliminarDetalles(int idPedido, int idProducto) throws SQLException{
        String query  = String.format("DELETE FROM %s WHERE %s = ? AND %s = ?",SchemDB.TAB_DETALLESPEDIDOS,
                SchemDB.COL_IDPRODUCT,SchemDB.COL_IDPEDIDO);

        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1,idProducto);
            preparedStatement.setInt(2,idPedido);

            int filasAfectadas = preparedStatement.executeUpdate();
            return filasAfectadas;
        }

    }

}

