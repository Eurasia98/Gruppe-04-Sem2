package io.github.eurasia98.sem2.logic;

import io.github.eurasia98.sem2.persistence.DatabaseProductionManager;

import java.util.ArrayList;

public class ProductionManager {

    public ArrayList<String> getMyProductions(int account_id){
        DatabaseProductionManager databaseProductionManager = new DatabaseProductionManager();
        ArrayList<String[]> myProductionsInfo = databaseProductionManager.getMyProductions(account_id);
        ArrayList<String> myProductionsList = new ArrayList<>();

        for (String[] s : myProductionsInfo){
            Production production = createMyProductions(s);
            myProductionsList.add(production.toString());
        }

        return myProductionsList;
    }

    public Production createMyProductions(String[] productionInfo){
        AccountManager accountManager = new AccountManager();
        Production production = new Production(productionInfo[0], Integer.parseInt(productionInfo[1]), productionInfo[2],
                productionInfo[3], accountManager.getAccount_id(Integer.parseInt(productionInfo[4])));
        return production;
    }
}
