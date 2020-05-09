package io.github.eurasia98.sem2.logic;

import io.github.eurasia98.sem2.persistence.DatabaseCreditsManager;

public class CreditManager {
    public Credit createCredit(Person person, Production production, String roleType, String rolename){
        Credit credit = new Credit(person.getUserId(), production.getProductionID(), roleType, rolename);
        return credit;
    }

    public Boolean insertCredit(Credit credit){
        DatabaseCreditsManager databaseCreditsManager = new DatabaseCreditsManager();
        return databaseCreditsManager.insertCredit(credit);
    }
}
