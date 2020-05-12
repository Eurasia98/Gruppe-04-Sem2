package io.github.eurasia98.sem2.persistence;

import io.github.eurasia98.sem2.logic.Production;
import io.github.eurasia98.sem2.logic.ProductionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseProductionManager {
    static Connection connection = null;

    public Boolean insertProduction(ArrayList<String> productionInfo){
        this.connection = DatabaseAccesHandler.getConnection();

        try {
            PreparedStatement insertProductionStatement = connection.prepareStatement(
                    "INSERT INTO productions(production_id, title, production_type) VALUES(?,?,?)");

            insertProductionStatement.setString(1, productionInfo.get(0));
            insertProductionStatement.setString(2, productionInfo.get(1));
            insertProductionStatement.setString(3, productionInfo.get(2));

            return insertProductionStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return false;
    }

    public ArrayList<String> getProduction(String production_id){
        connection = DatabaseAccesHandler.getConnection();
        ArrayList<String> productionInfo = new ArrayList<>();

        try {
            PreparedStatement getProductionStatement = connection.prepareStatement("SELECT * FROM productions WHERE production_id = ?");
            getProductionStatement.setString(1, production_id);

            ResultSet rs = getProductionStatement.executeQuery();

            while (rs.next()){
                productionInfo.add(rs.getString(1));
                productionInfo.add(rs.getString(2));
                productionInfo.add(rs.getString(3));
                productionInfo.add(rs.getString(4));
                productionInfo.add(rs.getString(5));
            }

            return productionInfo;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return null;
    }

/*    public Production getProduction(String production_id){
        connection = DatabaseAccesHandler.getConnection();


        try {
            PreparedStatement getProductionStatement = connection.prepareStatement("SELECT * FROM productions WHERE production_id = ?");
            getProductionStatement.setString(1, production_id);

            ResultSet rs = getProductionStatement.executeQuery();

            ProductionManager productionManager = new ProductionManager();

            while (rs.next()){
                Production production = productionManager.createProduction(rs.getString("title"), rs.getString("production_id"));
                return production;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return null;
    }*/

    public ArrayList<String[]> getMyProductions(int account_id){
        connection = DatabaseAccesHandler.getConnection();
        String[] productionInfoArray;
        ArrayList<String[]> productionsInfo = new ArrayList<>();

        try {
            PreparedStatement getMyProductionsStatement = connection.prepareStatement(
                    "SELECT * FROM productions WHERE owner = ?");
            getMyProductionsStatement.setInt(1, account_id);
            ResultSet rs = getMyProductionsStatement.executeQuery();
            while (rs.next()){
                productionInfoArray = new String[]{rs.getString(0), rs.getString(1),
                rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)};
                productionsInfo.add(productionInfoArray);
            }
            return productionsInfo;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return null;
    }

/*    public ArrayList<Production> getMyProductions(int account_id){
        connection = DatabaseAccesHandler.getConnection();
        ArrayList<Production> myProductions = new ArrayList<>();
        ProductionManager productionManager = new ProductionManager();

        try {
            PreparedStatement getMyProductionsStatement = connection.prepareStatement(
                    "SELECT * FROM productions WHERE owner = ?");
            getMyProductionsStatement.setInt(1, account_id);
            ResultSet rs = getMyProductionsStatement.executeQuery();
            while (rs.next()){
                Production production = productionManager.createProduction(rs.getString("title"), rs.getString("production_id"));
                myProductions.add(production);
            }
            return myProductions;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return null;
    }*/
}
