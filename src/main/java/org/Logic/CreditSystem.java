package org.Logic;

import javafx.event.ActionEvent;
import javafx.scene.control.Hyperlink;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreditSystem {
    public static DatabaseController databaseController = new DatabaseController();
    private SearchResults sResults;

    public CreditSystem(){}

    
    public ArrayList<Hyperlink> search(String searchString){
        List<SearchResults> productionsList = databaseController.searchProductions(searchString);
        ArrayList<Hyperlink> hyperLinksToDisplay = createHyperlinksToDisplay(productionsList);
        return hyperLinksToDisplay;
    }

    public ArrayList<Hyperlink> createHyperlinksToDisplay(List<SearchResults> searchResultsList){
        ArrayList<Hyperlink> resultsList = new ArrayList<>();
        for (SearchResults s : searchResultsList){
            Hyperlink hl = new Hyperlink(s.getTitle());
            resultsList.add(hl);
        }
        return resultsList;
    }

    public void searchCredits(String title){

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
