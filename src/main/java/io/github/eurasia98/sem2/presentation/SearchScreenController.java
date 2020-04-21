package io.github.eurasia98.sem2.presentation;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchScreenController {

    @FXML
    private VBox vBoxSearchResults;

    @FXML
    private Button buttonSearch;

    @FXML
    private TextField textFieldSearch;


    @FXML
    void buttonHandlerSearch(ActionEvent event) throws IOException {
        if (!textFieldSearch.getText().isEmpty()) {
            vBoxSearchResults.setVisible(true);
            ListView listView = new ListView();
            listView.getItems().addAll(vBoxSearchResults.getChildren().addAll(
                    App.creditSystem.userSearch(textFieldSearch.getText())));
        }
    }

}