package io.github.eurasia98.sem2.presentation;

public class ModelTableMyPersons {
    private String account_id;
    private String firstName;
    private String lastName;
    private String amountOfCredits;

    public ModelTableMyPersons(String account_id, String firstName, String lastName, String amountOfCredits) {
        this.account_id = account_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.amountOfCredits = amountOfCredits;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
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

    public String getAmountOfCredits() {
        return amountOfCredits;
    }

    public void setAmountOfCredits(String amountOfCredits) {
        this.amountOfCredits = amountOfCredits;
    }
}
