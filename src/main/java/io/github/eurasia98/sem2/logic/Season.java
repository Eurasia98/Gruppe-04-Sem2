package io.github.eurasia98.sem2.logic;

import java.util.ArrayList;

public class Season {
    private String season_title;
    private ArrayList<Episode> season_episodes;

    public Season(String season_title, ArrayList<Episode> season_episodes) {
        this.season_title = season_title;
        this.season_episodes = season_episodes;
    }

    public String getSeason_title() {
        return season_title;
    }

    public void setSeason_title(String season_title) {
        this.season_title = season_title;
    }

    public ArrayList<Episode> getSeason_episodes() {
        return season_episodes;
    }

    public void setSeason_episodes(ArrayList<Episode> season_episodes) {
        this.season_episodes = season_episodes;
    }
}
