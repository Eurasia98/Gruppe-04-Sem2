package io.github.eurasia98.sem2.logic;

public class TvSeries extends Production{
    private int season;
    private int episode;
    private String episode_title;
    private String format_audio;
    private String description;
    private String extendedcast;
    private String editor;


    public TvSeries(String title, int productionId, int season, int episode){
        super(title, productionId);
        this.season = season;
        this.episode = episode;
    }
}
