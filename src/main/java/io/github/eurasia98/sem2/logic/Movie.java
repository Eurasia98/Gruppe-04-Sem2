package io.github.eurasia98.sem2.logic;

public class Movie extends Production {

    public Movie(String title, String productionID){
        super(title, productionID);
        super.setProductionType("Movie");

    }
}
