package QuizGenerator;

import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.Random;

public class Question {

  public String question;
  String correctAnswer;
  String answer1;
  String answer2;
  String answer3;

  public Question(String q, String c, String a1, String a2, String a3) {
    this.question = q;
    this.correctAnswer = c;
    this.answer1 = a1;
    this.answer2 = a2;
    this.answer3 = a3;
  }

  public void show() {
    Stage main = new Stage();
    BorderPane screen = new BorderPane();
    Text question = new Text(this.question);

    screen.setTop(question);


  }
}
