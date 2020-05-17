package io.github.eurasia98.sem2.logic;

import java.util.ArrayList;

public class TvSeries extends Production {
    private String series_id;

    public TvSeries(String title, String productionID) {
        super(title, productionID);
    }

    public String getSeries_id() {
        return series_id;
    }

    public void setSeries_id(String series_id) {
        this.series_id = series_id;
    }
}
