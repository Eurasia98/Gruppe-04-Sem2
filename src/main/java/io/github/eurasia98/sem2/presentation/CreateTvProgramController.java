package io.github.eurasia98.sem2.presentation;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class CreateTvProgramController {
    @FXML
    private Button btnMyPage;

    @FXML
    private ImageView ivLogo;

    @FXML
    private TextField txtFieldTitle;

    @FXML
    private TextField txtFieldProductionId;

    @FXML
    private TextArea txtAreaDescription;

    @FXML
    private Button btnSave;

    @FXML
    void btnMyPageHandler() {
        App.switchScene("AccountScreen");
    }

    @FXML
    void btnSaveHandler() {
        if (!txtFieldTitle.getText().isEmpty() && !txtFieldProductionId.getText().isEmpty()){
            ArrayList<String> tvProgramInfo = new ArrayList<>();
            tvProgramInfo.add(txtFieldProductionId.getText());
            tvProgramInfo.add(txtFieldTitle.getText());
            if (!txtAreaDescription.getText().isEmpty()){
                tvProgramInfo.add(txtAreaDescription.getText());
            } else {
                tvProgramInfo.add(null);
            }
            if (App.getCreditSystem().createTvProgram(tvProgramInfo)){
                updateSucces();
            } else {
                updateFailure();
            }
        } else {
            updateFailure();
        }
    }

    private void updateFailure() {
        txtAreaDescription.clear();
        txtAreaDescription.appendText("Der skete desværre en fejl.");
        txtAreaDescription.setEditable(true);

        txtFieldProductionId.clear();
        txtFieldTitle.clear();
    }

    private void updateSucces() {
        App.switchScene("MyProductionsScreen");
    }

    @FXML
    void ivLogoHandler() {
        App.switchScene("FrontPage");
    }
}
