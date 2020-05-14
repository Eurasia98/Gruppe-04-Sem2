package io.github.eurasia98.sem2.presentation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    private TableView<ModelTableEditMyCredits> TVMyCredits;

    @FXML
    private TableColumn<ModelTableEditMyCredits, String> TVCFirstname;

    @FXML
    private TableColumn<ModelTableEditMyCredits, String> TVCLastname;

    @FXML
    private TableColumn<ModelTableEditMyCredits, String> TVCUserId;

    @FXML
    private TableColumn<ModelTableEditMyCredits, String> TVCTitle;

    @FXML
    private TableColumn<ModelTableEditMyCredits, String> TVCProductionType;

    @FXML
    private TableColumn<ModelTableEditMyCredits, String> TVCRoleType;

    @FXML
    private TableColumn<ModelTableEditMyCredits, String> TVCRoleName;

    @FXML
    private TextField txtFieldCurrentFirstname;

    @FXML
    private TextField txtFieldNewFirstname;

    @FXML
    private TextField txtFieldCurrentLastname;

    @FXML
    private TextField txtFieldNewLastname;

    @FXML
    private TextField txtFieldCurrentUserId;

    @FXML
    private TextField txtFieldNewUserId;

    @FXML
    private TextField txtFieldCurrentTitle;

    @FXML
    private TextField txtFieldNewTitle;

    @FXML
    private TextField txtFieldCurrentRoleType;

    @FXML
    private TextField txtFieldNewRoleType;

    @FXML
    private TextField txtFieldCurrentRolename;

    @FXML
    private TextField txtFieldNewRolename;

    @FXML
    private TextField txtFieldCreateFirstname;

    @FXML
    private TextField txtFieldCreateLastname;

    @FXML
    private TextField txtFieldCreateAccountId;

    @FXML
    void IVLogoHandler() {
        App.switchScene("FrontPage");
    }

    @FXML
    void btnAddCreditHandler(ActionEvent event) {

    }

    @FXML
    void btnMyPageHandler(ActionEvent event) {
        App.switchScene("AccountScreen");
    }

    @FXML
    void btnSaveChangesHandler(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resetFields();
        String productionId = App.getSelectedCreditToEdit();
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


        TVMyCredits.setItems(observableList);
    }

    private void resetFields(){
        txtFieldCurrentFirstname.clear();
        txtFieldCurrentFirstname.setVisible(false);
        txtFieldNewFirstname.clear();
        txtFieldNewFirstname.setVisible(false);
        txtFieldCurrentLastname.clear();
        txtFieldCurrentLastname.setVisible(false);
        txtFieldNewLastname.clear();
        txtFieldNewLastname.setVisible(false);
        txtFieldCurrentUserId.clear();
        txtFieldCurrentUserId.setVisible(false);
        txtFieldNewUserId.clear();
        txtFieldNewUserId.setVisible(false);
        txtFieldCurrentRoleType.clear();
        txtFieldCurrentRoleType.setVisible(false);
        txtFieldNewRolename.clear();
        txtFieldNewRolename.setVisible(false);
    }
}
