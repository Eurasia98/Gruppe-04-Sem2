package io.github.eurasia98.sem2.persistence;

import io.github.eurasia98.sem2.logic.Account;

import java.sql.*;
import java.util.*;

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

    public List<String> verifyLogin (String username, String password){
        this.connection = DatabaseAccesHandler.getConnection();

        List<String> accountInfo = new ArrayList<>();

        try {
            PreparedStatement verifyLoginStatement = connection.prepareStatement(
                    "SELECT * FROM accounts WHERE username = ? AND password = ?");
            verifyLoginStatement.setString(1, username);
            verifyLoginStatement.setString(2, password);
            ResultSet rs = verifyLoginStatement.executeQuery();

            if (!rs.next()){
//                accountInfo.add("Wrong username / password.");
//                return accountInfo;
                return Collections.emptyList();
            }
            accountInfo = Arrays.asList(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
            System.out.println(accountInfo);
            return accountInfo;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;

    }
}
