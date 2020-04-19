package org.Logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DatabaseController {
    File file;

    public List<SearchResults> searchProductions(String searchString) {
        file = new File("Productions.txt");
        List<SearchResults> searchResultsList = new ArrayList<SearchResults>();
        try {
            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                String[] productionsArray = line.split(";");
                searchResultsList.add(new SearchResults(productionsArray[0], Integer.parseInt(productionsArray[1])));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return searchResultsList;
    }

    public String[][] searchCredits(int productionId) {
        file = new File("Credits.txt");
        ArrayList<String[]> creditsArrayList = new ArrayList<>();
        try {
            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                String[] lineArray = line.split(";");
                if (productionId == Integer.parseInt(lineArray[0])) {
                    creditsArrayList.add(lineArray);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}