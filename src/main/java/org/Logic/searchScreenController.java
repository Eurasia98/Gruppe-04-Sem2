package org.Logic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class searchScreenController {

    @FXML
    private TextField textFieldSearch;

    @FXML
    private Button searchButton;

    @FXML
    void buttonHandlerSearch(ActionEvent event) {
        if (!textFieldSearch.getText().isEmpty()){
            DatabaseController databaseController = new DatabaseController();
            databaseController.searchProduction(textFieldSearch.getText());
        }
    }

}
