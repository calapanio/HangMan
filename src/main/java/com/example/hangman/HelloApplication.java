//hangman
//caleb perry
//11/11/2021
//purpose is to use java fx to play hangman
//when a letter or multiple letters are entered the dashed lines will be replaced by
//the correct letters. when a wrong letter is input then the player gets a strike

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

    //initial declarations
    Integer strikes = 0;
    Text winText = new Text();
    ArrayList<Character> hiddenWord = splitWord(getWord());
    ArrayList<Character> dashedLines = getDashedLines(hiddenWord);
    Label title = new Label(String.valueOf(dashedLines));
    TextField input = new TextField();
    Text strikeCount = new Text();
    boolean loss = false;


    @Override
    public void start(Stage stage){

            //declarations
            stage.setTitle("Hangman");
            StackPane stackPane = new StackPane();
            stackPane.getChildren().addAll(title);
            StackPane.setAlignment(title, Pos.CENTER);

            //sets font and location of title
            title.relocate(550, 10);
            Font wordFont = new Font(150);
            Font inputFont = new Font(100);
            title.setFont(wordFont);


            //sets fonts
            winText.setFont(wordFont);
            strikeCount.setFont(wordFont);

            //declares button for submit and location of text field
            Button submit = new Button("submit");
            Button onEnter = new Button("reset");
            submit.setPrefSize(100, 50);
            submit.relocate(550, 700);
            input.relocate(550, 550);
            input.setFont(inputFont);


            //declares Hbox's and Vbox's for formatting
            Pane spacer = new Pane();
            HBox hBox1 = new HBox(onEnter, spacer, stackPane, winText);
            HBox hBox2 = new HBox(spacer, input);
            HBox hBox3 = new HBox(spacer, submit, strikeCount);
            VBox vBox = new VBox(hBox1, hBox2, hBox3);
            vBox.setSpacing(100);

            //sets scene and shows stage
            Pane pane = new Pane(vBox);
            Scene scene = new Scene(pane, 300 ,400);
            stage.setScene(scene);
            stage.show();

            submit.setOnAction(e -> {
                for(char a : input.getText().toCharArray()){//enhanced for loop for each char in input

                    if(hiddenWord.contains(a)){//checks if the input was in the hidden word
                        for(int i = 0; i < hiddenWord.size(); i++){//iterates through size of hidden array
                            if(a == hiddenWord.get(i)){//finds index of equal char and replaces value
                                dashedLines.set(i, hiddenWord.get(i));
                                title.setText(String.valueOf(dashedLines));
                                if(!dashedLines.contains('-') && !loss){//displays win if win = true
                                    winText.setText("You won");
                                }
                            }
                        }
                        title.setText(String.valueOf(dashedLines));//Replace dashes with correct inputs
                    }else if(strikes == 6){
                        winText.setText("you lose");//shows loss if loss = true
                        loss = true;
                    }else{
                        strikes++;
                        strikeCount.setText("strikes:" + strikes);
                    }
                }
                input.clear();//clears the text field from new input
            });
            onEnter.setOnAction(e -> reset());//resets board on reset button press
    }
    public static ArrayList<Character> splitWord(String word){//splits word
        ArrayList<Character> value = new ArrayList<>();
        for(int i = 0; i < word.length(); i++){
            value.add(word.charAt(i));
        }
        return value;
    }
    //makes an array of dashed lines equal to that of the hidden word
    public static ArrayList<Character> getDashedLines(ArrayList<Character> sample){
        ArrayList<Character> returnValue = new ArrayList<>();
        for(int i = 0; i < sample.size(); i++){
            returnValue.add('-');
        }
        return returnValue;
    }
    //gets the word
    public static String getWord(){
        String[] words = {"jacket", "panic", "shortage", "peel",
                "branch", "good", "vain", "strict", "mountain", "pace",
                "umbrella", "ethics", "mass", "tiptoe", "despair", "prefer", "establish",
                "peanut", "quest", "jealous", "bell", "swear", "release",
                "respect", "section", "predict", "harvest", "mobile", "my",
                "care", "brother", "helpless", "disaster", "grounds", "code", "gossip",
                "marsh", "survival", "lion", "storm", "revoke", "sniff", "reverse",
                "bracket", "decay", "boom", "generate", "clash"};
        Random random = new Random();
        return words[random.nextInt(words.length)];
    }

    //resets values
    public void reset(){
        hiddenWord = splitWord(getWord());
        dashedLines = getDashedLines(hiddenWord);
        title.setText(String.valueOf(dashedLines));
        title = new Label(String.valueOf(dashedLines));
        input.clear();
        strikes = 0;
        strikeCount.setText("");
        winText.setText("");
    }

    public static void main(String[] args) {
        launch();
    }
}