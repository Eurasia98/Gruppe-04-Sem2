package io.github.eurasia98.sem2.logic;

import java.util.ArrayList;
import java.util.List;

public class Producer extends Account {
    private String productionCompanyName;
    private String fName;
    private String lName;
    private int madeByAccountId;
//    private String email;
    private List<Production> myProductions = new ArrayList<>();

    public Producer(int userId, String username, String password, String accountType,
                    String productionCompanyName, String fName, String lName, String email,
                    List<Production> myProductions) {
        super(userId, username, password, accountType, email);
        this.productionCompanyName = productionCompanyName;
        this.fName = fName;
        this.lName = lName;
//        this.email = email;
        this.myProductions = myProductions;
    }

    public Producer(int userId, String username, String password, String fName, String lName, String email) {
        super(userId, username, password, email);
        this.fName = fName;
        this.lName = lName;
//        this.email = email;
        super.setAccountType("Producer");
    }

    public Producer(int userId, String username, String password, String fName, String lName, String email, String accountType){
        super(userId, username, password, accountType, email);
        this.fName = fName;
        this.lName = lName;
//        this.email = email;
    }

    public Producer (int userId, String firstName, String lastName, String companyName,String email, int madeByAccountId, String username, String password){
        super(userId, username, password, email);
        super.setAccountType("Producer");
        this.fName = firstName;
        this.lName = lastName;
        this.productionCompanyName = companyName;
        this.madeByAccountId = madeByAccountId;
    }

    public String getProductionCompanyName() {
        return productionCompanyName;
    }
    public String getFName() {
        return fName;
    }
    public String getLName() {
        return lName;
    }
//    public String getEmail() {
//        return email;
//    }

    @Override
    public String toString(){
        return String.format("%s;%s;%s;%s;%s;%s;", super.getId(), super.getUsername(), super.getPassword(), fName, lName, super.getAccountType());
    }
}
