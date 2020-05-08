package io.github.eurasia98.sem2.persistence;

import io.github.eurasia98.sem2.logic.Account;

import java.sql.*;

public class DatabaseAccountHandler {
    static Connection connection = null;

    public Boolean checkUsernameAvailability(String username){
        try {
            this.connection = DatabaseAccesHandler.getConnection();
            PreparedStatement checkUsernameStatement = connection.prepareStatement(
                    "SELECT username FROM accounts WHERE username = ?");
            checkUsernameStatement.setString(1, username);
            ResultSet rs = checkUsernameStatement.executeQuery();
            if (rs.next() == false) return true;
            else return false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return false;
    }

    public Boolean insertAccount(Account account){
        try {
            this.connection = DatabaseAccesHandler.getConnection();

            PreparedStatement insertAccountStatement = connection.prepareStatement(
                    "INSERT INTO accounts(username, password, accounttype) VALUES(?,?,?)");
            insertAccountStatement.setString(1, account.getUsername());
            insertAccountStatement.setString(2, account.getPassword());
            insertAccountStatement.setString(3, account.getAccountType());

            return insertAccountStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return false;
    }
}
