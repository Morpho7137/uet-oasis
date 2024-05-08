package com.example.demo1;

import appSet.*;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;


public class Controller1 {


    @FXML
    private TextField searchTermTextField;

    @FXML
    private TextArea resultTextArea;

    @FXML
    private ListView<String> suggestionsListView;

    @FXML
    private RadioButton englishRadioButton;
    @FXML
    private RadioButton vietnameseRadioButton;

    private Operations2 operations2;
    private Operations2 operations22;

    public Controller1() {
    }

    @FXML
    void initialize() {
        englishRadioButton.setSelected(true);

        operations2 = new Operations2(Word.getWordMap());
        operations22 = new Operations2(Word.getWordMap2());

        searchTermTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                suggestionsListView.setVisible(true);


                updateSuggestions(newValue);
            } else {
                suggestionsListView.getItems().clear();
                suggestionsListView.setVisible(false);


            }
        });
        searchTermTextField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                findButtonClicked(null);
            }
        });

        suggestionsListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                searchTermTextField.setText(newValue);
                findButtonClicked(null);
                suggestionsListView.setVisible(false);

            }
        });
    }

    private void updateSuggestions(String input) {
        List<String> suggestedWords;
        if (englishRadioButton.isSelected()) {
            suggestedWords = operations2.suggestWords(input);
        } else {
            suggestedWords = operations22.suggestWords(input);
        }
        ObservableList<String> items = FXCollections.observableArrayList(suggestedWords);
        suggestionsListView.setItems(items);
    }

    @FXML
    void findButtonClicked(ActionEvent event) {
        String searchTerm = searchTermTextField.getText().trim();
        if (!searchTerm.isEmpty()) {
            if (englishRadioButton.isSelected()) {
                resultTextArea.setText(operations2.searchByKey(searchTerm));
            } else {
                resultTextArea.setText(operations22.searchByKey(searchTerm));
            }
            suggestionsListView.setVisible(false);
        }
    }

    @FXML
    private Button backButton;

    @FXML
    void backButtonClicked(ActionEvent event) {
        openMainMenu();
    }

    private void openMainMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private Button speakButton;
    @FXML
    void speakButtonClicked(ActionEvent event) {
        String text = searchTermTextField.getText();
        speak(text);
    }

    private void speak(String text) {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        VoiceManager voiceManager = VoiceManager.getInstance();
        Voice voice = voiceManager.getVoice("kevin");
        if (voice != null) {
            voice.allocate();
            try {
                voice.speak(text);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
