package org.Presentation;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import org.Data.DatabaseController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchScreenController {

    @FXML
    private Button buttonSearch;

    @FXML
    private TextField textFieldSearchInput;

    @FXML
    void buttonHandlerSearch(ActionEvent event) throws IOException {
        if (!textFieldSearchInput.getText().isEmpty()) {
            DatabaseController dc = new DatabaseController();
            Map<String, Integer> resultMap = dc.searchProduction(textFieldSearchInput.getText());
            displaySearchResults(resultMap);

        }
    }

    public void displaySearchResults(Map<String, Integer> productionMap) throws IOException {
        List<Hyperlink> hyperlinks = new ArrayList<>();
        for (String title : productionMap.keySet()) {
            Hyperlink hl = new Hyperlink(title);
            hl.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    App.switchToDisplaySearchResultsUI(productionMap.get(title));
                }
            });
            hyperlinks.add(hl);
        }


    }

}