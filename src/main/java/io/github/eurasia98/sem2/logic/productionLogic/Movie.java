package io.github.eurasia98.sem2.logic.productionLogic;

import io.github.eurasia98.sem2.logic.creditLogic.Credit;

import java.time.LocalDate;
import java.util.ArrayList;

public class Movie extends Production {

    public Movie(String title, String productionID){
        super(title, productionID);
        super.setProductionType("Movie");

    }
}
