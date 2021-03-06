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
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SearchShowCreditsController implements Initializable {

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
    private TableView<ModelTableSearchCredits> tvMyCredits;

    @FXML
    private TableColumn<ModelTableSearchCredits, String> tvcName;

    @FXML
    private TableColumn<ModelTableSearchCredits, String> tvcRoleName;

    @FXML
    private TableColumn<ModelTableSearchCredits, String> tvcRoleType;

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

    @FXML
    private Button btnExportDataAction;

    @FXML
    private void btnExportDataActionHandler(ActionEvent event) {
        ArrayList<String[]> creditsInfo = App.getCreditSystem().getSearchCredits(App.getSelectedSearchResult());
        App.setSelectedCreditsToExport(creditsInfo);
        if (App.getCreditSystem().exportData(creditsInfo, App.getCreditSystem().getProductionTitle(App.getSelectedSearchResult()))){
            update();
        }
        else {
            update();
        }
    }

    private void loggedIn(){
        if (!App.getUserInfo().isEmpty()){
            lblAccount.setText(App.getUserInfo().get(0));
            Hyperlink myPage = new Hyperlink("Min side");
            myPage.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    App.resetSelects();
                    App.switchScene("AccountScreen");
                }
            });
            vBoxAccount.getChildren().add(1, myPage);
        }
    }

    @FXML
    private void ivSearchHandler() {
        try {
            App.resetSelects();
            App.setSearchField(txtFieldSearch.getText());
            App.switchScene("SearchScreenUpdatedScreen");
        } catch (java.lang.NullPointerException e) {
            txtFieldSearch.clear();
        }
    }

    @FXML
    private void searchKeyHandler(KeyEvent event) {
        try {
            if (event.getCode().toString().equals("ENTER")) {
                App.resetSelects();
                App.setSearchField(txtFieldSearch.getText());
                App.switchScene("SearchScreenUpdatedScreen");
            }
        } catch (java.lang.NullPointerException e) {
            txtFieldSearch.clear();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        update();
        btnExportDataAction.setVisible(false);
        txtFieldSearch.clear();
        loggedIn();
        if (!App.getUserInfo().isEmpty()){
            if (App.getUserInfo().get(1).equals("RegistreringDanmark")){
                btnExportDataAction.setVisible(true);

            }
        }
    }

    private void update() {
        ObservableList<ModelTableSearchCredits> observableList = FXCollections.observableArrayList();
        ArrayList<String[]> creditsInfo = App.getCreditSystem().getSearchCredits(App.getSelectedSearchResult());
        for (String[] s : creditsInfo) {
            observableList.add(new ModelTableSearchCredits(s[0], s[1], s[2]));
        }

        tvcName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tvcRoleType.setCellValueFactory(new PropertyValueFactory<>("role"));
        tvcRoleName.setCellValueFactory(new PropertyValueFactory<>("role_type"));


        tvMyCredits.setItems(observableList);
    }
}
