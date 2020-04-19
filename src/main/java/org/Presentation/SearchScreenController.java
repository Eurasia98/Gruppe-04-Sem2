package org.Presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.Logic.CreditSystem;
import org.Logic.SearchResults;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
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
            VboxSearchResults.getChildren().addAll(App.creditSystem.search(textFieldSearch.getText()));
            VboxSearchResults.setVisible(true);
        }
    }

}