package io.github.eurasia98.sem2.logic;

import io.github.eurasia98.sem2.logic.accountLogic.AccountManager;
import io.github.eurasia98.sem2.logic.accountLogic.Person;
import io.github.eurasia98.sem2.logic.accountLogic.PersonManager;

public class CreditSystem {
   public Boolean availableUsername(String username){
      AccountManager accountManager = new AccountManager();
      return accountManager.checkUsernameAvailability(username) == true;
   }

   public Boolean createNewPerson(String username, String password, String firstName, String lastName){
      PersonManager personManager = new PersonManager();
      return personManager.saveNewPerson(new Person(username, password, firstName, lastName));
   }
}
