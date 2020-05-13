package io.github.eurasia98.sem2.presentation;

public class ModelTableMyProductions {
    private String production_id;
    private String title;
    private String production_type;

    public ModelTableMyProductions(String production_id, String title, String production_type) {
        this.production_id = production_id;
        this.title = title;
        this.production_type = production_type;
    }

    public String getProduction_id() {
        return production_id;
    }

    public void setProduction_id(String production_id) {
        this.production_id = production_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProduction_type() {
        return production_type;
    }

    public void setProduction_type(String production_type) {
        this.production_type = production_type;
    }
}
