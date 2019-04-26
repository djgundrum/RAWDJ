package QuizGenerator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.HashMap;

import java.util.ArrayList;


public class Main extends Application {

  BorderPane screen = new BorderPane();
  Scene currentScene = new Scene(screen, 600, 600);
  HashMap<String, Quiz> quizHolder = new HashMap<>();
  ListView<Button> quizzes = new ListView<>();


  @Override
  public void start(Stage primaryStage) throws Exception {
    setHomePage(primaryStage);
    primaryStage.setScene(currentScene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }

  public void setHomePage(Stage primaryStage){
    Label title = new Label("CS400 Quiz Generator");
    Button addNewQuiz = new Button("Add New Quiz");

    screen.setStyle("-fx-background-color: #FA8072;");
    quizzes.setStyle("-fx-background-color: #FA8072;");
    addNewQuiz.setStyle("-fx-background-color: #FA8072;");
    title.setStyle("-fx-background-color: #FA8072;");
    title.setFont(Font.font(40));
    screen.setTop(title);
    screen.setCenter(quizzes);
    quizzes.getItems().add(addNewQuiz);
    primaryStage.setScene(currentScene);
    primaryStage.show();



    addNewQuiz.setOnAction(event -> {
      makeQuiz makeQuiz = new makeQuiz();
      makeQuiz.show(primaryStage, currentScene, quizzes, quizHolder);
    });
  }

}
