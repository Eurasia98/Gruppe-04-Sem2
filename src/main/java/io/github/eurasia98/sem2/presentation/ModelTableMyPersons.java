package io.github.eurasia98.sem2.presentation;

public class ModelTableMyPersons {
    private String account_id;
    private String first_name;
    private String last_name;
    private String amountOfCredits;
    private String email;

    public ModelTableMyPersons(String account_id, String first_name, String last_name, String amountOfCredits, String email) {
        this.account_id = account_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.amountOfCredits = amountOfCredits;
        this.email = email;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAmountOfCredits() {
        return amountOfCredits;
    }

    public void setAmountOfCredits(String amountOfCredits) {
        this.amountOfCredits = amountOfCredits;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
