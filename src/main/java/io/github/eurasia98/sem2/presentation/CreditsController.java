package io.github.eurasia98.sem2.presentation;

import io.github.eurasia98.sem2.logic.Credit;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class CreditsController implements Initializable {

    @FXML
    private TextArea txtAreaCreditsDisplay;

    public TextArea getTxtAreaCreditsDisplay() {
        return txtAreaCreditsDisplay;
    }

    //metode til at tilføje credits til et tomt textarea så user kan se det. Virker ikke efter planen
    public void displayCredits(String credits){
        App.switchScene("DisplayCreditsFirstIteration");
        setText(credits);
    }

    //sætter tekst på textarea
    public void setText(String text){
        txtAreaCreditsDisplay.setText(text);
    }

    public String getText(){
        return txtAreaCreditsDisplay.getText();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}