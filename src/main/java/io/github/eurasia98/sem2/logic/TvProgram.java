package io.github.eurasia98.sem2.logic;

public class TvProgram extends Production {

    private String title;

    public TvProgram(String productionID, int id, String title, String productionType, int owner, String description, int releaseYear) {
        super(productionID, id, title, productionType, owner, description);
    }
}
