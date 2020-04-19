package org.Logic;

import org.Presentation.DisplaySearchResultsController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CreditSystem {
    static DatabaseController databaseController = new DatabaseController();

    public List<Map<String, Integer>> initializeSearch(String title) {
        List<Map<String, Integer>> searchResults = new ArrayList<>();
        searchResults.add(databaseController.searchProduction(title));
        return searchResults;
    }
    
    public void search(String searchString){
//        DatabaseController.Search(searchString);
    }

    public String login (String username, String password){
        String accountType = null;
//      accountType = Login.loginVerify(username, password);

        if (accountType.equals("Denied")){
            return "Wrong username / password.";
        }

        return "Login successful.";
    }

    public static void main(String[] args) {
        System.out.println("Kebab on wheels is a go!");
    }

    /*public void displaySearchResults(List<Map<String, Integer>> searchResults) {
        List<Hyperlink> hyperlinksList = new ArrayList<>();
        Iterator<>
        for (Map.Entry<String, Integer> entry : {
            Hyperlink h1 = new Hyperlink(resultMap.entrySet())
        }*/

   }
