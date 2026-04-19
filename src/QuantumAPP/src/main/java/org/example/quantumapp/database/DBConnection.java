package org.example.quantumapp.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null){
        createConnection();
        }
        return connection;
    }

    public static void createConnection() throws SQLException {
        String user = "root";
        String pass = "";
        String database = "quantum";
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/"+database,user,pass );

    }
}
