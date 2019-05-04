package QuizGenerator;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.Random;

public class Question {
//creates public variables for each of the test items
  public String question;
  public String correctAnswer;
  public String answer1;
  public String answer2;
  public String answer3;

  /*
  Constructor for the question class so that the questions are possible
  @param String q is the question for the question
  @param String c is the correct answer for the question
  @param String a1 is one of the answers that isn't correct
  @param String a2 is another answer that isn't correct
  @param String a3 is another answer that isn't correct
   */
  public Question(String q, String c, String a1, String a2, String a3) {
    this.question = q;
    this.correctAnswer = c;
    this.answer1 = a1;
    this.answer2 = a2;
    this.answer3 = a3;
  }
//quiz to show each question
  /*
  @param primaryStage is the primaryStage for javafx to use
  @param original is the Scene that will be used for displaying everything
  @param attempt is a double array of strings that holds the attempts from the user taking the quiz
  @param qs is an object array that contains the questions
  @param index is the index that the user is at with questions
   */
  public void show(Stage primaryStage, Scene original, String[][] attempt, Object[] qs, int index
          , HBox bot) {

    //initializes the base structures that are needed
    BorderPane screen = new BorderPane();
    Text question = new Text(this.question);
    GridPane questions = new GridPane();
    Random rand = new Random();

    // Makes the buttons that will be shown in the middle of the screen (holds the answers)
    Button correct = new Button(this.correctAnswer);
    Button aone = new Button(this.answer1);
    Button atwo = new Button(this.answer2);
    Button athree = new Button(this.answer3);

    //random value gens for setup
    int c = rand.nextInt(2);
    int r = rand.nextInt(2);
    questions.add(correct, c, r);

    //creates a button array to hold the buttons with the answers
    Button[] bees = {aone, atwo, athree};
    int ind = 0;
    //sets up the adding of the buttons to the gridpane for displaying
    for (int col = 0; col < 2; ++col) {
      for (int row = 0; row < 2; ++row) {
        if (c != col || r != row) {
          questions.add(bees[ind], col, row);
          ++ind;
        }
      }
    }

    //creates an hbox
    HBox bottom = new HBox();

    //initializes the creation of the previous, done and next button, and will add them to the hbox
    //depending on if there will be more questions or not
    Button prev = new Button("Previous");
    Button next;
    if (index == qs.length - 1)
      next = new Button("Done");
    else
      next = new Button("Next");
//adds the buttons to the hbox depending
    bottom.getChildren().addAll(prev, next);

//sets up the display screen that the user sees with the correct location of everything
    screen.setTop(question);
    screen.setCenter(questions);
    screen.setBottom(bottom);
    primaryStage.setScene(new Scene(screen, original.getWidth(), original.getHeight()));
    primaryStage.show();

//action handler depending on the amount of questions
    prev.setOnAction(event -> {
      Question current = (Question) qs[index - 1];
      current.show(primaryStage, original, attempt, qs, index - 1, bot);
    });

    //action handler depending on if there is more questions or not
    next.setOnAction(event -> {
      //sets up for the next question if there is a next question
      if (next.getText().equals("Next")) {
        Question current = (Question) qs[index + 1];
        current.show(primaryStage, original, attempt, qs, index + 1, bot);
      } else {
        //if not records the attempt
        Attempt attempt1 = new Attempt("Attempt " + (bot.getChildren().size() + 1));
        Button button = new Button(attempt1.name);
        button.setOnAction(event1 -> {
          attempt1.show(primaryStage, original, attempt[0], attempt[1], attempt[2]);
        });
        bot.getChildren().add(button);
        primaryStage.setScene(original);
        primaryStage.show();
      }
    });

    //action handler for if they click on the right answer
    correct.setOnAction(event -> {
     //sets the other buttons to null so user can't click on them
      aone.setStyle(null);
      atwo.setStyle(null);
      athree.setStyle(null);
      correct.setStyle("-fx-background-color: green;");
      //updates the array with the question, correct answer and their answer
      attempt[0][index] = this.question;
      attempt[1][index] = this.correctAnswer;
      attempt[2][index] = correct.getText();
    });

    //action handler for if they get the question wrong
    aone.setOnAction(event -> {
      //sets the other buttons to null so user can't click on the other buttons
      correct.setStyle(null);
      atwo.setStyle(null);
      athree.setStyle(null);
      aone.setStyle("-fx-background-color: green;");
      //adds the buttons to the attempt array with the question, correct answer, and the answer user had
      attempt[0][index] = this.question;
      attempt[1][index] = this.correctAnswer;
      attempt[2][index] = aone.getText();
    });
//action handler for if they get the question wrong
    atwo.setOnAction(event -> {
      //sets the other buttons to null so user can't click on the other buttons
      aone.setStyle(null);
      correct.setStyle(null);
      athree.setStyle(null);
      atwo.setStyle("-fx-background-color: green;");
      //adds the buttons to the attempt array with the question, correct answer, and the answer user had
      attempt[0][index] = this.question;
      attempt[1][index] = this.correctAnswer;
      attempt[2][index] = atwo.getText();
    });
//action handler for if they get the question wrong
    athree.setOnAction(event -> {
      //sets the other buttons to null so user can't click on the other buttons
      aone.setStyle(null);
      atwo.setStyle(null);
      correct.setStyle(null);
      athree.setStyle("-fx-background-color: green;");
      //adds the buttons to the attempt array with the question, correct answer, and the answer user had
      attempt[0][index] = this.question;
      attempt[1][index] = this.correctAnswer;
      attempt[2][index] = athree.getText();
    });
  }
}
