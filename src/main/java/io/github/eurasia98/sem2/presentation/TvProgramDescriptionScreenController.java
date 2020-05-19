package io.github.eurasia98.sem2.presentation;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class TvProgramDescriptionScreenController implements Initializable {

    @FXML
    private Button btnChangeDescription;

    @FXML
    private Button btnSaveChanges;

    @FXML
    private Button btnMyPage;

    @FXML
    private TextArea txtAreaDescrption;

    @FXML
    private ImageView ivLogo;

    @FXML
    void btnMyPageHandler() {
        App.switchScene("AccountScreen");
    }

    @FXML
    void ivLogoHandler() {
        App.switchScene("FrontPage");
    }

    @FXML
    void btnChangeDescriptionHandler() {
        txtAreaDescrption.setEditable(true);
    }

    @FXML
    void btnSaveChanges() {
        if (App.getCreditSystem().changeTvProgramEpisodeDescription(App.getSelectedTvProgramEpisodeToEdit(),txtAreaDescrption.getText()) == true){
            App.switchScene("ChoosenTvProgramToEdit");
        } else {
            try{
                txtAreaDescrption.appendText(App.getCreditSystem().getTvProgramEpisodeDescription(App.getSelectedTvProgramEpisodeToEdit()));
                txtAreaDescrption.setEditable(false);
            } catch (NullPointerException e){
                txtAreaDescrption.clear();
                txtAreaDescrption.setEditable(false);
            }

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (App.getCreditSystem().getTvProgramEpisodeDescription(App.getSelectedTvProgramEpisodeToEdit()) != null){
            txtAreaDescrption.appendText(App.getCreditSystem().getTvProgramEpisodeDescription(App.getSelectedTvProgramEpisodeToEdit()));
            txtAreaDescrption.setEditable(false);
        } else txtAreaDescrption.setEditable(false);
    }
}