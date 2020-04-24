package io.github.eurasia98.sem2.persistence;

import io.github.eurasia98.sem2.logic.APerson;
import io.github.eurasia98.sem2.logic.Credit;
import io.github.eurasia98.sem2.logic.Production;
import io.github.eurasia98.sem2.logic.SearchResults;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DatabaseProductionManager {
    private File file;
    private Production production;
    private Credit credit;
    private APerson person;

    private File getFile(String fileName) {
        return new File(getClass().getClassLoader().getResource(fileName).getFile());
    }

    public int getProductionId(String title){
        file = getFile("Productions.txt");
        int productionId = 0;
        try {
            Scanner s = new Scanner(file);
            while (s.hasNextLine()){
                String line = s.nextLine();
                String[] lineArray = line.split(";");
                if (title.equalsIgnoreCase(lineArray[0])){
                    productionId = Integer.parseInt(lineArray[1]);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } return productionId;
    }

    public ArrayList<SearchResults> searchProductions(String searchString) {
        file = getFile("Productions.txt");
        ArrayList<SearchResults> searchResultsList = new ArrayList<SearchResults>();
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
        file = getFile("Productions.txt");
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



    public List<String> verifyLogin (String username, String password){
        file = getFile("Accounts.txt");
        List<String> accountInfo = new ArrayList<>();
        try {
            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
                String account = s.nextLine();
                String[] accountArray = account.split(";");
                if (username.equals(accountArray[1]) && password.equals(accountArray[2])){
                    String[] foundAccount = Arrays.copyOfRange(accountArray, 1, 5);
                    return Arrays.asList(foundAccount);
                }
            }
            accountInfo.add("Wrong username / password.");
            return accountInfo;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        accountInfo.add("System error. Try again.");
        return accountInfo;

    }
}