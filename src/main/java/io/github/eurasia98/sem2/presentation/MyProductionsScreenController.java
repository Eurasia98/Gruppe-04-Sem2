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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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
    private Button btnDeleteProduction;

    @FXML
    private Button btnConfirmDelete;

    @FXML
    void btnConfirmDeleteHandler(ActionEvent event) {
        try {
            if (App.getCreditSystem().deleteProduction(tvMyProductions.getSelectionModel().getSelectedItem().getProduction_id()) == true){
                resetFields();
                update();
                txtAreaDisplayInfo.appendText("Produktionen er slettet. ");
            } else {
                resetFields();
                update();
                txtAreaDisplayInfo.appendText("Der skete desværre en fejl. ");
            }
        } catch (java.lang.NullPointerException e){
            resetFields();
            update();
        }
    }

    @FXML
    void btnDeleteProductionHandler(ActionEvent event) {
        btnConfirmDelete.setVisible(true);
    }

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
    void btnEditCreditsHandler(ActionEvent event) {

        switch (tvMyProductions.getSelectionModel().getSelectedItem().getProduction_type()){
            case "Movie":
                App.setSelectedProduction(tvMyProductions.getSelectionModel().getSelectedItems().get(0).getProduction_id());
                App.switchScene("EditMovieCreditsScreen");
                break;

            case "Serie":
                App.setSelectedProduction(tvMyProductions.getSelectionModel().getSelectedItem().getProduction_id());
                App.switchScene("EditMyTvSeriesCreditsScreen");
                break;

            case "Tv program":
                App.setSelectedProduction(tvMyProductions.getSelectionModel().getSelectedItem().getProduction_id());
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
                App.setSelectedProduction(tvMyProductions.getSelectionModel().getSelectedItem().getProduction_id());
                App.switchScene("ChoosenMovieToEditScreen");
                break;
            case "Serie":
                App.setSelectedProduction(tvMyProductions.getSelectionModel().getSelectedItem().getProduction_id());
                App.setSelectedTvSeries(App.getCreditSystem().getSeriesId(App.getSelectedProduction()));
                App.switchScene("ChoosenTvShowToEdit");
                break;
            case "Tv program":
                App.setSelectedProduction(tvMyProductions.getSelectionModel().getSelectedItem().getProduction_id());
                App.switchScene("ChoosenTvProgramToEdit");
                break;
        }
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
        loggedIn();
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
        btnConfirmDelete.setVisible(false);

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
