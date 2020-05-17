package io.github.eurasia98.sem2.logic;

import io.github.eurasia98.sem2.persistence.DatabaseProductionManager;

import java.util.ArrayList;

public class ProductionManager {

    public ArrayList<String[]> getMyProductions(String username){
        DatabaseProductionManager databaseProductionManager = new DatabaseProductionManager();
        ArrayList<String[]> myProductionsInfo = databaseProductionManager.getMyProductions(username);
        return myProductionsInfo;
    }
}
