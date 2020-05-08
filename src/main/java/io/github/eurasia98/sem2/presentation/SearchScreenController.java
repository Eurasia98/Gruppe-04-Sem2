package io.github.eurasia98.sem2.presentation;

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
    private VBox vBoxSearchResults;

    @FXML
    private TextField txtFieldSearch;

    @FXML
    private ImageView ivLogo;

    @FXML
    private ImageView ivSearch;

    /* håndterer "søg" og "søg igen" knapperne. Sender data
     *  videre til App og videre til creditsystem, der sender
     *  det videre til databasecontroller der sender det
     *  retur som hyperlinks.
     * */

    @FXML
    private void ivSearchMouseClickHandler() throws IOException {
        if(!txtFieldSearch.getText().isEmpty()) {
            search();
        }
        txtFieldSearch.setStyle("-fx-prompt-text-fill: red");
    }

    public void ivLogoActionHandler(MouseEvent mouseEvent) {
        App.switchScene("FrontPage");
    }

    @FXML
    void txtFieldSearchKeyPressHandler(KeyEvent event) throws IOException {
        if (event.getCode().toString().equals("ENTER")){
            ivSearchMouseClickHandler();
        }
    }

    /* Sætter hyperLinks fra getHyperlinks
    *  ind på vBox så bruger kan se dem i gui.
    * */
    public void search(){
        if (!txtFieldSearch.getText().isEmpty()) {
            vBoxSearchResults.getChildren().clear();
            vBoxSearchResults.setVisible(true);
            vBoxSearchResults.getChildren().addAll(getHyperLinks());
        } else {txtFieldSearch.setStyle("-fx-prompt-text-fill: red");}
//            ListView listView = new ListView();

//            listView.getItems().addAll(vBoxSearchResults.getChildren().addAll(finalList));
    }

    /*
         Finder alle titler der indeholder søge string
         og returnere en ArrayList med HyperLinks der
         linker videre til credits displayal.
     */
    public ArrayList<Hyperlink> getHyperLinks(){
        /*ArrayList<Hyperlink> hyperlinkArrayList = App.creditSystem.userSearch(txtFieldSearch.getText());
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
        return finalList;*/
        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*txtFieldSearch.setText(App.searchFieldString);
        search();*/
    }
}