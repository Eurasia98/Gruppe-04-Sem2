package io.github.eurasia98.sem2.presentation;

public class ModelTableChoosenSeasonToEdit {
    private String episode_number;
    private String title;
    private String season_id;

    public ModelTableChoosenSeasonToEdit(String episode_number, String title, String season_id) {
        this.episode_number = episode_number;
        this.title = title;
        this.season_id = season_id;
    }

    public String getEpisode_number() {
        return episode_number;
    }

    public void setEpisode_number(String episode_number) {
        this.episode_number = episode_number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSeason_id() {
        return season_id;
    }

    public void setSeason_id(String season_id) {
        this.season_id = season_id;
    }
}
