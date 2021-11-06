package com.example.hangman;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.Random;

public class HelloApplication extends Application {
    String letter;
    @Override
    public void start(Stage stage){
        ArrayList<Character> hiddenWord = splitWord(getWord());
        stage.setTitle("Hangman");
        Label title = new Label("Hangman");
        title.relocate(550, 10);
        Font wordFont = new Font(200);
        title.setFont(wordFont);

        Text check = new Text();
        check.setText(String.valueOf(hiddenWord.get(1)));
        HBox hBox1 = new HBox(check);
        VBox vBox1 = new VBox(hBox1);

        Pane pane = new Pane(title, vBox1);
        Scene scene = new Scene(pane, 300 ,400);
        stage.setScene(scene);
        stage.show();
    }
    public static ArrayList<Character> splitWord(String word){
        ArrayList<Character> value = new ArrayList<>();
        for(int i = 0; i < word.length(); i++){
            value.add(word.charAt(i));
        }
        return value;
    }
    public static String getWord(){
        String[] words = {"jacket", "panic", "shortage", "peel",
            "compliance", "branch", "good", "vain", "strict", "mountain", "pace",
            "umbrella", "ethics", "mass", "tiptoe", "despair", "prefer", "establish",
            "peanut", "quest", "jealous", "bell", "disagreement", "swear", "release",
            "respect", "section", "predict", "harvest", "production", "mobile", "my",
            "care", "brother", "helpless", "disaster", "grounds", "code", "gossip",
            "marsh", "survival", "lion", "storm", "revoke", "sniff", "reverse",
            "bracket", "decay", "boom", "generate"};
        Random random = new Random();
        return words[random.nextInt(words.length)];
    }

    public static void main(String[] args) {
        launch();
    }
}