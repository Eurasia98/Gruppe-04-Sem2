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

    public void insertAccount(ArrayList<String> accountInfo){
        try {
            this.connection = DatabaseAccesHandler.getConnection();

            PreparedStatement insertAccountStatement = connection.prepareStatement(
                    "INSERT INTO accounts(username, password, account_type) VALUES(?,?,?)");
            insertAccountStatement.setString(1, accountInfo.get(0));
            insertAccountStatement.setString(2, accountInfo.get(1));
            insertAccountStatement.setString(3, accountInfo.get(5));
            insertAccountStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Boolean insertSpecialAccount(ArrayList<String> accountInfo){
        try {
            this.connection = DatabaseAccesHandler.getConnection();

            PreparedStatement insertAccountStatement = connection.prepareStatement(
                    "INSERT INTO accounts(username, password, account_type) VALUES(?,?,?)");
            insertAccountStatement.setString(1, accountInfo.get(0));
            insertAccountStatement.setString(2, accountInfo.get(1));
            insertAccountStatement.setString(3, accountInfo.get(2));
            insertAccountStatement.execute();
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    /*public Boolean insertAccount(Account account){
        try {
            this.connection = DatabaseAccesHandler.getConnection();

            PreparedStatement insertAccountStatement = connection.prepareStatement(
                    "INSERT INTO accounts(username, password, account_type) VALUES(?,?,?)");
            insertAccountStatement.setString(1, account.getUsername());
            insertAccountStatement.setString(2, account.getPassword());
            insertAccountStatement.setString(3, account.getAccountType());

            return insertAccountStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return false;
    }*/

    // resetter idCounter
    /*public Boolean resetIdCount(){
        connection = DatabaseAccesHandler.getConnection();

        try {
            PreparedStatement resetIdCounterStatement = connection.prepareStatement(
                    "SELECT MAX(id) FROM accounts ");
            ResultSet currentId = resetIdCounterStatement.executeQuery();
            while (currentId.next()){
                PreparedStatement restIdStatement = connection.prepareStatement("ALTER TABLE accounts AUTO_INCREMENT = ?");
                restIdStatement.setInt(1, currentId.getInt(0)+1);
                restIdStatement.execute();
                return true;
            } return false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return false;
    }*/

    public List<String> verifyLogin (String username, String password){
        this.connection = DatabaseAccesHandler.getConnection();

        List<String> accountInfo = new ArrayList<>();

        try {
            PreparedStatement verifyLoginStatement = connection.prepareStatement(
                    "SELECT * FROM accounts WHERE username = ? AND password = ?");
            verifyLoginStatement.setString(1, username);
            verifyLoginStatement.setString(2, password);
            ResultSet rs = verifyLoginStatement.executeQuery();

//            while(rs.next()){
//                accountInfo.add(rs.getString(1));
//                accountInfo.add(rs.getString(2));
//                accountInfo.add(rs.getString(3));
//                accountInfo.add(rs.getString(4));
//            }

            // har ændret nedenstående til ovenstående i et forsøg på at løse en login ting.
            if (!rs.next()){
                return Collections.emptyList();
            }
            accountInfo = Arrays.asList(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));

            return accountInfo;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Collections.emptyList();

    }

    public ArrayList<String> getAccount(int account_id){
        connection = DatabaseAccesHandler.getConnection();
        ArrayList<String> accountInfo = new ArrayList<>();

        try {
            PreparedStatement getAccountStatement = connection.prepareStatement(
                    "SELECT * FROM accounts WHERE id = ?");
            getAccountStatement.setInt(1, account_id);

            ResultSet rs = getAccountStatement.executeQuery();

            while (rs.next()){
                accountInfo.add(rs.getString(1));
                accountInfo.add(rs.getString(2));
                accountInfo.add(rs.getString(3));
                accountInfo.add(rs.getString(4));
                accountInfo.add(rs.getString(5));
            }

            return accountInfo;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return null;
    }

    public ArrayList<String> getAccount(String username){
        connection = DatabaseAccesHandler.getConnection();
        ArrayList<String> accountInfo = new ArrayList<>();

        try {
            PreparedStatement getAccountStatement = connection.prepareStatement(
                    "SELECT * FROM accounts WHERE username = ?");
            getAccountStatement.setString(1, username);

            ResultSet rs = getAccountStatement.executeQuery();

            while (rs.next()){
                accountInfo.add(rs.getString(1));
                accountInfo.add(rs.getString(2));
                accountInfo.add(rs.getString(3));
                accountInfo.add(rs.getString(4));
                accountInfo.add(rs.getString(5));
            }

            return accountInfo;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return null;
    }

    public boolean editAccountPassword(String username, String oldPassword, String newPassword){
        connection = DatabaseAccesHandler.getConnection();

        try {
            PreparedStatement editAccountPasswordStatement = connection.prepareStatement(
                    "UPDATE accounts SET password = ? WHERE username = ? AND password = ?");
            editAccountPasswordStatement.setString(1, newPassword);
            editAccountPasswordStatement.setString(2, username);
            editAccountPasswordStatement.setString(3, oldPassword);

            editAccountPasswordStatement.execute();

            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean editAccountEmail(String username, String email){
        connection = DatabaseAccesHandler.getConnection();

        try {
            PreparedStatement editAccountEmailStatement = connection.prepareStatement(
                    "UPDATE accounts SET email = ? WHERE username = ?");
            editAccountEmailStatement.setString(1, email);
            editAccountEmailStatement.setString(2, username);

            editAccountEmailStatement.execute();

            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
