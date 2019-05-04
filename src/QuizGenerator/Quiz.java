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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.LinkedList;

public class Quiz {

  // Keeps track of all the created questions, will be used when quiz is taken
  public LinkedList<Question> questions = new LinkedList<>();

  // Keeps track of all the attempts that the user has made
  public HBox bottom = new HBox();

  // Keeps the name of the quiz, will be displayed in the listview
  public String name;

  // Holds a visible list of all the questions, will be displayed in button form so that the user
  // can click on the question and change the contents of it
  public ListView<Button> questionsButton = new ListView<>();

  private Stage pStage;

  private Scene newScene;

  /**
  Default constructor that assigns class variables
   */
  public Quiz(String name) {
    this.name = name;
  }

  /**
   * Method responsible for changing what is displayed on the screen to the user
   * Creates a new scene and sets button handlers
   * @param primaryStage // Window that is being displayed to the user
   * @param original // Scene of the window that was previously displayed
   */
  public void show(Stage primaryStage, Scene original, HashMap<String, Quiz> quizHolder, ListView<Button> quizzes) {
    this.pStage = primaryStage;


    BorderPane screen = new BorderPane();

    // New scene that will be displayed
    Scene newScene = new Scene(screen, original.getWidth(), original.getHeight());
    this.newScene = newScene;

    // String displayed at the top of the screen (Also sets the style)
    Label name = new Label(this.name);
    name.setStyle("-fx-background-color: #F5FFFA;");
    name.setFont(Font.font(40));
    screen.setStyle("-fx-background-color: #F5FFFA;");
    screen.setTop(name); // Puts the title string to the top of the window
    screen.setCenter(questionsButton); // Puts the listview into the middle of the screen

    // Makes button that will direct user to new question screen
    Button makeQ = new Button("Create New Question");
    makeQ.setStyle("-fx-background-color: #00CED1;-fx-border-color: black;");

    // Makes button that will allow user to take the quiz
    Button stopQuiz = new Button("Take the Quiz");
    stopQuiz.setStyle("-fx-background-color: #00CED1;-fx-border-color: black;");

    // Makes button so that the user can return to the list of quizzes from first screen
    Button returner = new Button("Return to Quizzes");
    returner.setStyle("-fx-background-color: #00CED1;-fx-border-color: black;");

    Button delete = new Button("Remove Quiz");
    delete.setStyle("-fx-background-color: #00CED1;-fx-border-color: black;");

    // Makes the structure to hold all the buttons on the right side of the screen
    VBox right = new VBox();
    right.getChildren().addAll(makeQ, stopQuiz, returner, delete);
    screen.setRight(right);

    // Sets the style and where the HBox of all the attempts will be
    this.bottom.setStyle("-fx-background-color: #F5FFFA;");
    screen.setBottom(this.bottom);

    // Displays the new screen
    primaryStage.setScene(newScene);
    primaryStage.show();

    // Sets what the make quiz button will do
    makeQ.setOnAction(event -> {
      createNewQuestionScreen the = new createNewQuestionScreen();
      the.show(null, 0, primaryStage, questions, questionsButton, newScene);
    });

    // Sets wha the take quiz button will do here you go
    stopQuiz.setOnAction(event -> {
      takeQuiz(primaryStage, newScene, this.bottom);
    });

    returner.setOnAction(event -> {
      primaryStage.setScene(original);
      primaryStage.show();
    });

    delete.setOnAction(event -> {
      quizHolder.remove(this.name);
      for (Button b : quizzes.getItems()) {
        if (b.getText().equals(this.name)) {
          quizzes.getItems().remove(b);
          break;
        }
      }
      primaryStage.setScene(original);
      primaryStage.show();
    });

  }

  /**
   * Method that will iterate through all the questions in the linked list and keep track of the
   * score of the current quiz the user is taking
   */
  public void takeQuiz(Stage primaryStage, Scene original, HBox bottom) {
    Object[] qs = questions.toArray();
    Question current = (Question) qs[0];
    String[][] attempt = new String[3][qs.length];
    current.show(primaryStage, original, attempt, qs, 0, bottom);
  }

  public void putInListViewHelper(Question question) {
    questions.add(question);
    Button b1 = new Button(question.question);
    b1.setStyle("-fx-border-color: transparent;-fx-background-color: transparent;");
    questionsButton.getItems().add(b1);
    b1.setOnAction(event -> {
      createNewQuestionScreen the = new createNewQuestionScreen();
      the.show(question, questionsButton.getItems().indexOf(b1), pStage, questions, questionsButton,
              newScene);
    });
  }


}
