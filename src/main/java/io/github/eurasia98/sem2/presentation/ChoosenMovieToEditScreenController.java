package io.github.eurasia98.sem2.presentation;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class ChoosenMovieToEditScreenController implements Initializable {
    @FXML
    private TextArea txtAreaDescription;

    @FXML
    private ImageView ivLogo;

    @FXML
    private Button btnMyPage;

    @FXML
    private Button btnChangeDescription;

    @FXML
    private Button btnSaveChanges;

    @FXML
    private TextArea txtAreaInfo;

    @FXML
    private Label lblTitle;

    @FXML
    void btnChangeDescriptionHandler() {
        btnSaveChanges.setVisible(true);
        txtAreaDescription.setEditable(true);
        txtAreaInfo.appendText("Du kan nu foretage ændringer i beskrivelsen. Max 1500 tegn. ");
    }

    @FXML
    void btnMyPageHandler() {
        App.switchScene("MyProductionsScreen");
    }

    @FXML
    void btnSaveChangesHandler() {
        if (App.getCreditSystem().changeDescriptionMovie(App.getSelectedProductionToEdit(), txtAreaDescription.getText()) == true){
            resetFields();
        } else {
            resetFields();
            txtAreaInfo.appendText("Der skete en fejl. ");
        }
    }

    private void resetFields() {
        txtAreaDescription.setEditable(false);
        btnSaveChanges.setVisible(false);
    }

    @FXML
    void ivLogoHandler() {
        App.switchScene("FrontPage");
    }

    public void update(){
        try{
            txtAreaDescription.clear();
            txtAreaDescription.appendText(App.getCreditSystem().getdescription(App.getSelectedProductionToEdit()));
            txtAreaDescription.setEditable(false);

            txtAreaInfo.clear();
            txtAreaInfo.setEditable(false);

            lblTitle.setText(App.getCreditSystem().getMovieTitle(App.getSelectedProductionToEdit()));
        } catch (java.lang.NullPointerException e){
            resetFields();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resetFields();
        update();
    }
}
