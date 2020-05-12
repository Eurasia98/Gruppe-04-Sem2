package io.github.eurasia98.sem2.persistence;

import io.github.eurasia98.sem2.logic.Movie;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseMovieHandler {

    static Connection connection = null;

    public void insertMovie(ArrayList<String> movieInfo){
        try {
            this.connection = DatabaseAccesHandler.getConnection();
            DatabaseProductionManager databaseProductionManager = new DatabaseProductionManager();
            databaseProductionManager.insertProduction(movieInfo);

            PreparedStatement insertPersonStatement = connection.prepareStatement(
                    "INSERT INTO movies(production_id, movie_title) VALUES(?,?)");
            insertPersonStatement.setString(1, movieInfo.get(0));
            insertPersonStatement.setString(2, movieInfo.get(1));

            insertPersonStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public String getTitle(String production_id){
        connection = DatabaseAccesHandler.getConnection();

        try {
            PreparedStatement getTitleStatement = connection.prepareStatement(
                    "SELECT movie_title FROM movies WHERE production_id = ?");
            getTitleStatement.setString(1, production_id);
            ResultSet rs = getTitleStatement.executeQuery();

            while (rs.next()){
                return rs.getString(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return null;
    }
}
