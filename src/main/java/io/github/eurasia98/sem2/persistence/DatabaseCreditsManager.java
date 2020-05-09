package io.github.eurasia98.sem2.persistence;


import io.github.eurasia98.sem2.logic.Credit;
import io.github.eurasia98.sem2.logic.Movie;
import io.github.eurasia98.sem2.logic.Person;
import io.github.eurasia98.sem2.logic.Production;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseCreditsManager {
    private static Connection connection = null;

    public Boolean insertCredit(Credit credit){
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
    }
}
