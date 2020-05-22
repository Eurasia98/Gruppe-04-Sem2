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

public class EditChoosenTvProgram implements Initializable {

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
    private TableView<ModelTableTvProgramEpisode> tvMyEpisodes;

    @FXML
    private TableColumn<ModelTableTvProgramEpisode, String> tvcEpisodeNumber;

    @FXML
    private TableColumn<ModelTableTvProgramEpisode, String> tvcTitle;

    @FXML
    private TableColumn<ModelTableTvProgramEpisode, String> tvcEpisodeId;

    @FXML
    private Button btnMyPage;

    @FXML
    private TextField txtFieldCurrentEpisodeNumber;

    @FXML
    private TextField txtFieldNewEpisodeNumber;

    @FXML
    private TextField txtFieldCurrentTitle;

    @FXML
    private TextField txtFieldNewTitle;

    @FXML
    private TextField txtFieldCurrentId;

    @FXML
    private TextField txtFieldNewId;

    @FXML
    private Button btnChangeEpisodeNumber;

    @FXML
    private Button btnSaveEpisodeNumber;

    @FXML
    private Button btnChangeTitle;

    @FXML
    private Button btnSaveTitle;

    @FXML
    private Button btnChangeId;

    @FXML
    private Button btnSaveId;

    @FXML
    private Button btnCreateEpisode;

    @FXML
    private TextField txtFieldCreateEpisodeTitle;

    @FXML
    private TextField txtFieldCreateEpisodeNumber;

    @FXML
    private TextField txtFieldCreateEpisodeId;

    @FXML
    private Button btnSaveCreatedEpisode;

    @FXML
    private Button btnShowDescription;

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
    void btnShowDescriptionHandler() {
        App.setSelectedTvProgramEpisodeToEdit(tvMyEpisodes.getSelectionModel().getSelectedItem().getEpisode_id());
        App.switchScene("TvProgramDescriptionScreen");
    }

    @FXML
    void btnSaveCreatedEpisodeHandler() {
        if (App.getCreditSystem().createNewTvProgramEpisode(App.getSelectedProductionToEdit(), txtFieldCreateEpisodeNumber.getText(),
                txtFieldCreateEpisodeTitle.getText(), txtFieldCreateEpisodeId.getText()) == true){
            resetFields();
            update();
        } else resetFields();
    }

    @FXML
    void btnCreateEpisodeHandler() {
        txtFieldCreateEpisodeTitle.setVisible(true);
        txtFieldCreateEpisodeNumber.setVisible(true);
        txtFieldCreateEpisodeId.setVisible(true);
        btnSaveCreatedEpisode.setVisible(true);
    }

    @FXML
    void btnChangeEpisodeNumberHandler() {
        try {
            resetFields();
            txtFieldCurrentEpisodeNumber.setText(tvMyEpisodes.getSelectionModel().getSelectedItem().getEpisode_number());
            txtFieldCurrentEpisodeNumber.setVisible(true);
            txtFieldCurrentEpisodeNumber.setEditable(false);
            txtFieldCurrentEpisodeNumber.setText(tvMyEpisodes.getSelectionModel().getSelectedItem().getEpisode_number());
            txtFieldNewEpisodeNumber.setVisible(true);
            btnSaveEpisodeNumber.setVisible(true);
        } catch (NullPointerException e){
            resetFields();
        }
    }

    @FXML
    void btnChangeIdHandler() {
        resetFields();
        txtFieldCurrentId.setVisible(true);
        txtFieldCurrentId.setEditable(false);
        txtFieldCurrentId.setText(tvMyEpisodes.getSelectionModel().getSelectedItem().getEpisode_id());
        txtFieldNewId.setVisible(true);
        btnSaveId.setVisible(true);
    }

    @FXML
    void btnChangeTitleHandler() {
        resetFields();
        txtFieldCurrentTitle.setVisible(true);
        txtFieldCurrentTitle.setEditable(false);
        txtFieldCurrentTitle.setText(tvMyEpisodes.getSelectionModel().getSelectedItem().getEpisode_title());
        txtFieldNewTitle.setVisible(true);
        btnSaveTitle.setVisible(true);
    }

    @FXML
    void btnMyPageHandler() {
        App.switchScene("AccountScreen");
    }

    @FXML
    void btnSaveEpisodeNumberHandler() {
        if (!txtFieldNewEpisodeNumber.getText().isEmpty()){
            if (App.getCreditSystem().changeTvProgramEpisodeNumber(tvMyEpisodes.getSelectionModel().getSelectedItem().getEpisode_id(),
                    txtFieldNewEpisodeNumber.getText()) == true){
                update();
                resetFields();
            } else resetFields();
        } else resetFields();
    }

    @FXML
    void btnSaveIdHandler() {
        if (!txtFieldNewId.getText().isEmpty()){
            if (App.getCreditSystem().changeTvProgramEpisodeId(tvMyEpisodes.getSelectionModel().getSelectedItem().getEpisode_id(),
                    txtFieldNewId.getText()) == true){
                update();
                resetFields();
            } else resetFields();
        } else resetFields();
    }

    @FXML
    void btnSaveTitleHandler() {
        if (!txtFieldNewTitle.getText().isEmpty()){
            if (App.getCreditSystem().changeTvProgramEpisodeTitle(tvMyEpisodes.getSelectionModel().getSelectedItem().getEpisode_id(),
                    txtFieldNewTitle.getText()) == true){
                update();
                resetFields();
            } else resetFields();
        } else resetFields();
    }

    private void resetFields() {
        txtFieldCurrentEpisodeNumber.clear();
        txtFieldCurrentEpisodeNumber.setVisible(false);
        txtFieldNewEpisodeNumber.clear();
        txtFieldNewEpisodeNumber.setVisible(false);
        txtFieldCurrentTitle.clear();
        txtFieldCurrentTitle.setVisible(false);
        txtFieldNewTitle.clear();
        txtFieldNewTitle.setVisible(false);
        txtFieldCurrentId.clear();
        txtFieldCurrentId.setVisible(false);
        txtFieldNewId.clear();
        txtFieldNewId.setVisible(false);
        txtFieldCreateEpisodeTitle.clear();
        txtFieldCreateEpisodeTitle.setVisible(false);
        txtFieldCreateEpisodeNumber.clear();
        txtFieldCreateEpisodeNumber.setVisible(false);
        txtFieldCreateEpisodeId.clear();
        txtFieldCreateEpisodeId.setVisible(false);

        btnSaveEpisodeNumber.setVisible(false);
        btnSaveTitle.setVisible(false);
        btnSaveId.setVisible(false);
        btnSaveCreatedEpisode.setVisible(false);

    }

    private void update() {
        ObservableList<ModelTableTvProgramEpisode> observableList = FXCollections.observableArrayList();
        ArrayList<String[]> episodesInfo = App.getCreditSystem().getTvProgramEpisodesInfo(App.getSelectedProductionToEdit());
         if (!episodesInfo.isEmpty()){
            for (String[] s : episodesInfo) {
                observableList.add(new ModelTableTvProgramEpisode(s[0], s[1], s[2]));
            }
        } else {
            observableList.add(new ModelTableTvProgramEpisode(null, null, null));
        }


        tvcEpisodeNumber.setCellValueFactory(new PropertyValueFactory<>("episode_number"));
        tvcTitle.setCellValueFactory(new PropertyValueFactory<>("episode_title"));
        tvcEpisodeId.setCellValueFactory(new PropertyValueFactory<>("episode_id"));

        tvMyEpisodes.setItems(observableList);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resetFields();
        update();
    }
}
