package io.github.eurasia98.sem2.presentation;

import io.github.eurasia98.sem2.logic.productionLogic.MovieManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CreateMovieScreenHandler {

    @FXML
    private TextField txtFieldTitel;

    @FXML
    private TextField txtFieldProductionid;

    @FXML
    private Button btnSave;

    @FXML
    private TextArea txtAreaMessage;

    @FXML
    void btnSaveHandler(ActionEvent event) {
        MovieManager movieManager = new MovieManager();
        if (movieManager.saveMovie(movieManager.createMovie(txtFieldTitel.getText(), txtFieldProductionid.getText())) == true){
            txtAreaMessage.clear();
            txtAreaMessage.setText("Filmen er blevet gemt. ");
        } else {
            txtAreaMessage.clear();
            txtAreaMessage.setText("Der skete en fejl. ");
        }
    }
}
