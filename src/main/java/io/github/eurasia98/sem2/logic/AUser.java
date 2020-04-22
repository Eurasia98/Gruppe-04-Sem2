package io.github.eurasia98.sem2.logic;

public class AUser extends Account{
    private String productionCompanyName;
    private String fName;
    private String lName;
    private String email;

    public AUser(String username, String password, String accountType, String productionCompanyName,
                 String fName, String lName, String email) {
        super(username, password, accountType);
        this.productionCompanyName = productionCompanyName;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
    }

    public AUser(String username, String password, String accountType, String productionCompanyName,
                 String fName, String lName){
        super(username, password, accountType);
        this.productionCompanyName = productionCompanyName;
        this.fName = fName;
        this.lName = lName;
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
}
