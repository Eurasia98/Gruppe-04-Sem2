package io.github.eurasia98.sem2.presentation;

public class ModelTablePersonMyCredits {
    private String title;
    private String production_type;
    private String role_type;
    private String role_name;

    public ModelTablePersonMyCredits(String title, String production_type, String role_type, String role_name) {
        this.title = title;
        this.production_type = production_type;
        this.role_type = role_type;
        this.role_name = role_name;
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

    public String getRole_type() {
        return role_type;
    }

    public void setRole_type(String role_type) {
        this.role_type = role_type;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }
}
