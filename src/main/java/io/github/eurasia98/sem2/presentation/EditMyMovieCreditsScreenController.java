package io.github.eurasia98.sem2.presentation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EditMyMovieCreditsScreenController implements Initializable {
    @FXML
    private ImageView IVLogo;

    @FXML
    private Button btnMyPage;

    @FXML
    private Button btnAddCredit;

    @FXML
    private Button btnDeleteCredit;

    @FXML
    private Button btnKnownUser;

    @FXML
    private Button btnNewUser;

    @FXML
    private Button btnSaveChanges;

    @FXML
    private TableView<ModelTableEditMyMovieCredits> tvMyCredits;

    @FXML
    private TableColumn<ModelTableEditMyMovieCredits, String> tvcName;

    @FXML
    private TableColumn<ModelTableEditMyMovieCredits, String> tvcRoleName;

    @FXML
    private TableColumn<ModelTableEditMyMovieCredits, String> tvcRoleType;

    @FXML
    private TextField txtFieldFirstName;

    @FXML
    private TextField txtFieldLastName;

    @FXML
    private TextField txtFieldUsername;

    @FXML
    private TextField txtFieldRoleType;

    @FXML
    private TextField txtFieldRoleName;

    @FXML
    private TextArea txtAreaInfo;

    @FXML
    private Hyperlink hlinkYes;

    @FXML
    private Hyperlink hlinkNo;

    @FXML
    void IVLogoHandler() {
        App.resetSelects();
        App.switchScene("FrontPage");
    }

    @FXML
    void btnAddCreditHandler(ActionEvent event) {
        resetFields();
        btnKnownUser.setVisible(true);
        btnNewUser.setVisible(true);
    }

    @FXML
    void btnDeleteCreditHandler(ActionEvent event) {
        String role = tvMyCredits.getSelectionModel().getSelectedItem().getRoleType();
        String roleName = tvMyCredits.getSelectionModel().getSelectedItem().getRoleName();
        if (App.getCreditSystem().deleteCredit(role, roleName, App.getSelectedProductionToEdit()) == true) {
            resetFields();
            update();
        } else {
            txtAreaInfo.setVisible(true);
            txtAreaInfo.setEditable(false);
            txtAreaInfo.appendText("Der skete desværre en fejl. ");
        }
    }

    @FXML
    void btnKnownUserHandler(ActionEvent event) {
        resetFields();

        txtAreaInfo.setVisible(true);
        txtAreaInfo.appendText("Udfyld venligst felterne markeret med stjerne. \n" +
                " Hvis personen der skal krediteres allerede har et account id kan du indtaste det. \n" +
                "Hvis ikke vil systemet præsentere en liste for dig med mulige eksisterende personer. \n" +
                "Du kan også oprette personen som ny person ved ikke at indtaste noget eksisterende id. ");

        txtFieldUsername.setVisible(true);
        txtFieldRoleType.setVisible(true);
        txtFieldRoleName.setVisible(true);

        btnSaveChanges.setVisible(true);
    }

    @FXML
    void btnMyPageHandler(ActionEvent event) {
        App.resetSelects();
        App.switchScene("AccountScreen");
    }

    @FXML
    void btnNewUserHandler(ActionEvent event) {
        resetFields();

        txtAreaInfo.setVisible(true);
        txtAreaInfo.appendText("Udfyld venligst felterne markeret med stjerne. \n" +
                " Hvis personen der skal krediteres allerede har et account id kan du indtaste det. \n" +
                "Hvis ikke vil systemet præsentere en liste for dig med mulige eksisterende personer. \n" +
                "Du kan også oprette personen som ny person ved ikke at indtaste noget eksisterende id. ");

        txtFieldFirstName.setVisible(true);
        txtFieldLastName.setVisible(true);
        txtFieldUsername.setVisible(true);
        txtFieldRoleType.setVisible(true);
        txtFieldRoleName.setVisible(true);
        btnSaveChanges.setVisible(true);
    }

    @FXML
    void btnSaveChangesHandler() {
        if (!txtFieldFirstName.getText().isEmpty() && !txtFieldLastName.getText().isEmpty()) {
            if (App.getCreditSystem().availableUsername(txtFieldUsername.getText()) == true) {
                ArrayList<String> personInfo = new ArrayList<>();
                personInfo.add(txtFieldUsername.getText());
                personInfo.add("test");
                personInfo.add(null);
                personInfo.add("Person");
                personInfo.add(txtFieldFirstName.getText());
                personInfo.add(txtFieldLastName.getText());
                if (App.getCreditSystem().createNewPerson(personInfo) == true) {
                    ArrayList<String> personDetails = App.getCreditSystem().getPersonInfo(txtFieldUsername.getText());
                    ArrayList<String> creditsInfo = new ArrayList<>();
                    creditsInfo.add(personDetails.get(1));
                    creditsInfo.add(App.getSelectedProductionToEdit());
                    creditsInfo.add(txtFieldRoleType.getText());
                    creditsInfo.add(txtFieldRoleName.getText());
                    if (App.getCreditSystem().createNewCredit(creditsInfo) == true) {
                        resetFields();
                        update();
                    } else {
                        txtAreaInfo.setVisible(true);
                        txtAreaInfo.setEditable(false);
                        txtAreaInfo.appendText("Der skete desværre en fejl. ");
                    }
                } else {
                    txtAreaInfo.setVisible(true);
                    txtAreaInfo.setEditable(false);
                    txtAreaInfo.appendText("Der skete desværre en fejl. ");
                }
            } else {
                txtAreaInfo.clear();
                txtAreaInfo.appendText("Brugernavnet er optaget. \n");
                txtAreaInfo.appendText("Ønsker du at tilføje krediteringen til den\n eksisterende bruger med dette brugernavn? ");
                setHyperLinks();
            }
        } else if (!txtFieldUsername.getText().isEmpty() && !txtFieldRoleType.getText().isEmpty()) {
            ArrayList<String> creditInfo = new ArrayList<>();
            creditInfo.add(App.getCreditSystem().getAnAccountId(txtFieldUsername.getText()));
            creditInfo.add(App.getSelectedProductionToEdit());
            creditInfo.add(tvMyCredits.getSelectionModel().getSelectedItem().getRoleType());
            creditInfo.add(tvMyCredits.getSelectionModel().getSelectedItem().getRoleName());
            if (App.getCreditSystem().createNewCredit(creditInfo) == true) {
                resetFields();
                update();
            } else {
                resetFields();
                txtAreaInfo.appendText("Noget gik galt. ");
            }
        }
    }

    @FXML
    void hlinkNoHandler() {

    }

    @FXML
    void hlinkYesHandler() {

    }

    public void update() {
        String productionId = App.getSelectedProductionToEdit();
        ObservableList<ModelTableEditMyMovieCredits> observableList = FXCollections.observableArrayList();
        ArrayList<String[]> myCredits = App.getCreditSystem().getCreditsInfo(productionId);

        for (String[] s : myCredits) {
            observableList.add(new ModelTableEditMyMovieCredits(s[0], s[1], s[2]));
        }

        tvcName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tvcRoleType.setCellValueFactory(new PropertyValueFactory<>("roleType"));
        tvcRoleName.setCellValueFactory(new PropertyValueFactory<>("roleName"));

        tvMyCredits.setItems(observableList);
    }

    private void resetFields() {

        txtFieldFirstName.clear();
        txtFieldFirstName.setVisible(false);
        txtFieldLastName.clear();
        txtFieldLastName.setVisible(false);
        txtFieldUsername.clear();
        txtFieldUsername.setVisible(false);
        txtFieldRoleType.clear();
        txtFieldRoleType.setVisible(false);
        txtFieldRoleName.clear();
        txtFieldRoleName.setVisible(false);
        txtAreaInfo.clear();
        txtAreaInfo.setVisible(false);

        btnKnownUser.setVisible(false);
        btnNewUser.setVisible(false);
        btnSaveChanges.setVisible(false);

        hlinkYes.setVisible(false);
        hlinkNo.setVisible(false);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resetFields();
        update();
    }

    public void setHyperLinks() {
        hlinkNo.setVisible(true);
        hlinkYes.setVisible(true);

        hlinkYes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ArrayList<String> creditInfo = new ArrayList<>();
                creditInfo.add(App.getCreditSystem().getAnAccountId(txtFieldUsername.getText()));
                creditInfo.add(App.getSelectedProductionToEdit());
                creditInfo.add(txtFieldRoleType.getText());
                creditInfo.add(txtFieldRoleName.getText());
                if (App.getCreditSystem().createNewCredit(creditInfo) == true) {
                    resetFields();
                    update();
                }
            }
        });

        hlinkNo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                txtFieldUsername.clear();
            }
        });
    }

}
