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
    Integer strikes = 0;
    @Override
    public void start(Stage stage){
        stage.setTitle("Hangman");
        ArrayList<Character> hiddenWord = splitWord(getWord());
        ArrayList<Character> dashedLines = getDashedLines(hiddenWord);
        Label title = new Label(String.valueOf(dashedLines));//will display hangman
        title.relocate(550, 10);
        Font wordFont = new Font(150);
        Font inputFont = new Font(100);
        title.setFont(wordFont);

        //make things centered

        TextField input = new TextField();
        Text text = new Text(String.valueOf(hiddenWord));
        Text strikeCount = new Text();
        Text winText = new Text();
        winText.setFont(wordFont);
        strikeCount.setFont(wordFont);

        Button restart = new Button();
        Button submit  = new Button("submit");
        submit.setPrefSize(100, 50);
        submit.relocate(550, 700);
        restart.setText("restart");
        input.relocate(550, 550);
        input.setFont(inputFont);


        Pane spacer = new Pane();
        HBox hBox1 = new HBox(restart, spacer, title, winText);
        HBox hBox2 = new HBox(spacer, input);
        HBox hBox3 = new HBox(spacer, submit, text, strikeCount);
        VBox vBox = new VBox(hBox1, hBox2, hBox3);
        vBox.setSpacing(100);

        Pane pane = new Pane(vBox);
        Scene scene = new Scene(pane, 300 ,400);
        stage.setScene(scene);
        stage.show();

        submit.setOnAction(e -> {
            for(char a : input.getText().toCharArray()){
                for(int i = 0; i < hiddenWord.size(); i++){
                    if(strikes == 6){
                        winText.setText("you lose");
                        //initiate lose
                    }
                    else if(a == hiddenWord.get(i)){
                        dashedLines.set(i, hiddenWord.get(i));
                        title.setText(String.valueOf(dashedLines));
                        if(!dashedLines.contains('-')){
                            winText.setText("You won");
                            //initiate win
                        }
                    }
                    if(!hiddenWord.contains(a)){
                        strikes++;
                        strikeCount.setText("strikes:" + String.valueOf(strikes));
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