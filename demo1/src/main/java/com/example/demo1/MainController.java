package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private void handleButton1(ActionEvent event) {
        openNewStage("Stage1.fxml", "Stage 1");
    }

    @FXML
    private void handleButton2(ActionEvent event) {
        openNewStage("Stage2.fxml", "Stage 2");
    }

    @FXML
    private void handleButton3(ActionEvent event) {
        openNewStage("Stage3.fxml", "Stage 3");
    }
    @FXML
    private void handleButton4(ActionEvent event) {
        openNewStage("Stage4.fxml", "Stage 4");
    }

    private void openNewStage(String fxmlFile, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) button1.getScene().getWindow();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
