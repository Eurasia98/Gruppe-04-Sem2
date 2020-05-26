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

public class EditChoosenTvSeriesController implements Initializable {

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
    private TextArea txtAreaDescription;

    @FXML
    private Button btnChangeDescription;

    @FXML
    private TableView<ModelTableChoosenEditTvSeries> tvSeasons;

    @FXML
    private TableColumn<ModelTableChoosenEditTvSeries, String> tvcSeasonNumber;

    @FXML
    private TableColumn<ModelTableChoosenEditTvSeries, String> tvcTitle;

    @FXML
    private TableColumn<ModelTableChoosenEditTvSeries, String> tvcId;

    @FXML
    private Button btnSelect;

    @FXML
    private TextArea txtAreaDisplayInfo;

    @FXML
    private Button btnAddSeason;

    @FXML
    private Button btnSaveSeason;

    @FXML
    private TextField txtFieldSeasonNumber;

    @FXML
    private TextField txtFieldSeasonId;

    @FXML
    private Button btnSaveDescription;

    @FXML
    private Button btnEditSeasonNumber;

    @FXML
    private Button btnSaveChanges;

    @FXML
    private Button btnEditSeasonId;

    @FXML
    private TextField txtFieldCurrentNumber;

    @FXML
    private TextField txtFieldCurrentId;

    @FXML
    private TextField txtFieldNewNumber;

    @FXML
    private TextField txtFieldNewId;

    @FXML
    void btnEditSeasonIdHandler(ActionEvent event) {
        try {
            resetFields();
            txtFieldCurrentId.setVisible(true);
            txtFieldCurrentId.setText(tvSeasons.getSelectionModel().getSelectedItem().getId());
            txtFieldCurrentId.setEditable(false);
            txtFieldNewId.setVisible(true);
            btnSaveChanges.setVisible(true);
        } catch (java.lang.NullPointerException e){
            resetFields();
            txtAreaDisplayInfo.appendText("Vælg venligst en sæson at redigere. ");
        }
    }

    @FXML
    void btnEditSeasonNumberHandler(ActionEvent event) {
        try {
            resetFields();
            txtFieldCurrentNumber.setVisible(true);
            txtFieldCurrentNumber.setText(tvSeasons.getSelectionModel().getSelectedItem().getSeason_number());
            txtFieldCurrentNumber.setEditable(false);
            txtFieldNewNumber.setVisible(true);
            btnSaveChanges.setVisible(true);
        } catch (java.lang.NullPointerException e){
            resetFields();
            txtAreaDisplayInfo.appendText("Vælg venligst en sæson at redigere. ");
        }
    }

    @FXML
    void btnSaveChangesHandler(ActionEvent event) {
        if (!txtFieldNewNumber.getText().isEmpty()){
            if (App.getCreditSystem().editSeasonNumber(tvSeasons.getSelectionModel().getSelectedItem().getId(), txtFieldNewNumber.getText()) == true){
                update();
                resetFields();
                txtAreaDisplayInfo.appendText("Ændringen blev gemt. ");
            } else {
                update();
                resetFields();
                txtAreaDisplayInfo.appendText("Ændringen blev ikke gemt. \nNoget gik galt. ");
            }
        } else if (!txtFieldNewId.getText().isEmpty()){
            if (App.getCreditSystem().editSeasonId(txtFieldCurrentId.getText(), txtFieldNewId.getText()) == true){
                update();
                resetFields();
                txtAreaDisplayInfo.appendText("Ændringen blev gemt. ");
            } else {
                update();
                resetFields();
                txtAreaDisplayInfo.appendText("Ændringen blev ikke gemt. \nNoget gik galt.");
            }
        } else {
            txtFieldNewId.setStyle("-fx-prompt-text-fill: red");
            txtFieldNewNumber.setStyle("-fx-prompt-text-fill: red");
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
    void btnChangeDescriptionHandler() {
        resetFields();
        btnSaveDescription.setVisible(true);
        txtAreaDescription.setEditable(true);
        btnSaveDescription.setVisible(true);
    }

    @FXML
    void btnSaveDescriptionHandler() {
        btnSaveDescription.setVisible(true);
        if (txtAreaDescription != null && !txtAreaDescription.getText().isEmpty()) {
            if (App.getCreditSystem().changeDescription(App.getSelectedTvSeries(), txtAreaDescription.getText()) == true) {
                resetFields();
                txtAreaDisplayInfo.appendText("Beskrivelsen blev ændret. ");
            } else {
                resetFields();
                txtAreaDisplayInfo.appendText("Der skete desværre en fejl.");
            }
        } else {
            resetFields();
            txtAreaDisplayInfo.appendText("Udfyld venligst alle felter. ");
        }
    }

    @FXML
    void btnAddSeasonHandler() {
        resetFields();
        btnSaveSeason.setVisible(true);
        txtFieldSeasonNumber.setVisible(true);
        txtFieldSeasonId.setVisible(true);
        btnSaveSeason.setVisible(true);
    }

    @FXML
    void btnSaveSeasonHandler() {
        ArrayList<String> seasonInfo = new ArrayList<>();
        seasonInfo.add(txtFieldSeasonId.getText());
        seasonInfo.add(App.getSelectedProduction());
        seasonInfo.add(App.getSelectedTvSeries());
        seasonInfo.add(txtFieldSeasonNumber.getText());
        if (App.getCreditSystem().createNewSeason(seasonInfo) == true) {
            resetFields();
            update();
            txtAreaDisplayInfo.appendText("Serien blev gemt. ");
        } else {
            txtAreaDisplayInfo.appendText("Der skete desværre en fejl. ");
        }
    }

    @FXML
    void btnSelectHandler() {
        App.setSelectedSeason(tvSeasons.getSelectionModel().getSelectedItem().getId());
        App.switchScene("ChoosenSeasonToEdit");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resetFields();
        update();
        loggedIn();
    }

    public void resetFields() {
        txtAreaDescription.setEditable(false);
        btnSaveSeason.setVisible(false);
        txtAreaDisplayInfo.clear();
        txtFieldSeasonNumber.clear();
        txtFieldSeasonNumber.setVisible(false);
        txtFieldSeasonId.clear();
        txtFieldSeasonId.setVisible(false);
        btnSaveDescription.setVisible(false);
        btnSaveSeason.setVisible(false);
        txtFieldCurrentNumber.setVisible(false);
        txtFieldCurrentNumber.clear();
        txtFieldNewNumber.setVisible(false);
        txtFieldNewNumber.clear();
        txtFieldCurrentId.setVisible(false);
        txtFieldCurrentId.clear();
        txtFieldNewId.setVisible(false);
        txtFieldNewId.clear();
        txtAreaDisplayInfo.clear();
        btnSaveChanges.setVisible(false);
    }

    public void update() {
        String productionId = App.getSelectedProduction();
        ObservableList<ModelTableChoosenEditTvSeries> observableList = FXCollections.observableArrayList();
        ArrayList<String[]> mySeriesInfo = App.getCreditSystem().getMySeriesInfo(productionId);


        for (String[] s : mySeriesInfo) {
            observableList.add(new ModelTableChoosenEditTvSeries(s[1], s[0]));
        }

        tvcSeasonNumber.setCellValueFactory(new PropertyValueFactory<>("season_number"));
        tvcId.setCellValueFactory(new PropertyValueFactory<>("id"));

        tvSeasons.setItems(observableList);
    }
}
