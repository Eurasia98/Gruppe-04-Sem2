package io.github.eurasia98.sem2.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CreditsController {

    @FXML
    private VBox VBoxDisplayCredits;

    @FXML
    private ListView<String> ListviewCredits;

    public void displayCredits(List<String> stringList){
//        ArrayList creditsList = new ArrayList<>();
        VBoxDisplayCredits.setVisible(true);
        ListviewCredits.getItems().addAll(stringList);
    }
}