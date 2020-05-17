package io.github.eurasia98.sem2.persistence;

import io.github.eurasia98.sem2.logic.ProductionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
        } return false;
    }
}
