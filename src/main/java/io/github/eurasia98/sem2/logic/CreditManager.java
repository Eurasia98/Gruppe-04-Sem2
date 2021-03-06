package io.github.eurasia98.sem2.logic;

import io.github.eurasia98.sem2.persistence.DatabaseCreditsHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CreditManager {

    public Boolean insertCredit(ArrayList<String> creditInfo){
        DatabaseCreditsHandler databaseCreditsHandler = new DatabaseCreditsHandler();
        return databaseCreditsHandler.insertCredit(creditInfo);
    }

    /*public Boolean insertCredit(Credit credit){
        DatabaseCreditsManager databaseCreditsManager = new DatabaseCreditsManager();
        return databaseCreditsManager.insertCredit(credit);
    }*/

    // fylder en arrayList med credit.toString() strings som fungere som rulletekster.
    public ArrayList<String> searchCredits(String production_id){
        DatabaseCreditsHandler databaseCreditsHandler = new DatabaseCreditsHandler();
        ArrayList<String> creditsToDisplay = new ArrayList<>();
        ArrayList<String[]> creditsInfo = databaseCreditsHandler.searchCredits(production_id);
        for (String[] creditsArray : creditsInfo){
            creditsToDisplay.add(createCreditToDisplay(creditsArray).toString());
        }
        return creditsToDisplay;
    }

    // tager String[] fra metoden oppe over (searchCredits) der indeholder alt info om en credit fra credit table
    // og laver et creditobjekt ud fra det. Returnere denne credit.
    public Credit createCreditToDisplay(String[] creditsInfo){
        ArrayList<Credit> creditsToDisplay = new ArrayList<>();
            Credit credit = new Credit(Integer.parseInt(creditsInfo[0]), Integer.parseInt(creditsInfo[1]),
                    creditsInfo[2], creditsInfo[3], creditsInfo[4]);
            creditsToDisplay.add(credit);
            return credit;
    }

    public ArrayList<String[]> sortMovieCreditsNames(ArrayList<String[]> creditsInfo){
        Collections.sort(creditsInfo,new Comparator<String[]>() {
            public int compare(String[] strings, String[] compareStrings) {
                return strings[2].compareTo(compareStrings[1]);
            }
        });
        return creditsInfo;
    }
}
