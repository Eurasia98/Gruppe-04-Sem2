package io.github.eurasia98.sem2.persistence;


import io.github.eurasia98.sem2.logic.Credit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseCreditsManager {
    private static Connection connection = null;

    public void insertCredit(ArrayList<String> creditsInfo){
        try {
            this.connection = DatabaseAccesHandler.getConnection();

            PreparedStatement insertCreditStatement = connection.prepareStatement("INSERT INTO credits(account_id, " +
                    "production_id, role_type, role_name) VALUES(?,?,?,?) ");
            insertCreditStatement.setInt(1, Integer.parseInt(creditsInfo.get(0)));
            insertCreditStatement.setString(2, creditsInfo.get(1));
            insertCreditStatement.setString(3, creditsInfo.get(2));
            insertCreditStatement.setString(4, creditsInfo.get(3));

            insertCreditStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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
        connection = DatabaseAccesHandler.getConnection();
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
}
