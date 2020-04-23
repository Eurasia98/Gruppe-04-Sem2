package io.github.eurasia98.sem2.presentation;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SearchScreenController implements Initializable {

    @FXML
    private Button buttonSearch;

    @FXML
    private VBox vBoxSearchResults;

    @FXML
    private TextField textFieldSearch;

    @FXML
    private ImageView ivLogo;

    /* håndterer "søg" og "søg igen" knapperne. Sender data
     *  videre til App og videre til creditsystem, der laver
     * det om til hyperlinks.
     * */
    @FXML
    void buttonHandlerSearch(ActionEvent event) throws IOException {
        search();
    }

    public void ivLogoActionHandler(MouseEvent mouseEvent) {
        App.switchScene("FrontPage");
    }

    public void search() throws IOException {
        if (!textFieldSearch.getText().isEmpty()) {
            vBoxSearchResults.setVisible(true);
            ListView listView = new ListView();
            ArrayList<Hyperlink> hyperlinkArrayList = App.creditSystem.userSearch(textFieldSearch.getText());
            ArrayList<Hyperlink> finalList = new ArrayList<>();
            for (Hyperlink hl : hyperlinkArrayList){
                hl.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        App.setSelectedTitle(hl);
                        App.switchScene("DisplayCreditsFirstIteration");
                    }
                });
                finalList.add(hl);
            }
            listView.getItems().addAll(vBoxSearchResults.getChildren().addAll(finalList));
        }
    }


    public VBox getVBoxSearchResults() {
        return vBoxSearchResults;
    }

    public TextField getTextFieldSearch(){
            return textFieldSearch;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}