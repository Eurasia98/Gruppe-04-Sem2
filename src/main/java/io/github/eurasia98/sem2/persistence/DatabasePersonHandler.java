package io.github.eurasia98.sem2.persistence;

import io.github.eurasia98.sem2.logic.Account;
import io.github.eurasia98.sem2.logic.Person;

import java.sql.*;

public class DatabasePersonHandler {
    static Connection connection = null;

    public Boolean insertPerson(Person person){
        try {
            this.connection = DatabaseAccesHandler.getConnection();

            DatabaseAccountHandler databaseAccountHandler = new DatabaseAccountHandler();
            databaseAccountHandler.insertAccount(person);
            PreparedStatement insertPersonStatement = connection.prepareStatement(
                    "INSERT INTO persons(first_name, last_name, email, account_id) VALUES(?,?,?,?)");
            insertPersonStatement.setString(1, person.getFirstName());
            insertPersonStatement.setString( 2, person.getLastName());
            insertPersonStatement.setString(3, person.getEmail());
            insertPersonStatement.setInt(4, getId(person.getUsername()));
            insertPersonStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return false;
    }

    public int getId(String username){
        try {
            this.connection = DatabaseAccesHandler.getConnection();
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
