package io.github.eurasia98.sem2.logic;

public class ASystemAdministrator extends Account {

    public ASystemAdministrator() {
        super("Systemadministrator");
    }

    public void createProducer(){
        Producer producer = new Producer("Lars", "Jensen");
    }



}
