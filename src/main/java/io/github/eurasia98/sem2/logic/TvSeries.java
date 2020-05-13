package io.github.eurasia98.sem2.logic;

public class TvSeries extends Production {
    private int season;
    private int episode;
    private String episode_title;
    private String format_audio;
    private String description;
    private String extendedcast;
    private String editor;

    public TvSeries(String productionID, int id, String title, String productionType, int owner,
                    String description, int releaseYear, int season, int episode, String episode_title,
                    String format_audio, String description1, String extendedcast, String editor) {
        super(productionID, id, title, productionType, owner, description, releaseYear);
        this.season = season;
        this.episode = episode;
        this.episode_title = episode_title;
        this.format_audio = format_audio;
        this.description = description1;
        this.extendedcast = extendedcast;
        this.editor = editor;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public int getEpisode() {
        return episode;
    }

    public void setEpisode(int episode) {
        this.episode = episode;
    }

    public String getEpisode_title() {
        return episode_title;
    }

    public void setEpisode_title(String episode_title) {
        this.episode_title = episode_title;
    }

    public String getFormat_audio() {
        return format_audio;
    }

    public void setFormat_audio(String format_audio) {
        this.format_audio = format_audio;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExtendedcast() {
        return extendedcast;
    }

    public void setExtendedcast(String extendedcast) {
        this.extendedcast = extendedcast;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }
}
