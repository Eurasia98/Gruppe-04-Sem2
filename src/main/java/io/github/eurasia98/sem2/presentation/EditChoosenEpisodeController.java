package io.github.eurasia98.sem2.presentation;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class EditChoosenEpisodeController implements Initializable {


    @FXML
    private TextArea txtAreaDescription;

    @FXML
    private ImageView ivLogo;

    @FXML
    private Button btnMyPage;

    @FXML
    private Button btnChangeDescription;

    @FXML
    private TextArea txtAreaInfo;

    @FXML
    private Button btnSaveChanges;

    @FXML
    void btnChangeDescriptionHandler() {
        btnSaveChanges.setVisible(true);
        txtAreaDescription.setEditable(true);
        txtAreaInfo.appendText("Du kan nu foretage ændringer i beskrivelsen. Max 1500 tegn. ");
    }

    @FXML
    void btnMyPageHandler() {
        App.switchScene("AccountScreen");
    }

    @FXML
    void btnSaveChangesHandler() {
        if (App.getCreditSystem().changeDescriptionSeriesEpisode(App.getSelectedSeriesEpisodeToEdit(), txtAreaDescription.getText()) == true){
            resetFields();
        } else resetFields();

    }

    @FXML
    void ivLogoHandler() {
        App.switchScene("FrontPage");
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        resetFields();
    }

    public void resetFields(){
        try {
            if (!App.getCreditSystem().getEpisodeDescription(App.getSelectedSeriesEpisodeToEdit()).isEmpty()){
                txtAreaDescription.appendText(App.getCreditSystem().getEpisodeDescription(App.getSelectedSeriesEpisodeToEdit()));
                btnSaveChanges.setVisible(false);
                txtAreaDescription.setEditable(false);

            } else {
                txtAreaDescription.setEditable(false);
            }
        } catch (java.lang.NullPointerException e){
            txtAreaDescription.setEditable(false);
        }
    }


}
