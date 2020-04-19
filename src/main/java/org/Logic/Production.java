package org.Logic;

import java.util.ArrayList;
import java.util.Date;

public class Production {
    private String title;
    private int productionID;
    private int releaseYear;
    private Date creationDate;
    private ArrayList<Credit> myCreditsList;

    public Production(String title, int productionID, int releaseYear, Date creationDate, ArrayList<Credit> myCreditsList) {
        this.title = title;
        this.productionID = productionID;
        this.releaseYear = releaseYear;
        this.creationDate = creationDate;
        this.myCreditsList = myCreditsList;
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
}
