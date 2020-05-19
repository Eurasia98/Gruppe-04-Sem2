package io.github.eurasia98.sem2.presentation;

public class ModelTableEditMyCredits {
    private String firstName;
    private String lastName;
    private String roleType;
    private String roleName;

    public ModelTableEditMyCredits(String firstName, String lastName, String roleType, String roleName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.roleType = roleType;
        this.roleName = roleName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
