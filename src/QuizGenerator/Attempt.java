package QuizGenerator;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Attempt {

  public String name;

  public Attempt(String name) {
    this.name = name;
  }

  public void show(Stage primaryStage, Scene original, String[] question, String[] ca,
                   String[] ya) {
    BorderPane screen = new BorderPane();
    Scene scene = new Scene(screen, original.getWidth(), original.getHeight());

    Label top = new Label(this.name);
    top.setStyle("-fx-background-color: #FA8072;");
    top.setFont(Font.font(40));
    ListView<String> list = new ListView<>();
    screen.setCenter(list);

    for (int i = 0; i < question.length; ++i) {
      list.getItems().addAll(question[i], "Correct Answer: " + ca[i], "Your answer: " + ya[i], "");
    }

    Button button = new Button("Return to Quiz");

    screen.setBottom(button);

    primaryStage.setScene(scene);
    primaryStage.show();


    button.setOnAction(event -> {
      primaryStage.setScene(original);
      primaryStage.show();
    });

  }
}
