package io.github.eurasia98.sem2.logic;

import java.util.ArrayList;
import java.util.List;

public class Producer extends Account {
    private String productionCompanyName;
    private String fName;
    private String lName;
    private String email;
    private List<Production> myProductions = new ArrayList<>();

    public Producer(int userId, String username, String password, String accountType,
                    String productionCompanyName, String fName, String lName, String email,
                    List<Production> myProductions) {
        super(userId, username, password, accountType);
        this.productionCompanyName = productionCompanyName;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.myProductions = myProductions;
    }

    public Producer(String username, String password, String fName, String lName, String email) {
        super(username, password);
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        super.setAccountType("Producer");
    }

    //    AUser user1 = new AUser("MediaKing" ,"1234" , "User", "TV2", "Jens", "Jensen", "Jensen.tv2.dk");

    public String getProductionCompanyName() {
        return productionCompanyName;
    }
    public String getFName() {
        return fName;
    }
    public String getLName() {
        return lName;
    }
    public String getEmail() {
        return email;
    }

    @Override
    public String toString(){
        return String.format("%s;%s;%s;%s;%s;%s;", super.getUserId(), super.getUsername(), super.getPassword(), fName, lName, super.getAccountType());
    }
}
