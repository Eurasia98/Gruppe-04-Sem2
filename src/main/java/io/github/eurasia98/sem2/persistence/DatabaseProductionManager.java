package io.github.eurasia98.sem2.persistence;

import io.github.eurasia98.sem2.logic.APerson;
import io.github.eurasia98.sem2.logic.Credit;
import io.github.eurasia98.sem2.logic.Production;
import io.github.eurasia98.sem2.logic.SearchResults;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DatabaseProductionManager {
    private File file;
    private Production production;
    private Credit credit;
    private APerson person;

    private File getFile(String fileName) {
        return new File(getClass().getClassLoader().getResource(fileName).getFile());
    }

    public ArrayList<SearchResults> searchProductions(String searchString) {
        file = getFile("Productions.txt");
        ArrayList<SearchResults> searchResultsList = new ArrayList<>();
        try {
            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                String[] productionsArray = line.split(";");
                if (productionsArray[0].toLowerCase().contains(searchString.toLowerCase())) {
                    searchResultsList.add(new SearchResults(productionsArray[0], Integer.parseInt(productionsArray[1])));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (SearchResults sr : searchResultsList) {
            System.out.println(" " + sr.toString());
        }
        return searchResultsList;
    }

    public void saveProduction(String title, int productionID, int releaseYear, LocalDate creationDate) throws Exception {
        String titleFromID = searchProductionTitleFromId(productionID);
        if (titleFromID != null) {
            throw new Exception("ProductionID already in database");
        }
        try {
            FileWriter filew = new FileWriter(getFile("Productions.txt"), true);
            String data = title + ";" + Integer.toString(productionID) + ";" + Integer.toString(releaseYear) + ";" + creationDate.toString() + "\n";
            filew.write(data);
            filew.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ;


    }

    public String searchProductionTitleFromId(int productionId) {
        file = getFile("Productions.txt");
        String title = null;
        try {
            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                String[] productionsArray = line.split(";");
                if (Integer.parseInt(productionsArray[1]) == productionId) {
                    title = productionsArray[0];
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return title;
    }

    public int searchProductionIdFromTitle(String title){
        file = new File("Productions.txt");
        int productionId;
        try {
            Scanner s = new Scanner(file);
            while (s.hasNextLine()){
                String line = s.nextLine();
                String[] pArray = line.split(";");
                if (title.equalsIgnoreCase(pArray[0])){
                    productionId = Integer.parseInt(pArray[1]);
                    return productionId;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } return 0;
    }

    public List<Credit> searchCredits(int productionId) {
        file = new File("Credits.txt");
        List<Credit> creditsList = new ArrayList<>();
        try {
            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                String[] lineArray = line.split(";");
                if (productionId == Integer.parseInt(lineArray[0])) {
                    person = new APerson(lineArray[1], lineArray[2]);
                    production = new Production(searchProductionTitleFromId(productionId), productionId,0, LocalDate.now(), null);
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
