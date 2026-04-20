package org.example.quantumapp.dao;

import org.example.quantumapp.database.DBConnection;
import org.example.quantumapp.database.SchemDB;

import org.example.quantumapp.model.Empleado;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {

    private Connection connection;



    public EmpleadoDAO() throws SQLException {
        connection = DBConnection.getConnection();
    }

    public int insertarUsuario(Empleado empleado) throws SQLException {
        String query =String.format("INSERT INTO %s (%s, %s, %s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?, ?, ?)", SchemDB.TAB_EMPLEADO, SchemDB.COL_NAME,SchemDB.COL_SURNAME,SchemDB.COL_DNI,SchemDB.COL_DATE, SchemDB.COL_EMAIL,SchemDB.COL_PASS,SchemDB.COL_ADDRESS);
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1,empleado.getNombre());
            preparedStatement.setString(2,empleado.getApellido());
            preparedStatement.setString(3,empleado.getDni());
            preparedStatement.setDate(4,empleado.getFechaNacimiento());
            preparedStatement.setString(5,empleado.getCorreo());
            preparedStatement.setString(6,empleado.getPass());
            preparedStatement.setString(7,empleado.getDireccion());

            int rows =preparedStatement.executeUpdate();
            return rows;
        }


    }

    public int actualizarEmpleado(Empleado empleado) throws SQLException{
        String query = String.format("UPDATE %s SET %s = ?, %s = ?, %s = ? , %s = ? , %s = ?, %s = ? WHERE %s = ? ;",SchemDB.TAB_EMPLEADO,SchemDB.COL_NAME,SchemDB.COL_SURNAME,SchemDB.COL_DATE,SchemDB.COL_EMAIL,SchemDB.COL_PASS, SchemDB.COL_ADDRESS,SchemDB.COL_DNI );
     try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
         preparedStatement.setString(1, empleado.getNombre());
         preparedStatement.setString(2,empleado.getApellido());
         preparedStatement.setDate(3,empleado.getFechaNacimiento());
         preparedStatement.setString(4,empleado.getCorreo());
         preparedStatement.setString(5,empleado.getPass());
         preparedStatement.setString(6,empleado.getDireccion());
         preparedStatement.setString(7,empleado.getDni());
         int numeroFilasCambiadas = preparedStatement.executeUpdate();
         return numeroFilasCambiadas;
     }


    }

    public List<Empleado> obtenerTodosEmpleaado() throws  SQLException{
        List<Empleado> infoEmpleados = new ArrayList<>();
        String query = "SELECT * FROM "+SchemDB.TAB_EMPLEADO;
        try(PreparedStatement  preparedStatement=connection.prepareStatement(query) ) {
            try(ResultSet resultSet = preparedStatement.executeQuery()){

                while (resultSet.next()){
                    String nombre = resultSet.getString(SchemDB.COL_NAME);
                    String apellido = resultSet.getString(SchemDB.COL_SURNAME);
                    String dni = resultSet.getString(SchemDB.COL_DNI);
                    Date fechaNacimiento = resultSet.getDate(SchemDB.COL_DATE);
                    String correo = resultSet.getString(SchemDB.COL_EMAIL);
                    String pass = resultSet.getString(SchemDB.COL_PASS);
                    String direccion = resultSet.getString(SchemDB.COL_ADDRESS);
                    Empleado empleado = new Empleado(nombre,apellido,direccion,dni,correo,pass,fechaNacimiento);
                    infoEmpleados.add(empleado);

                }
                return infoEmpleados;
            }

        }


    }

    public Empleado buscarEmpleado(String dni) throws SQLException{
        Empleado empleado = null;
        String query = String.format("SELECT * FROM %s WHERE %s = ?",SchemDB.TAB_EMPLEADO,SchemDB.COL_DNI);
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1,dni);
            try(ResultSet resultSet = preparedStatement.executeQuery()){

                if (resultSet.next()){

                    String nombre = resultSet.getString(SchemDB.COL_NAME);
                    String apellido = resultSet.getString(SchemDB.COL_SURNAME);
                    String DNI = resultSet.getString(SchemDB.COL_DNI);
                    Date fechaNacimiento = resultSet.getDate(SchemDB.COL_DATE);
                    String correo = resultSet.getString(SchemDB.COL_EMAIL);
                    String pass = resultSet.getString(SchemDB.COL_PASS);
                    String direccion = resultSet.getString(SchemDB.COL_ADDRESS);
                    empleado = new Empleado(nombre,apellido,direccion,dni,correo,pass,fechaNacimiento);
                }
                return empleado;

            }
        }



    }

    public int eliminarEmpleado(String dni) throws SQLException{
        String query = String.format("DELETE FROM %s WHERE %s = ?",SchemDB.TAB_EMPLEADO,SchemDB.COL_DNI);
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1,dni);
            int filasAfectadas = preparedStatement.executeUpdate();
            return filasAfectadas;
        }


    }

}
