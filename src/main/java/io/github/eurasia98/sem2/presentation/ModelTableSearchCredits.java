package io.github.eurasia98.sem2.presentation;

public class ModelTableSearchCredits {
    private String name;
    private String role;
    private String role_type;

    public ModelTableSearchCredits(String name, String role, String role_type) {
        this.name = name;
        this.role = role;
        this.role_type = role_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole_type() {
        return role_type;
    }

    public void setRole_type(String role_type) {
        this.role_type = role_type;
    }
}
