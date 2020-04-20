package io.github.eurasia98.sem2.logic;

import io.github.eurasia98.sem2.presentation.App;
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
        ArrayList<Hyperlink> finalList = new ArrayList<>();
        for (Hyperlink hl : hyperLinksToDisplay){
            hl.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    App.switchToDisplayCreditsScreen();
                }
            });
            finalList.add(hl);
        }
        return finalList;
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
