package io.github.eurasia98.sem2.logic;

import io.github.eurasia98.sem2.logic.Credit;

import java.time.LocalDate;
import java.util.ArrayList;

public class Production {
    private String productionID;
    private int id;
    private String title;
    private String productionType;
    private Account owner;
    private String description;
    private int releaseYear;
    private LocalDate creationDate;
    private ArrayList<Credit> myCreditsList;

    public Production(String productionID, int id, String title, String productionType, Account owner,
                      String description, int releaseYear) {
        this.productionID = productionID;
        this.id = id;
        this.title = title;
        this.productionType = productionType;
        this.owner = owner;
        this.description = description;
        this.releaseYear = releaseYear;
        myCreditsList = new ArrayList<>();
    }

    public Production(String title, String productionID) {
        this.title = title;
        this.productionID = productionID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProductionID() {
        return productionID;
    }

    public void setProductionID(String productionID) {
        this.productionID = productionID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductionType() {
        return productionType;
    }

    public void setProductionType(String productionType) {
        this.productionType = productionType;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public ArrayList<Credit> getMyCreditsList() {
        return myCreditsList;
    }

    public void setMyCreditsList(ArrayList<Credit> myCreditsList) {
        this.myCreditsList = myCreditsList;
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
