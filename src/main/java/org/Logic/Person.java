package org.Logic;

import java.util.ArrayList;

public class Person { //extends Account {

    private String accountNumber;
    private String firstName;
    private String lastName;
    private String email;
    private ArrayList<String> myCreditsList;

    public Person(String accountNumber, String firstName, String lastName, String email) {
        this.accountNumber = accountNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Person(String accountNumber, String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public ArrayList showMyCredits(){
        this.myCreditsList = DatabaseController.showMyCredits(accountNumber);
        return this.myCreditsList;
    }
}
