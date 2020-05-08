package io.github.eurasia98.sem2.persistence;

import io.github.eurasia98.sem2.logic.Movie;

import java.sql.*;

public class DatabaseMovieHandler {
    public DatabaseMovieHandler(){}

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

    public Boolean saveMovie(Movie movie){
        try {
            this.connection = getConnection();

            PreparedStatement insertProductionStatement = connection.prepareStatement(
                    "INSERT INTO productions(title, productionId, productiontype) VALUES(?,?,?)");
            insertProductionStatement.setString(1, movie.getTitle());
            insertProductionStatement.setString(2, movie.getProductionID());
            insertProductionStatement.setString(3, movie.getProductionType());
            return insertProductionStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return false;

    }
}
