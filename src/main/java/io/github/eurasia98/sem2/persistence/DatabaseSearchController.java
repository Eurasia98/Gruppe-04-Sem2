package io.github.eurasia98.sem2.persistence;

import io.github.eurasia98.sem2.logic.Credit;
import io.github.eurasia98.sem2.logic.Person;
import io.github.eurasia98.sem2.logic.SearchResults;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseSearchController {

    static Connection connection;

    public ArrayList<SearchResults> search(String searchString){
        connection = DatabaseAccesHandler.getConnection();
        ArrayList<SearchResults> searchResultsArrayList = new ArrayList<>();

        try {
            PreparedStatement productionSearchStatement = connection.prepareStatement("SELECT * FROM productions WHERE title ilike ?");
            productionSearchStatement.setString(1,"%" + searchString + "%");
            ResultSet proRS = productionSearchStatement.executeQuery();

            while (proRS.next()){
                searchResultsArrayList.add(new SearchResults(1,proRS.getString("title"), proRS.getString("production_id")));
            }
            PreparedStatement personSearchStatement = connection.prepareStatement("SELECT * FROM persons WHERE first_name ilike ?");
            personSearchStatement.setString(1,"%" + searchString + "%");
            ResultSet perRS = personSearchStatement.executeQuery();

            while (perRS.next()) {
                searchResultsArrayList.add(new SearchResults(perRS.getString("first_name"), perRS.getString("id"),1));
            }


                return searchResultsArrayList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return null;
    }

    public ArrayList<Credit> searchCredits(SearchResults searchResults){
        connection = DatabaseAccesHandler.getConnection();
        ArrayList<Credit> creditArrayList = new ArrayList<>();

        try {
            PreparedStatement searchCreditsStatement = connection.prepareStatement("SELECT * FROM credits WHERE production_id = ?");
            searchCreditsStatement.setString(1, searchResults.getProductionId());

            ResultSet rs = searchCreditsStatement.executeQuery();

            DatabasePersonHandler databasePersonHandler = new DatabasePersonHandler();

            while (rs.next()){
                Credit credit = new Credit(rs.getInt("account_id"), rs.getString("production_id"),
                        rs.getString("role_type"), rs.getString("role_name"));
                creditArrayList.add(credit);
            }

            return creditArrayList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return null;
    }
}
