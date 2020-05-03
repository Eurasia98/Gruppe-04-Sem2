package io.github.eurasia98.sem2.logic;

import io.github.eurasia98.sem2.persistence.DatabaseProductionManager;
import javafx.scene.chart.PieChart;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class ProductionManager {
    private HashMap<Integer, Production> productions;
    private DatabaseProductionManager databaseProductionManager;
    public ProductionManager() {
        productions = new HashMap<Integer, Production>();
        databaseProductionManager = new DatabaseProductionManager();
    }

    public Production createProduction(String title, int productionID, int releaseYear, LocalDate creationDate, ArrayList<Credit> myCreditsList) {
        Production production = new Production(title, productionID, releaseYear, creationDate, myCreditsList);
        productions.put(productionID, production);
        return production;
    }

    public Production loadProduction(int productionID) {
        return productions.get(productionID);
    }

    public void saveProduction(Production production) throws Exception {
        databaseProductionManager.saveProduction(production.getTitle(), production.getProductionID(), production.getReleaseYear(), production.getCreationDate());
    }

    public void deleteProduction(Production production) {
        productions.remove(production.getProductionID());
    }

    public void editProduction(Production production) throws Exception {
        saveProduction(production);
    }
}
