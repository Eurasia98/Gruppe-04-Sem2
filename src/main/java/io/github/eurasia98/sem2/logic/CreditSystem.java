package io.github.eurasia98.sem2.logic;

public class CreditSystem {
   public Boolean availableUsername(String username){
      AccountManager accountManager = new AccountManager();
      return accountManager.checkUsernameAvailability(username) == true;
   }

   public Boolean createNewPerson(String username, String password, String firstName, String lastName){
      PersonManager personManager = new PersonManager();
      return personManager.saveNewPerson(new Person(username, password, firstName, lastName));
   }

   public Boolean createNewMovie(String title, String productionId){
      MovieManager movieManager = new MovieManager();
      return movieManager.saveMovie(movieManager.createMovie(title, productionId));
   }
}
