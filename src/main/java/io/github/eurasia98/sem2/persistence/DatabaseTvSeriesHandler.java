package io.github.eurasia98.sem2.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseTvSeriesHandler {
    static Connection connection = null;

    public void insertTvSeries(ArrayList<String> tvSeriesInfo){
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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
}
