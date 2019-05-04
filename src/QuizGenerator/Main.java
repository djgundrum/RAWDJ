// Title: RAWDJ Quiz Generator
// Files: Attempt.java, Controller.java, createNewQuestionScreen.java, Main.java, makeQuiz.java,
// Question.java, Quiz.java
// Course: Comp Sci 400, Spring 2019
//
// Authors: Justin Burns, Declan Gundrum, Ryan Erdmann, William Weis, Atessa Amjadi
// Emails:  jdburns2@wisc.edu, djgundrum@wisc.edu, raerdmann@wisc.edu,
// wzweis@wisc.edu, aamjadi@wisc.edu
// Lecturer's Name: Debra Deppler
//////////////////////////////////////////////////////////////////////////////////////////////////
package QuizGenerator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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


/**
 * Main class that sets up the application
 */
public class Main extends Application {

  BorderPane screen = new BorderPane(); //main borderpane used on home screen
  Scene currentScene = new Scene(screen, 600, 600); //sets the dimensions of the scene
  HashMap<String, Quiz> quizHolder = new HashMap<>(); //creates a HashMap data type to store
  //the quizzes
  ListView<Button> quizzes = new ListView<>(); //This is the listview that
  // holds all the quiz buttons


  /**
   * This is the start method that runs on startup
   * @param primaryStage is the main stage that is used
   */
  @Override
  public void start(Stage primaryStage) throws Exception {
    setHomePage(primaryStage); //sets up the homepage
    primaryStage.setScene(currentScene); //sets the current scene
    primaryStage.show(); //shows the scene and items
  }

  /**
   * This is the main method
   */
  public static void main(String[] args) {
    launch(args);
  }

  /**
   * This method sets up the homepage seen when the program starts up
   * @param primaryStage is the main stage used
   */
  public void setHomePage(Stage primaryStage){
    // Used when a user loads or saves a file
    FileChooser fileChooser = new FileChooser();

    // Sets the string at the top of the current scene
    Label title = new Label("CS400 Quiz Generator");

    // This is made to store all the buttons at the top of the screen
    HBox topper = new HBox();

    // This is the button on the top below the title string
    Button addNewQuiz = new Button("Add New Quiz");

    //This is a button to exit
    Button exit = new Button("Exit");

    // This is a button so that the user can load a previous quiz generator file
    Button loadFile = new Button("Load File");

    /// This is a button to save all the current quizzes that the user has made
    Button saveFile = new Button("Save File");

    topper.getChildren().addAll(addNewQuiz, loadFile, saveFile, exit);

    // Screen is the main border pane for this scene
    screen.setStyle("-fx-background-color: #E0FFFF;");

    // Quizzes is the listview in the middle of the screen
    quizzes.setStyle("-fx-background-color: #E0FFFF;");
    addNewQuiz.setStyle("-fx-background-color: #00CED1;-fx-border-color: black;");
    exit.setStyle("-fx-background-color: #00CED1;-fx-border-color: black;");
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

    //lets user exit
    exit.setOnAction(event -> {
      closeHelper(fileChooser, primaryStage);
    });



    // Sets what the addquiz button will do
    // Calls the makeQuiz class
    addNewQuiz.setOnAction(event -> {
      makeQuiz makeQuiz = new makeQuiz();
      makeQuiz.show(primaryStage, currentScene, quizzes, quizHolder);
    });

    loadFile.setOnAction(event -> loadHelper(fileChooser, primaryStage));

    saveFile.setOnAction(event -> saveHelper(fileChooser, primaryStage));
  }

