package io.github.eurasia98.sem2.logic;

import java.util.ArrayList;

public class Person { //extends Account {

    private int accountNumber;
    private String firstName;
    private String lastName;
    private String email;
    private ArrayList<String> myCreditsList;
    static int accountCounter = 0;

    public Person(String firstName, String lastName, String email) {
        accountCounter++;
        this.accountNumber = accountCounter;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;

    }

    public Person(String firstName, String lastName) {
        accountCounter++;
        this.accountNumber = accountCounter;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /*public ArrayList showMyCredits(){
        this.myCreditsList = DatabaseController.showMyCredits(accountNumber);
        return this.myCreditsList;
    }*/

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public ArrayList<String> getMyCreditsList() {
        return myCreditsList;
    }
}
