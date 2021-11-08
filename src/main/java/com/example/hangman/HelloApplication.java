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
import java.util.Objects;
import java.util.Random;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage){
        stage.setTitle("Hangman");
        ArrayList<Character> hiddenWord = splitWord(getWord());
        ArrayList<Character> dashedLines = getDashedLines(hiddenWord);
        ArrayList<Button> strikes = new ArrayList<>();
        Label title = new Label(String.valueOf(dashedLines));//will display hangman
        title.relocate(550, 10);
        Font wordFont = new Font(150);
        Font inputFont = new Font(100);
        title.setFont(wordFont);

        //make things centered

        TextField input = new TextField();
        Text text = new Text(String.valueOf(hiddenWord));
        Text winText = new Text();

        Button strike1 = new Button();
        Button strike2 = new Button();
        Button strike3 = new Button();
        Button strike4 = new Button();
        Button strike5 = new Button();
        Button strike6 = new Button();
        Button restart = new Button();
        Button submit  = new Button("submit");
        submit.setPrefSize(100, 50);
        submit.relocate(550, 700);
        restart.setText("restart");
        input.relocate(550, 550);
        input.setFont(inputFont);

        strikes.add(strike1);
        strikes.add(strike2);
        strikes.add(strike3);
        strikes.add(strike4);
        strikes.add(strike5);
        strikes.add(strike6);

        Pane spacer = new Pane();
        HBox hBox1 = new HBox(restart, spacer, title);
        HBox hBox2 = new HBox(spacer, input, winText);
        HBox hBox3 = new HBox(spacer, submit, text,
                strike1, strike2, strike3, strike4, strike5, strike6);
        VBox vBox = new VBox(hBox1, hBox2, hBox3);
        vBox.setSpacing(100);

        Pane pane = new Pane(vBox);
        Scene scene = new Scene(pane, 300 ,400);
        stage.setScene(scene);
        stage.show();

        submit.setOnAction(e -> {
            for(char a : input.getText().toCharArray()){
                for(int i = 0; i < hiddenWord.size(); i++){
                    if(a == hiddenWord.get(i)){
                        dashedLines.set(i, hiddenWord.get(i));
                        title.setText(String.valueOf(dashedLines));
                        if(!dashedLines.contains('-') && !Objects.equals(strikes.get(5).getText(), "X")){
                            winText.setText("You won");
                        }else if(Objects.equals(strikes.get(5).getText(), "X")) winText.setText("you lose");
                    }else{
                        strikes.get(i).setText("X");
                    }
                }
            }
        });

    }
    public static ArrayList<Character> splitWord(String word){
        ArrayList<Character> value = new ArrayList<>();
        for(int i = 0; i < word.length(); i++){
            value.add(word.charAt(i));
        }
        return value;
    }
    public static ArrayList<Character> getDashedLines(ArrayList<Character> sample){
        ArrayList<Character> returnValue = new ArrayList<>();
        for(int i = 0; i < sample.size(); i++){
            returnValue.add('-');
        }
        return returnValue;
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