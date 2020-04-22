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
        CreditsController cr = new CreditsController();
        for (SearchResults s : searchResultsList){
            Hyperlink hl = new Hyperlink(s.getTitle());
            hl.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    App.switchScene("DisplayCreditsFirstIteration");
                    cr.displayCredits(databaseController.searchCredits(databaseController.getProductionId(hl.getText())));
                }
            });
            resultsList.add(hl);
        }
        return resultsList;
    }

    public List<Credit> searchCredits(String title){
        int id = databaseController.getProductionId(title);
        List<Credit> creditList = databaseController.searchCredits(id);
        return creditList;
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
