package io.github.eurasia98.sem2.presentation;

public class ModelTableChoosenSeasonToEdit {
    private String episode_number;
    private String title;
    private String id;

    public ModelTableChoosenSeasonToEdit(String episode_number, String title, String id) {
        this.episode_number = episode_number;
        this.title = title;
        this.id = id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
