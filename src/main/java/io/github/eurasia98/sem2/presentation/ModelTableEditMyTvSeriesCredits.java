package io.github.eurasia98.sem2.presentation;

public class ModelTableEditMyTvSeriesCredits {
    private String name;
    private String roleType;
    private String roleName;

    public ModelTableEditMyTvSeriesCredits(String name, String roleType, String roleName) {
        this.name = name;
        this.roleType = roleType;
        this.roleName = roleName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
