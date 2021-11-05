package com.example.hangman;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.*;
import javafx.scene.web.*;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;
import java.io.IOException;
import java.util.ArrayList;

public class HelloApplication extends Application {
    String letter;
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Hangman");
        Label word = new Label("Hangman");
        word.relocate(550, 10);
        Font wordFont = new Font(200);
        word.setFont(wordFont);

        Pane pane = new Pane(word);
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

    public static void main(String[] args) {
        launch();
    }
}