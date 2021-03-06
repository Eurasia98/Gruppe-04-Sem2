package io.github.eurasia98.sem2.presentation;

public class ModelTableTvProgramEpisode {
    private String episode_number;
    private String episode_title;
    private String episode_id;

    public ModelTableTvProgramEpisode(String episode_number, String episode_title, String episode_id) {
        this.episode_number = episode_number;
        this.episode_title = episode_title;
        this.episode_id = episode_id;
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

    public String getEpisode_id() {
        return episode_id;
    }

    public void setEpisode_id(String episode_id) {
        this.episode_id = episode_id;
    }
}
