package io.github.eurasia98.sem2.logic;

public class TvProgram extends Production {

    private String productionYear;
    private String productionCountry;
    private String series_id;
    private String episode_number;
    private String distributor_episode_count;

    public TvProgram(String productionID, int id, String title, String productionType, int owner, String description, int releaseYear) {
        super(productionID, id, title, productionType, owner, description, releaseYear);
    }
}
