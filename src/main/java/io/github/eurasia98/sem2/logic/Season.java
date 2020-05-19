package io.github.eurasia98.sem2.logic;

import java.util.ArrayList;

public class Season extends Production {
    private String season_title;


    public Season(String title, String productionID) {
        super(title, productionID);
    }

    public String getSeason_title() {
        return season_title;
    }

    public void setSeason_title(String season_title) {
        this.season_title = season_title;
    }
}
