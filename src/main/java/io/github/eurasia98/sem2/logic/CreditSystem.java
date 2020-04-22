package io.github.eurasia98.sem2.logic;

import io.github.eurasia98.sem2.presentation.App;
import io.github.eurasia98.sem2.presentation.CreditsController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Hyperlink;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CreditSystem {
    public static DatabaseController databaseController = new DatabaseController();
    private SearchResults sResults;

    public CreditSystem(){}

    
    public ArrayList<Hyperlink> userSearch(String searchString){
        List<SearchResults> productionsList = databaseController.searchProductions(searchString);
        ArrayList<Hyperlink> hyperLinksToDisplay = createHyperlinksToDisplay(productionsList);

        return hyperLinksToDisplay;
    }

    public ArrayList<Hyperlink> createHyperlinksToDisplay(List<SearchResults> searchResultsList){
        ArrayList<Hyperlink> resultsList = new ArrayList<>();
        return resultsList;
    }

    public String searchCredits(String title){
        int id = databaseController.getProductionId(title);
        return databaseController.searchCredits(id);
    }


    public String login (String username, String password){
        Login login = new Login();
        String verification = null;
        System.out.println("1");
        verification = login.loginVerify(username, password);
        System.out.println("2");
        return verification;
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
