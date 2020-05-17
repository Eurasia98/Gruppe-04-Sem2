package io.github.eurasia98.sem2.logic;

public class RegistreringDanmark extends Account{

    public RegistreringDanmark(int id, String username, String password, String accountType) {
        super(id, username, password, accountType);
    }

    public RegistreringDanmark(int id, String username, String password) {
        super(id, username, password);
    }

    public RegistreringDanmark(String username, String password) {
        super(username, password);
    }
}
