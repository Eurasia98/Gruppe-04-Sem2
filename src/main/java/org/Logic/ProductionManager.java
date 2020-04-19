package org.Logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class ProductionManager {
    private HashMap<Integer, Production> productions;

    public ProductionManager(){
        productions = new HashMap<Integer, Production>();
    }
    public Production createProduction(String title, int productionID, int releaseYear, Date creationDate, ArrayList<Credit> myCreditsList){
        Production production = new Production(title, productionID, releaseYear, creationDate, myCreditsList);
        productions.put(productionID, production);
        return production;
    }
    public Production loadProduction(int productionID){
        return productions.get(productionID);
    }
    public void saveProduction(Production production){
        productions.put(production.getProductionID(), production);
    }
    public void deleteProduction(Production production){
        productions.remove(production.getProductionID());
    }
    public void editProduction(Production production){
        saveProduction(production);
    }
}
