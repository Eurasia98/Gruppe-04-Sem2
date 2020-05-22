package io.github.eurasia98.sem2.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class CreateProductionScreenController {

    @FXML
    private ImageView ivLogo;

    @FXML
    private TextField txtFieldSearch;

    @FXML
    private ImageView ivSearch;

    @FXML
    private Label lblAccount;

    @FXML
    private VBox vBoxAccount;

    @FXML
    private Button btnCreateMovie;

    @FXML
    private Button btnCreateTvProgram;

    @FXML
    private Button btnCreateTvShow;

    @FXML
    void IVLogoHandler() {
        App.switchScene("FrontPage");
    }

    @FXML
    void btnCreateMovieHandler(ActionEvent event) {
        App.switchScene("CreateMovieScreen");
    }

    @FXML
    void btnCreateTvProgramHandler(ActionEvent event) {
        App.switchScene("CreateTvProgramScreen");
    }

    @FXML
    void btnCreateTvShowHandler(ActionEvent event) {
        App.switchScene("CreateTvSeriesScreen");
    }
}
