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
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.HashMap;


/**
 * This class is the screen in which you make a quiz
 */
public class makeQuiz {



/**
@param primaryStage is the primaryStage that will be used to display the scene that the user will see
@param original is the scene that has all the objects on it for the user to interact with
@param quizzes is a structure of the quizzes that are available for the user to do
 */
  public void show(Stage primaryStage, Scene original, ListView<Button> quizzes,
                   HashMap<String, Quiz> quizHolder) {
    //sets up the display and settings for the the screen that will allow to choose the quiz
    BorderPane pane = new BorderPane();
    Label title = new Label("Name Your Quiz");
    title.setStyle("-fx-background-color: #E0FFFF;");
    title.setFont(Font.font(40));
    TextField editor = new TextField();
    Button button= new Button("Enter");
    //sets up the pane with the different things
    pane.setTop(title);
    pane.setCenter(editor);
    pane.setBottom(button);
    Scene currentScene = new Scene(pane, original.getWidth(), original.getHeight());
    primaryStage.setScene(currentScene);
    primaryStage.show();


    editor.setOnAction(event -> {
      button.fire();
    });

    //button handler that will get the information for the quiz that the user would like to create
    button.setOnAction(event -> {
      //gets the info from the textfield
      String temp = editor.getText();
      //creates the quiz
      Quiz quiz = new Quiz(temp);
      //creates a new quiz button for the new quiz
      Button qb = new Button(temp);
      //adds the quiz to the hashmap
      quizHolder.put(temp, quiz);
      quizzes.getItems().add(qb);
      //if the user clicks on that then it creates the new quiz
      qb.setOnAction(eventS -> {
        Quiz ternary = quizHolder.get(temp);
        ternary.show(primaryStage, original, quizHolder, quizzes);
      });
      primaryStage.setScene(original);
      primaryStage.show();
    });
  }

  /**
   * A Helper method to help put the question into list view
   * @param question
   * @param index
   */
  public void putInListViewHelper(Question question, int index) {
    createNewQuestionScreen the = new createNewQuestionScreen();
  }
}
