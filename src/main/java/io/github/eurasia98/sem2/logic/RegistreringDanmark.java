package io.github.eurasia98.sem2.logic;

public class RegistreringDanmark extends Account{

    public RegistreringDanmark(int id, String username, String password, String email) {
        super(id, username, password, email);
        super.setAccountType("RegistreringDanmark");
    }
}
