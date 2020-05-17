package io.github.eurasia98.sem2.persistence;

import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseEpisodeHandler {
    static Connection connection = null;

    public ArrayList<String[]> getSelectedSeasonEpisodesInfo(String selectedSeasonToEdit) {
        connection = DatabaseAccessHandler.getConnection();
        ArrayList<String[]> episodeInfo = new ArrayList<>();

        try {
            PreparedStatement getEpisodeInfoStatement = connection.prepareStatement(
                    "SELECT title, episode_number, season_id FROM episodes WHERE season_id = ?");
            getEpisodeInfoStatement.setString(1, selectedSeasonToEdit);

            ResultSet episodeInfoResultSet = getEpisodeInfoStatement.executeQuery();

            while (episodeInfoResultSet.next()) {
                episodeInfo.add(new String[]{episodeInfoResultSet.getString(1),
                        Integer.toString(episodeInfoResultSet.getInt(2)),
                        episodeInfoResultSet.getString(3)});
            }

            return episodeInfo;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public String getDescription(String selectedEpisodeToEdit) {
        connection = DatabaseAccessHandler.getConnection();

        try {
            PreparedStatement getDescriptionStatement = connection.prepareStatement(
                    "SELECT description FROM episodes WHERE episode_id = ?");
            getDescriptionStatement.setString(1, selectedEpisodeToEdit);
            ResultSet descriptionResultSet = getDescriptionStatement.executeQuery();

            while (descriptionResultSet.next()) {
                return descriptionResultSet.getString(1);
            }
            return null;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public boolean insertEpisode(String selectedSeasonToEdit, String selectedTvSeriesToEdit,
                                 String selectedProductionToEdit, String title,
                                 String description, String txtFieldEpisodeId,
                                 String txtFieldEpisodeNumber) {
        connection = DatabaseAccessHandler.getConnection();

        try {
            PreparedStatement insertEpisodeStatement = connection.prepareStatement(
                    "INSERT INTO episodes(season_id, tv_series_id, production_id, " +
                            "episode_title, description, episode_id, episode_numbre) " +
                            "VALUES(?,?,?,?,?,?,?)");
            insertEpisodeStatement.setString(1, selectedSeasonToEdit);
            insertEpisodeStatement.setString(2, selectedTvSeriesToEdit);
            insertEpisodeStatement.setString(3, selectedProductionToEdit);
            insertEpisodeStatement.setString(4, title);
            insertEpisodeStatement.setString(5, description);
            insertEpisodeStatement.setString(6, txtFieldEpisodeId);
            insertEpisodeStatement.setString(7, txtFieldEpisodeNumber);

            insertEpisodeStatement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return false;
    }
}
