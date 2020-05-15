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

public class MyProductionsScreenController implements Initializable {



    @FXML
    private ImageView IVLogo;

    @FXML
    private TableView<ModelTableMyProductions> TVMyProductions;

    @FXML
    private TableColumn<ModelTableMyProductions, String> TVCProduction_id;

    @FXML
    private TableColumn<ModelTableMyProductions, String> TVCTitle;

    @FXML
    private TableColumn<ModelTableMyProductions, String> TVCProductionType;

    @FXML
    private Button btnEditProductionId;

    @FXML
    private Button btnEditTitle;

    @FXML
    private Button btnEditCredits;

    @FXML
    private Button btnMyPage;

    @FXML
    private TextField txtFieldCurrentProductionId;

    @FXML
    private TextField txtFieldNewId;

    @FXML
    private TextField txtFieldCurrentTitle;

    @FXML
    private TextField txtFieldNewTitle;

    @FXML
    private Button btnSaveChanges;

    @FXML
    private TextArea txtAreaDisplayInfo;

    @FXML
    void IVLogoHandler() {
        App.switchScene("FrontPage");
    }

    @FXML
    void btnEditCreditsHandler(ActionEvent event) {
        resetFields();
        App.setSelectedProductionToEdit(TVMyProductions.getSelectionModel().getSelectedItems().get(0).getProduction_id());
        App.switchScene("EditCreditsScreen");
    }

    @FXML
    void btnEditProductionIdHandler(ActionEvent event) {
        resetFields();
        // Object selectedItems = TVMyProductions.getSelectionModel().getSelectedItems().get(0);

        txtFieldCurrentProductionId.setManaged(true);
        txtFieldCurrentProductionId.setVisible(true);
        txtFieldCurrentProductionId.setText(TVMyProductions.getSelectionModel().getSelectedItems().get(0).getProduction_id());

        txtFieldNewId.setManaged(true);
        txtFieldNewId.setVisible(true);


    }

    @FXML
    void btnEditTitleHandler(ActionEvent event) {
        resetFields();

        txtFieldCurrentTitle.setManaged(true);
        txtFieldCurrentTitle.setVisible(true);
        txtFieldCurrentTitle.setText(TVMyProductions.getSelectionModel().getSelectedItems().get(0).getTitle());

        txtFieldNewTitle.setManaged(true);
        txtFieldNewTitle.setVisible(true);
    }

    @FXML
    void btnMyPageHandler(ActionEvent event) {
        resetFields();
        App.switchScene("AccountScreen");
    }

    @FXML
    void btnSaveChangesHandler(ActionEvent event) {
        if (!txtFieldNewId.getText().isEmpty()){
            if (App.getCreditSystem().editProductionId(txtFieldCurrentProductionId.getText(), txtFieldNewId.getText()) == true){
                txtAreaDisplayInfo.appendText("Ændringen er blevet gemt. ");
                resetFields();
            } else txtAreaDisplayInfo.appendText("Ændringen blev ikke gemt, der skete desværre en fejl. ");
        } else if (!txtFieldNewTitle.getText().isEmpty()){
            if (App.getCreditSystem().editTitle(txtFieldNewTitle.getText(), TVMyProductions.getSelectionModel().getSelectedItems().get(0).getProduction_id()) == true){
                txtAreaDisplayInfo.appendText("Ændringen er blevet gemt. ");
            } else txtAreaDisplayInfo.appendText("Ændringen er ikke blevet gemt, der skete desværre 2 fejl. ");
        }

        resetFields();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resetFields();
        ObservableList<ModelTableMyProductions> observableList = FXCollections.observableArrayList();
        ArrayList<String[]> myProductions = App.getCreditSystem().showMyProductions();
        for (String[] s : myProductions){
            observableList.add(new ModelTableMyProductions(s[0], s[2], s[3]));
        }

        TVCProduction_id.setCellValueFactory(new PropertyValueFactory<>("production_id"));
        TVCTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        TVCProductionType.setCellValueFactory(new PropertyValueFactory<>("production_type"));

        TVMyProductions.setItems(observableList);
    }

    private void resetFields(){
        txtFieldCurrentProductionId.clear();
        txtFieldCurrentProductionId.setVisible(false);
        txtFieldNewId.clear();
        txtFieldNewId.setVisible(false);
        txtFieldCurrentTitle.clear();
        txtFieldCurrentTitle.setVisible(false);
        txtFieldNewTitle.clear();
        txtFieldNewTitle.setVisible(false);
    }
}
