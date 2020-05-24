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
                    "SELECT episode_number, episode_title, episode_id FROM tvprogram_episodes WHERE production_id = ?");
            getEpisodesStatement.setString(1, selectedProductionToEdit);

            ResultSet getEpisodesResultSet = getEpisodesStatement.executeQuery();

            while (getEpisodesResultSet.next()) {
                episodesInfo.add(new String[]{Integer.toString(getEpisodesResultSet.getInt(1)),
                        getEpisodesResultSet.getString(2), getEpisodesResultSet.getString(3)});

            }
            return episodesInfo;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
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
        }
        return false;
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
        }
        return false;
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
        }
        return false;
    }

    public boolean insertEpisode(ArrayList<String> episodeInfo) {
        connection = DatabaseAccessHandler.getConnection();

        try {
            PreparedStatement insertStatement = connection.prepareStatement(
                    "INSERT INTO tvprogram_episodes(production_id, episode_number, episode_title, episode_id, owner_username) " +
                            "VALUES (?,?,?,?,?)");
            insertStatement.setString(1, episodeInfo.get(0));
            insertStatement.setInt(2, Integer.parseInt(episodeInfo.get(1)));
            insertStatement.setString(3, episodeInfo.get(2));
            insertStatement.setString(4, episodeInfo.get(3));
            insertStatement.setString(5, episodeInfo.get(4));
            insertStatement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public String getDescription(String selectedTvProgramEpisodeToEdit) {
        connection = DatabaseAccessHandler.getConnection();

        try {
            PreparedStatement getDescriptionStatement = connection.prepareStatement(
                    "SELECT description FROM tvprogram_episodes WHERE episode_id = ?");
            getDescriptionStatement.setString(1, selectedTvProgramEpisodeToEdit);

            ResultSet decriptionResultSet = getDescriptionStatement.executeQuery();

            if (decriptionResultSet.next()) {
                return decriptionResultSet.getString(1);
            } else return null;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public boolean changeEpisodeDescription(String episode_id, String newDescription) {
        connection = DatabaseAccessHandler.getConnection();

        try {
            PreparedStatement changeDesciptionStatement = connection.prepareStatement(
                    "UPDATE tvprogram_episodes SET description = ? WHERE episode_id = ?");
            changeDesciptionStatement.setString(1, newDescription);
            changeDesciptionStatement.setString(2, episode_id);
            changeDesciptionStatement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean insertBackupEpisodes(String oldProductionId) {
        connection = DatabaseAccessHandler.getConnection();

        try {
            PreparedStatement backupTvProgramEpisodesStatement = connection.prepareStatement(
                    "SELECT * FROM tvprogram_episodes WHERE production_id = ?");
            backupTvProgramEpisodesStatement.setString(1, oldProductionId);

            ResultSet backupTvProgramEpisodesResultSet = backupTvProgramEpisodesStatement.executeQuery();

            ArrayList<String[]> tvProgramEpisodesInfo = new ArrayList<>();

            while (backupTvProgramEpisodesResultSet.next()) {
                tvProgramEpisodesInfo.add(new String[]{Integer.toString(backupTvProgramEpisodesResultSet.getInt(1)),
                backupTvProgramEpisodesResultSet.getString(2), Integer.toString(backupTvProgramEpisodesResultSet.getInt(3)),
                backupTvProgramEpisodesResultSet.getString(4), backupTvProgramEpisodesResultSet.getString(5),
                backupTvProgramEpisodesResultSet.getString(6), backupTvProgramEpisodesResultSet.getString(7)});
            }

            PreparedStatement insertBackupTvProgramEpisodes = connection.prepareStatement(
                    "INSERT INTO backup_tvprogram_episodes(id, production_id, episode_number, episode_title, " +
                            "episode_id, description, owner_username)" +
                            "VALUES(?,?,?,?,?,?,?)");

            for (String[] s : tvProgramEpisodesInfo){
                insertBackupTvProgramEpisodes.setInt(1, Integer.parseInt(s[0]));
                insertBackupTvProgramEpisodes.setString(2, s[1]);
                insertBackupTvProgramEpisodes.setInt(3, Integer.parseInt(s[2]));
                insertBackupTvProgramEpisodes.setString(4, s[3]);
                insertBackupTvProgramEpisodes.setString(5, s[4]);
                insertBackupTvProgramEpisodes.setString(6, s[5]);
                insertBackupTvProgramEpisodes.setString(7, s[6]);

                insertBackupTvProgramEpisodes.addBatch();
            }



            insertBackupTvProgramEpisodes.executeBatch();

            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public ArrayList<String[]> getBackupTvProgramEpisodesInfo(String newProductionId, String oldProductionId) {
        connection = DatabaseAccessHandler.getConnection();
        ArrayList<String[]> tvProgramEpisodesInfo = new ArrayList<>();

        try {
            PreparedStatement getTvProgramEpisodesStatement = connection.prepareStatement(
                    "SELECT * FROM backup_tvprogram_episodes where production_id = ?");
            getTvProgramEpisodesStatement.setString(1, oldProductionId);
            ResultSet tvProgramEpisodesResultSet = getTvProgramEpisodesStatement.executeQuery();

            while (tvProgramEpisodesResultSet.next()) {
                tvProgramEpisodesInfo.add(new String[]{Integer.toString(tvProgramEpisodesResultSet.getInt(1)),
                        newProductionId, Integer.toString(tvProgramEpisodesResultSet.getInt(3)),
                        tvProgramEpisodesResultSet.getString(4),
                        tvProgramEpisodesResultSet.getString(5), tvProgramEpisodesResultSet.getString(6),
                        tvProgramEpisodesResultSet.getString(7)});
            }

            return tvProgramEpisodesInfo;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void editInsertEpisodes(ArrayList<String[]> episodesInfo) {
        connection = DatabaseAccessHandler.getConnection();

        try {
            PreparedStatement insertTvProgramEpisodesStatement = connection.prepareStatement(
                    "INSERT INTO tvprogram_episodes(id, production_id, episode_number, " +
                            "episode_title, episode_id, description, owner_username) VALUES(?,?,?,?,?,?,?)");

            for (String[] episodeInfo : episodesInfo) {
                insertTvProgramEpisodesStatement.setInt(1, Integer.parseInt(episodeInfo[0]));
                insertTvProgramEpisodesStatement.setString(2, episodeInfo[1]);
                insertTvProgramEpisodesStatement.setInt(3, Integer.parseInt(episodeInfo[2]));
                insertTvProgramEpisodesStatement.setString(4, episodeInfo[3]);
                insertTvProgramEpisodesStatement.setString(5, episodeInfo[4]);
                insertTvProgramEpisodesStatement.setString(6, episodeInfo[5]);
                insertTvProgramEpisodesStatement.setString(7, episodeInfo[6]);


                insertTvProgramEpisodesStatement.addBatch();
            }

            insertTvProgramEpisodesStatement.executeBatch();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean deleteEpisode(String production_id) {
        connection = DatabaseAccessHandler.getConnection();

        try {
            PreparedStatement deleteStatement = connection.prepareStatement(
                    "DELETE FROM tvprogram_episodes WHERE production_id = ?");
            deleteStatement.setString(1, production_id);
            deleteStatement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return false;
    }
}
