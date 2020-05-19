package io.github.eurasia98.sem2.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseAccessHandler {
    static Connection connection = null;

    public static Connection getConnection(){
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/Krediteringssystem",
                    "postgres",
                    "kebabonwheels");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }
}
