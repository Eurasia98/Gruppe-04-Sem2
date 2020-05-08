package io.github.eurasia98.sem2.persistence;

import io.github.eurasia98.sem2.logic.Producer;

import java.sql.*;

public class DatabaseProducerManager{
    static Connection connection = null;

    // laver forbindelse til database.
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

    public Boolean saveProducer(Producer producer){
        try {
            connection = getConnection();

            PreparedStatement insertAccountStatement = connection.prepareStatement(
                    "INSERT INTO accounts(username, password, accounttype) VALUES(?,?,?)");
            insertAccountStatement.setString(1, producer.getUsername());
            insertAccountStatement.setString(2, producer.getPassword());
            insertAccountStatement.setString(3, producer.getAccountType());

            PreparedStatement getId = connection.prepareStatement(
                    "SELECT id FROM accounts WHERE username = ?");
            getId.setString(1, producer.getUsername());
            ResultSet rs = getId.executeQuery();

            PreparedStatement insertPersonStatement = connection.prepareStatement(
                    "INSERT INTO producers(first_name, last_name, email, account_id) VALUES(?,?,?,?)");
            insertPersonStatement.setString(1, producer.getFName());
            insertPersonStatement.setString( 2, producer.getLName());
            insertPersonStatement.setString(3, producer.getEmail());
            insertPersonStatement.setInt(4, rs.getInt(0));

            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return false;

    }
}
