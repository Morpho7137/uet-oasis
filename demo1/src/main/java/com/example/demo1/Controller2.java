package com.example.demo1;

import appSet.Operations3;
import appSet.Word;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class Controller2 extends Controller1 {
    @FXML
    Button speakButton;

    @FXML
    private TextField searchTermTextField;

    @FXML
    private TextField keyTextField;

    @FXML
    private TextArea resultTextArea;


    private String currentKey;



    public Controller2() {

    }
    private Operations3 operations3;
    private Operations3 operations32;

    @Override
    void initialize() {
        super.initialize();


        operations3 = new Operations3("en_vi - Copy - Copy2.txt");
        operations32 = new Operations3("D:\\New folder\\demo1\\src\\main\\resources\\com\\example\\demo1\\star_vietanh.txt");
    }

    @Override
    void findButtonClicked(ActionEvent event) {
        super.findButtonClicked(event);

        String searchTerm = searchTermTextField.getText().trim();
        if (!searchTerm.isEmpty()) {
            currentKey = searchTerm;
            keyTextField.setText(currentKey);
        }
    }
    @FXML
    private RadioButton englishRadioButton;
    @FXML
    private RadioButton vietnameseRadioButton;

    @FXML
    void updateButtonClicked(ActionEvent event) {
        String newTarget = keyTextField.getText().trim();
        String newExplanation = resultTextArea.getText().trim();

        if (!keyTextField.getText().isEmpty() && !newTarget.isEmpty() && !newExplanation.isEmpty()) {
            if (englishRadioButton.isSelected()) {
                String message = operations3.editWord(Word.getWordMap(), keyTextField.getText(), newTarget, newExplanation);
                resultTextArea.setText(message);
            } else {
                String message = operations32.editWord(Word.getWordMap2(), keyTextField.getText(), newTarget, newExplanation);
                resultTextArea.setText(message);
            }
        } else {
            resultTextArea.setText("Cần có đối tượng để cập nhật hoặc xóa.");
        }
    }

    @FXML
    void deleteButtonClicked(ActionEvent event) {
        String wordToDelete = keyTextField.getText().trim();

        if (!wordToDelete.isEmpty()) {
            if (englishRadioButton.isSelected()) {
                String message = operations3.deleteWord(Word.getWordMap(), wordToDelete);
                resultTextArea.setText(message);
            } else {
                String message = operations32.deleteWord(Word.getWordMap2(), wordToDelete);
                resultTextArea.setText(message);
            }
        } else {
            resultTextArea.setText("Cần có đối tượng để cập nhật hoặc xóa.");
        }
    }


}