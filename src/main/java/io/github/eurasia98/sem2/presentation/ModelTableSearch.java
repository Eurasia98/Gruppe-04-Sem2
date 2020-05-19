package io.github.eurasia98.sem2.presentation;

public class ModelTableSearch {
    private String title;
    private String production_id;

    public ModelTableSearch(String title, String production_id) {
        this.title = title;
        this.production_id = production_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProduction_id() {
        return production_id;
    }

    public void setProduction_id(String production_id) {
        this.production_id = production_id;
    }
}
