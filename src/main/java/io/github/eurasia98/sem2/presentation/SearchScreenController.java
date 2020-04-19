package io.github.eurasia98.sem2.presentation;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

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
            ListView listView = new ListView();
            VboxSearchResults.setVisible(true);
            ArrayList<Hyperlink> hyperlinkArrayList = App.creditSystem.search(textFieldSearch.getText());
            ArrayList<Hyperlink> finalList;
            for (Hyperlink hl : hyperlinkArrayList){
                hl.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        Scene scene = new Scene();

                    }
                });
            }
            listView.getItems().addAll(VboxSearchResults.getChildren().addAll(hyperlinkArrayList));
        }
    }

}