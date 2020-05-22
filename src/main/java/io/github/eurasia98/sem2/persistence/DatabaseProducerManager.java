package io.github.eurasia98.sem2.persistence;

import io.github.eurasia98.sem2.logic.Producer;

import java.sql.*;

public class DatabaseProducerManager{
    static Connection connection = null;

    public Boolean saveProducer(Producer producer){
        try {
            connection = DatabaseAccesHandler.getConnection();
            DatabaseAccountHandler databaseAccountHandler = new DatabaseAccountHandler();
            databaseAccountHandler.insertAccount(producer); // gemmer data på en account i databasen


            /*PreparedStatement getId = connection.prepareStatement(
                    "SELECT id FROM accounts WHERE username = ?");
            getId.setString(1, producer.getUsername());
            ResultSet rs = getId.executeQuery();*/

            PreparedStatement insertPersonStatement = connection.prepareStatement(
                    "INSERT INTO producers(first_name, last_name, company_name, account_id) VALUES(?,?,?,?)");
            insertPersonStatement.setString(1, producer.getFName());
            insertPersonStatement.setString(2, producer.getLName());
            insertPersonStatement.setString(3, producer.getProductionCompanyName());
            insertPersonStatement.setInt(4, getId(producer.getUsername()));
            insertPersonStatement.execute();

            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return false;

    }
    public int getId(String username){
        try {
            connection = DatabaseAccesHandler.getConnection();
            PreparedStatement searchIdStatement = connection.prepareStatement("SELECT id FROM accounts WHERE username = ?");
            searchIdStatement.setString(1, username);
            ResultSet rs = searchIdStatement.executeQuery();
            if (rs.next()){
                return rs.getInt(1);
            } else return 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return 0;
    }
}
