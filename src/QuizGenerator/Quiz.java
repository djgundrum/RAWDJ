package QuizGenerator;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.LinkedList;

public class Quiz {

  // Keeps track of all the created questions, will be used when quiz is taken
  private LinkedList<Question> questions = new LinkedList<>();

  // Keeps track of how many questions there are, useful when calculating percent on a quiz
  private double length;

  // Keeps track of how many questions the user got right, must be in array format so that it can
  // be passed as a parameter and not require anything to be returned from other method/class
  private double[] correct;

  // Keeps the name of the quiz, will be displayed in the listview
  private String name;

  // Holds a visible list of all the questions, will be displayed in button form so that the user
  // can click on the question and change the contents of it
  private ListView<Button> questionsButton = new ListView<>();

  /*
  Default constructor that assigns class variables
   */
  public Quiz(String name) {
    length = 0;
    correct = new double[1];
    this.name = name;
  }

  /**
   * Method responsible for changing what is displayed on the screen to the user
   * Creates a new scene and sets button handlers
   * @param primaryStage // Window that is being displayed to the user
   * @param original // Scene of the window that was previously displayed
   */
  public void show(Stage primaryStage, Scene original) {
    BorderPane screen = new BorderPane();

    // New scene that will be displayed
    Scene newScene = new Scene(screen, original.getWidth(), original.getHeight());

    // String displayed at the top of the screen (Also sets the style)
    Label name = new Label(this.name);
    name.setStyle("-fx-background-color: #FA8072;");
    name.setFont(Font.font(40));
    screen.setStyle("-fx-background-color: #FA8072;");
    screen.setTop(name); // Puts the title string to the top of the window
    screen.setCenter(questionsButton); // Puts the listview into the middle of the screen

    // Makes button that will direct user to new question screen
    Button makeQ = new Button("Create New Question");

    // Makes button that will allow user to take the quiz
    Button stopQuiz = new Button("Take the Quiz");

    screen.setLeft(makeQ); // Puts the create question button on the left side of the screen
    screen.setRight(stopQuiz); // Puts the take quiz button on the right side of the screen

    // Displays the new screen
    primaryStage.setScene(newScene);
    primaryStage.show();

    // Sets what the make quiz button will do
    makeQ.setOnAction(event -> {
      createNewQuestionScreen the = new createNewQuestionScreen();
      the.show(null, 0, primaryStage, questions, questionsButton, newScene);
      //makeQuiz(the.getQuestion());
      length = questions.size();
    });

    // Sets wha the take quiz button will do
    stopQuiz.setOnAction(event -> {
      takeQuiz(primaryStage, newScene);
    });

  }

  /**
   * Method that will iterate through all the questions in the linked list and keep track of the
   * score of the current quiz the user is taking
   */
  public void takeQuiz(Stage primaryStage, Scene original) {
    System.out.println(questions.size());
    correct[0] = 0.0;
    Object[] qs = questions.toArray();
    Question current = (Question) qs[0];
    current.show(primaryStage, original, correct, qs, 0);
  }


}
