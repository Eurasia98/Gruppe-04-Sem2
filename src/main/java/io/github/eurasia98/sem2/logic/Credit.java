package io.github.eurasia98.sem2.logic;

public class Credit {
    private APerson person;
    private Production production;
    private String roleType;
    private String roleName;


    public Credit(APerson person, Production production, String roleType, String roleName) {
        this.person = person;
        this.production = production;
        this.roleType = roleType;
        this.roleName = roleName;
    }

    public APerson getPerson() {
        return person;
    }

    public void setPerson(APerson person) {
        this.person = person;
    }

    public Production getProduction() {
        return production;
    }

    public void setProduction(Production production) {
        this.production = production;
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

    @Override
    public String toString(){
        return person.getFirstName() + " " + person.getLastName() + "\t" + roleType + "\t" + roleName;
//        return String.format("%s %10s");
    }
}
