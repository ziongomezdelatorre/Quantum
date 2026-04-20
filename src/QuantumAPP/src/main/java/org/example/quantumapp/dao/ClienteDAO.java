package org.example.quantumapp.dao;

import org.example.quantumapp.database.DBConnection;
import org.example.quantumapp.database.SchemDB;
import org.example.quantumapp.model.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClienteDAO {

    private Connection connection;


    public ClienteDAO() throws SQLException {
        connection = DBConnection.getConnection();
    }

    public int insertarUsuario(Cliente cliente) throws SQLException {
        String query =String.format("INSERT INTO %s (%s, %s, %s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?, ?, ?)",SchemDB.TAB_CLIENTES, SchemDB.COL_NAME,SchemDB.COL_SURNAME,SchemDB.COL_DNI,SchemDB.COL_DATE, SchemDB.COL_EMAIL,SchemDB.COL_PASS,SchemDB.COL_ADDRESS);
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1,cliente.getNombre());
            preparedStatement.setString(2,cliente.getApellido());
            preparedStatement.setString(3,cliente.getDni());
            preparedStatement.setDate(4,cliente.getFechaNacimiento());
            preparedStatement.setString(5,cliente.getCorreo());
            preparedStatement.setString(6,cliente.getPass());
            preparedStatement.setString(7,cliente.getDireccion());

            int filasAfectadas =preparedStatement.executeUpdate();
            return filasAfectadas;
        }



    }

    public int actualizarCliente(Cliente cliente) throws SQLException{
        String query = String.format("UPDATE %s SET %s = ?, %s = ?, %s = ? , %s = ? , %s = ?, %s = ? WHERE %s = ? ;",SchemDB.TAB_CLIENTES,SchemDB.COL_NAME,SchemDB.COL_SURNAME,SchemDB.COL_DATE,SchemDB.COL_EMAIL,SchemDB.COL_PASS, SchemDB.COL_ADDRESS,SchemDB.COL_DNI );
                try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, cliente.getNombre());
                    preparedStatement.setString(2,cliente.getApellido());
                    preparedStatement.setDate(3,cliente.getFechaNacimiento());
                    preparedStatement.setString(4,cliente.getCorreo());
                    preparedStatement.setString(5,cliente.getPass());
                    preparedStatement.setString(6,cliente.getDireccion());
                    preparedStatement.setString(7,cliente.getDni());
                    int numeroFilasCambiadas = preparedStatement.executeUpdate();
                    return numeroFilasCambiadas;
                }


    }

    public List<Cliente> obtenerTodosClientes() throws  SQLException{
        List<Cliente> infoClientes = new ArrayList<>();
        String query = "SELECT * FROM "+SchemDB.TAB_CLIENTES;
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()){
                    String nombre = resultSet.getString(SchemDB.COL_NAME);
                    String apellido = resultSet.getString(SchemDB.COL_SURNAME);
                    String dni = resultSet.getString(SchemDB.COL_DNI);
                    Date fechaNacimiento = resultSet.getDate(SchemDB.COL_DATE);
                    String correo = resultSet.getString(SchemDB.COL_EMAIL);
                    String pass = resultSet.getString(SchemDB.COL_PASS);
                    String direccion = resultSet.getString(SchemDB.COL_ADDRESS);
                    Cliente cliente = new Cliente(nombre,apellido,direccion,dni,correo,pass,fechaNacimiento);
                    infoClientes.add(cliente);

                }
                return infoClientes;
            }

        }



    }

    public Cliente buscarCliente(String dni) throws SQLException{
        Cliente cliente = null;
       String query = String.format("SELECT * FROM %s WHERE %s = ?",SchemDB.TAB_CLIENTES,SchemDB.COL_DNI);
       try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
           preparedStatement.setString(1,dni);
           try(ResultSet resultSet = preparedStatement.executeQuery()) {
               if (resultSet.next()){

                   String nombre = resultSet.getString(SchemDB.COL_NAME);
                   String apellido = resultSet.getString(SchemDB.COL_SURNAME);
                   String DNI = resultSet.getString(SchemDB.COL_DNI);
                   Date fechaNacimiento = resultSet.getDate(SchemDB.COL_DATE);
                   String correo = resultSet.getString(SchemDB.COL_EMAIL);
                   String pass = resultSet.getString(SchemDB.COL_PASS);
                   String direccion = resultSet.getString(SchemDB.COL_ADDRESS);
                   cliente = new Cliente(nombre,apellido,direccion,dni,correo,pass,fechaNacimiento);
               }
               return cliente;
           }
           }
       }






    public int eliminarCliente(String dni) throws SQLException{
        String query = String.format("DELETE FROM %s WHERE %s = ?",SchemDB.TAB_CLIENTES,SchemDB.COL_DNI);
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1,dni);
            int filasAfectadas = preparedStatement.executeUpdate();
            return filasAfectadas;

        }


    }



}
