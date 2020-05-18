package io.github.eurasia98.sem2.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseTvProgramEpisodeHandler {

    static Connection connection = null;

    public ArrayList<String[]> getEpisodesInfo(String selectedProductionToEdit) {
        connection = DatabaseAccessHandler.getConnection();
        ArrayList<String[]> episodesInfo = new ArrayList<>();

        try {
            PreparedStatement getEpisodesStatement = connection.prepareStatement(
                    "SELECT * FROM tvprogram_episodes WHERE production_id = ?");
            getEpisodesStatement.setString(1, selectedProductionToEdit);

            ResultSet getEpisodesResultSet = getEpisodesStatement.executeQuery();

            if (getEpisodesResultSet.next()){
                while (getEpisodesResultSet.next()){
                    episodesInfo.add(new String[]{Integer.toString(getEpisodesResultSet.getInt(3)),
                    getEpisodesResultSet.getString(4), getEpisodesResultSet.getString(5)});
                }
                return episodesInfo;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return null;
    }

    public Boolean changeTitle(String episode_id, String newTitle) {
        connection = DatabaseAccessHandler.getConnection();

        try {
            PreparedStatement changeTitleStatement = connection.prepareStatement(
                    "UPDATE tvprogram_episodes SET episode_title = ? WHERE episode_id = ?");
            changeTitleStatement.setString(1, newTitle);
            changeTitleStatement.setString(2, episode_id);
            changeTitleStatement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return false;
    }

    public boolean changeEpisodeId(String oldId, String newId) {
        connection = DatabaseAccessHandler.getConnection();

        try {
            PreparedStatement changeIdStatement = connection.prepareStatement(
                    "UPDATE tvprogram_episodes SET episode_id = ? WHERE episode_id = ?");
            changeIdStatement.setString(1, newId);
            changeIdStatement.setString(2, oldId);
            changeIdStatement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return false;
    }

    public boolean changeEpisodeNumber(String episode_id, String newEpisodeNumber) {
        connection = DatabaseAccessHandler.getConnection();

        try {
            PreparedStatement changeNumberStatement = connection.prepareStatement(
                    "UPDATE tvprogram_episodes SET episode_number = ? WHERE episode_id = ?");
            changeNumberStatement.setInt(1, Integer.parseInt(newEpisodeNumber));
            changeNumberStatement.setString(2, episode_id);
            changeNumberStatement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return false;
    }
}
