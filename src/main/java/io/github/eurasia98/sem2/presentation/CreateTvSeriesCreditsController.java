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

public class CreateTvSeriesCreditsController implements Initializable {

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
    private Button btnAvailableUsername;

    @FXML
    private TextField txtFieldNewUsername;

    @FXML
    private TextField txtFieldPassword;

    @FXML
    private TextField txtFieldFirstName;

    @FXML
    private TextField txtFieldLastName;

    @FXML
    private TextField txtFieldEmail;

    @FXML
    private Button btnSavePerson;

    @FXML
    private TextField txtFieldNewRole;

    @FXML
    private TextField txtFieldNewRoleName;

    @FXML
    private TextArea txtArea;

    @FXML
    private Button btnCreateNewPerson;

    @FXML
    private Button btnKnownUser;

    @FXML
    private TextField txtFieldUsername;

    @FXML
    private TextField txtFieldRole;

    @FXML
    private TextField txtFieldRoleName;

    @FXML
    private Button btnSaveCredit;

    @FXML
    private TextField txtFieldUserId;

    @FXML
    private Button btnMyPage;

    @FXML
    private void ivLogoActionHandler(MouseEvent mouseEvent) {
        App.resetSelects();
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
    void btnAvailableUsernameHandler() {
        if (App.getCreditSystem().availableUsername(txtFieldNewUsername.getText())) {
            txtArea.clear();
            txtArea.setVisible(true);
            txtArea.setText("Brugernavnet: " + txtFieldNewUsername.getText() + " er ledigt. ");
            txtArea.setEditable(false);
        } else {
            txtArea.clear();
            txtArea.setVisible(true);
            txtArea.setText("Brugernavnet: " + txtFieldNewUsername.getText() + " er ikke ledigt. ");
            txtArea.setEditable(false);
            txtFieldNewUsername.clear();
        }
    }

    @FXML
    void btnCreateNewPersonHandler() {
        resetFields();
        txtFieldNewUsername.setVisible(true);
        txtFieldPassword.setVisible(true);
        txtFieldFirstName.setVisible(true);
        txtFieldLastName.setVisible(true);
        txtFieldEmail.setVisible(true);
        txtFieldNewRole.setVisible(true);
        txtFieldNewRoleName.setVisible(true);
        btnSavePerson.setVisible(true);
        btnAvailableUsername.setVisible(true);
    }

    @FXML
    void btnKnownUserHandler() {
        resetFields();
        txtFieldUsername.setVisible(true);
        txtFieldUserId.setVisible(true);
        txtFieldRole.setVisible(true);
        txtFieldRoleName.setVisible(true);

        btnSaveCredit.setVisible(true);
    }

    @FXML
    void btnMyPageHandler() {
        App.resetSelects();
        App.switchScene("FrontPage");
    }

    @FXML
    void btnSaveCreditHandler() {
        if (!txtFieldUsername.getText().isEmpty()){
            ArrayList<String> creditInfo = new ArrayList<>();
            creditInfo.add(txtFieldUsername.getText());
            creditInfo.add(App.getSelectedProductionToEdit());
            creditInfo.add(txtFieldRole.getText());
            creditInfo.add(txtFieldRoleName.getText());

            if (App.getCreditSystem().createNewTvSeriesCreditWithUsername(creditInfo) == true){
                resetFields();
                App.switchScene("EditMyTvSeriesCreditsScreen");
            } else {
                resetFields();
                txtArea.setVisible(true);
                txtArea.setEditable(false);
                txtArea.appendText("Der skete desværre en fejl. ");
            }
        } else if (!txtFieldUserId.getText().isEmpty()){
            ArrayList<String> creditsInfo = new ArrayList<>();
            creditsInfo.add(txtFieldUserId.getText());
            creditsInfo.add(App.getSelectedProductionToEdit());
            creditsInfo.add(txtFieldRole.getText());
            creditsInfo.add(txtFieldRoleName.getText());
            if (App.getCreditSystem().createNewTvSeriesCredit(creditsInfo) == true){
                resetFields();
                App.switchScene("EditMyTvSeriesCreditsScreen");
            } else {
                resetFields();
                txtArea.appendText("Krediteringen blev ikke gemt. Noget gik galt. ");
            }
        } else {
            resetFields();
            txtArea.setVisible(true);
            txtArea.appendText("Der skete desværre en fejl. ");
        }

    }

    @FXML
    void btnSavePersonHandler() {

        if (!txtFieldNewUsername.getText().isEmpty()){
            ArrayList<String> creditInfo = new ArrayList<>();
            creditInfo.add(txtFieldNewUsername.getText());
            creditInfo.add(txtFieldPassword.getText());
            creditInfo.add(txtFieldFirstName.getText());
            creditInfo.add(txtFieldLastName.getText());
            if (!txtFieldEmail.getText().isEmpty()){
                creditInfo.add(txtFieldEmail.getText());
            } else creditInfo.add(null);
            creditInfo.add(txtFieldNewRole.getText());
            creditInfo.add(txtFieldNewRoleName.getText());
            creditInfo.add(App.getSelectedProductionToEdit());
            if (App.getCreditSystem().createNewTvSeriesCreditAndPerson(creditInfo)== true){
                resetFields();
                App.switchScene("EditMyTvSeriesCreditsScreen");
            } else txtArea.appendText("Der skete desværre en fejl. ");
        } else {
            resetFields();
            txtArea.setVisible(true);
            txtArea.appendText("Der skete desværre en fejl. ");
        }

    }



    public void resetFields(){
        txtFieldUsername.clear();
        txtFieldUsername.setVisible(false);
        txtFieldUserId.clear();
        txtFieldUserId.setVisible(false);
        txtFieldRole.clear();
        txtFieldRole.setVisible(false);
        txtFieldRoleName.clear();
        txtFieldRoleName.setVisible(false);
        btnSaveCredit.setVisible(false);

        btnAvailableUsername.setVisible(false);
        txtFieldNewUsername.clear();
        txtFieldNewUsername.setVisible(false);
        txtFieldPassword.clear();
        txtFieldPassword.setVisible(false);
        txtFieldFirstName.clear();
        txtFieldFirstName.setVisible(false);
        txtFieldLastName.clear();
        txtFieldLastName.setVisible(false);
        txtFieldEmail.clear();
        txtFieldEmail.setVisible(false);
        txtFieldNewRole.clear();
        txtFieldNewRole.setVisible(false);
        txtFieldNewRoleName.clear();
        txtFieldNewRoleName.setVisible(false);
        btnSavePerson.setVisible(false);

        txtArea.setVisible(false);
        txtArea.setEditable(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resetFields();
        loggedIn();
    }
}
