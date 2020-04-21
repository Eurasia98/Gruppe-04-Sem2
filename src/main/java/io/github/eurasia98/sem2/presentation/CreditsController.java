package io.github.eurasia98.sem2.presentation;

import io.github.eurasia98.sem2.logic.Credit;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import java.util.List;


public class CreditsController {

    @FXML
    private TextArea txtAreaCreditsDisplay;

    public void displayCredits(List<Credit> creditList){
        StringBuilder txt = new StringBuilder();
        for (Credit credit : creditList){
            txt.append(credit.toString()).append("\n");
        }
        txtAreaCreditsDisplay.setText(txt.toString());
    }

}