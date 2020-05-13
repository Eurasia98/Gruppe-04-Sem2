package io.github.eurasia98.sem2.logic;

import java.util.ArrayList;

public class TvSeries extends Production {
    private String series_id;
    private ArrayList<Season> seasons;

    public TvSeries(String title, String productionID, String series_id, int owner_id, String description) {
        super(title, productionID);
        this.series_id = series_id;
        super.setOwner(owner_id);
        super.setDescription(description);
        super.setProductionType("Serie");
    }

    public String getSeries_id() {
        return series_id;
    }

    public void setSeries_id(String series_id) {
        this.series_id = series_id;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(ArrayList<Season> seasons) {
        this.seasons = seasons;
    }
}
