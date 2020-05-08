package io.github.eurasia98.sem2.persistence;

import io.github.eurasia98.sem2.logic.Movie;

import java.sql.*;

public class DatabaseMovieHandler {
    public DatabaseMovieHandler(){}

    static Connection connection = null;

    public Boolean insertMovie(Movie movie){
        try {
            this.connection = DatabaseAccesHandler.getConnection();
            DatabaseProductionManager databaseProductionManager = new DatabaseProductionManager();
            databaseProductionManager.insertProduction(movie);

            PreparedStatement insertPersonStatement = connection.prepareStatement(
                    "INSERT INTO movies(production_id, movie_title) VALUES(?,?)");
            insertPersonStatement.setString(1, movie.getProductionID());
            insertPersonStatement.setString(2, movie.getTitle());

            insertPersonStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return false;
    }
}
