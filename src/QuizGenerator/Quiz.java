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
  private int correct;
  private String name;
  public static ListView<Button> questionsButton = new ListView<>();

  public Quiz(String name) {
    length = 0;
    correct = 0;
    this.name = name;
  }

  public void show(Stage primaryStage, Scene original) {
    Stage main = new Stage();
    BorderPane screen = new BorderPane();
    Scene newScene = new Scene(screen,original.getWidth(),original.getHeight());
    Label name = new Label(this.name);
    name.setStyle("-fx-background-color: #FA8072;");
    name.setFont(Font.font(40));
    screen.setStyle("-fx-background-color: #FA8072;");
    screen.setTop(name);
    screen.setBottom(questionsButton);
      Button makeQ = new Button("Create New Question");
      makeQ.setOnAction(event -> {
        createNewQuestionScreen the = new createNewQuestionScreen();
        the.show(primaryStage,questions,original);
        makeQuiz(the.getQuestion());
        Button questionButton = new Button(questions.get(length).question);

        questionButton.setOnAction(event1 -> {
          Question q = questions.get(length);
          q.show(primaryStage, newScene);
        });
        length++;
        questionsButton.getItems().add(questionButton);
      });
      Button stopQuiz = new Button("Done Adding Questions");
      stopQuiz.setOnAction(event -> {
        addQuiz();
      });
      screen.setLeft(makeQ);
      screen.setRight(stopQuiz);


    primaryStage.setScene(newScene);
    primaryStage.show();

  }

  public void makeQuiz(Question question) {
    questions.add(question);
  }
  //adds Quiz to the hashMap
  //key for hashmap is string name of quiz, value is the linkedlist
  public void addQuiz(){

  }
  public void getAllQuestions() {
    correct = 0;
    Question[] all = (Question[])questions.toArray();
    length = all.length;
    for (int i = 0; i < all.length; ++i) {

    }
  }
  public String getName(){
    return name;
  }


}
