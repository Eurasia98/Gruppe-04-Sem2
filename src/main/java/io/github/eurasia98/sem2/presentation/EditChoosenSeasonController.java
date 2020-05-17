package io.github.eurasia98.sem2.presentation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EditChoosenSeasonController implements Initializable {

    @FXML
    private ImageView ivLogo;

    @FXML
    private Button btnMyPage;

    @FXML
    private TableView<ModelTableChoosenSeasonToEdit> tvEpisodes;

    @FXML
    private TableColumn<ModelTableChoosenSeasonToEdit, String> tvcEpisodeNumber;

    @FXML
    private TableColumn<ModelTableChoosenSeasonToEdit, String> tvcEpisodeTitle;

    @FXML
    private TableColumn<ModelTableChoosenSeasonToEdit, String> tvcId;

    @FXML
    private Button btnChangeEpisode;

    @FXML
    private Button btnSaveEpisode;

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
    void btnNewEpisodeHandler() {
        App.switchScene("CreateEpisodeScreen");
    }

    @FXML
    void btnChangeEpisode() {
        txtFieldCurrentEpisodeNumber.setVisible(true);
        txtFieldCurrentEpisodeNumber.setText(tvEpisodes.getSelectionModel().getSelectedItem().getEpisode_number());
        txtFieldNewEpisodeNumber.setVisible(true);
        btnSaveEpisode.setVisible(true);
    }

    @FXML
    void btnSaveEpisode() {

    }

    @FXML
    void btnChangeTitleHandler() {
        txtFieldCurrentTitle.setVisible(true);
        txtFieldCurrentTitle.setText(tvEpisodes.getSelectionModel().getSelectedItem().getTitle());
        txtFieldNewTitle.setVisible(true);
        btnSaveTitle.setVisible(true);
    }

    @FXML
    void btnSaveTitleHandler() {

    }

    @FXML
    void btnMyPageHandler() {
        App.switchScene("AccountScreen");
    }


    @FXML
    void btnSelectHandler() {
        App.setSelectedEpisodeToEdit(tvEpisodes.getSelectionModel().getSelectedItem().getSeason_id());
        App.switchScene("ChoosenEpisodeToEdit");

    }

    @FXML
    void ivLogoHandler() {
        App.switchScene("Frontpage");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resetFields();
        update();
    }

    public void resetFields(){
        txtFieldCurrentEpisodeNumber.clear();
        txtFieldCurrentEpisodeNumber.setVisible(false);
        txtFieldNewEpisodeNumber.clear();
        txtFieldNewEpisodeNumber.setVisible(false);
        txtFieldCurrentTitle.clear();
        txtFieldCurrentTitle.setVisible(false);
        txtFieldNewTitle.clear();
        txtFieldNewTitle.setVisible(false);
        btnSaveEpisode.setVisible(false);
        btnSaveTitle.setVisible(false);
    }

    public void update(){
        ObservableList<ModelTableChoosenSeasonToEdit> observableList = FXCollections.observableArrayList();

        ArrayList<String[]> seasonInfo = App.getCreditSystem().getSelectedSeasonEpisodesInfo(App.getSelectedSeasonToEdit());
        for (String[] s : seasonInfo){
            observableList.add(new ModelTableChoosenSeasonToEdit(s[1], s[0], s[2]));
        }

        tvcEpisodeNumber.setCellValueFactory(new PropertyValueFactory<>("episode_number"));
        tvcEpisodeTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tvcId.setCellValueFactory(new PropertyValueFactory<>("season_id"));

        tvEpisodes.setItems(observableList);
    }
}
