package io.github.eurasia98.sem2.presentation;

public class ModelTableEditMyCredits {
    private String firstName;
    private String lastName;
    private String account_id;
    private String title;
    private String productionType;
    private String roleType;
    private String roleName;

    public ModelTableEditMyCredits(String firstName, String lastName, String account_id,
                                   String title, String productionType, String roleType,
                                   String roleName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.account_id = account_id;
        this.title = title;
        this.productionType = productionType;
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

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProductionType() {
        return productionType;
    }

    public void setProductionType(String productionType) {
        this.productionType = productionType;
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
