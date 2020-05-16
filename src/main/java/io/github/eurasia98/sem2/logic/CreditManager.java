package io.github.eurasia98.sem2.logic;

import io.github.eurasia98.sem2.persistence.DatabaseCreditsManager;

import java.util.ArrayList;

public class CreditManager {

    public Boolean insertCredit(Credit credit){
        DatabaseCreditsManager databaseCreditsManager = new DatabaseCreditsManager();
        ArrayList<String> creditsInfo = new ArrayList<>();
        creditsInfo.add(Integer.toString(credit.getAccount_id()));
        creditsInfo.add(credit.getProduction_id());
        creditsInfo.add(credit.getRoleType());
        creditsInfo.add(credit.getRoleName());
        return databaseCreditsManager.insertCredit(creditsInfo);
    }

    /*public Boolean insertCredit(Credit credit){
        DatabaseCreditsManager databaseCreditsManager = new DatabaseCreditsManager();
        return databaseCreditsManager.insertCredit(credit);
    }*/

    // fylder en arrayList med credit.toString() strings som fungere som rulletekster.
    public ArrayList<String> searchCredits(String production_id){
        DatabaseCreditsManager databaseCreditsManager = new DatabaseCreditsManager();
        ArrayList<String> creditsToDisplay = new ArrayList<>();
        ArrayList<String[]> creditsInfo = databaseCreditsManager.searchCredits(production_id);
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
}
