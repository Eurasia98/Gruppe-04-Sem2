package io.github.eurasia98.sem2.presentation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EditChoosenTvSeriesController implements Initializable {
    @FXML
    private ImageView ivLogo;

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
    private Button btnMyPage;

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
    void btnChangeDescriptionHandler() {
        txtAreaDescription.setEditable(true);
        btnSaveDescription.setVisible(true);
    }

    @FXML
    void btnSaveDescriptionHandler() {
        if (txtAreaDescription != null && !txtAreaDescription.getText().isEmpty()) {
            if (App.getCreditSystem().changeDescription(App.getSelectedTvSeriesToEdit(), txtAreaDescription.getText()) == true) {
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
    }

    @FXML
    void btnSaveSeasonHandler() {
        ArrayList<String> seasonInfo = new ArrayList<>();
        seasonInfo.add(txtFieldSeasonId.getText());
        seasonInfo.add(App.getSelectedProductionToEdit());
        seasonInfo.add(App.getSelectedTvSeriesToEdit());
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
    void btnMyPageHandler() {
        App.switchScene("AccountScreen");
    }

    @FXML
    void btnSelectHandler() {
        App.setSelectedSeasonToEdit(tvSeasons.getSelectionModel().getSelectedItem().getId());
        App.switchScene("ChoosenSeasonToEdit");
    }

    @FXML
    void ivLogoHandler() {
        App.switchScene("FrontPage");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resetFields();
        update();
    }

    public void resetFields() {
        txtAreaDescription.setEditable(false);
        btnSaveSeason.setVisible(false);
        txtAreaDisplayInfo.clear();
        txtFieldSeasonNumber.clear();
        txtFieldSeasonNumber.setVisible(false);
        txtFieldSeasonId.clear();
        txtFieldSeasonId.setVisible(false);
    }

    public void update() {
        String productionId = App.getSelectedProductionToEdit();
        ObservableList<ModelTableChoosenEditTvSeries> observableList = FXCollections.observableArrayList();
        ArrayList<String[]> mySeriesInfo = App.getCreditSystem().getMySeriesInfo(productionId);


        for (String[] s : mySeriesInfo) {
            observableList.add(new ModelTableChoosenEditTvSeries(s[0], s[1]));
        }

        tvcSeasonNumber.setCellValueFactory(new PropertyValueFactory<>("season_number"));
        tvcId.setCellValueFactory(new PropertyValueFactory<>("id"));

        tvSeasons.setItems(observableList);
    }
}
