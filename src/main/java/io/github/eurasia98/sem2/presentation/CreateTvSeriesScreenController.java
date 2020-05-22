package io.github.eurasia98.sem2.presentation;

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

public class CreateTvSeriesScreenController implements Initializable {

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
    void btnMyPageHandler() {
        App.switchScene("AccountScreen");
    }

    @FXML
    void btnSaveSeriesHandler() {
        ArrayList<String> seriesInfo = new ArrayList<>();
        seriesInfo.add(txtFieldProductionId.getText());
        seriesInfo.add(txtFieldTitle.getText());
        seriesInfo.add(txtFieldSeriesId.getText());
        seriesInfo.add(txtAreaDescription.getText());
        if (App.getCreditSystem().createNewTv_series(seriesInfo) == true){
            resetFields();
            updateSucces();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void resetFields(){
        txtFieldTitle.clear();
        txtFieldProductionId.clear();
        txtFieldSeriesId.clear();
        txtAreaDescription.clear();
        txtAreaDisplayInfo.clear();
        txtAreaDisplayInfo.setEditable(false);
    }

    public void updateSucces(){
        txtAreaDisplayInfo.appendText("Serien er blevet oprettet. Find den under mine produktioner. " +
                "\n Her kan du ligeledes tilføje sæsoner og episoder til serien. ");
    }
}
