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
        ArrayList<SearchResults> productionsList = databaseController.searchProductions(searchString);

        ArrayList<Hyperlink> hyperLinksToDisplay = createHyperlinksResults(productionsList);

        return hyperLinksToDisplay;
    }

    public String searchCredits(String title){
        int id = databaseController.getProductionId(title);
        return databaseController.searchCredits(id);
    }


    public String login (String username, String password){
        Login login = new Login();
        String verification = null;
        verification = login.loginVerify(username, password);
        return verification;
    }

    // metode til at lave hyperlinks ud af de resultater der findes i en søgning.
    public ArrayList<Hyperlink> createHyperlinksResults(ArrayList<SearchResults> searchResults){
        ArrayList<Hyperlink> searchResultsList = new ArrayList<>();
        for (int i = 0; i < searchResults.size(); i++){
            Hyperlink hl = new Hyperlink(searchResults.get(i).getTitle());
//            hl.setOnAction(new EventHandler<ActionEvent>() {
//                @Override
//                public void handle(ActionEvent actionEvent) {
//                    App.switchScene("DisplayCreditsFirstIteration");
//                    CreditsController creditsController = new CreditsController();
//                    creditsController.displayCredits(searchCredits(hl.getText()));
//                }
//            });

            searchResultsList.add(hl);
        }
        return searchResultsList;
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
