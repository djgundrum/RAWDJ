package QuizGenerator;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicReference;

public class createNewQuestionScreen {

  public Question question = null;

  public void show(Stage primaryStage, LinkedList<Question> quizzes, Scene original) {
    BorderPane screen = new BorderPane();
    VBox fields = new VBox();
    AtomicReference<Question> current = null;
    Label question = new Label("Question: ");
    TextField q = new TextField();
    Label correct = new Label("Correct Answer: ");
    TextField correctAnswer = new TextField();
    Label a1a = new Label("Other Answers: ");
    TextField a1 = new TextField();
    TextField a2 = new TextField();
    TextField a3 = new TextField();
    fields.getChildren().addAll(question, q, correct, correctAnswer, a1a, a1, a2, a3);

    screen.setTop(new Label("Make a Question!"));
    screen.setCenter(fields);

    Button makeQ = new Button("Create Question");
    makeQ.setOnAction(event -> {
      String que = q.getText();
      String correctA = correctAnswer.getText();
      String aone = a1.getText();
      String atwo = a2.getText();
      String athree = a3.getText();
      Question qu = new Question(que, correctA, aone, atwo, athree);
      quizzes.add(qu);
      System.out.println(this.question);
      primaryStage.setScene(original);
      primaryStage.show();
    });

    screen.setBottom(makeQ);

    primaryStage.setScene(new Scene(screen, 300, 275));
    primaryStage.show();
  }

  public void setQuestion (Question question) {
    this.question = question;
  }

  public Question getQuestion() {
    return this.question;
  }
}
