package QuizGenerator;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Attempt {

  String name;

  public Attempt(String name) {
    this.name = name;
  }

  public void show(Stage primaryStage, Scene original, String[] question, String[] ca,
                   String[] ya) {
    BorderPane screen = new BorderPane();
    ListView<String> list = new ListView<>();

    for (int i = 0; i < question.length; ++i) {
      list.getItems().addAll(question[i], "Correct Answer: " + ca[i], "Your answer: " + ya[i], "");
    }

  }
}
