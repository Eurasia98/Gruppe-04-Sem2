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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtAreaCreditsDisplay.appendText(App.creditSystem.searchCredits(App.selectedTitle));
    }
}