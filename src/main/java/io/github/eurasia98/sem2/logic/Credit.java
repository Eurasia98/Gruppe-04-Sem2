package io.github.eurasia98.sem2.logic;

import io.github.eurasia98.sem2.persistence.DatabasePersonHandler;

import java.util.ArrayList;

public class Credit {
    private int id;
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

    public Credit(int id, int account_id, String production_id, String roleType, String roleName) {
        this.id = id;
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
        ArrayList<String> personInfo = databasePersonHandler.getPersonInfo(getAccount_id());
        Person person = new Person(personInfo.get(2), personInfo.get(3), personInfo.get(4),
                personInfo.get(5), personInfo.get(7));
        return person.getFirstName() + " " + person.getLastName() + "\t" + getRoleType() + "\t" + getRoleName();
    }
}