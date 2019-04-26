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


public class createNewQuestionScreen {


  public void show(Question prequestion, int index, Stage primaryStage,
                   LinkedList<Question> questions,
                   ListView<Button> questionsButton, Scene original) {
    BorderPane screen = new BorderPane();
    VBox fields = new VBox();
    Label question = new Label("Question: ");
    TextField q = new TextField();
    Label correct = new Label("Correct Answer: ");
    TextField correctAnswer = new TextField();
    Label a1a = new Label("Other Answers: ");
    TextField a1 = new TextField();
    TextField a2 = new TextField();
    TextField a3 = new TextField();
    if (prequestion != null) {
      q.setText(prequestion.question);
      correctAnswer.setText(prequestion.correctAnswer);
      a1.setText(prequestion.answer1);
      a2.setText(prequestion.answer2);
      a3.setText(prequestion.answer3);
    }
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
      questions.add(qu);
      Button button = new Button(que);
      button.setOnAction(event1 -> {
        createNewQuestionScreen curr = new createNewQuestionScreen();
        int inder = questionsButton.getItems().indexOf(button);
        curr.show(qu, inder, primaryStage, questions, questionsButton, original);
      });
      if (prequestion != null) {
        questionsButton.getItems().set(index, button);
      }
      else {
        questionsButton.getItems().add(button);
      }
      primaryStage.setScene(original);
      primaryStage.show();
    });

    screen.setBottom(makeQ);

    primaryStage.setScene(new Scene(screen, 300, 275));
    primaryStage.show();
  }
}
