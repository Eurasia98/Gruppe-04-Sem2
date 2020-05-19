package io.github.eurasia98.sem2.logic;

import java.time.LocalDate;
import java.util.ArrayList;

public class Production {
    private String productionID;
    private int id;
    private String title;
    private String productionType;
    private int owner;
    private String description;

    public Production(String productionID) {
        this.productionID = productionID;
    }

    public Production(String productionID, int id, String title, String productionType, int owner,
                      String description) {
        this.productionID = productionID;
        this.id = id;
        this.title = title;
        this.productionType = productionType;
        this.owner = owner;
        this.description = description;
    }

    public Production(String title, String productionID) {
        this.title = title;
        this.productionID = productionID;
    }

    public Production(String productionID, int id, String title, String productionType, int owner) {
        this.productionID = productionID;
        this.id = id;
        this.title = title;
        this.productionType = productionType;
        this.owner = owner;
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

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString(){
        return title;
    }
}
