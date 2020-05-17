package io.github.eurasia98.sem2.presentation;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateEpisodeHandler implements Initializable {

    @FXML
    private TextField txtFieldEpisodeTitle;

    @FXML
    private TextArea txtAreaDescription;

    @FXML
    private TextField txtFieldEpisodeNumber;

    @FXML
    private TextField txtFieldEpisodeId;

    @FXML
    private Button btnMyPage;

    @FXML
    private Button btnSave;

    @FXML
    private ImageView ivLogo;

    @FXML
    void ivLogoHandler() {
        App.switchScene("FrontPage");
    }

    @FXML
    void btnMyPageHandler() {
        App.switchScene("AccountScreen");
    }

    @FXML
    void btnSaveHandler() {
        if (App.getCreditSystem().createNewEpisode(App.getSelectedSeasonToEdit(), App.getSelectedTvSeriesToEdit(),
                App.getSelectedProductionToEdit(),txtFieldEpisodeTitle.getText(), txtAreaDescription.getText(),
                txtFieldEpisodeId.getText(), txtFieldEpisodeNumber.getText()) == true){
            updateSucces();
        } else resetFields();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resetFields();

    }

    public void updateSucces(){
        App.switchScene("ChoosenSeasonToEdit");
    }

    public void resetFields(){
        txtAreaDescription.clear();
        txtFieldEpisodeId.clear();
        txtFieldEpisodeId.clear();
        txtFieldEpisodeNumber.clear();
    }

}
