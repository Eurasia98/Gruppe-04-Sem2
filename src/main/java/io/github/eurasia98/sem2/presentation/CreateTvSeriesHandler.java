package io.github.eurasia98.sem2.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreateTvSeriesHandler implements Initializable {

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
    private Button btnMyPage;

    @FXML
    private TextArea txtAreaDisplayInfo;

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
            ArrayList<String> seriesInfo = new ArrayList<>();
            seriesInfo.add(txtFieldProductionId.getText());
            seriesInfo.add(txtFieldTitle.getText());
            seriesInfo.add("Serie");
            seriesInfo.add(txtFieldSeriesId.getText());
            seriesInfo.add(txtAreaDescription.getText());
            if(App.getCreditSystem().createNewTv_series(seriesInfo) == true){
                //txtAreaInfo.appendText("Succes. ");
                /*resetFields();
                updateSucces();*/
                txtAreaDisplayInfo.appendText("Succes");
            } else {
                resetFields();
                updateFailure();
            }
         }
        else if (!txtFieldTitle.getText().isEmpty() && !txtFieldProductionId.getText().isEmpty() &&
                !txtFieldSeriesId.getText().isEmpty() &&
                (txtAreaDescription.getText().isEmpty() || txtAreaDescription.getText() == null)){
            ArrayList<String> seriesInfo = new ArrayList<>();
            seriesInfo.add(txtFieldProductionId.getText());
            seriesInfo.add(txtFieldTitle.getText());
            seriesInfo.add("Serie");
            seriesInfo.add(txtFieldSeriesId.getText());
            seriesInfo.add(null);
            if (App.getCreditSystem().createNewTv_series(seriesInfo) == true){
                resetFields();
                updateSucces();
            } else {
                resetFields();
                updateFailure();
            }
        } else {
            resetFields();
            txtFieldTitle.setStyle("-fx-prompt-text-fill: red");
            txtFieldProductionId.setStyle("-fx-prompt-text-fill: red");
            txtFieldSeriesId.setStyle("-fx-prompt-text-fill: red");
            txtAreaDisplayInfo.appendText("Udfyld venligst alle felter markeret med stjerne. ");
        }
    }

    public void resetFields(){
        txtFieldTitle.clear();
        txtAreaDescription.clear();
        txtFieldSeriesId.clear();
        txtFieldProductionId.clear();
        txtAreaDisplayInfo.clear();
        txtAreaDisplayInfo.setEditable(false);
    }

    public void updateSucces(){
        txtAreaDisplayInfo.appendText("Serien blev gemt. Se den under mine produktioner. ");
    }

    private void updateFailure() {
        txtAreaDisplayInfo.appendText("Der skete desværre en fejl. ");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resetFields();
    }
}
