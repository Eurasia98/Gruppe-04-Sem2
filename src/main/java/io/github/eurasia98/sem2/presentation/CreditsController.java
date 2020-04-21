package io.github.eurasia98.sem2.presentation;

import io.github.eurasia98.sem2.logic.Credit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CreditsController {

    @FXML
    private ListView<String> ListviewCredits;

    @FXML
    private VBox VBoxDisplayCredits;

    public void displayCredits(List<Credit> creditList){
        for (Credit credit : creditList){
            ListviewCredits.getItems().add(credit.toString());
        }
    }
}