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

public class EditChoosenSeasonController implements Initializable {

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
    private TableView<ModelTableChoosenSeasonToEdit> tvEpisodes;

    @FXML
    private TableColumn<ModelTableChoosenSeasonToEdit, String> tvcEpisodeNumber;

    @FXML
    private TableColumn<ModelTableChoosenSeasonToEdit, String> tvcEpisodeTitle;

    @FXML
    private TableColumn<ModelTableChoosenSeasonToEdit, String> tvcId;

    @FXML
    private Button btnChangeEpisodeNumber;

    @FXML
    private Button btnSaveEpisodeNumber;

    @FXML
    private Button btnChangeTitle;

    @FXML
    private Button btnSaveTitle;

    @FXML
    private TextField txtFieldCurrentEpisodeNumber;

    @FXML
    private TextField txtFieldNewEpisodeNumber;

    @FXML
    private TextField txtFieldCurrentTitle;

    @FXML
    private TextField txtFieldNewTitle;

    @FXML
    private Button btnSelect;

    @FXML
    private Button btnNewEpisode;

    @FXML
    private TextField txtFieldCurrentId;

    @FXML
    private TextField txtFieldNewId;

    @FXML
    private Button btnEditId;

    @FXML
    private Button btnSaveEditId;

    @FXML
    private void btnEditIdHandler(ActionEvent event) {
        try {
            resetFields();
            txtFieldCurrentId.setVisible(true);
            txtFieldNewId.setVisible(true);
            txtFieldCurrentId.setText(tvEpisodes.getSelectionModel().getSelectedItem().getId());
            txtFieldCurrentId.setEditable(false);
            btnSaveEditId.setVisible(true);
        } catch (java.lang.NullPointerException e){
            resetFields();
        }
    }

    @FXML
    private void btnSaveEditIdHandler(ActionEvent event) {
        if (!txtFieldNewId.getText().isEmpty()){
            App.getCreditSystem().editSeriesEpisodeId(txtFieldCurrentId.getText(), txtFieldNewId.getText());
            resetFields();
            update();
        } else txtFieldNewId.setStyle("-fx-prompt-text-fill: red");
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
            App.switchScene("searchScreen");
        } else {
            txtFieldSearch.setStyle("-fx-prompt-text-fill: red");
        }
    }

    private void loggedIn(){
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

    @FXML
    private void btnNewEpisodeHandler() {
        App.switchScene("CreateEpisodeScreen");
    }

    @FXML
    private void btnChangeEpisodeNumberHandler() {
        try {
            txtFieldCurrentEpisodeNumber.setVisible(true);
            txtFieldCurrentEpisodeNumber.setText(tvEpisodes.getSelectionModel().getSelectedItem().getEpisode_number());
            txtFieldCurrentEpisodeNumber.setEditable(false);
            txtFieldNewEpisodeNumber.setVisible(true);
            btnSaveEpisodeNumber.setVisible(true);
        } catch (java.lang.NullPointerException e){
            resetFields();
        }
    }

    @FXML
    private void btnSaveEpisodeNumberHandler() {
        if (!txtFieldNewEpisodeNumber.getText().isEmpty()){
            if (App.getCreditSystem().changeTvSeriesEpisodeNumber(tvEpisodes.getSelectionModel().getSelectedItem().getId(), txtFieldNewEpisodeNumber.getText()) == true){
                update();
                resetFields();
            } else resetFields();
        } else resetFields();
    }

    @FXML
    private void btnChangeTitleHandler() {
        try {
            txtFieldCurrentTitle.setVisible(true);
            txtFieldCurrentTitle.setText(tvEpisodes.getSelectionModel().getSelectedItem().getTitle());
            txtFieldNewTitle.setVisible(true);
            btnSaveTitle.setVisible(true);
        } catch (java.lang.NullPointerException e){
            resetFields();
        }
    }

    @FXML
    private void btnSaveTitleHandler() {
        if (!txtFieldNewTitle.getText().isEmpty()){
            if (App.getCreditSystem().changeTvSeriesEpisodeTitle(tvEpisodes.getSelectionModel().getSelectedItem().getId(), txtFieldNewTitle.getText()) == true){
                update();
                resetFields();
            } else resetFields();
        } else resetFields();
    }

    @FXML
    private void btnSelectHandler() {
        App.setSelectedSeriesEpisode(tvEpisodes.getSelectionModel().getSelectedItem().getId());
        App.switchScene("ChoosenEpisodeToEdit");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resetFields();
        update();
        loggedIn();
    }

    private void resetFields(){
        txtFieldCurrentEpisodeNumber.clear();
        txtFieldCurrentEpisodeNumber.setVisible(false);
        txtFieldNewEpisodeNumber.clear();
        txtFieldNewEpisodeNumber.setVisible(false);
        txtFieldCurrentTitle.clear();
        txtFieldCurrentTitle.setVisible(false);
        txtFieldNewTitle.clear();
        txtFieldNewTitle.setVisible(false);
        btnSaveEpisodeNumber.setVisible(false);
        btnSaveTitle.setVisible(false);
        btnSaveEditId.setVisible(false);
        txtFieldCurrentId.setVisible(false);
        txtFieldCurrentId.clear();
        txtFieldNewId.clear();
        txtFieldNewId.setVisible(false);
    }

    private void update(){
        ObservableList<ModelTableChoosenSeasonToEdit> observableList = FXCollections.observableArrayList();

        ArrayList<String[]> seasonInfo = App.getCreditSystem().getSelectedSeasonEpisodesInfo(App.getSelectedSeason());
        for (String[] s : seasonInfo){
            observableList.add(new ModelTableChoosenSeasonToEdit(s[1], s[0], s[2]));
        }

        tvcEpisodeNumber.setCellValueFactory(new PropertyValueFactory<>("episode_number"));
        tvcEpisodeTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tvcId.setCellValueFactory(new PropertyValueFactory<>("id"));

        tvEpisodes.setItems(observableList);
    }
}
