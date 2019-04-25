package QuizGenerator;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
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
//quiz to show each question
  public void show(Stage primaryStage, Scene original) {
    BorderPane screen = new BorderPane();
    Text question = new Text(this.question);
    question.setStyle("-fx-background-color: #FA8072;");
    question.setFont (Font.font(40));
    GridPane questions = new GridPane();
    ArrayList<String> answers = new ArrayList<String>();
    answers.add(correctAnswer);
    answers.add(answer1);
    answers.add(answer2);
    answers.add(answer3);
    Random num = new Random();
    int hor=0;
    int vet=0;
    boolean switc = true;
    do{
      if(hor ==2){
        hor =0;
        vet =1;
      }
      int rand =num.nextInt(answers.size());

      Button add = new Button(answers.get(rand));
      if(rand==0&&switc){
        //add.setOnAction(event -> );
        switc = false;
      }
      questions.add(add,hor,vet);
      hor++;
      answers.remove(rand);
    }while(answers.size()!=0);
    screen.setTop(question);
    screen.setCenter(questions);
    primaryStage.setScene(new Scene(screen, 300, 275));
    primaryStage.show();

  }
}
