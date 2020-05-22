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

public class CreateSeasonController implements Initializable {

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
    private TextField txtFieldTitle;

    @FXML
    private TextField txtFieldSeasonNumber;

    @FXML
    private TextField txtFieldSeasonId;

    @FXML
    private Button btnMyPage;

    @FXML
    private Button btnSave;

    @FXML
    private TextArea txtAreaDisplayInfo;

    @FXML
    void btnMyPageHandler() {
        App.switchScene("AccountScreen");
    }

    @FXML
    void btnSaveHandler() {
        ArrayList<String> seasonInfo = new ArrayList<>();
        seasonInfo.add(txtFieldSeasonId.getText());
        seasonInfo.add(txtFieldTitle.getText());
        seasonInfo.add(txtFieldSeasonNumber.getText());
        if (App.getCreditSystem().createNewSeason(seasonInfo) == true){
            resetFields();
            updateSucces();
        }
    }

    @FXML
    void ivLogoHandler() {
        App.switchScene("FrontPage");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void updateSucces(){
        txtAreaDisplayInfo.appendText("Sæsonen er blevet gemt. Den kan findes under Detaljer " +
                "under Mine produktioner, hvor du også kan tilføje episoder. ");
    }

    public void resetFields(){
        txtFieldTitle.clear();
        txtFieldSeasonNumber.clear();
        txtFieldSeasonId.clear();
        txtAreaDisplayInfo.clear();
    }
}
