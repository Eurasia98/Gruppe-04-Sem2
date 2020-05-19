package io.github.eurasia98.sem2.persistence;

import javafx.fxml.Initializable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseTvSeriesHandler {
    static Connection connection = null;

    public Boolean insertTvSeries(ArrayList<String> tvSeriesInfo){
        try {
            this.connection = DatabaseAccessHandler.getConnection();
            DatabaseProductionManager databaseProductionManager = new DatabaseProductionManager();
            databaseProductionManager.insertProduction(tvSeriesInfo);

            PreparedStatement insertTvSeriesStatement = connection.prepareStatement(
                    "INSERT INTO tv_series(production_id, title, series_id, description) VALUES(?,?, ?, ?)");
            insertTvSeriesStatement.setString(1, tvSeriesInfo.get(0));
            insertTvSeriesStatement.setString(2, tvSeriesInfo.get(1));
            insertTvSeriesStatement.setString(3, tvSeriesInfo.get(4));
            insertTvSeriesStatement.setString(4, tvSeriesInfo.get(5));

            insertTvSeriesStatement.execute();

            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return false;
    }

    public String getTitle(String production_id){
        connection = DatabaseAccessHandler.getConnection();

        try {
            PreparedStatement getTitleStatement = connection.prepareStatement(
                    "SELECT title FROM tv_series WHERE production_id = ?");
            getTitleStatement.setString(1, production_id);
            ResultSet rs = getTitleStatement.executeQuery();

            while (rs.next()){
                return rs.getString(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return null;
    }

    public ArrayList<String[]> getSeriesInfo(String productionId) {
        connection = DatabaseAccessHandler.getConnection();
        ArrayList<String[]> seasonToEditList = new ArrayList<>();

        try {
            PreparedStatement getSeasonStatement = connection.prepareStatement(
                    "SELECT season_id, season_number FROM seasons WHERE production_id = ?");
            getSeasonStatement.setString(1, productionId);
            ResultSet getPersonsResults = getSeasonStatement.executeQuery();

            while (getPersonsResults.next()){
                seasonToEditList.add(new String[]{getPersonsResults.getString(1),
                Integer.toString(getPersonsResults.getInt(2))});
            }
            return seasonToEditList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return null;
    }

    public String getSeriesId(String production_id) {
        connection = DatabaseAccessHandler.getConnection();
        try {
            PreparedStatement getIdStatement = connection.prepareStatement(
                    "SELECT series_id FROM tv_series WHERE production_id = ?");
            getIdStatement.setString(1, production_id);
            ResultSet idResultSet = getIdStatement.executeQuery();

            while (idResultSet.next()){
                return idResultSet.getString(1);
            }
            return null;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return null;
    }

    public Boolean insertBackupSeries(String oldProductionId) {
        connection = DatabaseAccessHandler.getConnection();

        try {
            PreparedStatement backupSeriesStatement = connection.prepareStatement(
                    "SELECT * FROM tv_series WHERE production_id = ?");
            backupSeriesStatement.setString(1, oldProductionId);

            ResultSet backupSeriesResultset = backupSeriesStatement.executeQuery();

            ArrayList<String> seriesInfo = new ArrayList<>();

            while (backupSeriesResultset.next()){
                seriesInfo.add(Integer.toString(backupSeriesResultset.getInt(1)));
                seriesInfo.add(backupSeriesResultset.getString(2));
                seriesInfo.add(backupSeriesResultset.getString(3));
                seriesInfo.add(backupSeriesResultset.getString(4));
                seriesInfo.add(backupSeriesResultset.getString(5));

            }

            PreparedStatement insertBackupSeries = connection.prepareStatement(
                    "INSERT INTO backup_series(id, production_id, title, series_id, description) VALUES (?,?,?,?,?)");

            insertBackupSeries.setInt(1, Integer.parseInt(seriesInfo.get(0)));
            insertBackupSeries.setString(2, seriesInfo.get(1));
            insertBackupSeries.setString(3, seriesInfo.get(2));
            insertBackupSeries.setString(4, seriesInfo.get(3));
            insertBackupSeries.setString(5, seriesInfo.get(4));

            insertBackupSeries.execute();

            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return false;
    }

    public boolean checkBackupSeries() {
        connection = DatabaseAccessHandler.getConnection();

        try {
            PreparedStatement checkUpdateStatement = connection.prepareStatement(
                    "SELECT * FROM backup_series");

            ResultSet checkUpdateResultset = checkUpdateStatement.executeQuery();

            while (checkUpdateResultset.next()){
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public ArrayList<String> getBackupSeriesInfo(String newProductionId, String oldProductionId) {

        connection = DatabaseAccessHandler.getConnection();
        ArrayList<String> seriesInfo = new ArrayList<>();

        try {
            PreparedStatement getSeriesStatement = connection.prepareStatement(
                    "SELECT * FROM backup_series WHERE production_id = ?");
            getSeriesStatement.setString(1, oldProductionId);
            ResultSet seriesResultSet = getSeriesStatement.executeQuery();

            while (seriesResultSet.next()) {
                seriesInfo.add(Integer.toString(seriesResultSet.getInt(1)));
                seriesInfo.add(newProductionId);
                seriesInfo.add(seriesResultSet.getString(3));
                seriesInfo.add(seriesResultSet.getString(4));
                seriesInfo.add(seriesResultSet.getString(5));
            }

            return seriesInfo;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return null;
    }

    public void editInsertTvSeries(ArrayList<String> series_info) {
        connection = DatabaseAccessHandler.getConnection();

        try {
            if (series_info != null){
                PreparedStatement insertBackupSeriesStatement = connection.prepareStatement(
                        "INSERT INTO tv_series(id, production_id, title, series_id, description) VALUES" +
                                "(?,?,?,?,?)");
                insertBackupSeriesStatement.setInt(1, Integer.parseInt(series_info.get(0)));
                insertBackupSeriesStatement.setString(2, series_info.get(1));
                insertBackupSeriesStatement.setString(3, series_info.get(2));
                insertBackupSeriesStatement.setString(4, series_info.get(3));
                insertBackupSeriesStatement.setString(5, series_info.get(4));

                insertBackupSeriesStatement.execute();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean editTitle(String newTitle, String production_id) {
        connection = DatabaseAccessHandler.getConnection();

        try {
            PreparedStatement editTitleStatement = connection.prepareStatement(
                    "UPDATE tv_series SET title = ? WHERE production_id = ?");
            editTitleStatement.setString(1, newTitle);
            editTitleStatement.setString(2, production_id);

            editTitleStatement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return false;
    }
}
