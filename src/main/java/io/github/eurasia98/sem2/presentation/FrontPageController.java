package io.github.eurasia98.sem2.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class FrontPageController implements Initializable {

    @FXML
    private Button btnLogin;

    @FXML
    private ImageView ivLogo;

    @FXML
    private Button btnAddMovie;

    @FXML
    private Button btnAddPerson;

    @FXML
    private TextField txtFieldSearch;

    @FXML
    private ImageView ivSearch;

    @FXML
    void btnAddMovieHandler(ActionEvent event) {
        App.switchScene("CreateMovieScreen");
    }

    @FXML
    void btnAddPersonHandler(ActionEvent event) {
        App.switchScene("CreatePersonScreen");
    }

    @FXML
    void btnLoginHandler(ActionEvent event) {

    }

    @FXML
    void ivLogoActionHandler(MouseEvent event) {
        App.switchScene("FrontPage");
    }

    @FXML
    void ivSearchMouseClickHandler(MouseEvent event) {
        App.setSearchField(txtFieldSearch.getText());
        App.switchScene("SearchScreen");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
