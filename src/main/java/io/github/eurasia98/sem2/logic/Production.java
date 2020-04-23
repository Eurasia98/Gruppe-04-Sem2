package io.github.eurasia98.sem2.logic;

import java.util.ArrayList;
import java.util.Date;

public class Production {
    private String title;
    private int productionID;
    private int releaseYear;
    private Date creationDate;
    private ArrayList<Credit> myCreditsList;

    public Production(String title, int productionID, int releaseYear, Date creationDate) {
        this.title = title;
        this.productionID = productionID;
        this.releaseYear = releaseYear;
        this.creationDate = creationDate;
        myCreditsList = null;
    }

    public Production(String title, int id, int productionID, Date creationDate, ArrayList<Credit> myCreditsList) {
        this.title = title;
        this.productionID = productionID;
        this.myCreditsList = myCreditsList;
    }

    public Production(String title, int productionID){
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

    public Date getCreationDate() {
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
