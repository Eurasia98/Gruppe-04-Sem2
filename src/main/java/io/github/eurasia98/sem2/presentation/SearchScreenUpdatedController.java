package io.github.eurasia98.sem2.presentation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SearchScreenUpdatedController implements Initializable {

    @FXML
    private ImageView ivLogo;

    @FXML
    private TextField txtFieldSearch;

    @FXML
    private ImageView ivSearch;

    @FXML
    private Label lblAccount;

    @FXML
    private VBox vBoxAccount;

    @FXML
    private TableView<ModelTableSearch> tvEpisodes;

    @FXML
    private TableColumn<ModelTableSearch, String> tvcEpisodeTitle;

    @FXML
    private TableColumn<ModelTableSearch, String> tvcProductionId;

    @FXML
    private Button btnSelect;

    @FXML
    private ImageView ivSearchImage;

    @FXML
    private void btnSelectHandler() {
        try {
            App.setSelectedSearchResult(tvEpisodes.getSelectionModel().getSelectedItem().getProduction_id());
            App.switchScene("SearchShowCreditsScreen");
        } catch (java.lang.NullPointerException e){
            update();
        }

    }

    @FXML
    private void ivLogoActionHandler(MouseEvent mouseEvent) {
        App.switchScene("FrontPage");
    }

    @FXML
    private void txtFieldSearchKeyPressHandler(KeyEvent event) {
        if (event.getCode().toString().equals("ENTER")){
            ivSearchMouseClickHandler();
        }
    }

    @FXML
    private void ivSearchMouseClickHandler() {
        if(!txtFieldSearch.getText().isEmpty()) {
            App.setSearchField(txtFieldSearch.getText());
            App.switchScene("SearchScreenUpdatedScreen");
        } else {
            txtFieldSearch.setStyle("-fx-prompt-text-fill: red");
        }
    }

    private void loggedIn(){
        try {
            lblAccount.setText(App.getUserInfo().get(0));
            Hyperlink myPage = new Hyperlink("Min side");
            myPage.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    App.switchScene("AccountScreen");
                }
            });
            vBoxAccount.getChildren().add(1, myPage);
        } catch (java.lang.IndexOutOfBoundsException e){

        }
    }

    @FXML
    private void ivSearchImageHandler() {
        try {
            App.setSearchField(txtFieldSearch.getText());
            txtFieldSearch.clear();
            update();
        } catch (java.lang.NullPointerException e){
            txtFieldSearch.clear();
        }
    }

    @FXML
    private void txtFieldSearchKeyHandler(KeyEvent event) {
        if (event.getCode().toString().equals("ENTER")){
            App.setSearchField(txtFieldSearch.getText());
            update();
            txtFieldSearch.clear();
            App.resetSelects();
        }
    }

    private void update(){
        ObservableList<ModelTableSearch> observableList = FXCollections.observableArrayList();
        ArrayList<String[]> searchInfo = App.getCreditSystem().searchUpdated(App.getSearchField());

        try {
            for (String[] s : searchInfo){
                observableList.add(new ModelTableSearch(s[0], s[1]));
            }
        } catch (java.lang.NullPointerException e){

        }

        tvcEpisodeTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tvcProductionId.setCellValueFactory(new PropertyValueFactory<>("production_id"));

        tvEpisodes.setItems(observableList);;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtFieldSearch.clear();
        update();
        loggedIn();
    }
}
