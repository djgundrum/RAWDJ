package QuizGenerator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import java.util.LinkedList;


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
    // Used when a user loads or saves a file
    FileChooser fileChooser = new FileChooser();

    // Sets the string at the top of the current scene
    Label title = new Label("CS400 Quiz Generator");

    // This is made to store all the buttons at the top of the screen
    HBox topper = new HBox();

    // This is the button on the top below the title string
    Button addNewQuiz = new Button("Add New Quiz");

    // This is a button so that the user can load a previous quiz generator file
    Button loadFile = new Button("Load File");

    /// This is a button to save all the current quizzes that the user has made
    Button saveFile = new Button("Save File");

    topper.getChildren().addAll(addNewQuiz, loadFile, saveFile);

    // Screen is the main border pane for this scene
    screen.setStyle("-fx-background-color: #E0FFFF;");

    // Quizzes is the listview in the middle of the screen
    quizzes.setStyle("-fx-background-color: #E0FFFF;");
    addNewQuiz.setStyle("-fx-background-color: #00CED1;-fx-border-color: black;");
    loadFile.setStyle("-fx-background-color: #00CED1;-fx-border-color: black;");
    saveFile.setStyle("-fx-background-color: #00CED1;-fx-border-color: black;");
    title.setStyle("-fx-background-color: #E0FFFF;");
    title.setFont(Font.font(40));

    // Setting where each of the created items will be displayed on the screen
    screen.setTop(title);
    screen.setBottom(quizzes);
    screen.setCenter(topper);

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

    loadFile.setOnAction(event -> {
      loadHelper(fileChooser, primaryStage);
    });

    saveFile.setOnAction(event -> {
      saveHelper(fileChooser, primaryStage);
    });
  }

  @SuppressWarnings("unchecked")
  private void saveHelper(FileChooser fileChooser, Stage primaryStage) {
    try {
      // Setting up the files
      FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("JSON files " +
              "(*.json)", "*.json");
      File init = new File(".");
      fileChooser.setInitialDirectory(init);
      fileChooser.getExtensionFilters().add(extensionFilter);
      File file = fileChooser.showOpenDialog(primaryStage);
      if (file == null)
        file = new File("./CURRENT.json");
      FileWriter fw = new FileWriter(file);

      Collection<Quiz> quizzers = quizHolder.values();

      // Will hold all the quizzes in separate arrays
      JSONArray allQuizzes = new JSONArray();

      // Goes through all the quizzes in the hashmap and puts each of them into JSON format
      for (Quiz current : quizzers) {
        JSONObject currentQuiz = new JSONObject();
        currentQuiz.put("Name", current.name);
        JSONArray questionsInQuiz = new JSONArray(); // Holds all the question JSON types
        LinkedList<Question> questions = current.questions;

        // Goes through all the questions in the quiz and puts each question into JSON format
        for (Question question : questions) {
          JSONObject currentQuestion = new JSONObject();
          currentQuestion.put("Question", question.question);
          currentQuestion.put("Correct Answer", question.correctAnswer);
          currentQuestion.put("Answer 1", question.answer1);
          currentQuestion.put("Answer 2", question.answer2);
          currentQuestion.put("Answer 3", question.answer3);
          questionsInQuiz.add(currentQuestion);
        }

        currentQuiz.put("Questions", questionsInQuiz);
        allQuizzes.add(currentQuiz);
      }

      fw.write(allQuizzes.toJSONString());
      fw.flush();
    } catch (IOException e) {
      System.out.println("You suck at everything");
    }
  }

  @SuppressWarnings("unchecked")
  public void loadHelper(FileChooser fileChooser, Stage primaryStage) {
    try {
      // Setting up the files
      FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("JSON files " +
              "(*.json)", "*.json");
      File init = new File(".");
      fileChooser.setInitialDirectory(init);
      fileChooser.getExtensionFilters().add(extensionFilter);
      File file = fileChooser.showOpenDialog(primaryStage);
      if (file == null)
        file = new File("./CURRENT.json");

      FileReader fr = new FileReader(file);
      Object o = new JSONParser().parse(fr);

      JSONArray quizzes = (JSONArray) o;

      for (int i = 0; i < quizzes.size(); ++i) {
        JSONObject jo = (JSONObject) quizzes.get(i);
        Quiz current = new Quiz((String)jo.get("Name"));
        JSONArray questions = (JSONArray) jo.get("Questions");

        for (int j = 0; j < questions.size(); ++j) {
          JSONObject currentQuestion = (JSONObject) questions.get(j);
          String question = (String) currentQuestion.get("Question");
          String correctAnswer = (String) currentQuestion.get("Correct Answer");
          String aonw = (String) currentQuestion.get("Answer 1");
          String atwo = (String) currentQuestion.get("Answer 2");
          String athree = (String) currentQuestion.get("Answer 3");

          Question q = new Question(question, correctAnswer, aonw, atwo, athree);
          current.putInListViewHelper(q);
        }
        quizHolder.put(current.name, current);
        Button b1 = new Button(current.name);
        b1.setStyle("-fx-border-color: transparent;-fx-background-color: transparent;");
        this.quizzes.getItems().add(b1);
        b1.setOnAction(event -> {
          Quiz ternary = quizHolder.get(current.name);
          ternary.show(primaryStage, currentScene);
        });
      }

    } catch (Exception e) {
      System.out.println("Something went wrong");
      e.printStackTrace();
    }
  }

}
