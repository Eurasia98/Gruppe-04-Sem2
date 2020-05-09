package io.github.eurasia98.sem2.presentation;

import io.github.eurasia98.sem2.logic.MovieManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateMovieScreenHandler implements Initializable {

    @FXML
    private TextField txtFieldTitel;

    @FXML
    private TextField txtFieldProductionid;

    @FXML
    private Button btnSave;

    @FXML
    private TextArea txtAreaMessage;

    @FXML
    private ImageView IVLogo;

    @FXML
    void IVLogoHandler(javafx.scene.input.MouseEvent mouseEvent) {
        App.switchScene("FrontPage");
    }

    @FXML
    void btnSaveHandler(ActionEvent event) {
        MovieManager movieManager = new MovieManager();
        if (movieManager.insertMovie(movieManager.createMovie(txtFieldTitel.getText(), txtFieldProductionid.getText())) == false){
            updateSucces();
        } else {
            updateFail();
        }
    }

    private void updateSucces(){
        txtAreaMessage.clear();
        txtFieldProductionid.clear();
        txtFieldTitel.clear();
        txtAreaMessage.setText("Filmen er blevet gemt. ");
    }

    private void updateFail(){
        txtAreaMessage.clear();
        txtFieldTitel.clear();
        txtFieldProductionid.clear();
        txtAreaMessage.setText("Der skete en fejl. ");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
