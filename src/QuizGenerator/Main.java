package QuizGenerator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class Main extends Application {

  BorderPane border = new BorderPane();
  Button newQuiz = new Button("New Quiz");
  VBox titleAndButton= new VBox();
  ListView<Question> quizzes = new ListView<>();
  Scene currentScene = new Scene(border, 300, 275);

  @Override
  public void start(Stage primaryStage) throws Exception {
    border.setStyle("-fx-background-color: #fa8072;");
    TextField title = new TextField();
    title.setText("CS400 Quiz Generator");
    title.setStyle("-fx-background-color: #fa8072;");
    title.setFont(Font.font(40));
    border.setCenter(quizzes);
    titleAndButton.getChildren().add(title);
    titleAndButton.getChildren().add(newQuiz);
    border.setTop(titleAndButton);
    primaryStage.setScene(currentScene);
    primaryStage.show();






    newQuiz.setOnAction(event -> {
      createNewQuestionScreen main = new createNewQuestionScreen();
      main.show(primaryStage, quizzes, currentScene);
      System.out.println(main.question);
      System.out.println(main.getQuestion());
    });
  }



  public static void main(String[] args) {
    launch(args);
  }
}
