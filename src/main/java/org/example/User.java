package org.example;

public class User extends Account{
    private String productionCompanyName;
    private String fName;
    private String lName;
    private String email;

    public User(String username, String password,String productionCompanyName, String fName, String lName, String email, int typeNum) {
        super(username, password, typeNum);
        this.productionCompanyName = productionCompanyName;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
    }
    User
    user1 = new User("FirstUser", "Password1234","Tv2", "Jens", "Jensen", "Jens@Jensen.tv2.dk", 1);

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
}
