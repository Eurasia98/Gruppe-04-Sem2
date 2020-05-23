package io.github.eurasia98.sem2.presentation;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreateTvProgramController implements Initializable {

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
    private TextField txtFieldProductionId;

    @FXML
    private TextArea txtAreaDescription;

    @FXML
    private Button btnSave;

    @FXML
    private void ivLogoActionHandler(MouseEvent mouseEvent) {
        App.switchScene("FrontPage");
    }

    @FXML
    private void txtFieldSearchKeyPressHandler(KeyEvent event) {
        if (event.getCode().toString().equals("ENTER")){
            ivSearchMouseClickHandler();
        }
    }

    @FXML
    private void ivSearchMouseClickHandler() {
        if(!txtFieldSearch.getText().isEmpty()) {
            App.setSearchField(txtFieldSearch.getText());
            App.switchScene("searchScreen");
        } else {
            txtFieldSearch.setStyle("-fx-prompt-text-fill: red");
        }
    }

    private void loggedIn(){
        lblAccount.setText(App.getUserInfo().get(0));
        Hyperlink myPage = new Hyperlink("Min side");
        myPage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                App.switchScene("AccountScreen");
            }
        });
        vBoxAccount.getChildren().add(1, myPage);
    }

    @FXML
    void btnSaveHandler() {
        if (!txtFieldTitle.getText().isEmpty() && !txtFieldProductionId.getText().isEmpty()){
            ArrayList<String> tvProgramInfo = new ArrayList<>();
            tvProgramInfo.add(txtFieldProductionId.getText());
            tvProgramInfo.add(txtFieldTitle.getText());
            if (!txtAreaDescription.getText().isEmpty()){
                tvProgramInfo.add(txtAreaDescription.getText());
            } else {
                tvProgramInfo.add(null);
            }
            if (App.getCreditSystem().createTvProgram(tvProgramInfo)){
                updateSucces();
            } else {
                updateFailure();
            }
        } else {
            updateFailure();
        }
    }

    private void updateFailure() {
        txtAreaDescription.clear();
        txtAreaDescription.appendText("Der skete desværre en fejl.");
        txtAreaDescription.setEditable(true);

        txtFieldProductionId.clear();
        txtFieldTitle.clear();
    }

    private void updateSucces() {
        App.switchScene("MyProductionsScreen");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loggedIn();
    }
}
