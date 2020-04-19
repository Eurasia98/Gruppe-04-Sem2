package org.Presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.Logic.CreditSystem;

import java.io.IOException;
import java.util.Map;

public class SearchScreenController {

    @FXML
    private VBox VboxSearchResults;

    @FXML
    private Button buttonSearch;

    @FXML
    private TextField textFieldSearch;

    @FXML
    void buttonHandlerSearch(ActionEvent event) throws IOException {
        if (!textFieldSearch.getText().isEmpty()) {
            App.creditSystem.databaseController.searchProductions(textFieldSearch.getText());
        }
    }

    @FXML
    public void initialize() throws Exception {
        VboxSearchResults.setVisible(false);
    }

    public void updateUI(){
        VboxSearchResults.setVisible(true);
    }

}