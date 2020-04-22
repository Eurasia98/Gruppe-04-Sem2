package io.github.eurasia98.sem2.logic;

import java.util.ArrayList;

public class APerson { //extends Account {

    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private ArrayList<String> myCreditsList;

    public APerson(String username, String firstName, String lastName, String email) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;

    }

    public APerson(String username, String firstName, String lastName) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public APerson(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

   /*public ArrayList showMyCredits(){
        this.myCreditsList = DatabaseController.showMyCredits(accountNumber);
        return this.myCreditsList;
    }*/

    public String getUsername() {
        return username;
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
