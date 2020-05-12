package io.github.eurasia98.sem2.logic;

import io.github.eurasia98.sem2.persistence.DatabasePersonHandler;
import io.github.eurasia98.sem2.persistence.DatabaseProductionManager;

public class Credit {
    private int account_id;
    private String production_id;
    private String roleType;
    private String roleName;

    public Credit(int account_id, String production_id, String roleType, String roleName) {
        this.account_id = account_id;
        this.production_id = production_id;
        this.roleType = roleType;
        this.roleName = roleName;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public String getProduction_id() {
        return production_id;
    }

    public void setProduction_id(String production_id) {
        this.production_id = production_id;
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

    public String toString(){
        DatabasePersonHandler databasePersonHandler = new DatabasePersonHandler();
        Person person = databasePersonHandler.getPerson(getAccount_id());
        DatabaseProductionManager databaseProductionManager = new DatabaseProductionManager();
        Production production = databaseProductionManager.getProduction(getProduction_id());
        return person.getFirstName() + " " + person.getLastName() + "\t" + getRoleType() + "\t" + getRoleName();
    }
}