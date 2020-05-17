package io.github.eurasia98.sem2.logic;

public class TvProgramEpisode extends Production{
    private String episode_number;
    private String episode_title;

    public TvProgramEpisode(String productionID, String episode_number, String episode_title) {
        super(productionID);
        this.episode_number = episode_number;
        this.episode_title = episode_title;
    }

    public String getEpisode_number() {
        return episode_number;
    }

    public void setEpisode_number(String episode_number) {
        this.episode_number = episode_number;
    }

    public String getEpisode_title() {
        return episode_title;
    }

    public void setEpisode_title(String episode_title) {
        this.episode_title = episode_title;
    }
}
