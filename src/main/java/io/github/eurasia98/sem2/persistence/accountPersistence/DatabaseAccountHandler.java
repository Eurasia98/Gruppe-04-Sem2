package io.github.eurasia98.sem2.persistence.accountPersistence;

import java.sql.*;

public class DatabaseAccountHandler {
    static Connection connection = null;

    private Connection getConnection(){
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

    public Boolean checkUsernameAvailability(String username){

        try {
            PreparedStatement checkUsernameStatement = connection.prepareStatement(
                    "SELECT username FROM accounts WHERE username = ?");
            checkUsernameStatement.setString(1, username);
            ResultSet rs = checkUsernameStatement.executeQuery();
            if (rs.next() == false) return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return false;
    }
}
