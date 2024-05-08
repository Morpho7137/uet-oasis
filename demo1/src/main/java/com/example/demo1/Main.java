package com.example.demo1;

import appSet.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {


        Operations1 operations1 = new Operations1();
        try {
            operations1.insertFile("D:\\New folder\\demo1\\src\\main\\resources\\com\\example\\demo1\\en_vi - Copy - Copy.txt", Word.getWordMap());
            operations1.insertFile("D:\\New folder\\demo1\\src\\main\\resources\\com\\example\\demo1\\game.txt",Word.getWordMap3());
            operations1.insertFile("D:\\New folder\\demo1\\src\\main\\resources\\com\\example\\demo1\\star_vietanh - Copy.txt", Word.getWordMap2());
            System.out.println("Data loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error loading data: " + e.getMessage());
            return;
        }


        // Load FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent root = loader.load();


        // Set scene
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Dictionary");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

