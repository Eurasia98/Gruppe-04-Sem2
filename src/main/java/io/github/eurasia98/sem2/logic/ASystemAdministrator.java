package io.github.eurasia98.sem2.logic;

public class ASystemAdministrator extends Account {

    public ASystemAdministrator(int userId, String username, String password, String accountType){
        super(userId, username, password, accountType);
    }

    public ASystemAdministrator() {
        super("Systemadministrator");
    }

    public void createProducer(String fName, String lName){
        Producer producer = new Producer(fName, lName);
    }



}
