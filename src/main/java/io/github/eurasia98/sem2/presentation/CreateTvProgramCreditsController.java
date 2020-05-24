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
import org.postgresql.jdbc2.ArrayAssistant;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreateTvProgramCreditsController implements Initializable {

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
    private TextField txtFieldUsername;

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
    private TextField txtFieldRole;

    @FXML
    private TextField txtFieldNewRole;

    @FXML
    private TextArea txtArea;

    @FXML
    private Button btnCreateNewPerson;

    @FXML
    private Button btnKnownUser;

    @FXML
    private Button btnSaveCredit;

    @FXML
    private TextField txtFieldUserId;

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
    void btnAvailableUsernameHandler() {
        if (App.getCreditSystem().availableUsername(txtFieldNewUsername.getText())) {
            txtArea.clear();
            txtArea.setText("Brugernavnet: " + txtFieldNewUsername.getText() + " er ledigt. ");
        } else {
            txtArea.clear();
            txtArea.setText("Brugernavnet: " + txtFieldNewUsername.getText() + " er ikke ledigt. ");
            txtFieldNewUsername.clear();
        }
    }

    @FXML
    void btnCreateNewPersonHandler() {
        resetFields();
        btnAvailableUsername.setVisible(true);
        btnSavePerson.setVisible(true);

        txtFieldNewUsername.setVisible(true);
        txtFieldPassword.setVisible(true);
        txtFieldFirstName.setVisible(true);
        txtFieldLastName.setVisible(true);
        txtFieldEmail.setVisible(true);
        txtFieldNewRole.setVisible(true);
    }

    @FXML
    void btnKnownUserHandler() {
        resetFields();
        txtFieldUsername.setVisible(true);
        txtFieldUserId.setVisible(true);
        txtFieldRole.setVisible(true);
        btnSaveCredit.setVisible(true);
    }

    @FXML
    void btnSaveCreditHandler() {
        try{
            if (!txtFieldUsername.getText().isEmpty()){
                ArrayList<String> creditsInfo = new ArrayList<>();
                creditsInfo.add(txtFieldRole.getText());
                creditsInfo.add(App.getSelectedProductionToEdit());
                creditsInfo.add(txtFieldUsername.getText());
                if (App.getCreditSystem().createNewTvProgramCredit(creditsInfo) == true){
                    resetFields();
                    App.switchScene("EditMyTvProgramCreditsScreen");
                } else {
                    resetFields();
                    txtArea.appendText("Krediteringen blev ikke gemt. Noget gik galt. ");
                }
            } else if (!txtFieldUserId.getText().isEmpty()){
                ArrayList<String> creditsInfo = new ArrayList<>();
                creditsInfo.add(txtFieldRole.getText());
                creditsInfo.add(App.getSelectedProductionToEdit());
                creditsInfo.add(txtFieldUserId.getText());
                if (App.getCreditSystem().createNewTvProgramCreditWithUserId(creditsInfo) == true){
                    resetFields();
                    App.switchScene("EditMyTvProgramCreditsScreen");
                } else {
                    resetFields();
                    txtArea.appendText("Krediteringen blev ikke gemt. Noget gik galt. ");
                }
            } else {
                txtArea.appendText("Der skete desværre en fejl.");
            }
        } catch (java.lang.NumberFormatException e){
            txtArea.appendText("Der skete desværre en fejl. Måske har du skrevet noget forkert. ");
        }
    }

    @FXML
    void btnSavePersonHandler() {
        if (!txtFieldNewUsername.getText().isEmpty()){
            if (App.getCreditSystem().availableUsername(txtFieldNewUsername.getText()) == true){
                ArrayList<String> creditsInfo = new ArrayList<>();
                creditsInfo.add(txtFieldNewUsername.getText());
                creditsInfo.add(txtFieldPassword.getText());
                creditsInfo.add(txtFieldFirstName.getText());
                creditsInfo.add(txtFieldLastName.getText());
                if (!txtFieldEmail.getText().isEmpty()){
                    creditsInfo.add(txtFieldEmail.getText());
                } else creditsInfo.add(null);
                creditsInfo.add(txtFieldNewRole.getText());
                creditsInfo.add(App.getSelectedProductionToEdit());
                if (App.getCreditSystem().createNewTvProgramCreditAndPerson(creditsInfo) == true){
                    App.switchScene("EditMyTvProgramCreditsScreen");
                } else {
                    resetFields();
                    txtArea.appendText("Der skete desværre en fejl. ");
                }
            } else {
                resetFields();
                txtArea.appendText("Brugernavnet er desværre optaget. ");
            }
        } else {
            resetFields();
            txtArea.appendText("Der skete desværre en fejl. ");
        }
    }

    private void resetFields() {
        btnAvailableUsername.setVisible(false);
        btnSavePerson.setVisible(false);
        btnSaveCredit.setVisible(false);

        txtFieldRole.clear();
        txtFieldRole.setVisible(false);
        txtFieldEmail.clear();
        txtFieldEmail.setVisible(false);
        txtFieldLastName.clear();
        txtFieldLastName.setVisible(false);
        txtFieldFirstName.clear();
        txtFieldFirstName.setVisible(false);
        txtFieldPassword.clear();
        txtFieldPassword.setVisible(false);
        txtFieldNewUsername.clear();
        txtFieldNewUsername.setVisible(false);
        txtFieldUsername.clear();
        txtFieldUsername.setVisible(false);
        txtFieldUserId.clear();
        txtFieldUserId.setVisible(false);
        txtFieldNewRole.clear();
        txtFieldNewRole.setVisible(false);
        txtArea.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resetFields();
        loggedIn();
    }
}
