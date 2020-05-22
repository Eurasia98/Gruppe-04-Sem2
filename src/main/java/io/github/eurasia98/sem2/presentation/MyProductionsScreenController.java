package io.github.eurasia98.sem2.presentation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MyProductionsScreenController implements Initializable {

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
        App.resetSelects();
        App.switchScene("FrontPage");
    }

    @FXML
    void btnEditCreditsHandler(ActionEvent event) {

        switch (tvMyProductions.getSelectionModel().getSelectedItem().getProduction_type()){
            case "Movie":
                App.setSelectedProductionToEdit(tvMyProductions.getSelectionModel().getSelectedItems().get(0).getProduction_id());
                App.switchScene("EditMovieCreditsScreen");
                break;

            case "Serie":
                App.setSelectedProductionToEdit(tvMyProductions.getSelectionModel().getSelectedItem().getProduction_id());
                App.switchScene("EditMyTvSeriesCreditsScreen");
                break;

            case "Tv program":
                App.setSelectedProductionToEdit(tvMyProductions.getSelectionModel().getSelectedItem().getProduction_id());
                App.switchScene("EditMyTvProgramCreditsScreen");
                break;
        }
    }

    @FXML
    void btnEditProductionIdHandler(ActionEvent event) {
        txtFieldCurrentProductionId.setManaged(true);
        txtFieldCurrentProductionId.setVisible(true);
        txtFieldCurrentProductionId.setText(tvMyProductions.getSelectionModel().getSelectedItem().getProduction_id());

        txtFieldNewId.setManaged(true);
        txtFieldNewId.setVisible(true);
    }

    @FXML
    void btnEditTitleHandler(ActionEvent event) {
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
                App.setSelectedProductionToEdit(tvMyProductions.getSelectionModel().getSelectedItem().getProduction_id());
                App.switchScene("ChoosenMovieToEditScreen");
                break;
            case "Serie":
                App.setSelectedProductionToEdit(tvMyProductions.getSelectionModel().getSelectedItem().getProduction_id());
                App.setSelectedTvSeriesToEdit(App.getCreditSystem().getSeriesId(App.getSelectedProductionToEdit()));
                App.switchScene("ChoosenTvShowToEdit");
                break;
            case "Tv program":
                App.setSelectedProductionToEdit(tvMyProductions.getSelectionModel().getSelectedItem().getProduction_id());
                App.switchScene("ChoosenTvProgramToEdit");
                break;
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
                update();
                resetFields();
                txtAreaDisplayInfo.appendText("Ændringen er blevet gemt. ");
            } else {
                resetFields();
                txtAreaDisplayInfo.appendText("Ændringen blev ikke gemt, det nye produktions id er desværre optaget. ");
            }
        }
        else if (!txtFieldNewTitle.getText().isEmpty()){
            if (App.getCreditSystem().editTitle(txtFieldNewTitle.getText(), tvMyProductions.getSelectionModel().getSelectedItems().get(0).getProduction_id()) == true){
                update();
                resetFields();
                txtAreaDisplayInfo.appendText("Ændringen er blevet gemt. ");
            } else {
                update();
                resetFields();
                txtAreaDisplayInfo.appendText("Udfyld venligst alle felter markeret med rød. ");
            }
        } else{
            update();
            resetFields();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        update();
        resetFields();
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
        txtAreaDisplayInfo.clear();
        txtAreaDisplayInfo.setEditable(false);

        App.resetSelects();
    }

    public void update(){
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
