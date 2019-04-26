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

  private LinkedList<Question> questions = new LinkedList<>();
  private int length;
  private int[] correct;
  private String name;
  private ListView<Button> questionsButton = new ListView<>();

  public Quiz(String name) {
    length = 0;
    correct = new int[0];
    this.name = name;
  }

  public void show(Stage primaryStage, Scene original) {
    BorderPane screen = new BorderPane();
    Scene newScene = new Scene(screen, original.getWidth(), original.getHeight());
    Label name = new Label(this.name);
    name.setStyle("-fx-background-color: #FA8072;");
    name.setFont(Font.font(40));
    screen.setStyle("-fx-background-color: #FA8072;");
    screen.setTop(name);
    screen.setBottom(questionsButton);
    Button makeQ = new Button("Create New Question");

    Button stopQuiz = new Button("Take the Quiz");
    stopQuiz.setOnAction(event -> {
      takeQuiz();
    });
    screen.setLeft(makeQ);
    screen.setRight(stopQuiz);


    primaryStage.setScene(newScene);
    primaryStage.show();

    makeQ.setOnAction(event -> {
      createNewQuestionScreen the = new createNewQuestionScreen();
      the.show(null, 0, primaryStage, questions, questionsButton, newScene);
      //makeQuiz(the.getQuestion());
      length = questions.size();
    });
  }

  public void takeQuiz() {
    correct[0] = 0;
    for (Question question : questions) {

    }
  }


}
