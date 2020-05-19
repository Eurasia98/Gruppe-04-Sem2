package io.github.eurasia98.sem2.logic;

public class SystemAdministrator extends Account {

    public SystemAdministrator(int userId, String username, String password, String email){
        super(userId, username, password, email);
        super.setAccountType("SystemAdministrator");
    }


}
