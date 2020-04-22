package io.github.eurasia98.sem2.presentation;

import io.github.eurasia98.sem2.logic.Credit;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.util.List;


public class CreditsController {

    @FXML
    private TextArea txtAreaCreditsDisplay;

    //metode til at tilføje credits til et tomt textarea så user kan se det. Virker ikke efter planen
    public void displayCredits(List<Credit> creditList){
        StringBuilder txt = new StringBuilder();
        for (Credit credit : creditList){
            txt.append(credit.toString()).append("\n");
        }
        setText(txt.toString());
    }
    //sætter tekst på textarea
    public void setText(String text){
        this.txtAreaCreditsDisplay.setText(text);
    }

    public String getText(){
        return txtAreaCreditsDisplay.getText();
    }

}