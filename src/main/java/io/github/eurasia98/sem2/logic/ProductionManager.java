package io.github.eurasia98.sem2.logic;

import io.github.eurasia98.sem2.persistence.DatabaseProductionHandler;

import java.util.ArrayList;

public class ProductionManager {

    public ArrayList<String[]> getMyProductions(String username){
        DatabaseProductionHandler databaseProductionHandler = new DatabaseProductionHandler();
        ArrayList<String[]> myProductionsInfo = databaseProductionHandler.getMyProductions(username);
        return myProductionsInfo;
    }
}
