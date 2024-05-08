package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Controller3 {

    @FXML
    private TextArea userInputArea;

    @FXML
    private TextArea translatedDataArea;

    @FXML
    private RadioButton rbEnToVi;

    @FXML
    private RadioButton rbViToEn;

    public void initialize() {
        rbEnToVi.setSelected(true);
        userInputArea.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                translateText();
            }
        });
    }

    public void translateText() {
        String userInput = userInputArea.getText();
        String langFrom = rbEnToVi.isSelected() ? "en" : "vi";
        String langTo = rbEnToVi.isSelected() ? "vi" : "en";
        try {
            String translatedText = translate(langFrom, langTo, userInput);
            translatedDataArea.setText(translatedText);
        } catch (IOException e) {
            translatedDataArea.setText("Không thể dịch do lỗi kết nối mạng. Vui lòng thử lại sau.");
        }
    }

    private String translate(String langFrom, String langTo, String text) throws IOException {
        // INSERT YOU URL HERE
        String urlStr = "https://script.google.com/macros/s/AKfycbzPgxK3bjiqrE1WatTVeZ-lLFzYpmpXiy960TY_eKrs1ZjJQ9-tktANcOedXznSc-cnQQ/exec" +
                "?q=" + URLEncoder.encode(text, "UTF-8") +
                "&target=" + langTo +
                "&source=" + langFrom;
        URL url = new URL(urlStr);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        con.setConnectTimeout(300000); // Set timeout to 5 minutes
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
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
