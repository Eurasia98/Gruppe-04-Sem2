package io.github.eurasia98.sem2.logic;

public class User extends Account{
    private String productionCompanyName;
    private String fName;
    private String lName;
    private String email;

    public User(String username, String password, int typeNum,String productionCompanyName,
                String fName, String lName, String email) {
        super(username, password, typeNum);
        this.productionCompanyName = productionCompanyName;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
    }
    User user1 = new User("MediaKing" ,"1234" , 2, "TV2", "Jens", "Jensen", "Jensen.tv2.dk");

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
