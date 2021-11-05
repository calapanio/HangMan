package com.example.hangman;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.util.ArrayList;

public class HelloApplication extends Application {
    String letter;
    @Override
    public void start(Stage stage){
        stage.setTitle("Hangman");
        Label title = new Label("Hangman");
        title.relocate(550, 10);
        Font wordFont = new Font(200);
        title.setFont(wordFont);

        Pane pane = new Pane(title);
        Scene scene = new Scene(pane, 300 ,400);
        stage.setScene(scene);
        stage.show();

        ArrayList<Character> word = splitWord(getWord());
    }
    public static ArrayList<Character> splitWord(String word){
        ArrayList<Character> value = new ArrayList<>();
        for(int i = 0; i < word.length(); i++){
            value.add(word.charAt(i));
        }
        return value;
    }
    public static String getWord(){return "";}

    public static void main(String[] args) {
        launch();
    }
}