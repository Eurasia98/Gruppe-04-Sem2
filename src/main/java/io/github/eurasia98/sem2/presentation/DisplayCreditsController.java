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
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class DisplayCreditsController implements Initializable {

    @FXML
    private TextArea txtAreaCreditsDisplay;

    @FXML
    private TextField txtFieldSearch;

    @FXML
    private ImageView ivLogo;

    @FXML
    private ImageView ivSearch;

    @FXML
    private Button btnLogin;

    @FXML
    private VBox vBoxAccount;

    @FXML
    private Label lblAccount;

    @FXML
    private Button btnExportData;

    @FXML
    private Label lblExport;

    @FXML
    private Label lblTitle;

    // Returns to front page
    @FXML
    private void ivLogoActionHandler(MouseEvent event) {
        App.switchScene("FrontPage");
    }

    // Stores content of txtFieldSearch as a static variable in App
    @FXML
    private void ivSearchMouseClickHandler() {
        if(!txtFieldSearch.getText().isEmpty()) {
            App.setSearchField(txtFieldSearch.getText());
            App.switchScene("searchScreen");
        }
        else {
            txtFieldSearch.setStyle("-fx-prompt-text-fill: red");
        }
    }

    // Enables search by pressing return key
    @FXML
    private void txtFieldSearchKeyPressHandler(KeyEvent event) {
        if (event.getCode().toString().equals("ENTER")){
            ivSearchMouseClickHandler();
        }
    }

    @FXML
    private void btnLoginActionHandler(ActionEvent event) {
        App.switchScene("LoginScreen");
    }

    @FXML
    private void btnExportDataActionHandler(ActionEvent event){
        lblExport.setVisible(true);
        if (App.getCreditSystem().exportData(App.getSelectedTitle())){
            lblExport.setText("Krediteringer gemt");
            lblExport.setTextFill(Color.GREEN);
        }
        else {
            lblExport.setText("Fejl med export af krediteringer");
            lblExport.setTextFill(Color.RED);
        }
    }

    private void loggedIn(){
        if (!App.getUserInfo().isEmpty()){
            btnLogin.setVisible(false);
            vBoxAccount.setVisible(true);
            lblAccount.setText(App.getUserInfo().get(0));
            Hyperlink myPage = new Hyperlink("Min side");
            myPage.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    App.switchScene("AccountScreen");
                }
            });
            vBoxAccount.getChildren().add(1, myPage);

            btnExportData.setVisible(true);
        }

        else {
            btnLogin.setVisible(true);
            vBoxAccount.setVisible(false);
            lblAccount.setText("");
            if (vBoxAccount.getChildren().size() == 2){
                vBoxAccount.getChildren().remove(1);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayCredits();
        loggedIn();
        lblTitle.setText(App.getSelectedTitle());
    }

    // Indsætter credits på scenen.
    private void displayCredits() {
        ArrayList<String> creditsToDisplay = App.getCreditSystem().getCreditsToDisplay();
        for (String credit : creditsToDisplay) {
            txtAreaCreditsDisplay.appendText(credit + "\n");
        }
    }
}
