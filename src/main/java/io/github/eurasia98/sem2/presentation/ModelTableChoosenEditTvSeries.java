package io.github.eurasia98.sem2.presentation;

public class ModelTableChoosenEditTvSeries {
    private String season_number;
    private String id;

    public ModelTableChoosenEditTvSeries(String season_number, String id) {
        this.season_number = season_number;
        this.id = id;
    }

    public String getSeason_number() {
        return season_number;
    }

    public void setSeason_number(String season_number) {
        this.season_number = season_number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
