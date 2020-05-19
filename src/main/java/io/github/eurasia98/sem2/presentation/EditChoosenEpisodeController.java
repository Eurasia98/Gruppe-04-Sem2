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
    private Button btnSaveChanges;

    @FXML
    void btnMyPageHandler() {
        App.switchScene("AccountScreen");
    }

    @FXML
    void btnSaveChangesHandler() {

    }

    @FXML
    void ivLogoHandler() {
        App.switchScene("FrontPage");
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        resetFields();
    }

    public void resetFields(){
        txtAreaDescription.appendText(App.getCreditSystem().getEpisodeDescription(App.getSelectedSeriesEpisodeToEdit()));
        btnSaveChanges.setVisible(false);
    }


}
