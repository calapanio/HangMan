package com.example.hangman;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
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
    Boolean loop = false;
    Text winText = new Text();


    @Override
    public void start(Stage stage){
        do{
            ArrayList<Character> hiddenWord = splitWord(getWord());
            ArrayList<Character> dashedLines = getDashedLines(hiddenWord);
            Label title = new Label(String.valueOf(dashedLines));
            TextField input = new TextField();

            Text text = new Text(String.valueOf(hiddenWord));
            stage.setTitle("Hangman");
            StackPane stackPane = new StackPane();
            stackPane.getChildren().addAll(title);
            StackPane.setAlignment(title, Pos.CENTER);

            //will display hangman
            title.relocate(550, 10);
            Font wordFont = new Font(150);
            Font inputFont = new Font(100);
            title.setFont(wordFont);

            //make things centered

            Text strikeCount = new Text();
            winText.setFont(wordFont);
            strikeCount.setFont(wordFont);

            Button submit = new Button("submit");
            Button onEnter = new Button("reset");
            submit.setPrefSize(100, 50);
            submit.relocate(550, 700);
            input.relocate(550, 550);
            input.setFont(inputFont);


            Pane spacer = new Pane();
            HBox hBox1 = new HBox(onEnter, spacer, stackPane, winText);
            HBox hBox2 = new HBox(spacer, input);////
            HBox hBox3 = new HBox(spacer, submit, text, strikeCount);
            VBox vBox = new VBox(hBox1, hBox2, hBox3);
            vBox.setSpacing(100);

            Pane pane = new Pane(vBox);
            Scene scene = new Scene(pane, 300 ,400);
            stage.setScene(scene);
            stage.show();

            submit.setOnAction(e -> {
                for(char a : input.getText().toCharArray()){

                    if(hiddenWord.contains(a)){
                        for(int i = 0; i < hiddenWord.size(); i++){
                            if(a == hiddenWord.get(i)){
                                dashedLines.set(i, hiddenWord.get(i));
                                title.setText(String.valueOf(dashedLines));
                                if(!dashedLines.contains('-')){
                                    winText.setText("You won");
                                    //initiate win
                                }
                            }
                        }
                        title.setText(String.valueOf(dashedLines));
                    }else if(strikes == 6){
                        winText.setText("you lose");
                        //initiate lose
                    }else{
                        strikes++;
                        strikeCount.setText("strikes:" + strikes);
                    }
                }
                input.clear();
            });
            onEnter.setOnAction(e -> reset());
        }while(loop);
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
                "branch", "good", "vain", "strict", "mountain", "pace",
                "umbrella", "ethics", "mass", "tiptoe", "despair", "prefer", "establish",
                "peanut", "quest", "jealous", "bell", "swear", "release",
                "respect", "section", "predict", "harvest", "mobile", "my",
                "care", "brother", "helpless", "disaster", "grounds", "code", "gossip",
                "marsh", "survival", "lion", "storm", "revoke", "sniff", "reverse",
                "bracket", "decay", "boom", "generate"};
        Random random = new Random();
        return words[random.nextInt(words.length)];
    }

    public void reset(){
        //hiddenWord = splitWord(getWord());
        //dashedLines = getDashedLines(hiddenWord);
        //title.setText(String.valueOf(dashedLines));
        //title = new Label(String.valueOf(dashedLines));
        //input.clear();
        strikes = 0;
        winText.setText("");
        loop = true;

    }

    public static void main(String[] args) {
        launch();
    }
}