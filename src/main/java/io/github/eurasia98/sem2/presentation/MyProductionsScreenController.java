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
    private TableView<ModelTableMyProductions> tvMyProductions;

    @FXML
    private TableColumn<ModelTableMyProductions, String> tvCProduction_id;

    @FXML
    private TableColumn<ModelTableMyProductions, String> tvCTitle;

    @FXML
    private TableColumn<ModelTableMyProductions, String> tvcProductionType;

    @FXML
    private Button btnEditProductionId;

    @FXML
    private Button btnEditTitle;

    @FXML
    private Button btnEditCredits;

    @FXML
    private Button btnMyPage;

    @FXML
    private Button btnWatchDetails;

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
        App.setSelectedProductionToEdit(tvMyProductions.getSelectionModel().getSelectedItems().get(0).getProduction_id());
        App.switchScene("EditCreditsScreen");
    }

    @FXML
    void btnEditProductionIdHandler(ActionEvent event) {
        resetFields();
        // Object selectedItems = TVMyProductions.getSelectionModel().getSelectedItems().get(0);

        txtFieldCurrentProductionId.setManaged(true);
        txtFieldCurrentProductionId.setVisible(true);
        txtFieldCurrentProductionId.setText(tvMyProductions.getSelectionModel().getSelectedItem().getProduction_id());

        txtFieldNewId.setManaged(true);
        txtFieldNewId.setVisible(true);
    }

    @FXML
    void btnEditTitleHandler(ActionEvent event) {
        resetFields();

        txtAreaDisplayInfo.appendText(App.getCreditSystem().testProductionType(tvMyProductions.getSelectionModel().getSelectedItem().getProduction_id()));

        txtFieldCurrentTitle.setManaged(true);
        txtFieldCurrentTitle.setVisible(true);
        txtFieldCurrentTitle.setText(tvMyProductions.getSelectionModel().getSelectedItem().getTitle());

        txtFieldNewTitle.setManaged(true);
        txtFieldNewTitle.setVisible(true);
    }

    @FXML
    void btnWatchDetailsHandler(ActionEvent event) {
        switch (tvMyProductions.getSelectionModel().getSelectedItem().getProduction_type()){
            case "Movie":
            case "Serie":
                App.setSelectedProductionToEdit(tvMyProductions.getSelectionModel().getSelectedItem().getProduction_id());
                App.setSelectedTvSeriesToEdit(App.getCreditSystem().getSeriesId(App.getSelectedProductionToEdit()));
                System.out.println(App.getSelectedProductionToEdit());
                System.out.println(App.getCreditSystem().getSeriesId(App.getSelectedProductionToEdit()));
                App.switchScene("ChoosenTvShowToEdit");
            case "Tv_Program":
        }
    }

    @FXML
    void btnMyPageHandler(ActionEvent event) {
        resetFields();
        App.switchScene("AccountScreen");
    }

    @FXML
    void btnSaveChangesHandler() {
        if (!txtFieldNewId.getText().isEmpty()){
            if (App.getCreditSystem().editProductionId(txtFieldCurrentProductionId.getText(), txtFieldNewId.getText())){
                txtAreaDisplayInfo.appendText("Ændringen er blevet gemt. ");
                update();
            } else {
                resetFields();
                txtAreaDisplayInfo.appendText("Ændringen blev ikke gemt, der skete desværre en fejl 1. ");
            }
        }
        else if (!txtFieldNewTitle.getText().isEmpty()){
            if (App.getCreditSystem().editTitle(txtFieldNewTitle.getText(), tvMyProductions.getSelectionModel().getSelectedItems().get(0).getProduction_id()) == true){
                txtAreaDisplayInfo.appendText("Ændringen er blevet gemt. ");
                update();
            } else txtAreaDisplayInfo.appendText("Ændringen er ikke blevet gemt, der skete desværre en fejl 2. ");
        } else{
            update();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // resetFields();
        update();
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

    public void update(){
        resetFields();

        ObservableList<ModelTableMyProductions> observableList = FXCollections.observableArrayList();
        ArrayList<String[]> myProductions = App.getCreditSystem().showMyProductions();
        for (String[] s : myProductions){
            observableList.add(new ModelTableMyProductions(s[0], s[2], s[3]));
        }

        tvCProduction_id.setCellValueFactory(new PropertyValueFactory<>("production_id"));
        tvCTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tvcProductionType.setCellValueFactory(new PropertyValueFactory<>("production_type"));

        tvMyProductions.setItems(observableList);
    }


}
