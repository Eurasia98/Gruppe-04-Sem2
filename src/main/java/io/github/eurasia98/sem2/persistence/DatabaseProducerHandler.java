package io.github.eurasia98.sem2.persistence;

import io.github.eurasia98.sem2.logic.Producer;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseProducerHandler {
    static Connection connection = null;

    public Boolean saveProducer(Producer producer){
        try {
            connection = DatabaseAccessHandler.getConnection();

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

    public ArrayList<String> getProducer(int account_id){
        connection = DatabaseAccessHandler.getConnection();
        ArrayList<String> producerInfo = new ArrayList<>();

        try {
            PreparedStatement getProducerStatement = connection.prepareStatement(
                    "SELECT * FROM producers WHERE account_id = ?");
            getProducerStatement.setInt(1, account_id);

            ResultSet rs = getProducerStatement.executeQuery();

            while (rs.next()){
                producerInfo.add(rs.getString(1));
                producerInfo.add(rs.getString(2));
                producerInfo.add(rs.getString(3));
                producerInfo.add(rs.getString(4));
                producerInfo.add(rs.getString(5));
                producerInfo.add(rs.getString(6));
            }

            return producerInfo;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return null;
    }

    public int getId(String username){
        try {
            connection = DatabaseAccessHandler.getConnection();
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

    public Boolean insertProducer(ArrayList<String> producerInfo) {
        try {
            connection = DatabaseAccessHandler.getConnection();

            PreparedStatement insertProducerStatement = connection.prepareStatement(
                    "INSERT INTO producers(first_name, last_name, company_name, account_id) VALUES(?,?,?,?)");
            insertProducerStatement.setString(1, producerInfo.get(0));
            insertProducerStatement.setString(2, producerInfo.get(1));
            insertProducerStatement.setString(3, producerInfo.get(2));
            insertProducerStatement.setInt(4, Integer.parseInt(producerInfo.get(3)));
            insertProducerStatement.execute();

            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
