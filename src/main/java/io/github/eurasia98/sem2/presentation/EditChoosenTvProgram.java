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

public class EditChoosenTvProgram implements Initializable {

    @FXML
    private TableView<ModelTableTvProgramEpisode> tvMyEpisodes;

    @FXML
    private TableColumn<ModelTableTvProgramEpisode, String> tvcEpisodeNumber;

    @FXML
    private TableColumn<ModelTableTvProgramEpisode, String> tvcTitle;

    @FXML
    private TableColumn<ModelTableTvProgramEpisode, String> tvcEpisodeId;

    @FXML
    private ImageView ivLogo;

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
    void btnChangeEpisodeNumberHandler() {
        resetFields();
        txtFieldCurrentEpisodeNumber.setVisible(true);
        txtFieldCurrentEpisodeNumber.setEditable(false);
        txtFieldCurrentEpisodeNumber.setText(tvMyEpisodes.getSelectionModel().getSelectedItem().getEpisode_number());
        txtFieldNewEpisodeNumber.setVisible(true);
    }

    @FXML
    void btnChangeIdHandler() {
        resetFields();
        txtFieldCurrentId.setVisible(true);
        txtFieldCurrentId.setEditable(false);
        txtFieldCurrentId.setText(tvMyEpisodes.getSelectionModel().getSelectedItem().getEpisode_id());
        txtFieldNewId.setVisible(true);
    }

    @FXML
    void btnChangeTitleHandler() {
        resetFields();
        txtFieldCurrentTitle.setVisible(true);
        txtFieldCurrentTitle.setEditable(false);
        txtFieldCurrentTitle.setText(tvMyEpisodes.getSelectionModel().getSelectedItem().getEpisode_title());
        txtFieldNewTitle.setVisible(true);
    }

    @FXML
    void btnMyPageHandler() {
        App.switchScene("AccountScreen");
    }

    @FXML
    void btnSaveEpisodeNumberHandler() {
        if (!txtFieldNewEpisodeNumber.getText().isEmpty()){
            if (App.getCreditSystem().changeTvProgramEpisodeNumber(tvMyEpisodes.getSelectionModel().getSelectedItem().getEpisode_id(),
                    txtFieldNewId.getText()) == true){
                update();
            } else resetFields();
        } else resetFields();
    }

    @FXML
    void btnSaveIdHandler() {
        if (!txtFieldNewId.getText().isEmpty()){
            if (App.getCreditSystem().changeTvProgramEpisodeId(tvMyEpisodes.getSelectionModel().getSelectedItem().getEpisode_id(),
                    txtFieldNewId.getText()) == true){
                update();
            } else resetFields();
        } else resetFields();
    }

    @FXML
    void btnSaveTitleHandler() {
        if (!txtFieldNewTitle.getText().isEmpty()){
            if (App.getCreditSystem().changeTvProgramEpisodeTitle(tvMyEpisodes.getSelectionModel().getSelectedItem().getEpisode_id(),
                    txtFieldNewTitle.getText()) == true){
                update();
            } else resetFields();
        } else resetFields();
    }

    @FXML
    void ivLogoHandler() {
        App.switchScene("FrontPage");
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
    }

    private void update() {
        ObservableList<ModelTableTvProgramEpisode> observableList = FXCollections.observableArrayList();
        ArrayList<String[]> episodesInfo = App.getCreditSystem().getTvProgramEpisodesInfo(App.getSelectedProductionToEdit());
        for (String[] s : episodesInfo) {
            observableList.add(new ModelTableTvProgramEpisode(s[1], s[2], s[3]));
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
