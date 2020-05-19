package io.github.eurasia98.sem2.persistence;

import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseTvSeriesEpisodeHandler {
    static Connection connection = null;

    public ArrayList<String[]> getSelectedSeasonEpisodesInfo(String selectedSeasonToEdit) {
        connection = DatabaseAccessHandler.getConnection();
        ArrayList<String[]> episodeInfo = new ArrayList<>();

        try {
            PreparedStatement getEpisodeInfoStatement = connection.prepareStatement(
                    "SELECT episode_title, episode_number, episode_id FROM episodes WHERE season_id = ?");
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
                            "episode_title, description, episode_id, episode_number) " +
                            "VALUES(?,?,?,?,?,?,?)");
            insertEpisodeStatement.setString(1, selectedSeasonToEdit);
            insertEpisodeStatement.setString(2, selectedTvSeriesToEdit);
            insertEpisodeStatement.setString(3, selectedProductionToEdit);
            insertEpisodeStatement.setString(4, title);
            insertEpisodeStatement.setString(5, description);
            insertEpisodeStatement.setString(6, txtFieldEpisodeId);
            insertEpisodeStatement.setInt(7, Integer.parseInt(txtFieldEpisodeNumber));

            insertEpisodeStatement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public Boolean insertBackupEpisodes(String oldProductionId) {

        connection = DatabaseAccessHandler.getConnection();

        try {
            PreparedStatement backupEpisodesStatement = connection.prepareStatement(
                    "SELECT * FROM episodes WHERE production_id = ?");
            backupEpisodesStatement.setString(1, oldProductionId);

            ResultSet backupEpisodesResultset = backupEpisodesStatement.executeQuery();

            ArrayList<String[]> episodesInfo = new ArrayList<>();

            if (backupEpisodesResultset.next()) {
                while (backupEpisodesResultset.next()) {
                    episodesInfo.add(new String[]{Integer.toString(backupEpisodesResultset.getInt(1)),
                            backupEpisodesResultset.getString(2), backupEpisodesResultset.getString(3),
                    backupEpisodesResultset.getString(4), backupEpisodesResultset.getString(5),
                    backupEpisodesResultset.getString(6), backupEpisodesResultset.getString(7),
                    Integer.toString(backupEpisodesResultset.getInt(8))});
                }
                PreparedStatement insertBackupEpisodes = connection.prepareStatement(
                        "INSERT INTO backup_episodes(id, season_id, tv_series_id, production_id, episode_title, " +
                                "description, episode_id, episode_number)" +
                                "VALUES(?,?,?,?,?,?,?,?)");

                for (String[] s : episodesInfo){
                    insertBackupEpisodes.setInt(1, Integer.parseInt(s[0]));
                    insertBackupEpisodes.setString(2, s[1]);
                    insertBackupEpisodes.setString(3, s[2]);
                    insertBackupEpisodes.setString(2, s[3]);
                    insertBackupEpisodes.setString(3, s[4]);
                    insertBackupEpisodes.setString(2, s[5]);
                    insertBackupEpisodes.setString(3, s[6]);
                    insertBackupEpisodes.setInt(2, Integer.parseInt(s[7]));

                    insertBackupEpisodes.addBatch();
                }

                insertBackupEpisodes.executeBatch();
            } return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return false;
    }

    public boolean checkBackupEpisodes() {
        connection = DatabaseAccessHandler.getConnection();

        try {
            PreparedStatement checkUpdateStatement = connection.prepareStatement(
                    "SELECT * FROM backup_episodes");

            ResultSet checkUpdateResultset = checkUpdateStatement.executeQuery();

            while (checkUpdateResultset.next()) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public ArrayList<String[]> getBackupEpisodeInfo(String newProductionId, String oldProductionId) {
        connection = DatabaseAccessHandler.getConnection();
        ArrayList<String[]> episodesInfo = new ArrayList<>();

        try {
            PreparedStatement getEpisodesStatement = connection.prepareStatement(
                    "SELECT * FROM backup_episodes where production_id = ?");
            getEpisodesStatement.setString(1, oldProductionId);
            ResultSet episodeResultSet = getEpisodesStatement.executeQuery();

            while (episodeResultSet.next()) {
                episodesInfo.add(new String[]{Integer.toString(episodeResultSet.getInt(1)),
                        episodeResultSet.getString(2), episodeResultSet.getString(3),
                        newProductionId, episodeResultSet.getString(5),
                        episodeResultSet.getString(6), episodeResultSet.getString(7),
                        episodeResultSet.getString(8)});
            }

            return episodesInfo;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void editInsertEpisodes(ArrayList<String[]> episodesInfo) {
        connection = DatabaseAccessHandler.getConnection();

        try {
            PreparedStatement insertEpisodesStatement = connection.prepareStatement(
                    "INSERT INTO episodes(id, season_id, tv_series_id, production_id, " +
                            "episode_title, description, episode_id, episode_number) VALUES(?,?,?,?,?,?,?,?)");

            for (String[] episodeInfo : episodesInfo) {
                insertEpisodesStatement.setInt(1, Integer.parseInt(episodeInfo[0]));
                insertEpisodesStatement.setString(2, episodeInfo[1]);
                insertEpisodesStatement.setString(3, episodeInfo[2]);
                insertEpisodesStatement.setString(4, episodeInfo[3]);
                insertEpisodesStatement.setString(2, episodeInfo[4]);
                insertEpisodesStatement.setString(3, episodeInfo[5]);
                insertEpisodesStatement.setString(4, episodeInfo[6]);
                insertEpisodesStatement.setInt(1, Integer.parseInt(episodeInfo[7]));


                insertEpisodesStatement.addBatch();
            }

            insertEpisodesStatement.executeBatch();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Boolean editDescription(String selectedSeriesEpisodeToEdit, String newDescription) {
        connection = DatabaseAccessHandler.getConnection();

        try {
            PreparedStatement editDescriptionStatement = connection.prepareStatement(
                    "UPDATE episodes SET description = ? WHERE episode_id = ?");
            editDescriptionStatement.setString(1, newDescription);
            editDescriptionStatement.setString(2, selectedSeriesEpisodeToEdit);
            editDescriptionStatement.execute();

            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
