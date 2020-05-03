package io.github.eurasia98.sem2.logic;

import io.github.eurasia98.sem2.persistence.DatabaseCreditsManager;
import io.github.eurasia98.sem2.persistence.DatabaseProductionManager;
import javafx.scene.control.Hyperlink;

import java.util.ArrayList;

public class CreditSystem {
    private static DatabaseProductionManager databaseProductionManager = new DatabaseProductionManager();
    private ProducerManager producerManager = new ProducerManager();
    private DatabaseCreditsManager databaseCreditsManager = new DatabaseCreditsManager();

    public static DatabaseProductionManager getDatabaseProductionManager() {
        return databaseProductionManager;
    }

    public ProducerManager getProducerManager() {
        return producerManager;
    }

    public ArrayList<Hyperlink> userSearch(String searchString){
        ArrayList<SearchResults> productionsList = databaseProductionManager.searchProductions(searchString);
        ArrayList<Hyperlink> hyperLinksToDisplay = createHyperlinksResults(productionsList);
        return hyperLinksToDisplay;
    }

    public String searchCredits(String title){
        int id = databaseProductionManager.searchProductionIdFromTitle(title);
        return databaseCreditsManager.searchCredits(id);
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
            searchResultsList.add(hl);
        }
        return searchResultsList;
    }

    public static void main(String[] args) {
        System.out.println("Kebab on wheels is a go!");
    }

   }
