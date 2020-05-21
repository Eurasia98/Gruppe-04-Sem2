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
import java.util.Collections;
import java.util.ResourceBundle;

public class CreateRegDanmarkScreenController implements Initializable {


    @FXML
    private ImageView ivLogo;

    @FXML
    private Label lblAccount;

    @FXML
    private VBox vBoxAccount;

    @FXML
    private Label lblAvailableUsername;

    @FXML
    private TextField txtFieldUsername;

    @FXML
    private PasswordField pwField;

    @FXML
    private PasswordField pwField2;

    @FXML
    private Button btnCreate;

    @FXML
    private Button btnAvailableUsername;

    @FXML
    private Label lblPassword;

    @FXML
    private Label lblUserCreated;

    @FXML
    private void ivLogoActionHandler(MouseEvent mouseEvent) {
        App.switchScene("FrontPage");
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
    private void btnAvailableUsernameHandler(ActionEvent event) {
        if (App.getCreditSystem().availableUsername(txtFieldUsername.getText())){
            lblAvailableUsername.setText("Ledigt.");
            btnCreate.setVisible(true);
        } else {
            lblAvailableUsername.setText("Ikke ledigt.");
        }
    }

    @FXML
    private void btnCreateActionHandler(ActionEvent event) {
        if (App.getCreditSystem().availableUsername(txtFieldUsername.getText()) && pwField.getText().equals(pwField2.getText())){
            if (App.getCreditSystem().createSpecialAccount(txtFieldUsername.getText(), pwField.getText(), "RegistreringDanmark")){
                lblUserCreated.setVisible(true);
                lblUserCreated.setText("Bruger oprettet");
                txtFieldUsername.clear();
                pwField.clear();
                pwField2.clear();
            }
            else{
                lblAvailableUsername.setVisible(true);
                lblUserCreated.setText("Fejl med oprettelse af bruger");
            }
        }
        else {
            if (!App.getCreditSystem().availableUsername(txtFieldUsername.getText())) {
                lblAvailableUsername.setText("Ikke ledigt.");
            }
            if (!pwField.getText().equals(pwField2.getText())) {
                lblPassword.setText("Kodeorde er ikke ens");
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loggedIn();
    }
}
