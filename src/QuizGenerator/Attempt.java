// Title: RAWDJ Quiz Generator
// Files: Attempt.java, Controller.java, createNewQuestionScreen.java, Main.java, makeQuiz.java,
// Question.java, Quiz.java
// Course: Comp Sci 400, Spring 2019
//
// Authors: Justin Burns, Declan Gundrum, Ryan Erdmann, William Weis, Atessa Amjadi
// Emails:  jdburns2@wisc.edu, djgundrum@wisc.edu, raerdmann@wisc.edu,
// wzweis@wisc.edu, aamjadi@wisc.edu
// Lecturer's Name: Debra Deppler
//////////////////////////////////////////////////////////////////////////////////////////////////
package QuizGenerator;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Attempt {

  public String name;

  public Attempt(String name) {
    this.name = name;
  }

  /**
   * Shows the attempt screen when called so you know
   * the attempts that occurred as well as the score
   * @param primaryStage
   * @param original
   * @param question
   * @param ca
   * @param ya
   */
  public void show(Stage primaryStage, Scene original, String[] question, String[] ca,
                   String[] ya) {
    BorderPane screen = new BorderPane();
    Scene scene = new Scene(screen, original.getWidth(), original.getHeight());
    VBox percentageHolder = new VBox();//the score holder
    Label top = new Label(this.name);

    top.setStyle("-fx-background-color: #FA8072;");
    top.setFont(Font.font(40));
    ListView<String> list = new ListView<>();
    screen.setCenter(list);
    int counter = 0;//the counter for the amount of correct answers
    for (int i = 0; i < question.length; ++i) {
      list.getItems().addAll(question[i], "Correct Answer: " + ca[i], "Your answer: " + ya[i], "");
      if(ca[i].equals(ya[i])){
        counter++;
      }
    }
    Label correct = new Label("Questions correct: "+counter);
    Label attempts = new Label("Number of Questions: "+question.length);
    Label percent = new Label("Score: "+((double)counter/question.length*100));
    percentageHolder.getChildren().addAll(correct,attempts,percent);
    screen.setRight(percentageHolder);
    Button button = new Button("Return to Quiz");
    button.setStyle("-fx-background-color: #00CED1;-fx-border-color: black;");

    screen.setBottom(button);

    primaryStage.setScene(scene);
    primaryStage.show();


    button.setOnAction(event -> {
      primaryStage.setScene(original);
      primaryStage.show();
    });

  }
}
