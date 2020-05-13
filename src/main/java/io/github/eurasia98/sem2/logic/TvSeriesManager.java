package io.github.eurasia98.sem2.logic;

import io.github.eurasia98.sem2.persistence.DatabaseTvSeriesHandler;

import java.util.ArrayList;

public class TvSeriesManager {
    public void insertTvSeries(TvSeries tvSeries){
        DatabaseTvSeriesHandler databaseTvSeriesHandler = new DatabaseTvSeriesHandler();
        ArrayList<String> seriesInfo = new ArrayList<>();
        seriesInfo.add(tvSeries.getProductionID());
        seriesInfo.add(tvSeries.getTitle());
        seriesInfo.add(tvSeries.getProductionType());
        seriesInfo.add(Integer.toString(tvSeries.getOwner()));
        seriesInfo.add(tvSeries.getSeries_id());
        seriesInfo.add(tvSeries.getDescription());
        databaseTvSeriesHandler.insertTvSeries(seriesInfo);
    }
}
