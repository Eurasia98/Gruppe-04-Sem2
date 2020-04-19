package org.Logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DatabaseController {
    private File file;
    private Production production;
    private Credit credit;
    private Person person;

    public List<SearchResults> searchProductions(String searchString) {
        file = new File("Productions.txt");
        List<SearchResults> searchResultsList = new ArrayList<SearchResults>();
        try {
            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                String[] productionsArray = line.split(";");
                if (productionsArray[0].toLowerCase().contains(searchString.toLowerCase())){
                    searchResultsList.add(new SearchResults(productionsArray[0], Integer.parseInt(productionsArray[1])));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return searchResultsList;
    }

    public String searchProductionTitleFromId(int productionId) {
        file = new File("Productions.txt");
        String title = null;
        try {
            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                String[] productionsArray = line.split(";");
                if (Integer.parseInt(productionsArray[1]) == productionId){
                    title = productionsArray[0];
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return title;
    }

    public List<Credit> searchCredits(int productionId) {
        file = new File("Credits.txt");
        List<Credit> creditsList = new ArrayList<>();
        try {
            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                String[] lineArray = line.split(";");
                if (productionId == Integer.parseInt(lineArray[0])){
                    person = new Person(lineArray[1], lineArray[2]);
                    production = new Production(searchProductionTitleFromId(productionId), productionId);
                    credit = new Credit(person, production, lineArray[3], lineArray[4]);
                    creditsList.add(credit);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return creditsList;
    }
}