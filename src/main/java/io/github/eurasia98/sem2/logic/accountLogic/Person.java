package io.github.eurasia98.sem2.logic.accountLogic;

import io.github.eurasia98.sem2.logic.creditLogic.Credit;

import java.util.ArrayList;
import java.util.List;

public class Person extends Account {

    private String firstName;
    private String lastName;
    private String email;
    private ArrayList<Credit> myCreditsList;

    public Person(String username, String password, String firstName, String lastName,
                  String email, ArrayList<Credit> myCreditsList) {
        super(username, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.myCreditsList = myCreditsList;
        super.setAccountType("Person");
    }

    public Person(String username, String password, String firstName, String lastName,
                  ArrayList<Credit> myCreditsList) {
        super(username, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.myCreditsList = myCreditsList;
        super.setAccountType("Person");
    }

    public Person(String username, String password, String firstName, String lastName, String email) {
        super(username, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        super.setAccountType("Person");
    }

    public Person(String username, String password, String firstName, String lastName) {
        super(username, password);
        this.firstName = firstName;
        this.lastName = lastName;
        super.setAccountType("Person");
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Credit> getMyCreditsList() {
        return myCreditsList;
    }

    public void setMyCreditsList(ArrayList<Credit> myCreditsList) {
        this.myCreditsList = myCreditsList;
    }

    public void addCreditsToPersonalList(Credit credit){
        myCreditsList.add(credit);
    }
}
