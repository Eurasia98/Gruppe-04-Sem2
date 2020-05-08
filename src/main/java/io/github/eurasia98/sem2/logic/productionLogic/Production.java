package io.github.eurasia98.sem2.logic.productionLogic;

import io.github.eurasia98.sem2.logic.creditLogic.Credit;

import java.time.LocalDate;
import java.util.ArrayList;

public class Production {
    private String title;
    private int productionID;
    private int releaseYear;
    private LocalDate creationDate;
    private ArrayList<Credit> myCreditsList;

    public Production(String title, int productionID, int releaseYear, LocalDate creationDate, ArrayList<Credit> myCreditsList) {
        this.title = title;
        this.productionID = productionID;
        this.releaseYear = releaseYear;
        this.creationDate = creationDate;
        this.myCreditsList = myCreditsList;
    }

    public Production(String title, int productionID) {
        this.title = title;
        this.productionID = productionID;
    }

    public String getTitle() {
        return title;
    }

    public int getProductionID() {
        return productionID;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public ArrayList<Credit> getMyCreditsList() {
        return myCreditsList;
    }

    @Override
    public String toString(){
        return title;
    }

    public void printMyCreditsList(){
        for (int i = 0; i < myCreditsList.size(); i++){
            System.out.println(myCreditsList.get(i).toString());
        }
    }
}
