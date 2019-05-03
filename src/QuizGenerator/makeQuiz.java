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

public class makeQuiz {




  public void show(Stage primaryStage, Scene original, ListView<Button> quizzes,
                   HashMap<String, Quiz> quizHolder) {
    BorderPane pane = new BorderPane();
    Label title = new Label("Name Your Quiz");
    title.setStyle("-fx-background-color: #E0FFFF;");
    title.setFont(Font.font(40));
    TextField editor = new TextField();
    Button button= new Button("Enter");
    pane.setTop(title);
    pane.setCenter(editor);
    pane.setBottom(button);
    Scene currentScene = new Scene(pane, original.getWidth(), original.getHeight());
    primaryStage.setScene(currentScene);
    primaryStage.show();


    editor.setOnAction(event -> {
      button.fire();
    });

    button.setOnAction(event -> {
      String temp = editor.getText();
      Quiz quiz = new Quiz(temp);
      Button qb = new Button(temp);
      quizHolder.put(temp, quiz);
      quizzes.getItems().add(qb);
      qb.setOnAction(eventS -> {
        Quiz ternary = quizHolder.get(temp);
        ternary.show(primaryStage, original);
      });
      primaryStage.setScene(original);
      primaryStage.show();
    });
  }

  public void putInListViewHelper(Question question, int index) {
    createNewQuestionScreen the = new createNewQuestionScreen();
    //the.show(question, index, this.pStage, questions, questionsButton, newScene);
  }
}
