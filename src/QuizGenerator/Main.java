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
    // Sets the string at the top of the current scene
    Label title = new Label("CS400 Quiz Generator");

    // This is the button on the top below the title string
    Button addNewQuiz = new Button("Add New Quiz");

    // Screen is the main border pane for this scene
    screen.setStyle("-fx-background-color: #FA8072;");

    // Quizzes is the listview in the middle of the screen
    quizzes.setStyle("-fx-background-color: #FA8072;");
    addNewQuiz.setStyle("-fx-background-color: #FA8072;");
    title.setStyle("-fx-background-color: #FA8072;");
    title.setFont(Font.font(40));

    // Setting where each of the created items will be displayed on the screen
    screen.setTop(title);
    screen.setCenter(quizzes);
    quizzes.getItems().add(addNewQuiz);

    // Sets the primary stage to this screen
    primaryStage.setScene(currentScene);

    // Shows the current screen
    primaryStage.show();

    // Sets what the addquiz button will do
    // Calls the makeQuiz class
    addNewQuiz.setOnAction(event -> {
      makeQuiz makeQuiz = new makeQuiz();
      makeQuiz.show(primaryStage, currentScene, quizzes, quizHolder);
    });
  }

}
