package com.example.demo1;

import appSet.Word;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.*;

public class Controller4 {

    @FXML
    private TextField keyTextField;

    @FXML
    private TextField answerTextField;

    @FXML
    private TextField scoreTextField;

    @FXML
    private TextField timerTextField;

    @FXML
    private ListView<String> topScoresListView;

    @FXML
    private Button startButton;

    @FXML
    private Button replayButton;

    @FXML
    private Button stopButton;

    @FXML
    private ImageView imageView;
    @FXML
    private Label label;

    private Random random;
    private Timeline timeline;
    private int score;
    private int seconds;

    public Controller4() {
        random = new Random();
    }

    @FXML
    void initialize() {
        timerTextField.setEditable(false);
        keyTextField.setEditable(false);
        imageView.setVisible(false);
        label.setVisible(false);

        answerTextField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                checkAnswer();
            }
        });
        // Initialize the timer
        seconds = 120;
        timerTextField.setText("02:00");

        // Initialize the top scores list view
        updateTopScoresListView(Collections.emptyMap());

        // Set up the timeline for the timer
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            seconds--;
            int minutes = seconds / 60;
            int remainingSeconds = seconds % 60;
            timerTextField.setText(String.format("%02d:%02d", minutes, remainingSeconds));

            if (seconds <= 0) {
                stopGame();
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);

        // Hide the replay and stop buttons initially
        replayButton.setVisible(false);
        stopButton.setVisible(false);

    }

    @FXML
    void startButtonClicked(ActionEvent event) {
        startGame();
    }

    @FXML
    void replayButtonClicked(ActionEvent event) {
        stopGame();
        startGame();
    }

    @FXML
    void stopButtonClicked(ActionEvent event) {
        stopGame();
    }

    private void startGame() {
        score = 0;
        scoreTextField.setText("0");
        startButton.setDisable(true);
        replayButton.setVisible(true);
        stopButton.setVisible(true);
        keyTextField.setText(getRandomKey());
        answerTextField.setText("");
        imageView.setVisible(false);
        label.setVisible(false);

        timeline.play();
    }

    private void stopGame() {
        timeline.stop();
        startButton.setDisable(false);
        keyTextField.setText("Your score: " + scoreTextField.getText());

        // Set lại thời gian về 2 phút
        seconds = 120;
        timerTextField.setText("02:00");

        // Ẩn hai nút replay và stop game
        replayButton.setVisible(false);
        stopButton.setVisible(false);
        imageView.setVisible(false);
        label.setVisible(false);
        scoreTextField.setText(null);

        // Update top scores list view
        Map<String, Integer> topScores = getTopScores();
        updateTopScoresListView(topScores);
    }


    @FXML
    void checkAnswer(ActionEvent event) {
        checkAnswer();
    }

    private void checkAnswer() {
        String key = keyTextField.getText();
        String answer = answerTextField.getText();
        String value = Word.getWordMap3().get(key);

        if (value != null && value.equalsIgnoreCase(answer)) {
            score++;
            scoreTextField.setText(String.valueOf(score));
            keyTextField.setText(getRandomKey());
            answerTextField.setText("");
            imageView.setVisible(false);// Hide the image view
            label.setVisible(false);
        } else {
            imageView.setVisible(true); // Show the image view
            label.setVisible(true);
        }
    }

    private String getRandomKey() {
        List<String> keys = new ArrayList<>(Word.getWordMap3().keySet());
        return keys.get(random.nextInt(keys.size()));
    }

    private Map<String, Integer> getTopScores() {

        Map<String, Integer> topScores = new LinkedHashMap<>();
        topScores.put("Player1", 100);
        topScores.put("Player2", 90);
        topScores.put("Player3", 80);
        topScores.put("Player4", 70);
        topScores.put("Player5", 60);
        return topScores;
    }

    private void updateTopScoresListView(Map<String, Integer> topScores) {
        topScoresListView.getItems().clear();
        topScores.forEach((player, score) -> {
            topScoresListView.getItems().add(player + ": " + score);
        });
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
}
