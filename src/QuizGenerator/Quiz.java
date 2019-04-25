package QuizGenerator;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.LinkedList;

public class Quiz {

  private LinkedList<Question> questions = new LinkedList<>();
  private int length;
  private int correct;
  private String name;

  public Quiz(String name) {
    length = 0;
    correct = 0;
    this.name = name;
  }

  public void show(Stage primaryStage, Scene original) {
    Stage main = new Stage();
    BorderPane screen = new BorderPane();
    Text name = new Text(this.name);
    screen.setTop(name);
    boolean question = true;
    while(question) {
      Button makeQ = new Button("Create New Question");
      makeQ.setOnAction(event -> {
        createNewQuestionScreen the = new createNewQuestionScreen();
        //Question q = the.show();
        //makeQuiz(q);
        length++;
      });
      Button stopQuiz = new Button("Done Adding Questions");
      stopQuiz.setOnAction(event -> {
        changeQuestionToFalse(question);
        addQuiz();
      });
      screen.setRight(makeQ);
      screen.setLeft(stopQuiz);
    }

  }
  public void changeQuestionToFalse(boolean question){
    question = false;
  }
  public void makeQuiz(Question question) {
    questions.add(question);
  }
  //adds Quiz to the hashMap
  //key for hashmap is string name of quiz, value is the linkedlist
  public void addQuiz(){

  }
  public void getAllQuestions() {
    correct = 0;
    Question[] all = (Question[])questions.toArray();
    length = all.length;
    for (int i = 0; i < all.length; ++i) {

    }
  }
  public String getName(){
    return name;
  }


}
