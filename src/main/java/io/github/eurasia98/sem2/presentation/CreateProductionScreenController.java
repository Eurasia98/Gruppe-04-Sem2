package io.github.eurasia98.sem2.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class CreateProductionScreenController {
    @FXML
    private Button btnCreateMovie;

    @FXML
    private Button btnCreateTvProgram;

    @FXML
    private Button btnCreateTvShow;

    @FXML
    private ImageView IVLogo;

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
