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
                    "SELECT season_id FROM seasons WHERE production_id = ?");
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
}
