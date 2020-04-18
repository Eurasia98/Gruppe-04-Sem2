package org.Presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.Logic.CreditSystem;

import java.io.IOException;

public class SearchScreenController {

    @FXML
    private Button buttonSearch;

    @FXML
    private TextField textFieldSearch;

    @FXML
    void buttonHandlerSearch(ActionEvent event) throws IOException {
        if (!textFieldSearch.getText().isEmpty()) {
            App.creditSystem.initializeSearch(textFieldSearch.getText());


        }
    }

}