package io.github.eurasia98.sem2.persistence;

import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseProductionManager {
    static Connection connection = null;
    private ArrayList<String> backupProductions;

    public Boolean insertProduction(ArrayList<String> productionInfo){
        this.connection = DatabaseAccesHandler.getConnection();

        try {
            PreparedStatement insertProductionStatement = connection.prepareStatement(
                    "INSERT INTO productions(production_id, title, production_type, owner_id) VALUES(?,?,?,?)");

            insertProductionStatement.setString(1, productionInfo.get(0));
            insertProductionStatement.setString(2, productionInfo.get(1));
            insertProductionStatement.setString(3, productionInfo.get(2));
            insertProductionStatement.setInt(4, Integer.parseInt(productionInfo.get(3)));

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
                productionInfo.add(Integer.toString(rs.getInt(2)));
                productionInfo.add(rs.getString(3));
                productionInfo.add(rs.getString(4));
                productionInfo.add(Integer.toString(rs.getInt(5)));
                productionInfo.add(rs.getString(6));
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
                    "SELECT * FROM productions WHERE owner_id = ?");
            getMyProductionsStatement.setInt(1, account_id);
            ResultSet rs = getMyProductionsStatement.executeQuery();
            while (rs.next()){
                productionInfoArray = new String[]{rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5)};
                productionsInfo.add(productionInfoArray);
            }
            return productionsInfo;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return null;
    }

    public Boolean editProductionId(String oldProductionId, String newProductionId) {
        connection = DatabaseAccesHandler.getConnection();
        String productionType = getProductionType(oldProductionId);


        switch (productionType) {
            case "Movie":
                DatabaseMovieHandler databaseMovieHandler = new DatabaseMovieHandler();
                databaseMovieHandler.insertBackupMovies(oldProductionId);
                if (databaseMovieHandler.checkBackupMovies(oldProductionId) == true) {
                    try {
                        PreparedStatement deleteMovieRowStatement = connection.prepareStatement(
                                "DELETE FROM movies WHERE production_id = ?");
                        deleteMovieRowStatement.setString(1, oldProductionId);
                        deleteMovieRowStatement.execute();
                        if (databaseMovieHandler.checkifMovieIsDeleted(oldProductionId) == true) {
                            insertBackupProductions(oldProductionId);
                            if (checkBackupProductions(oldProductionId) == true){
                                PreparedStatement deleteProductionRowStatement = connection.prepareStatement(
                                        "DELETE FROM productions WHERE production_id = ?");
                                deleteProductionRowStatement.setString(1, oldProductionId);
                                deleteProductionRowStatement.execute();
                                if (checkIfProductionIsDeleted(oldProductionId) == true){
                                    ArrayList<String> productionInfo = getBackupProductions(oldProductionId, newProductionId);
                                    editInsertProduction(productionInfo);
                                    databaseMovieHandler.insertBackupMovieToMovie(databaseMovieHandler.getBackupMovieInfo(oldProductionId, newProductionId));
                                    return true;
                                }
                            }
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            break;
            case "TvSeries":
                break;
            case "TvProgram":
                break;
        } return false;
    }

    public Boolean editProductionTitle(String newTitle, String production_id){
        connection = DatabaseAccesHandler.getConnection();
        String productionType = getProductionType(production_id);

        switch (productionType){
            case "Movie":
                DatabaseMovieHandler databaseMovieHandler = new DatabaseMovieHandler();
                if (databaseMovieHandler.editTitle(newTitle, production_id) == true ){
                    try {
                        PreparedStatement editProductionTitle = connection.prepareStatement(
                                "UPDATE productions SET title = ? WHERE production_id = ?");
                        editProductionTitle.setString(1, newTitle);
                        editProductionTitle.setString(2, production_id);
                        editProductionTitle.execute();
                        return true;
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
        }
        return false;
    }

    private String getProductionType(String production_id){
        try {
            PreparedStatement getProductionType = connection.prepareStatement(
                    "SELECT production_type FROM productions WHERE production_id = ?");
            getProductionType.setString(1, production_id);

            ResultSet productionTypeResultSet = getProductionType.executeQuery();
            ArrayList<String> productionType = new ArrayList<>();

            while (productionTypeResultSet.next()){
                productionType.add(productionTypeResultSet.getString(1));
            }
            return productionType.get(0);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return null;
    }

    private void insertBackupProductions(String oldProductionId){
        connection = DatabaseAccesHandler.getConnection();

        try {
            PreparedStatement backupProductionsStatement = connection.prepareStatement(
                    "SELECT * FROM productions WHERE production_id = ?");
            backupProductionsStatement.setString(1, oldProductionId);

            ResultSet backupProductionResultSet = backupProductionsStatement.executeQuery();

            ArrayList<String> productionInfo = new ArrayList<>();

            while (backupProductionResultSet.next()){
                productionInfo.add(backupProductionResultSet.getString(1));
                productionInfo.add(backupProductionResultSet.getString(2));
                productionInfo.add(backupProductionResultSet.getString(3));
                productionInfo.add(backupProductionResultSet.getString(4));
                productionInfo.add(backupProductionResultSet.getString(5));
                productionInfo.add(backupProductionResultSet.getString(6));
                productionInfo.add(backupProductionResultSet.getString(7));
            }

            PreparedStatement insertBackupProductions = connection.prepareStatement(
                    "INSERT INTO backup_productions(production_id, id, title, production_type, " +
                            "owner_id, description)" +
                            "VALUES(?,?,?,?,?,?)");

            insertBackupProductions.setString(1, productionInfo.get(0));
            insertBackupProductions.setInt(2, Integer.parseInt(productionInfo.get(1)));
            insertBackupProductions.setString(3, productionInfo.get(2));
            insertBackupProductions.setString(4, productionInfo.get(3));
            insertBackupProductions.setInt(5, Integer.parseInt(productionInfo.get(4)));
            insertBackupProductions.setString(6, productionInfo.get(5));

            insertBackupProductions.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private Boolean checkBackupProductions(String oldProductionId){
        connection = DatabaseAccesHandler.getConnection();

        try {
            PreparedStatement checkUpdateStatement = connection.prepareStatement(
                    "SELECT * FROM backup_productions WHERE production_id = ?");
            checkUpdateStatement.setString(1, oldProductionId);

            ResultSet checkUpdateResultset = checkUpdateStatement.executeQuery();

            if (checkUpdateResultset.next()){
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    private Boolean checkIfProductionIsDeleted(String oldProductionId){
        connection = DatabaseAccesHandler.getConnection();

        try {
            PreparedStatement productionStatement = connection.prepareStatement(
                    "SELECT * FROM productions WHERE production_id = ?");
            productionStatement.setString(1, oldProductionId);

            ResultSet productionsResultset = productionStatement.executeQuery();

            if (productionsResultset.next()){
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return true;
    }

    private void editInsertProduction(ArrayList<String> productionInfo){
        connection = DatabaseAccesHandler.getConnection();

        try {
            PreparedStatement insertProductionStatement = connection.prepareStatement(
                    "INSERT INTO productions(production_id, id, title, production_type," +
                            "owner_id, description) VALUES(?,?,?,?,?,?)");
            insertProductionStatement.setString(1, productionInfo.get(0));
            insertProductionStatement.setInt(2, Integer.parseInt(productionInfo.get(1)));
            insertProductionStatement.setString(3, productionInfo.get(2));
            insertProductionStatement.setString(4, productionInfo.get(3));
            insertProductionStatement.setInt(5, Integer.parseInt(productionInfo.get(4)));
            insertProductionStatement.setString(6, productionInfo.get(5));

            insertProductionStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private ArrayList<String> getBackupProductions(String oldProductionId, String newProductionId){
        connection = DatabaseAccesHandler.getConnection();
        ArrayList<String> productionsInfo = new ArrayList<>();

        try {
            PreparedStatement getProductionStatement = connection.prepareStatement(
                    "SELECT * FROM backup_productions WHERE production_id = ?");
            getProductionStatement.setString(1, oldProductionId);
            ResultSet productionsResultSet = getProductionStatement.executeQuery();

            while (productionsResultSet.next()){
                productionsInfo.add(newProductionId);
                productionsInfo.add(productionsResultSet.getString(2));
                productionsInfo.add(productionsResultSet.getString(3));
                productionsInfo.add(productionsResultSet.getString(4));
                productionsInfo.add(productionsResultSet.getString(5));
                productionsInfo.add(productionsResultSet.getString(6));
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
