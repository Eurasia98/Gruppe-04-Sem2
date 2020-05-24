package io.github.eurasia98.sem2.persistence;

import java.net.Inet4Address;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseSeasonHandler {
    static Connection connection = null;

    public boolean insertSeason(ArrayList<String> seasonInfo) {
        try {
            this.connection = DatabaseAccessHandler.getConnection();

            PreparedStatement insertTvSeriesStatement = connection.prepareStatement(
                    "INSERT INTO seasons(season_id, production_id, series_id, season_number) VALUES(?,?,?,?)");
            insertTvSeriesStatement.setString(1, seasonInfo.get(0));
            insertTvSeriesStatement.setString(2, seasonInfo.get(1));
            insertTvSeriesStatement.setString(3, seasonInfo.get(2));
            insertTvSeriesStatement.setInt(4, Integer.parseInt(seasonInfo.get(3)));

            insertTvSeriesStatement.execute();

            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public Boolean insertBackupSeasons(String oldProductionId) {
        connection = DatabaseAccessHandler.getConnection();

        try {
            PreparedStatement backupSeasonStatement = connection.prepareStatement(
                    "SELECT * FROM seasons WHERE production_id = ?");
            backupSeasonStatement.setString(1, oldProductionId);

            ResultSet backupSeasonResultset = backupSeasonStatement.executeQuery();

            ArrayList<String[]> seasonInfo = new ArrayList<>();
            if (backupSeasonResultset.next()) {
                while (backupSeasonResultset.next()) {
                    seasonInfo.add(new String[]{Integer.toString(backupSeasonResultset.getInt(1)),
                            backupSeasonResultset.getString(2), backupSeasonResultset.getString(3),
                            backupSeasonResultset.getString(4), Integer.toString(backupSeasonResultset.getInt(5))});
                }

                PreparedStatement insertBackupSeason = connection.prepareStatement(
                        "INSERT INTO backup_seasons(id, season_id, production_id, series_id) VALUES (?,?,?,?)");

                for (String[] s : seasonInfo) {
                    insertBackupSeason.setInt(1, Integer.parseInt(s[0]));
                    insertBackupSeason.setString(2, s[1]);
                    insertBackupSeason.setString(3, s[2]);
                    insertBackupSeason.setString(2, s[3]);
                    insertBackupSeason.setInt(2, Integer.parseInt(s[4]));

                    insertBackupSeason.addBatch();
                }
                insertBackupSeason.executeBatch();
            }
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean checkBackupSeasons() {
        connection = DatabaseAccessHandler.getConnection();

        try {
            PreparedStatement checkUpdateStatement = connection.prepareStatement(
                    "SELECT * FROM backup_seasons");

            ResultSet checkUpdateResultset = checkUpdateStatement.executeQuery();

            while (checkUpdateResultset.next()) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public ArrayList<String[]> getBackupSeasonInfo(String newProductionId, String oldProductionId) {

        connection = DatabaseAccessHandler.getConnection();
        ArrayList<String[]> seasonInfo = new ArrayList<>();

        try {
            PreparedStatement getCreditsStatement = connection.prepareStatement(
                    "SELECT * FROM backup_seasons WHERE production_id = ?");
            getCreditsStatement.setString(1, oldProductionId);
            ResultSet seasonResultSet = getCreditsStatement.executeQuery();

            while (seasonResultSet.next()) {
                seasonInfo.add(new String[]{Integer.toString(seasonResultSet.getInt(1)),
                        seasonResultSet.getString(2), newProductionId,
                        seasonResultSet.getString(4), Integer.toString(seasonResultSet.getInt(5))});
            }

            return seasonInfo;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void editInsertSeasons(ArrayList<String[]> seasonsInfo) {

        connection = DatabaseAccessHandler.getConnection();

        try {
            PreparedStatement insertSeasonsStatement = connection.prepareStatement(
                    "INSERT INTO seasons(id, username, password, account_type) VALUES(?,?,?,?)");

            for (String[] seasonInfo : seasonsInfo) {
                insertSeasonsStatement.setInt(1, Integer.parseInt(seasonInfo[0]));
                insertSeasonsStatement.setString(2, seasonInfo[1]);
                insertSeasonsStatement.setString(3, seasonInfo[2]);
                insertSeasonsStatement.setString(4, seasonInfo[3]);
                insertSeasonsStatement.setInt(1, Integer.parseInt(seasonInfo[4]));


                insertSeasonsStatement.addBatch();
            }

            insertSeasonsStatement.executeBatch();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean editSeasonId(String currentId, String newId) {
        connection = DatabaseAccessHandler.getConnection();
        try {
            PreparedStatement updateStatement = connection.prepareStatement(
                    "UPDATE seasons SET season_id = ? WHERE season_id = ?");
            updateStatement.setString(1, newId);
            updateStatement.setString(2, currentId);
            updateStatement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return false;
    }

    public boolean editSeasonNumber(String id, String newNumber) {
        connection = DatabaseAccessHandler.getConnection();
        try {
            PreparedStatement updateStatement = connection.prepareStatement(
                    "UPDATE seasons SET season_number = ? WHERE season_id = ?");
            updateStatement.setInt(1, Integer.parseInt(newNumber));
            updateStatement.setString(2, id);
            updateStatement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return false;
    }

    public boolean deleteSeason(String production_id) {
        connection = DatabaseAccessHandler.getConnection();

        try {
            PreparedStatement deleteStatement = connection.prepareStatement(
                    "DELETE FROM seasons WHERE production_id = ?");
            deleteStatement.setString(1, production_id);
            deleteStatement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return false;
    }
}
