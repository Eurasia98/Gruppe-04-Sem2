package io.github.eurasia98.sem2.persistence;

import io.github.eurasia98.sem2.logic.Account;
import io.github.eurasia98.sem2.logic.Person;
import io.github.eurasia98.sem2.logic.PersonManager;

import java.sql.*;

public class DatabasePersonHandler {
    static Connection connection = null;

    // gemmer data på en person i databasen.
    public Boolean insertPerson(Person person){
        try {
            this.connection = DatabaseAccesHandler.getConnection();

            DatabaseAccountHandler databaseAccountHandler = new DatabaseAccountHandler();
            databaseAccountHandler.insertAccount(person); // gemmer data på en account i databasen
            PreparedStatement insertPersonStatement = connection.prepareStatement(
                    "INSERT INTO persons(account_id, account_username, account_password, first_name, last_name) VALUES(?,?,?,?,?)");
            insertPersonStatement.setInt(1, getId(person.getUsername()));
            insertPersonStatement.setString( 2, person.getUsername());
            insertPersonStatement.setString(3, person.getPassword());
            insertPersonStatement.setString(4, person.getFirstName());
            insertPersonStatement.setString(5, person.getLastName());
            insertPersonStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return false;
    }

    // Slet er person i databasen.
    public Boolean deletePerson(String username){
        connection = DatabaseAccesHandler.getConnection();

        try {
            PreparedStatement deletePersonStatement = connection.prepareStatement("DELETE FROM persons WHERE account_username = ?");
            deletePersonStatement.setString(1, username);
            deletePersonStatement.execute();

            //DatabaseAccountHandler databaseAccountHandler = new DatabaseAccountHandler();
            //databaseAccountHandler.resetIdCount();
            //resetIdCount();

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

    // opretter et person objekt ud fra database info på account_id.
    public Person getPerson(int account_id){
        connection = DatabaseAccesHandler.getConnection();
        try {
            PreparedStatement getPersonStatement = connection.prepareStatement("SELECT * FROM persons WHERE account_id = ?");
            getPersonStatement.setInt(1, account_id);

            ResultSet rs = getPersonStatement.executeQuery();

            PersonManager personManager = new PersonManager();

            while (rs.next()){
                Person person = personManager.createPerson(rs.getString("account_username"), rs.getString(
                        "account_password"), rs.getString("first_name"), rs.getString("last_name"));
                return person;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return null;
    }

    // Resetter personIdCounter
    /*public Boolean resetIdCount(){
        connection = DatabaseAccesHandler.getConnection();

        try {
            PreparedStatement resetIdCounterStatement = connection.prepareStatement(
                    "SELECT MAX(id) FROM persons ");
            ResultSet currentId = resetIdCounterStatement.executeQuery();
            while (currentId.next()){
                PreparedStatement restIdStatement = connection.prepareStatement("ALTER TABLE persons AUTO_INCREMENT = ?");
                restIdStatement.setInt(1, currentId.getInt(0)+1);
                restIdStatement.execute();
                return true;
            } return false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return false;
    }*/
}
