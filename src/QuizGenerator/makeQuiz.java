package QuizGenerator;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class makeQuiz {




  public void show(Stage primaryStage, Scene original) {
    BorderPane pane = new BorderPane();
    Button button= new Button("HI Declan");
    pane.setTop(button);
    Scene currentScene = new Scene(pane, 300, 275);
    primaryStage.setScene(currentScene);
    primaryStage.show();




    button.setOnAction(event -> {
      primaryStage.setScene(original);
      primaryStage.show();
    });
  }
}
