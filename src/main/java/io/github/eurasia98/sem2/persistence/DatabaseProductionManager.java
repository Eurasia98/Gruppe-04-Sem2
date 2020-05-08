package io.github.eurasia98.sem2.persistence;

import io.github.eurasia98.sem2.logic.Production;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseProductionManager {
    static Connection connection = null;

    public Boolean insertProduction(Production production){
        this.connection = DatabaseAccesHandler.getConnection();

        try {
            PreparedStatement insertProductionStatement = connection.prepareStatement(
                    "INSERT INTO productions(title, production_id, production_type) VALUES(?,?,?)");

            insertProductionStatement.setString(1, production.getTitle());
            insertProductionStatement.setString(2, production.getProductionID());
            insertProductionStatement.setString(3, production.getProductionType());

            return insertProductionStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return false;
    }
}
