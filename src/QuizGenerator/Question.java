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

  public String question;
  public String correctAnswer;
  public String answer1;
  public String answer2;
  public String answer3;

  public Question(String q, String c, String a1, String a2, String a3) {
    this.question = q;
    this.correctAnswer = c;
    this.answer1 = a1;
    this.answer2 = a2;
    this.answer3 = a3;
  }
//quiz to show each question
  public void show(Stage primaryStage, Scene original, String[][] attempt, Object[] qs, int index
          , HBox bot) {
    BorderPane screen = new BorderPane();
    Text question = new Text(this.question);
    GridPane questions = new GridPane();
    Random rand = new Random();

    // Makes the buttons that will be shown in the middle of the screen (holds the answers)
    Button correct = new Button(this.correctAnswer);
    Button aone = new Button(this.answer1);
    Button atwo = new Button(this.answer2);
    Button athree = new Button(this.answer3);

    int c = rand.nextInt(2);
    int r = rand.nextInt(2);
    questions.add(correct, c, r);

    Button[] bees = {aone, atwo, athree};
    int ind = 0;
    for (int col = 0; col < 2; ++col) {
      for (int row = 0; row < 2; ++row) {
        if (c != col || r != row) {
          questions.add(bees[ind], col, row);
          ++ind;
        }
      }
    }

    HBox bottom = new HBox();

    Button prev = new Button("Previous");
    Button next;
    if (index == qs.length - 1)
      next = new Button("Done");
    else
      next = new Button("Next");

    bottom.getChildren().addAll(prev, next);


    screen.setTop(question);
    screen.setCenter(questions);
    screen.setBottom(bottom);
    primaryStage.setScene(new Scene(screen, original.getWidth(), original.getHeight()));
    primaryStage.show();


    prev.setOnAction(event -> {
      Question current = (Question) qs[index - 1];
      current.show(primaryStage, original, attempt, qs, index - 1, bot);
    });

    next.setOnAction(event -> {
      if (next.getText().equals("Next")) {
        Question current = (Question) qs[index + 1];
        current.show(primaryStage, original, attempt, qs, index + 1, bot);
      } else {
        Attempt attempt1 = new Attempt("Attempt " + bot.getChildren().size() + 1);
        Button button = new Button(attempt1.name);
        button.setOnAction(event1 -> {
          attempt1.show(primaryStage, original, attempt[0], attempt[1], attempt[2]);
        });
        bot.getChildren().add(button);
        primaryStage.setScene(original);
        primaryStage.show();
      }
    });

    correct.setOnAction(event -> {
      aone.setStyle(null);
      atwo.setStyle(null);
      athree.setStyle(null);
      correct.setStyle("-fx-background-color: green;");
      attempt[0][index] = this.question;
      attempt[1][index] = this.correctAnswer;
      attempt[2][index] = correct.getText();
    });

    aone.setOnAction(event -> {
      correct.setStyle(null);
      atwo.setStyle(null);
      athree.setStyle(null);
      aone.setStyle("-fx-background-color: green;");
      attempt[0][index] = this.question;
      attempt[1][index] = this.correctAnswer;
      attempt[2][index] = aone.getText();
    });

    atwo.setOnAction(event -> {
      aone.setStyle(null);
      correct.setStyle(null);
      athree.setStyle(null);
      atwo.setStyle("-fx-background-color: green;");
      attempt[0][index] = this.question;
      attempt[1][index] = this.correctAnswer;
      attempt[2][index] = atwo.getText();
    });

    athree.setOnAction(event -> {
      aone.setStyle(null);
      atwo.setStyle(null);
      correct.setStyle(null);
      athree.setStyle("-fx-background-color: green;");
      attempt[0][index] = this.question;
      attempt[1][index] = this.correctAnswer;
      attempt[2][index] = athree.getText();
    });
  }
}
