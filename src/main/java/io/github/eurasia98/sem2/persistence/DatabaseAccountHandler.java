package io.github.eurasia98.sem2.persistence;

import java.sql.*;
import java.util.*;

public class DatabaseAccountHandler {
    static Connection connection = null;

    public Boolean checkUsernameAvailability(String username){
        try {
            this.connection = DatabaseAccessHandler.getConnection();
            PreparedStatement checkUsernameStatement = connection.prepareStatement(
                    "SELECT username FROM accounts WHERE username = ?");
            checkUsernameStatement.setString(1, username);
            ResultSet rs = checkUsernameStatement.executeQuery();
            if (rs.next() == false){
                return true;
            }
            else {
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return false;
    }

    public Boolean insertAccount(ArrayList<String> accountInfo){
        try {
            this.connection = DatabaseAccessHandler.getConnection();

            PreparedStatement insertAccountStatement = connection.prepareStatement(
                    "INSERT INTO accounts(username, password, email, account_type) VALUES(?,?,?,?)");
            insertAccountStatement.setString(1, accountInfo.get(0));
            insertAccountStatement.setString(2, accountInfo.get(1));
            insertAccountStatement.setString(3, accountInfo.get(2));
            insertAccountStatement.setString(4, accountInfo.get(3));
            insertAccountStatement.execute();

            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return false;
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
        this.connection = DatabaseAccessHandler.getConnection();

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
        connection = DatabaseAccessHandler.getConnection();
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
        connection = DatabaseAccessHandler.getConnection();
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

    public Boolean insertBackupAccounts(ArrayList<Integer> personsInCreditsAccountId) {
        connection = DatabaseAccessHandler.getConnection();

        try {
            PreparedStatement insertBackupAccountsStatement = connection.prepareStatement(
                    "INSERT INTO backup_accounts(id, username, password, account_type) " +
                            " VALUES(?,?,?,?)");

            for (int account_id : personsInCreditsAccountId){
                ArrayList<String> accountInfoArray = getAccount(account_id);
                insertBackupAccountsStatement.setInt(1, Integer.parseInt(accountInfoArray.get(0)));
                insertBackupAccountsStatement.setString(2, accountInfoArray.get(1));
                insertBackupAccountsStatement.setString(3, accountInfoArray.get(2));
                insertBackupAccountsStatement.setString(4, accountInfoArray.get(3));

                insertBackupAccountsStatement.addBatch();
            }

            insertBackupAccountsStatement.executeBatch();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return false;
    }

    public boolean checkBackupAccounts() {
        connection = DatabaseAccessHandler.getConnection();

        try {
            PreparedStatement checkBackupStatement = connection.prepareStatement(
                    "SELECT * FROM backup_accounts");
            ResultSet backupResultSet = checkBackupStatement.executeQuery();

            if (backupResultSet.next()){
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return false;
    }

    public ArrayList<String[]> getBackupAccounts(){
        connection = DatabaseAccessHandler.getConnection();
        ArrayList<String[]> accountInfo = new ArrayList<>();

        try {
            PreparedStatement getAccountsStatement = connection.prepareStatement(
                    "SELECT * FROM backup_accounts");
            ResultSet accountsResultSet = getAccountsStatement.executeQuery();

            while (accountsResultSet.next()){
                accountInfo.add(new String[]{Integer.toString(accountsResultSet.getInt(1)),
                accountsResultSet.getString(2), accountsResultSet.getString(3),
                accountsResultSet.getString(4)});
            }

            return accountInfo;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return null;

    }

    public void editInsertAccounts(ArrayList<String[]> accountsInfo) {
        connection = DatabaseAccessHandler.getConnection();

        try {
            PreparedStatement insertAccountsStatement = connection.prepareStatement(
                    "INSERT INTO accounts(id, username, password, account_type) VALUES(?,?,?,?)");

            for (String[] accountInfo : accountsInfo){
                insertAccountsStatement.setInt(1, Integer.parseInt(accountInfo[0]));
                insertAccountsStatement.setString(2, accountInfo[1]);
                insertAccountsStatement.setString(3, accountInfo[2]);
                insertAccountsStatement.setString(4, accountInfo[3]);

                insertAccountsStatement.addBatch();
            }

            insertAccountsStatement.executeBatch();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public String getAccountId(String username) {
        connection = DatabaseAccessHandler.getConnection();

        try {
            PreparedStatement getAccountIdStatement = connection.prepareStatement(
                    "SELECT id FROM accounts WHERE username = ?");
            getAccountIdStatement.setString(1, username);

            ResultSet idResultSet = getAccountIdStatement.executeQuery();

            if (idResultSet.next()){
                return Integer.toString(idResultSet.getInt(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return null;
    }

    public String getAccountUsername(String account_id) {
        ArrayList<String> usernameInfo = getAccount(account_id);
        return usernameInfo.get(1);
    }
}
