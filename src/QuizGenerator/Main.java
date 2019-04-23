package QuizGenerator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {

  BorderPane border = new BorderPane();
  ListView<Quiz> quizzes = new ListView<>();

  @Override
  public void start(Stage primaryStage) throws Exception {

    border.setStyle("-fx-background-color: #fa8072;");


    TextField title = new TextField();
    title.setText("CS400 Quiz Generator");
    title.setStyle("-fx-background-color: #fa8072;");
    border.setTop(title);
    //gridPane.setAlignment(Pos.TOP_CENTER);


    TextField title2 = new TextField();
    title.setText("Declan gay - Hi atessa");
    title.setStyle("-fx-background-color: #fa8072;");
    //gridPane.setAlignment(Pos.CENTER);

    Button newQuiz = new Button("New Quiz");
    newQuiz.setOnAction(event -> {
      makeQuiz current = new makeQuiz();
      quizzes.getItems().add(current.runner());
    });
    border.setRight(newQuiz);

    border.setCenter(quizzes);


    primaryStage.setScene(new Scene(border, 300, 275));


    primaryStage.show();
  }

  public void setupQuizzes() {
    quizzes.setMaxSize(200, 200);
    quizzes.setStyle("-fx-background-color: #fa8072;");
  }


  public static void main(String[] args) {
    launch(args);
  }
}
