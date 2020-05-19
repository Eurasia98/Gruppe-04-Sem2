package io.github.eurasia98.sem2.persistence;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseCreditsManager {
    private static Connection connection = null;

    public Boolean insertCredit(ArrayList<String> creditsInfo){
        try {
            this.connection = DatabaseAccessHandler.getConnection();

            PreparedStatement insertCreditStatement = connection.prepareStatement("INSERT INTO credits(account_id, " +
                    "production_id, role_type, role_name) VALUES(?,?,?,?) ");
            insertCreditStatement.setInt(1, Integer.parseInt(creditsInfo.get(0)));
            insertCreditStatement.setString(2, creditsInfo.get(1));
            insertCreditStatement.setString(3, creditsInfo.get(2));
            insertCreditStatement.setString(4, creditsInfo.get(3));

            insertCreditStatement.execute();

            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return false;
    }

    /*public Boolean insertCredit(Credit credit){
        try {
            this.connection = DatabaseAccesHandler.getConnection();

            PreparedStatement insertCreditStatement = connection.prepareStatement("INSERT INTO credits(account_id, " +
                    "production_id, role_type, role_name) VALUES(?,?,?,?) ");
            insertCreditStatement.setInt(1, credit.getAccount_id());
            insertCreditStatement.setString(2, credit.getProduction_id());
            insertCreditStatement.setString(3, credit.getRoleType());
            insertCreditStatement.setString(4, credit.getRoleName());

            insertCreditStatement.execute();

            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return false;
    }*/

    public ArrayList<String[]> searchCredits(String productionId) {
        connection = DatabaseAccessHandler.getConnection();
        ArrayList<String[]> creditsList = new ArrayList<>();
        String[] creditsArray;

        try {
            PreparedStatement getCreditsStatement = connection.prepareStatement("SELECT * FROM credits WHERE production_id = ?");
            getCreditsStatement.setString(1, productionId);

            ResultSet rs = getCreditsStatement.executeQuery();

            while (rs.next()) {
                creditsArray = new String[]{rs.getString(1), rs.getString(2),rs.getString(3),
                rs.getString(4), rs.getString(5)};
                creditsList.add(creditsArray);
            }

            return creditsList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return null;
    }

    public ArrayList<String[]> getCreditsInfo(String productionId) {
        connection = DatabaseAccessHandler.getConnection();
        ArrayList<String[]> creditsInfoList = new ArrayList<>();
        try {
            PreparedStatement getCreditsInfo = connection.prepareStatement(
                    "SELECT role_type, role_name, account_id FROM credits WHERE production_id = ?");
            getCreditsInfo.setString(1, productionId);

            ResultSet creditsResultSet = getCreditsInfo.executeQuery();
            while (creditsResultSet.next()){
                creditsInfoList.add(new String[]{creditsResultSet.getString(1),
                        creditsResultSet.getString(2), Integer.toString(creditsResultSet.getInt(3))});
            }

            return creditsInfoList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return null;
    }

    public Boolean insertBackupCredits(String oldProductionId) {
        connection = DatabaseAccessHandler.getConnection();

        try {
            PreparedStatement backupCreditStatement = connection.prepareStatement(
                    "SELECT * FROM credits WHERE production_id = ?");
            backupCreditStatement.setString(1, oldProductionId);

            ResultSet backupCreditResultSet = backupCreditStatement.executeQuery();

            ArrayList<String[]> creditInfo = new ArrayList<>();

            while (backupCreditResultSet.next()){
                creditInfo.add(new String[]{Integer.toString(backupCreditResultSet.getInt(1)),
                Integer.toString(backupCreditResultSet.getInt(2)), backupCreditResultSet.getString(3),
                backupCreditResultSet.getString(4), backupCreditResultSet.getString(5)});
            }

            PreparedStatement insertBackupCredits = connection.prepareStatement(
                    "INSERT INTO backup_credits(id, account_id, production_id, role_type, role_name)" +
                            "VALUES(?,?,?,?,?)");

            for (String[] s : creditInfo){

                insertBackupCredits.setInt(1, Integer.parseInt(s[0]));
                insertBackupCredits.setInt(2, Integer.parseInt(s[1]));
                insertBackupCredits.setString(3, s[2]);
                insertBackupCredits.setString(4, s[3]);
                insertBackupCredits.setString(5, s[4]);

                insertBackupCredits.addBatch();
            }

            insertBackupCredits.executeBatch();

            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return false;
    }

    public boolean checkBackupCredits(String oldProductionId) {
        connection = DatabaseAccessHandler.getConnection();

        try {
            PreparedStatement checkUpdateStatement = connection.prepareStatement(
                    "SELECT * FROM backup_credits");

            ResultSet checkUpdateResultSet = checkUpdateStatement.executeQuery();

            while (checkUpdateResultSet.next()){
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public ArrayList<Integer> getAllPersonsFromCreditsAccountId(String oldProductionId) {
        connection = DatabaseAccessHandler.getConnection();
        ArrayList<Integer> personsFromCreditsInfo = new ArrayList<>();

        try {
            PreparedStatement getAccountIdStatement = connection.prepareStatement(
                    "SELECT account_id FROM credits WHERE production_id = ?");
            getAccountIdStatement.setString(1, oldProductionId);

            ResultSet accountIdResultSet = getAccountIdStatement.executeQuery();

            while (accountIdResultSet.next()){
                personsFromCreditsInfo.add(accountIdResultSet.getInt(1));
            }

            return personsFromCreditsInfo;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return null;
    }

    public ArrayList<String[]> getBackupCreditsInfo(String newProductionId, String oldProductionId) {
        connection = DatabaseAccessHandler.getConnection();
        ArrayList<String[]> creditsInfo = new ArrayList<>();

        try {
            PreparedStatement getCreditsStatement = connection.prepareStatement(
                    "SELECT * FROM backup_credits where production_id = ?");
            getCreditsStatement.setString(1, oldProductionId);
            ResultSet creditsResultSet = getCreditsStatement.executeQuery();

            while (creditsResultSet.next()){
                creditsInfo.add(new String[]{Integer.toString(creditsResultSet.getInt(1)),
                        Integer.toString(creditsResultSet.getInt(2)),newProductionId,
                        creditsResultSet.getString(4), creditsResultSet.getString(5)});
            }

            return creditsInfo;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return null;

    }

    public void editInsertCredits(ArrayList<String[]> creditsInfo) {
        connection = DatabaseAccessHandler.getConnection();

        try {
            PreparedStatement insertCreditsStatement = connection.prepareStatement(
                    "INSERT INTO credits(id, account_id, production_id, role_type, " +
                            "role_name) VALUES(?,?,?,?,?)");

            for (String[] creditInfo : creditsInfo){
                insertCreditsStatement.setInt(1, Integer.parseInt(creditInfo[0]));
                insertCreditsStatement.setInt(2, Integer.parseInt(creditInfo[1]));
                insertCreditsStatement.setString(3, creditInfo[2]);
                insertCreditsStatement.setString(4, creditInfo[3]);
                insertCreditsStatement.setString(5, creditInfo[4]);

                insertCreditsStatement.addBatch();
            }

            insertCreditsStatement.executeBatch();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ArrayList<String[]> getLoggedInPersonsCredits(int account_id) {
        connection = DatabaseAccessHandler.getConnection();
        ArrayList<String> productionIds = new ArrayList<>();
        ArrayList<String> role_types = new ArrayList<>();
        ArrayList<String> role_names = new ArrayList<>();
        ArrayList<String> productionTitles = new ArrayList<>();
        ArrayList<String> productionTypes = new ArrayList<>();
        ArrayList<String[]> finalList = new ArrayList<>();

        try {
            PreparedStatement getCreditsStatement = connection.prepareStatement(
                    "SELECT production_id, role_type, role_name FROM credits WHERE account_id = ?");
            getCreditsStatement.setInt(1, account_id);

            ResultSet creditsResultSet = getCreditsStatement.executeQuery();

            while (creditsResultSet.next()){
                productionIds.add(creditsResultSet.getString(1));
                role_types.add(creditsResultSet.getString(2));
                role_names.add(creditsResultSet.getString(3));
            }

            for (String s : productionIds){
                PreparedStatement getProductionInfoStatement = connection.prepareStatement(
                        "SELECT title, production_type FROM productions WHERE production_id = ?");
                getProductionInfoStatement.setString(1, s);
                ResultSet productionInfoResultSet = getProductionInfoStatement.executeQuery();
                while (productionInfoResultSet.next()){
                    productionTitles.add(productionInfoResultSet.getString(1));
                    productionTypes.add(productionInfoResultSet.getString(2));
                }
                getProductionInfoStatement.close();
                productionInfoResultSet.close();
            }

            for (int i = 0; i < productionTitles.size(); i++){
                finalList.add(new String[]{productionTitles.get(i), role_types.get(i),
                        role_types.get(i), role_names.get(i)});
            }
            return finalList;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return null;
    }

    public boolean insertTvProgramCredits(ArrayList<String> creditsInfo) {
        connection = DatabaseAccessHandler.getConnection();

        try {
            PreparedStatement insertStatement = connection.prepareStatement(
                    "INSERT INTO credits(role_type, production_id, account_id) " +
                            "VALUES (?,?,?)");
            insertStatement.setString(1, creditsInfo.get(0));
            insertStatement.setString(2, creditsInfo.get(1));
            insertStatement.setInt(3, Integer.parseInt(creditsInfo.get(2)));
            insertStatement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return false;
    }

    public boolean deleteCredit(String role, String roleName, String productionId) {
        connection = DatabaseAccessHandler.getConnection();

        try {
            PreparedStatement deleteStatement = connection.prepareStatement(
                    "DELETE FROM credits WHERE production_id = ? AND role_type = ? AND role_name = ?");
            deleteStatement.setString(1, productionId);
            deleteStatement.setString(2, role);
            deleteStatement.setString(3, roleName);
            deleteStatement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return false;
    }

    public boolean deleteCreditFromTvProgram(String role, String productionId) {

        connection = DatabaseAccessHandler.getConnection();

        try {
            PreparedStatement deleteStatement = connection.prepareStatement(
                    "DELETE FROM credits WHERE production_id = ? AND role_type = ?");
            deleteStatement.setString(1, productionId);
            deleteStatement.setString(2, role);
            deleteStatement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return false;
    }

  /*  public boolean insertTvSeriesCredits(ArrayList<String> finalList) {
        connection = DatabaseAccessHandler.getConnection();

        try {
            PreparedStatement insertStatement = connection.prepareStatement(
                    "INSERT INTO credits(role_type, production_id, account_id) " +
                            "VALUES (?,?,?)");
            insertStatement.setString(1, creditsInfo.get(0));
            insertStatement.setString(2, creditsInfo.get(1));
            insertStatement.setInt(3, Integer.parseInt(creditsInfo.get(2)));
            insertStatement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return false;
    }*/
}
