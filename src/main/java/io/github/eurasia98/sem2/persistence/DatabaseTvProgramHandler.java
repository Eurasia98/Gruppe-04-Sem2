package io.github.eurasia98.sem2.persistence;

import io.github.eurasia98.sem2.logic.ProductionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseTvProgramHandler {
    static Connection connection = null;


    public boolean insertTvProgram(ArrayList<String> tvProgramInfo) {
        connection = DatabaseAccessHandler.getConnection();
        DatabaseProductionManager databaseProductionManager = new DatabaseProductionManager();
        databaseProductionManager.insertProduction(tvProgramInfo);

        try {
            PreparedStatement insertStatement = connection.prepareStatement(
                    "INSERT INTO tv_programs(production_id, title, owner_username, description) VALUES (?,?,?,?)");
            insertStatement.setString(1, tvProgramInfo.get(0));
            insertStatement.setString(2, tvProgramInfo.get(1));
            insertStatement.setString(3, tvProgramInfo.get(3));
            insertStatement.setString(4, tvProgramInfo.get(4));
            insertStatement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean insertBackupTvProgram(String oldProductionId) {
        connection = DatabaseAccessHandler.getConnection();

        try {
            PreparedStatement backupTvProgramStatement = connection.prepareStatement(
                    "SELECT * FROM tv_programs WHERE production_id = ?");
            backupTvProgramStatement.setString(1, oldProductionId);

            ResultSet backupTvProgramResultset = backupTvProgramStatement.executeQuery();

            ArrayList<String> tvProgramInfo = new ArrayList<>();

            while (backupTvProgramResultset.next()) {
                tvProgramInfo.add(Integer.toString(backupTvProgramResultset.getInt(1)));
                tvProgramInfo.add(backupTvProgramResultset.getString(2));
                tvProgramInfo.add(backupTvProgramResultset.getString(3));
                tvProgramInfo.add(backupTvProgramResultset.getString(4));
                tvProgramInfo.add(backupTvProgramResultset.getString(5));
            }

            PreparedStatement insertBackupTvProgram = connection.prepareStatement(
                    "INSERT INTO backup_tvprograms(id, production_id, title, owner_username, description)" +
                            "VALUES(?,?,?,?,?)");

            insertBackupTvProgram.setInt(1, Integer.parseInt(tvProgramInfo.get(0)));
            insertBackupTvProgram.setString(2, tvProgramInfo.get(1));
            insertBackupTvProgram.setString(3, tvProgramInfo.get(2));
            insertBackupTvProgram.setString(4, tvProgramInfo.get(3));
            insertBackupTvProgram.setString(5, tvProgramInfo.get(4));

            insertBackupTvProgram.execute();

            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public ArrayList<String> getBackupTvProgramInfo(String newProductionId, String oldProductionId) {
        connection = DatabaseAccessHandler.getConnection();
        ArrayList<String> tvProgramInfo = new ArrayList<>();

        try {
            PreparedStatement getBackUpTvProgramInfoStatement = connection.prepareStatement(
                    "SELECT * FROM backup_tvprograms WHERE production_id = ?");
            getBackUpTvProgramInfoStatement.setString(1, oldProductionId);

            ResultSet tvProgramInfoResultSet = getBackUpTvProgramInfoStatement.executeQuery();

            while (tvProgramInfoResultSet.next()){
                tvProgramInfo.add(Integer.toString(tvProgramInfoResultSet.getInt(1)));
                tvProgramInfo.add(newProductionId);
                tvProgramInfo.add(tvProgramInfoResultSet.getString(3));
                tvProgramInfo.add(tvProgramInfoResultSet.getString(4));
                tvProgramInfo.add(tvProgramInfoResultSet.getString(5));
            }

            return tvProgramInfo;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return null;
    }

    public void editInsertTvProgram(ArrayList<String> tvprogram_info) {
        connection = DatabaseAccessHandler.getConnection();

        try {
            if (!tvprogram_info.isEmpty()){
                PreparedStatement insertBackupTvProgramsStatement = connection.prepareStatement(
                        "INSERT INTO tv_programs(id, production_id, title, owner_username, description) VALUES" +
                                "(?,?,?,?,?)");
                insertBackupTvProgramsStatement.setInt(1, Integer.parseInt(tvprogram_info.get(0)));
                insertBackupTvProgramsStatement.setString(2, tvprogram_info.get(1));
                insertBackupTvProgramsStatement.setString(3, tvprogram_info.get(2));
                insertBackupTvProgramsStatement.setString(4, tvprogram_info.get(3));
                insertBackupTvProgramsStatement.setString(5, tvprogram_info.get(4));

                insertBackupTvProgramsStatement.execute();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean editTitle(String newTitle, String production_id) {
        connection = DatabaseAccessHandler.getConnection();

        try {
            PreparedStatement editTitleStatement = connection.prepareStatement(
                    "UPDATE tv_programs SET title = ? WHERE production_id = ?");
            editTitleStatement.setString(1, newTitle);
            editTitleStatement.setString(2, production_id);

            editTitleStatement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return false;
    }
}
