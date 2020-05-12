package io.github.eurasia98.sem2.logic;

import io.github.eurasia98.sem2.persistence.DatabaseProductionManager;

public class ProductionManager {
    public Production createProduction(String title, String production_id){
        Production production = new Production(title, production_id);
        return production;
    }

    public Boolean insertProduction(Production production){
        DatabaseProductionManager databaseProductionManager = new DatabaseProductionManager();
        return databaseProductionManager.insertProduction(production);
    }
}
