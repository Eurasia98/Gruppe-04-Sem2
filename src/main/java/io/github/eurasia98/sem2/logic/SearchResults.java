package io.github.eurasia98.sem2.logic;

import io.github.eurasia98.sem2.presentation.App;
import io.github.eurasia98.sem2.presentation.CreditsController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Hyperlink;

import java.util.ArrayList;
import java.util.List;

public class SearchResults {
    private String title;
    private int productionId;
    private CreditSystem cs = new CreditSystem();

    public SearchResults(String title, int productionId){
        this.title = title;
        this.productionId = productionId;
    }

    public String getTitle() {
        return title;
    }

    public int getProductionId(){return productionId; }

    @Override
    public String toString(){
        return title;
    }




}
