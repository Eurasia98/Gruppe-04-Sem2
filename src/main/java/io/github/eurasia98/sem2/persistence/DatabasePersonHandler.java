package io.github.eurasia98.sem2.persistence;

import java.sql.*;
import java.util.ArrayList;

public class DatabasePersonHandler {
    static Connection connection = null;

    public void insertPerson(ArrayList<String> personInfo){
        try {
            this.connection = DatabaseAccessHandler.getConnection();

            DatabaseAccountHandler databaseAccountHandler = new DatabaseAccountHandler();
            databaseAccountHandler.insertAccount(personInfo); // gemmer data på en account i databasen

            PreparedStatement insertPersonStatement = connection.prepareStatement(
                    "INSERT INTO persons(account_id, account_username, account_password, " +
                            "first_name, last_name, created_by) VALUES(?,?,?,?,?,?)");
            insertPersonStatement.setInt(1, getId(personInfo.get(0)));
            insertPersonStatement.setString( 2, personInfo.get(0));
            insertPersonStatement.setString(3, personInfo.get(1));
            insertPersonStatement.setString(4, personInfo.get(2));
            insertPersonStatement.setString(5, personInfo.get(3));
            insertPersonStatement.setString(6, personInfo.get(4));
            insertPersonStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    // gemmer data på en person i databasen. Er udkommenteret fordi den bryder 3-lags-arkitekter ved at modtage et
    // objekt fra logik i persistence.
    /*public Boolean insertPerson(Person person){
        try {
            this.connection = DatabaseAccesHandler.getConnection();

            DatabaseAccountHandler databaseAccountHandler = new DatabaseAccountHandler();
            databaseAccountHandler.insertAccount(person); // gemmer data på en account i databasen
            PreparedStatement insertPersonStatement = connection.prepareStatement(
                    "INSERT INTO persons(account_id, account_username, account_password, first_name, last_name) VALUES(?,?,?,?,?)");
            insertPersonStatement.setInt(1, getId(person.getUsername()));
            insertPersonStatement.setString( 2, person.getUsername());
            insertPersonStatement.setString(3, person.getPassword());
            insertPersonStatement.setString(4, person.getFirstName());
            insertPersonStatement.setString(5, person.getLastName());
            insertPersonStatement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return false;
    }*/

    // Slet er person i databasen.
    public Boolean deletePerson(String username){
        connection = DatabaseAccessHandler.getConnection();

        try {
            PreparedStatement deletePersonStatement = connection.prepareStatement("DELETE FROM persons WHERE account_username = ?");
            deletePersonStatement.setString(1, username);
            deletePersonStatement.execute();

            //DatabaseAccountHandler databaseAccountHandler = new DatabaseAccountHandler();
            //databaseAccountHandler.resetIdCount();
            //resetIdCount();

            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return false;
    }

    public int getId(String username){
        try {
            connection = DatabaseAccessHandler.getConnection();
            PreparedStatement searchIdStatement = connection.prepareStatement("SELECT id FROM accounts WHERE username = ?");
            searchIdStatement.setString(1, username);
            ResultSet rs = searchIdStatement.executeQuery();
            while (rs.next()){
                return rs.getInt("id");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return 0;
    }

    // opretter et person objekt ud fra database info på account_id.
    public ArrayList<String> getPersonInfo(int account_id){
        connection = DatabaseAccessHandler.getConnection();
        ArrayList<String> personInfoList = new ArrayList<>();
        try {
            PreparedStatement getPersonStatement = connection.prepareStatement("SELECT * FROM persons WHERE account_id = ?");
            getPersonStatement.setInt(1, account_id);

            ResultSet personsResultSet = getPersonStatement.executeQuery();

            while (personsResultSet.next()){
                personInfoList.add(Integer.toString(personsResultSet.getInt(1)));
                personInfoList.add(Integer.toString(personsResultSet.getInt(2)));
                personInfoList.add(personsResultSet.getString(3));
                personInfoList.add(personsResultSet.getString(4));
                personInfoList.add(personsResultSet.getString(5));
                personInfoList.add(personsResultSet.getString(6));
                personInfoList.add(personsResultSet.getString(7));
            }

            return personInfoList;

            // Dette er udkommenteret fordi det bryder 3-lags-arkitektur ved at oprette objekter fra logik i persistence.
            /*PersonManager personManager = new PersonManager();

            while (rs.next()){
                Person person = personManager.createPerson(rs.getString("account_username"), rs.getString(
                        "account_password"), rs.getString("first_name"), rs.getString("last_name"));
                return person;
            }*/
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return null;
    }

    // resetter personIdCounter. Lavet til at resette counter ved sletning af row i database, men er udkommenteret
    // fordi at den kun vil have effekt hvis det er den seneste oprettede der bliver slettet.
    /*public Boolean resetIdCount(){
        connection = DatabaseAccesHandler.getConnection();

        try {
            PreparedStatement resetIdCounterStatement = connection.prepareStatement(
                    "SELECT MAX(id) FROM persons ");
            ResultSet currentId = resetIdCounterStatement.executeQuery();
            while (currentId.next()){
                PreparedStatement restIdStatement = connection.prepareStatement("ALTER TABLE persons AUTO_INCREMENT = ?");
                restIdStatement.setInt(1, currentId.getInt(0)+1);
                restIdStatement.execute();
                return true;
            } return false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return false;
    }*/

    public ArrayList<String[]> getMyPersons(String ownerUsername){
        connection = DatabaseAccessHandler.getConnection();
        DatabaseAccountHandler databaseAccountHandler;
        ArrayList<String[]> personsInfo = new ArrayList<>();

        try {
            PreparedStatement getMyPersonsStatement = connection.prepareStatement(
                    "SELECT account_id, first_name, last_name FROM persons WHERE created_by = ?");
            getMyPersonsStatement.setString(1, ownerUsername);
            ResultSet rs = getMyPersonsStatement.executeQuery();

            while (rs.next()){
                databaseAccountHandler = new DatabaseAccountHandler();
                ArrayList<String> accountInfo = databaseAccountHandler.getAccount(rs.getInt(1));

                personsInfo.add(new String[]{Integer.toString(rs.getInt(1)), rs.getString(2),
                        rs.getString(3), getAmountOfCredits(rs.getInt(1)), accountInfo.get(4)});

            }
            return personsInfo;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return null;
    }

    public String getAmountOfCredits(int account_id){
        PreparedStatement getPersonsCreditsStatement = null;
        try {
            getPersonsCreditsStatement = connection.prepareStatement(
                    "SELECT * FROM credits WHERE account_id = ?");
            getPersonsCreditsStatement.setInt(1, account_id);

            int amountOfCredits = 0;
            ResultSet rs = getPersonsCreditsStatement.executeQuery();
            while (rs.next()){
                amountOfCredits++;
            }

            return Integer.toString(amountOfCredits);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return null;
    }

    // Returnere ArrayList<String[]> hver hvert StringArray indeholder firstname, lastname og accountid.
    public ArrayList<String> getPersonToEditMyCredits(int accountId) {
        connection = DatabaseAccessHandler.getConnection();
        ArrayList<String> personsToEditList = new ArrayList<>();

        try {
            PreparedStatement getPersonsStatement = connection.prepareStatement(
                    "SELECT first_name, last_name FROM persons WHERE account_id = ?");
            getPersonsStatement.setInt(1, accountId);
            ResultSet getPersonsResults = getPersonsStatement.executeQuery();

            while (getPersonsResults.next()){
                personsToEditList.add(Integer.toString(accountId));
                personsToEditList.add(getPersonsResults.getString(1));
                personsToEditList.add(getPersonsResults.getString(2));
            }
            return personsToEditList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return null;
    }

    public void insertBackupPersons(ArrayList<Integer> personInfo) {
        connection = DatabaseAccessHandler.getConnection();

        try {
            PreparedStatement insertBackupPersonsStatement = connection.prepareStatement(
                    "INSERT INTO backup_persons(id, account_id, account_username, account_password, " +
                            "first_name, last_name, created_by) VALUES(?,?,?,?,?,?,?)");


            for (int account_id : personInfo){
                ArrayList<String> personInfoArray = getPersonInfo(account_id);
                insertBackupPersonsStatement.setInt(1, Integer.parseInt(personInfoArray.get(0)));
                insertBackupPersonsStatement.setInt(2, Integer.parseInt(personInfoArray.get(1)));
                insertBackupPersonsStatement.setString(3, personInfoArray.get(2));
                insertBackupPersonsStatement.setString(4, personInfoArray.get(3));
                insertBackupPersonsStatement.setString(5, personInfoArray.get(4));
                insertBackupPersonsStatement.setString(6, personInfoArray.get(5));
                insertBackupPersonsStatement.setString(7, personInfoArray.get(6));

                insertBackupPersonsStatement.addBatch();
            }
            insertBackupPersonsStatement.executeBatch();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public boolean checkBackupPersons() {
        connection = DatabaseAccessHandler.getConnection();

        try {
            PreparedStatement checkBackupStatement = connection.prepareStatement(
                    "SELECT * FROM backup_persons");
            ResultSet backupResultSet = checkBackupStatement.executeQuery();

            if (backupResultSet.next()){
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return false;
    }

    public ArrayList<String[]> getBackupPersons() {
        connection = DatabaseAccessHandler.getConnection();
        ArrayList<String[]> personsInfo = new ArrayList<>();

        try {
            PreparedStatement getPersonsStatement = connection.prepareStatement(
                    "SELECT * FROM backup_persons");
            ResultSet personsResultSet = getPersonsStatement.executeQuery();

            while (personsResultSet.next()){
                personsInfo.add(new String[]{Integer.toString(personsResultSet.getInt(1)),
                        Integer.toString(personsResultSet.getInt(2)),personsResultSet.getString(3),
                        personsResultSet.getString(4), personsResultSet.getString(5),
                personsResultSet.getString(6), personsResultSet.getString(7)});
            }

            return personsInfo;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return null;
    }

    public void editInsertPersons(ArrayList<String[]> personsInfo) {
        connection = DatabaseAccessHandler.getConnection();

        try {
            PreparedStatement insertPersonsStatement = connection.prepareStatement(
                    "INSERT INTO persons(id, account_id, account_username, account_password, " +
                            "first_name, last_name, created_by) VALUES(?,?,?,?,?,?,?)");

            for (String[] personInfo : personsInfo){
                insertPersonsStatement.setInt(1, Integer.parseInt(personInfo[0]));
                insertPersonsStatement.setInt(2, Integer.parseInt(personInfo[1]));
                insertPersonsStatement.setString(3, personInfo[2]);
                insertPersonsStatement.setString(4, personInfo[3]);
                insertPersonsStatement.setString(5, personInfo[4]);
                insertPersonsStatement.setString(6, personInfo[5]);
                insertPersonsStatement.setString(7, personInfo[6]);

                insertPersonsStatement.addBatch();
            }

            insertPersonsStatement.executeBatch();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
