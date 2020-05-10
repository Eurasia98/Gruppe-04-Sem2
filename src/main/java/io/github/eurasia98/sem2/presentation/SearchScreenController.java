package io.github.eurasia98.sem2.presentation;

import io.github.eurasia98.sem2.logic.SearchResults;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SearchScreenController implements Initializable {

    @FXML
    private ImageView ivLogo;

    @FXML
    private VBox vBoxSearchResults;

    @FXML
    private TextField txtFieldSearch;

    @FXML
    private ImageView ivSearch;

    @FXML
    void ivLogoActionHandler(MouseEvent event) {
        App.switchScene("FrontPage");
    }

    // Enables search by pressing return key
    @FXML
    private void txtFieldSearchKeyPressHandler(KeyEvent event) throws IOException {
        if (event.getCode().toString().equals("ENTER")){
            ivSearchMouseClickHandler();
        }
    }

    @FXML
    private void ivSearchMouseClickHandler() throws IOException {
        if(!txtFieldSearch.getText().isEmpty()) {
            search();
        }
        txtFieldSearch.setStyle("-fx-prompt-text-fill: red");
    }


    public void search(){
        if (!txtFieldSearch.getText().isEmpty()) {
            vBoxSearchResults.getChildren().clear();
            vBoxSearchResults.setVisible(true);
            ArrayList<Hyperlink> hyperlinkArrayList = getHyperLinks(txtFieldSearch.getText());
            vBoxSearchResults.getChildren().addAll(hyperlinkArrayList);
            txtFieldSearch.clear();
        } else {
            txtFieldSearch.setStyle("-fx-prompt-text-fill: #ef0707");
        }
    }

    /*
         Finder alle titler der indeholder søge string
         og returnere en ArrayList med HyperLinks der
         linker videre til credits displayal.
     */
    public ArrayList<Hyperlink> getHyperLinks(String searchString){
        ArrayList<Hyperlink> hyperlinkArrayList = new ArrayList<>();
        ArrayList<SearchResults> searchResultsArrayList = App.getCreditSystem().search(searchString);
        for (SearchResults sr : searchResultsArrayList){
            Hyperlink hyperlink = new Hyperlink();
            hyperlink.setText(sr.getTitle());
            hyperlink.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    App.getCreditSystem().setCreditsToDisplay(sr.getProductionId());
                    App.switchScene("DisplayCreditsFirstIteration");
                }
            });
            hyperlinkArrayList.add(hyperlink);
        }
        return hyperlinkArrayList;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtFieldSearch.setText(App.getSearchField());
        search();
    }
}