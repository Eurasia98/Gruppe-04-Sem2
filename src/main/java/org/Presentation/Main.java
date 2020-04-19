package org.Presentation;

import org.Logic.CreditSystem;

public class Main {
    public static void main(String[] args) {
        App.injectCreditSystem(new CreditSystem());
        App.launch();
    }
}
