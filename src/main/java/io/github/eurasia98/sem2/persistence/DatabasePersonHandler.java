package io.github.eurasia98.sem2.persistence;

import io.github.eurasia98.sem2.logic.Person;

import java.sql.*;

public class DatabasePersonHandler {
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

    public Boolean savePerson(Person person){
        try {
            this.connection = getConnection();

            PreparedStatement insertAccountStatement = connection.prepareStatement(
                    "INSERT INTO accounts(username, password, accounttype) VALUES(?,?,?)");
            insertAccountStatement.setString(1, person.getUsername());
            insertAccountStatement.setString(2, person.getPassword());
            insertAccountStatement.setString(3, person.getAccountType());

            insertAccountStatement.execute();

            PreparedStatement getId = connection.prepareStatement("SELECT id FROM accounts WHERE username = ?");
            getId.setString(1, person.getUsername());
            ResultSet rs = getId.executeQuery();
            while (rs.next()){
                PreparedStatement insertPersonStatement = connection.prepareStatement(
                        "INSERT INTO persons(first_name, last_name, email, account_id) VALUES(?,?,?,?)");
                insertPersonStatement.setString(1, person.getFirstName());
                insertPersonStatement.setString( 2, person.getLastName());
                insertPersonStatement.setString(3, person.getEmail());
                insertPersonStatement.setInt(4, rs.getInt(1));
                insertPersonStatement.execute();
            } return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return false;
    }
}
