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

    public void insertBackupMovies(String oldProductionId){
        connection = DatabaseAccesHandler.getConnection();

        try {
            PreparedStatement backupMoviesStatement = connection.prepareStatement(
                    "SELECT * FROM movies WHERE production_id = ?");
            backupMoviesStatement.setString(1, oldProductionId);

            ResultSet backupMovieResultset = backupMoviesStatement.executeQuery();

            ArrayList<String> movieInfo = new ArrayList<>();

            while (backupMovieResultset.next()){
                movieInfo.add(Integer.toString(backupMovieResultset.getInt(1)));
                movieInfo.add(backupMovieResultset.getString(2));
                movieInfo.add(backupMovieResultset.getString(3));
            }

            PreparedStatement insertBackupMovies = connection.prepareStatement(
                    "INSERT INTO backup_movies(movie_id, production_id, movie_title)" +
                            "VALUES(?,?,?)");

            insertBackupMovies.setInt(1, Integer.parseInt(movieInfo.get(0)));
            insertBackupMovies.setString(2, movieInfo.get(1));
            insertBackupMovies.setString(3, movieInfo.get(2));

            insertBackupMovies.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Boolean checkBackupMovies(String oldProductionId){
        connection = DatabaseAccesHandler.getConnection();

        try {
            PreparedStatement checkUpdateStatement = connection.prepareStatement(
                    "SELECT * FROM backup_movies WHERE production_id = ?");
            checkUpdateStatement.setString(1, oldProductionId);

            ResultSet checkUpdateResultset = checkUpdateStatement.executeQuery();

            if (checkUpdateResultset.next()){
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public Boolean checkifMovieIsDeleted(String oldProductionId){
        connection = DatabaseAccesHandler.getConnection();

        try {
            PreparedStatement checkMoviesStatement = connection.prepareStatement(
                    "SELECT * FROM movies WHERE production_id = ?");
            checkMoviesStatement.setString(1, oldProductionId);

            ResultSet checkMoviesResultset = checkMoviesStatement.executeQuery();

            if (checkMoviesResultset.next()){
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return true;
    }

    public ArrayList<String> getBackupMovieInfo(String oldProductionId, String newProductionId){
        connection = DatabaseAccesHandler.getConnection();
        ArrayList<String> movieInfo = new ArrayList<>();

        try {
            PreparedStatement getBackUpMovieInfoStatement = connection.prepareStatement(
                    "SELECT * FROM backup_movies WHERE production_id = ?");
            getBackUpMovieInfoStatement.setString(1, oldProductionId);

            ResultSet movieInfoResultSet = getBackUpMovieInfoStatement.executeQuery();

            while (movieInfoResultSet.next()){
                movieInfo.add(movieInfoResultSet.getString(1));
                movieInfo.add(newProductionId);
                movieInfo.add(movieInfoResultSet.getString(3));
            }

            return movieInfo;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return null;
    }

    public void insertBackupMovieToMovie(ArrayList<String> movieInfo){
        connection = DatabaseAccesHandler.getConnection();

        try {
            PreparedStatement insertBackupMovieStatement = connection.prepareStatement(
                    "INSERT INTO movies(movie_id, production_id, movie_title) VALUES" +
                            "(?,?,?)");
            insertBackupMovieStatement.setInt(1, Integer.parseInt(movieInfo.get(0)));
            insertBackupMovieStatement.setString(2, movieInfo.get(1));
            insertBackupMovieStatement.setString(3, movieInfo.get(2));

            insertBackupMovieStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
