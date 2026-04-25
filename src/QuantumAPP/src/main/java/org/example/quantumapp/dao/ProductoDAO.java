package org.example.quantumapp.dao;

import org.example.quantumapp.database.DBConnection;
import org.example.quantumapp.database.SchemDB;

import org.example.quantumapp.model.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    private Connection connection;


   public ProductoDAO () throws SQLException{
       connection = DBConnection.getConnection();

   }

   public int insertarProducto(Producto producto) throws SQLException{
       String query =String.format("INSERT INTO %s (%s, %s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?, ?)", SchemDB.TAB_PRODUCTOS, SchemDB.COL_PRODUCTNAME,SchemDB.COL_DESCRIPTION,SchemDB.COL_STOCK, SchemDB.COL_AVAILABILITY,SchemDB.COL_PRICE,SchemDB.COL_FKPRODUCT);
       try(PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
           preparedStatement.setString(1,producto.getNombreProducto());
           preparedStatement.setString(2,producto.getDescripcion());
           preparedStatement.setInt(3,producto.getStock());
           preparedStatement.setBoolean(4,producto.isDisponibilidad());
           preparedStatement.setDouble(5,producto.getPrecio());
           preparedStatement.setInt(6,producto.getIdCategoria());
           preparedStatement.executeUpdate();
           try(ResultSet resultSet = preparedStatement.getGeneratedKeys()){
               if(resultSet.next()){
                   return resultSet.getInt(1);
               }
           }
       }
       return -1;
   }

   public int actualizarProducto(Producto producto) throws SQLException{
       String query = String.format("UPDATE %s SET %s = ?, %s = ?, %s = ? , %s = ? , %s = ?, %s = ? WHERE %s = ? ;",SchemDB.TAB_PRODUCTOS,SchemDB.COL_PRODUCTNAME,SchemDB.COL_DESCRIPTION,SchemDB.COL_STOCK,SchemDB.COL_AVAILABILITY,SchemDB.COL_PRICE, SchemDB.COL_FKPRODUCT,SchemDB.COL_IDPRODUCT );
        try(PreparedStatement preparedStatement =connection.prepareStatement(query)) {
            preparedStatement.setString(1,producto.getNombreProducto());
            preparedStatement.setString(2,producto.getDescripcion());
            preparedStatement.setInt(3,producto.getStock());
            preparedStatement.setBoolean(4,producto.isDisponibilidad());
            preparedStatement.setDouble(5,producto.getPrecio());
            preparedStatement.setInt(6,producto.getIdCategoria());
            preparedStatement.setInt(7,producto.getId());
            int numeroFilasCambiadas = preparedStatement.executeUpdate();
            return numeroFilasCambiadas;
        }

   }


   public Producto buscarProducto(String nombre) throws SQLException{
       Producto producto = null;
       String query = String.format("SELECT * FROM %s WHERE %s = ?",SchemDB.TAB_PRODUCTOS,SchemDB.COL_PRODUCTNAME);
       try(PreparedStatement  preparedStatement = connection.prepareStatement(query) ) {

           preparedStatement.setString(1,nombre);
           try(ResultSet resultSet = preparedStatement.executeQuery()){
               if (resultSet.next()){
                   int idProducto = resultSet.getInt(SchemDB.COL_IDPRODUCT);
                   String nombreProducto = resultSet.getString(SchemDB.COL_PRODUCTNAME);
                   String descripcion = resultSet.getString(SchemDB.COL_DESCRIPTION);
                   int stock = resultSet.getInt(SchemDB.COL_STOCK);
                   boolean disponibilidad = resultSet.getBoolean(SchemDB.COL_AVAILABILITY);
                   double precio = resultSet.getDouble(SchemDB.COL_PRICE);
                   int idCategoria = resultSet.getInt(SchemDB.COL_FKPRODUCT);

                   producto = new Producto(idProducto,nombreProducto,descripcion,idCategoria,stock,disponibilidad,precio);
               }
               return producto;
           }


       }


   }

   public List<Producto> buscarTodosLosProducto() throws SQLException {
       List<Producto> listaProductos = new ArrayList<>();
       String query = "SELECT * FROM " +SchemDB.TAB_PRODUCTOS;
       try(PreparedStatement  preparedStatement=connection.prepareStatement(query) ) {
           try(ResultSet resultSet = preparedStatement.executeQuery()){

               while (resultSet.next()){
                   int idProducto = resultSet.getInt(SchemDB.COL_IDPRODUCT);
                   String nombreProducto = resultSet.getString(SchemDB.COL_PRODUCTNAME);
                   String descripcion = resultSet.getString(SchemDB.COL_DESCRIPTION);
                   int stock = resultSet.getInt(SchemDB.COL_STOCK);
                   boolean disponibilidad = resultSet.getBoolean(SchemDB.COL_AVAILABILITY);
                   double precio = resultSet.getDouble(SchemDB.COL_PRICE);
                   int idCategoria = resultSet.getInt(SchemDB.COL_FKPRODUCT);
                   // int id,String nombreProducto, String descripcion, int idCategoria, int stock, boolean disponibilidad, double precio

                   listaProductos.add(new Producto(idProducto,nombreProducto,descripcion,idCategoria,stock,disponibilidad,precio));

               }
               return listaProductos;
           }

       }

   }

   public int eliminarProducto (int idProducto) throws SQLException{
       String query = String.format("DELETE FROM %s WHERE %s = ?",SchemDB.TAB_PRODUCTOS,SchemDB.COL_IDPRODUCT);
       try(PreparedStatement  preparedStatement = connection.prepareStatement(query) ) {
           preparedStatement.setInt(1,idProducto);
           int filasAfectadas = preparedStatement.executeUpdate();
           return filasAfectadas;
       }


   }
}