  /**
   *
   * @param fileChooser
   * @param primaryStage
   */
  private void closeHelper(FileChooser fileChooser, Stage primaryStage){

    Stage stage = new Stage(); //creates a new stage

    BorderPane screen = new BorderPane(); //creates a new borderpane
    Scene currentScene = new Scene(screen, 400, 200); //sets dimensions of the scene

    // Sets the string at the top of the current scene
    Label title = new Label("Do you want to exit");

    screen.setTop(title); //sets the title to the top of the borderpane

    HBox box = new HBox(); //creates a horizontal box used for organization

    //two buttons used when exiting the program
    //save is used to save program, noSave doesn't save the program
    Button save = new Button("Save and exit");
    Button noSave = new Button("Exit without saving");

    box.getChildren().addAll(save, noSave); //adds children to the hbox
    screen.setCenter(box); //sets the horizontal box to the center of the borderpane

    //event handler for the save button
    save.setOnAction(event -> {
      saveHelper(fileChooser, primaryStage);
      stage.close();
      primaryStage.close();
    });

    //event handler for the noSave button
    noSave.setOnAction(event -> {
      stage.close();
      primaryStage.close();
    });

    stage.setScene(currentScene); //sets the stage with the current screen

    stage.show(); //shows the stage



  }

  /**
   * This is a helper method for saving the program
   * @param fileChooser is the FileChooser used to save files
   * @param primaryStage is the main stage used in the program
   */
  @SuppressWarnings("unchecked")
  private void saveHelper(FileChooser fileChooser, Stage primaryStage) {
    try {
      // Setting up the files
      FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("JSON files " +
              "(*.json)", "*.json");
      File init = new File("."); //creates a new file
      fileChooser.setInitialDirectory(init); //sets the initial directory
      fileChooser.getExtensionFilters().add(extensionFilter); //gets an extension filter
      File file = fileChooser.showSaveDialog(primaryStage); //save file
      if (file == null)
        return;
      FileWriter fw = new FileWriter(file); //creates a new FileWriter

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

  /**
   * This is a helper method for loading a file
   * @param fileChooser is a fileChooser used to load in file
   * @param primaryStage is the main stage used in the program
   */
  @SuppressWarnings("unchecked")
  public void loadHelper(FileChooser fileChooser, Stage primaryStage) {
    try {
      // Setting up the files
      FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("JSON files " +
              "(*.json)", "*.json");
      File init = new File("."); //creates a new file
      fileChooser.setInitialDirectory(init); //gets the initial directory
      fileChooser.getExtensionFilters().add(extensionFilter); //gets the extension filter
      File file = fileChooser.showOpenDialog(primaryStage); //creates a new file
      if (file == null)
        return;

      FileReader fr = new FileReader(file); //creates a new fileReader
      Object o = new JSONParser().parse(fr); //creates a new object

      JSONArray quizzes = (JSONArray) o; //creates a new JSON array

      //loop that runs through all the quizzes
      for (int i = 0; i < quizzes.size(); ++i) {
        JSONObject jo = (JSONObject) quizzes.get(i); //creates a new JSON object
        Quiz current = new Quiz((String)jo.get("Name")); //creates a quiz with the current name
        JSONArray questions = (JSONArray) jo.get("Questions"); //loads in all the questions

        //loop that runs through all the questions in the quiz
        for (int j = 0; j < questions.size(); ++j) {
          JSONObject currentQuestion = (JSONObject) questions.get(j); //creates a json object
          //for a question
          String question = (String) currentQuestion.get("Question"); //creates a string for
          //the question
          //creates four strings for all the answer options
          String correctAnswer = (String) currentQuestion.get("Correct Answer");
          String aonw = (String) currentQuestion.get("Answer 1");
          String atwo = (String) currentQuestion.get("Answer 2");
          String athree = (String) currentQuestion.get("Answer 3");

          //creates a question with the passed in data
          Question q = new Question(question, correctAnswer, aonw, atwo, athree);
          current.putInListViewHelper(q); //adds that question to the list
        }
        quizHolder.put(current.name, current); //puts the quiz in the quizHolder
        Button b1 = new Button(current.name); //creates a new button for the quiz
        b1.setStyle("-fx-border-color: transparent;-fx-background-color: transparent;");
        this.quizzes.getItems().add(b1); //adds the button the the list of quiz buttons
        b1.setOnAction(event -> { //event handler for the button
          Quiz ternary = quizHolder.get(current.name);
          ternary.show(primaryStage, currentScene, quizHolder, this.quizzes);
        });
      }

    } catch (Exception e) {
      System.out.println("Something went wrong");
      e.printStackTrace();
    }
  }

}
