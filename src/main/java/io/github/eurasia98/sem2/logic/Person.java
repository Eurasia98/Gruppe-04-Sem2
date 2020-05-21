package io.github.eurasia98.sem2.logic;

import java.util.ArrayList;

public class Person extends Account {

    private int id;
    private String firstName;
    private String lastName;
    private ArrayList<Credit> myCreditsList;
    private String created_by;

    public Person(int id, int account_id, String username, String password, String firstName, String lastName,
                  String created_by) {
        super(account_id, username, password, "Person");
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.created_by = created_by;
    }

    public Person(String username, String password, String firstName, String lastName,
                  ArrayList<Credit> myCreditsList) {
        super(username, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.myCreditsList = myCreditsList;
        super.setAccountType("Person");
    }

    public Person(String username, String password, String firstName, String lastName, String created_by) {
        super(username, password);
        this.firstName = firstName;
        this.lastName = lastName;
        super.setAccountType("Person");
        this.created_by = created_by;
    }

    public Person (int id, int accountId, String firstName, String lastName, String createdBy, String username, String password, String email){
        super(accountId, username, password, email);
        super.setAccountType("Person");
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.created_by = createdBy;
    }

    public void addCreditsToPersonalList(Credit credit){
        myCreditsList.add(credit);
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public ArrayList<Credit> getMyCreditsList() {
        return myCreditsList;
    }

    public String getCreated_by() {
        return created_by;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMyCreditsList(ArrayList<Credit> myCreditsList) {
        this.myCreditsList = myCreditsList;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }
}
