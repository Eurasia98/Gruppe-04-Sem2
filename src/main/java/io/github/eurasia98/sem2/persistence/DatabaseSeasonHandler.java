package io.github.eurasia98.sem2.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseSeasonHandler {
    static Connection connection = null;

    public boolean insertSeason(ArrayList<String> seasonInfo) {
        try {
            this.connection = DatabaseAccessHandler.getConnection();
            DatabaseProductionManager databaseProductionManager = new DatabaseProductionManager();
            databaseProductionManager.insertProduction(seasonInfo);

            PreparedStatement insertTvSeriesStatement = connection.prepareStatement(
                    "INSERT INTO season(season_id, production_id, series_id, season_number) VALUES(?,?,?,?)");
            insertTvSeriesStatement.setString(1, seasonInfo.get(0));
            insertTvSeriesStatement.setString(2, seasonInfo.get(1));
            insertTvSeriesStatement.setString(3, seasonInfo.get(2));
            insertTvSeriesStatement.setString(4, seasonInfo.get(3));

            insertTvSeriesStatement.execute();

            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return false;
    }
}
