package org.Logic;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Hyperlink;
import org.Data.DatabaseController;
import org.Presentation.App;
import org.Presentation.DisplaySearchResultsController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CreditSystem {
    static DatabaseController databaseController = new DatabaseController();

    public List<Map<String, Integer>> initializeSearch(String title) {
        List<Map<String, Integer>> searchResults = new ArrayList<>();
        searchResults.add(databaseController.searchProduction(title));
        return searchResults;
    }

    /*public void displaySearchResults(List<Map<String, Integer>> searchResults) {
        List<Hyperlink> hyperlinksList = new ArrayList<>();
        Iterator<>
        for (Map.Entry<String, Integer> entry : {
            Hyperlink h1 = new Hyperlink(resultMap.entrySet())
        }*/

   }
