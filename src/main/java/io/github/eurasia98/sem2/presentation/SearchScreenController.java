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
import java.util.HashMap;
import java.util.Map;
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
    private Button btnLogin;

    @FXML
    private Label lblAccount;

    @FXML
    private VBox vBoxAccount;

    @FXML
    private void ivLogoActionHandler(MouseEvent event) {
        App.switchScene("FrontPage");
    }

    // Switches to login screen
    @FXML
    private void btnLoginHandler(ActionEvent event) {
        App.switchScene("LoginScreen");
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
        else {
            txtFieldSearch.setStyle("-fx-prompt-text-fill: red");
        }
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
         linker videre til visning af credits.
     */
    private ArrayList<Hyperlink> getHyperLinks(String searchString){
        ArrayList<Hyperlink> hyperlinkArrayList = new ArrayList<>();
        HashMap<String, String> creditsMap = App.getCreditSystem().search(searchString);
        for (Map.Entry<String, String> hashMap : creditsMap.entrySet()){
            Hyperlink hyperlink = new Hyperlink();
            hyperlink.setText(hashMap.getKey());
            hyperlink.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    App.getCreditSystem().setCreditsToDisplay(hashMap.getValue());
                    App.setSelectedTitle(hashMap.getKey());
                    App.switchScene("DisplayCreditsFirstIteration");
                }
            });
            hyperlinkArrayList.add(hyperlink);
        }
        return hyperlinkArrayList;
    }

    private void loggedIn(){
        if (!App.getUserInfo().isEmpty()){
            btnLogin.setVisible(false);
            vBoxAccount.setVisible(true);
            lblAccount.setText(App.getUserInfo().get(0));
            Hyperlink myPage = new Hyperlink("Min side");
            myPage.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    App.switchScene("AccountScreen");
                }
            });
            vBoxAccount.getChildren().add(1, myPage);
        }

        else {
            btnLogin.setVisible(true);
            vBoxAccount.setVisible(false);
            lblAccount.setText("");
            if (vBoxAccount.getChildren().size() == 2){
                vBoxAccount.getChildren().remove(1);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtFieldSearch.setText(App.getSearchField());
        search();
        loggedIn();
    }
}