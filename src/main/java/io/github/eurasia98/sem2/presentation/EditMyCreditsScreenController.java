package io.github.eurasia98.sem2.presentation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EditMyCreditsScreenController implements Initializable {
    @FXML
    private ImageView IVLogo;

    @FXML
    private Button btnMyPage;

    @FXML
    private Button btnAddCredit;

    @FXML
    private Button btnSaveChanges;

    @FXML
    private Button btnDeleteCredit;

    @FXML
    private TableView<ModelTableEditMyCredits> tvMyCredits;

    @FXML
    private TableColumn<ModelTableEditMyCredits, String> tvcFirstname;

    @FXML
    private TableColumn<ModelTableEditMyCredits, String> tvcLastname;

    @FXML
    private TableColumn<ModelTableEditMyCredits, String> tvcUserId;

    @FXML
    private TableColumn<ModelTableEditMyCredits, String> tvcTitle;

    @FXML
    private TableColumn<ModelTableEditMyCredits, String> tvcProductionType;

    @FXML
    private TableColumn<ModelTableEditMyCredits, String> tvcRoleName;

    @FXML
    private TableColumn<ModelTableEditMyCredits, String> tvcRoleType;

    @FXML
    private TextArea txtAreaInfo;

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
    private TableView<ModelTablePersonsInMyCredits> tvSearchPersons;

    @FXML
    private TableColumn<ModelTablePersonsInMyCredits, String> tvcSearchPersonsFirstName;

    @FXML
    private TableColumn<ModelTablePersonsInMyCredits, String> tvcSearchPersonsLastName;

    @FXML
    private TableColumn<ModelTablePersonsInMyCredits, String> tvcSearchPersonsUsername;

    @FXML
    private Button btnSearchPersons;

    @FXML
    void IVLogoHandler() {
        App.switchScene("FrontPage");
    }

    @FXML
    void btnAddCreditHandler(ActionEvent event) {
        resetFields();
        txtFieldFirstName.setVisible(true);
        txtFieldLastName.setVisible(true);
        txtFieldUsername.setVisible(true);
        txtFieldRoleType.setVisible(true);
        txtFieldRoleName.setVisible(true);
        btnSaveChanges.setVisible(true);
        txtAreaInfo.setVisible(true);

        txtAreaInfo.appendText("Udfyld venligst felterne markeret med stjerne. \n" +
                " Hvis personen der skal krediteres allerede har et account id kan du indtaste det. \n" +
                "Hvis ikke vil systemet præsentere en liste for dig med mulige eksisterende personer. \n" +
                "Du kan også oprette personen som ny person ved ikke at indtaste noget eksisterende id. ");
    }

    @FXML
    void btnMyPageHandler(ActionEvent event) {
        App.switchScene("AccountScreen");
    }

    @FXML
    void btnDeleteCreditHandler(ActionEvent event) {

    }

/*    @FXML
    void btnSaveChangesHandler(ActionEvent event) {
        App.getCreditSystem().availableUsername(txtFieldUsername.getText());
            App.getCreditSystem().createNewPerson(txtFieldUsername.getText(), "test123",
                    txtFieldFirstName.getText(), txtFieldLastName.getText());
            ArrayList<String> personsInfo = App.getCreditSystem().getPersonInfo(txtFieldUsername.getText());
            String production_id = App.getSelectedProductionToEdit();
            if (App.getCreditSystem().createNewCredit(Integer.parseInt(personsInfo.get(1)), production_id,
                    txtFieldRoleType.getText(), txtFieldRoleName.getText()) == true){
                resetFields();
                txtAreaInfo.setVisible(true);
                txtAreaInfo.appendText("Personen er blevet oprettet. \n Krediteringen er blevet oprettet. ");

            }
        }*/

    @FXML
    void btnSaveChangesHandler(ActionEvent event) {
        if (App.getCreditSystem().availableUsername(txtFieldUsername.getText()) == true){
            System.out.println("First true");
            App.getCreditSystem().createNewPerson(txtFieldUsername.getText(), "test123",
                    txtFieldFirstName.getText(), txtFieldLastName.getText());
            ArrayList<String> personsInfo = App.getCreditSystem().getPersonInfo(txtFieldUsername.getText());
            String production_id = App.getSelectedProductionToEdit();
            if (App.getCreditSystem().createNewCredit(Integer.parseInt(personsInfo.get(1)), production_id,
                    txtFieldRoleType.getText(), txtFieldRoleName.getText()) == true){
                resetFields();
                txtAreaInfo.setVisible(true);
                txtAreaInfo.appendText("Personen er blevet oprettet. \n Krediteringen er blevet oprettet. ");

                resetFields();

            } else {
                txtAreaInfo.appendText("Der skete desværre en fejl 2. ");
                resetFields();
            }
        } else {
            txtAreaInfo.appendText("Der skete desværre en fejl 1. ");
            resetFields();
        }
    }

    @FXML
    void btnSearchPersonsHandler(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resetFields();
/*        String productionId = App.getSelectedProductionToEdit();
        ObservableList<ModelTableEditMyCredits> observableList = FXCollections.observableArrayList();
        ArrayList<String[]> myCredits = App.getCreditSystem().getCreditsInfo(productionId);

        for (String[] s : myCredits){
            observableList.add(new ModelTableEditMyCredits(s[0], s[1], s[2], s[3], s[4], s[5], s[6]));
        }

        TVCFirstname.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        TVCLastname.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        TVCUserId.setCellValueFactory(new PropertyValueFactory<>("account_id"));
        TVCTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        TVCProductionType.setCellValueFactory(new PropertyValueFactory<>("productionType"));
        TVCRoleType.setCellValueFactory(new PropertyValueFactory<>("roleType"));
        TVCRoleName.setCellValueFactory(new PropertyValueFactory<>("roleName"));


        TVMyCredits.setItems(observableList);*/
    }

    private void resetFields(){

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
        tvSearchPersons.setVisible(false);

        String productionId = App.getSelectedProductionToEdit();
        ObservableList<ModelTableEditMyCredits> observableList = FXCollections.observableArrayList();
        ArrayList<String[]> myCredits = App.getCreditSystem().getCreditsInfo(productionId);

        for (String[] s : myCredits){
            observableList.add(new ModelTableEditMyCredits(s[0], s[1], s[2], s[3], s[4], s[5], s[6]));
        }

        tvcFirstname.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tvcLastname.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tvcUserId.setCellValueFactory(new PropertyValueFactory<>("account_id"));
        tvcTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tvcProductionType.setCellValueFactory(new PropertyValueFactory<>("productionType"));
        tvcRoleType.setCellValueFactory(new PropertyValueFactory<>("roleType"));
        tvcRoleName.setCellValueFactory(new PropertyValueFactory<>("roleName"));

        tvMyCredits.setItems(observableList);
    }
}
