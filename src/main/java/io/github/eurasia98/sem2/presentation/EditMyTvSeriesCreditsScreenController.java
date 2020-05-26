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

public class EditMyTvSeriesCreditsScreenController implements Initializable {

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
    private TableView<ModelTableEditMyTvSeriesCredits> tvCredits;

    @FXML
    private TableColumn<ModelTableEditMyTvSeriesCredits, String> tvcName;

    @FXML
    private TableColumn<ModelTableEditMyTvSeriesCredits, String> tvcRoleType;

    @FXML
    private TableColumn<ModelTableEditMyTvSeriesCredits, String> tvcRoleName;

    @FXML
    private Button btnAddCredits;

    @FXML
    private Button btnDeleteCredit;

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
    void btnAddCreditsHandler() {
        App.switchScene("CreateTvSeriesCreditsScreen");
    }

    @FXML
    void btnDeleteCreditHandler() {
        try{
            String role = tvCredits.getSelectionModel().getSelectedItem().getRoleType();
            String roleName = tvCredits.getSelectionModel().getSelectedItem().getRoleName();
            if (App.getCreditSystem().deleteCreditFromTvSeries(role, roleName,App.getSelectedProduction()) == true){
                update();
            } else {
                update();
            }
        } catch (java.lang.NullPointerException e){
            update();
        }
    }

    private void update() {
        ObservableList<ModelTableEditMyTvSeriesCredits> observableList = FXCollections.observableArrayList();
        ArrayList<String[]> creditsInfo = App.getCreditSystem().getCreditsInfo(App.getSelectedProduction());

        for (String[] s : creditsInfo){
            observableList.add(new ModelTableEditMyTvSeriesCredits(s[0], s[1] ,s[2]));
        }

        tvcName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tvcRoleType.setCellValueFactory(new PropertyValueFactory<>("roleType"));
        tvcRoleName.setCellValueFactory(new PropertyValueFactory<>("roleName"));

        tvCredits.setItems(observableList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        update();
        loggedIn();
    }
}
