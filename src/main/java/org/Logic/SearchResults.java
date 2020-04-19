package org.Logic;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Hyperlink;

import java.util.ArrayList;
import java.util.List;

public class SearchResults {
    private String title;
    private int productionId;

    public String getTitle() {
        return title;
    }

    public int getProductionId() {
        return productionId;
    }

    public SearchResults(String title, int productionId){
        this.title = title;
        this.productionId = productionId;
    }

    // metode til at lave hyperlinks ud af de resultater der findes i en søgning.
    public List<Hyperlink> createHyperlinksResults(ArrayList<SearchResults> searchResults){
        List<Hyperlink> searchResultsList = new ArrayList<>();
        for (int i = 0; i < searchResults.size(); i++){
            Hyperlink hl = new Hyperlink(searchResults.get(i).title);
            searchResultsList.add(hl);
        }
        return searchResultsList;
    }
}
