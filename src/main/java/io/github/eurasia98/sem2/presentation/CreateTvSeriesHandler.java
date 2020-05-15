package io.github.eurasia98.sem2.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class CreateTvSeriesHandler {
    @FXML
    private TextField txtFieldProductionId;

    @FXML
    private TextField txtFieldTitle;

    @FXML
    private TextField txtFieldSeriesId;

    @FXML
    private Button btnSaveSeries;

    @FXML
    private TextArea txtAreaDescription;

    @FXML
    private ImageView IVLogo;

    @FXML
    private Button btnMyPage;

    @FXML
    private TextArea txtAreaInfo;

    @FXML
    void IVLogoHandler() {
        App.switchScene("FrontPage");
    }

    @FXML
    void btnMyPageHandler(ActionEvent event) {
        App.switchScene("AccountScreen");
    }

    @FXML
    void btnSaveSeriesHandler() {
        if (!txtFieldTitle.getText().isEmpty() && !txtFieldProductionId.getText().isEmpty() &&
                !txtFieldSeriesId.getText().isEmpty() && !txtAreaDescription.getText().isEmpty()){
            txtAreaInfo.clear();
            App.getCreditSystem().createNewTv_series(txtFieldTitle.getText(),
                    txtFieldProductionId.getText(), txtFieldSeriesId.getText(), txtAreaDescription.getText());
            txtAreaInfo.appendText("Serien er blevet gemt. ");
        } else if (!txtFieldTitle.getText().isEmpty() && !txtFieldProductionId.getText().isEmpty() &&
                !txtFieldSeriesId.getText().isEmpty() &&
                (txtAreaDescription.getText().isEmpty() || txtAreaDescription.getText() == null)){
            txtAreaInfo.clear();
            App.getCreditSystem().createNewTv_series(txtFieldTitle.getText(), txtFieldProductionId.getText(),
                    txtFieldSeriesId.getText(), null);
            txtAreaInfo.appendText("Serien er blevet gemt. ");
        } else {
            txtAreaInfo.clear();
            txtFieldTitle.setStyle("-fx-prompt-text-fill: red");
            txtFieldProductionId.setStyle("-fx-prompt-text-fill: red");
            txtFieldSeriesId.setStyle("-fx-prompt-text-fill: red");
            txtAreaInfo.appendText("Udfyld venligst alle felter markeret med stjerne. ");
        }
    }
}
