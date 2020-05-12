package io.github.eurasia98.sem2.logic;

import io.github.eurasia98.sem2.persistence.DatabaseCreditsManager;

import java.util.ArrayList;

public class CreditManager {

    public Boolean insertCredit(Credit credit){
        DatabaseCreditsManager databaseCreditsManager = new DatabaseCreditsManager();
        return databaseCreditsManager.insertCredit(credit);
    }

    public ArrayList<String> searchCredits(String production_id){
        DatabaseCreditsManager databaseCreditsManager = new DatabaseCreditsManager();
        ArrayList<String> creditsToDisplay = new ArrayList<>();
        ArrayList<String[]> creditsInfo = databaseCreditsManager.searchCredits(production_id);
        for (String[] creditsArray : creditsInfo){
            creditsToDisplay.add(createCreditToDisplay(creditsArray).toString());
        }
        return creditsToDisplay;
    }

    public Credit createCreditToDisplay(String[] creditsInfo){
        ArrayList<Credit> creditsToDisplay = new ArrayList<>();
            Credit credit = new Credit(Integer.parseInt(creditsInfo[0]), Integer.parseInt(creditsInfo[1]),
                    creditsInfo[2], creditsInfo[3], creditsInfo[4]);
            creditsToDisplay.add(credit);
            return credit;
    }
}
