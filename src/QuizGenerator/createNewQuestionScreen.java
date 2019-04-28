package QuizGenerator;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.LinkedList;


public class createNewQuestionScreen {


  /**
   * Method responsible for making and and changing what is displayed on the screen to the user
   * @param prequestion Question sent in if the user wants to change the contents of an existing one
   * @param index The index of the prequestion in the listview so that it can be reset
   * @param primaryStage // The stage that is being displayed to the user
   * @param questions // Linked List of all the questions in the quiz
   * @param questionsButton // Listview of all the buttons in the quiz
   * @param original // Scene that was previously displayed
   */
  public void show(Question prequestion, int index, Stage primaryStage,
                   LinkedList<Question> questions,
                   ListView<Button> questionsButton, Scene original) {

    // The structure of the new scene
    BorderPane screen = new BorderPane();

    // The vertical box that will hold all the text fields
    VBox fields = new VBox();

    // What will become the user's question
    Label question = new Label("Question: ");
    TextField q = new TextField();

    // What the correct answer to the question is
    Label correct = new Label("Correct Answer: ");
    TextField correctAnswer = new TextField();

    // Other answers that will be displayed, none of these should be correct
    Label a1a = new Label("Other Answers: ");
    TextField a1 = new TextField();
    TextField a2 = new TextField();
    TextField a3 = new TextField();

    // Will enter if the user wants to change a previously existing question
    // Sets all the open text boxes to what the user originally had in them
    if (prequestion != null) {
      q.setText(prequestion.question);
      correctAnswer.setText(prequestion.correctAnswer);
      a1.setText(prequestion.answer1);
      a2.setText(prequestion.answer2);
      a3.setText(prequestion.answer3);
    }

    // Adds all the labels and textfields to the vertical box
    fields.getChildren().addAll(question, q, correct, correctAnswer, a1a, a1, a2, a3);

    // Sets where everything will be displayed
    screen.setTop(new Label("Make a Question!"));
    screen.setCenter(fields);

    // Creates the button that makes the question and returns to the previous screen
    Button makeQ = new Button("Create Question");
    screen.setBottom(makeQ);

    // Displays the new screen
    primaryStage.setScene(new Scene(screen, 300, 275));
    primaryStage.show();

    // Sets what the create question button will do
    makeQ.setOnAction(event -> {
      // Gets the text from all the text fields and stores them in String variables
      String que = q.getText();
      String correctA = correctAnswer.getText();
      String aone = a1.getText();
      String atwo = a2.getText();
      String athree = a3.getText();

      // Creates a new question from all the strings
      Question qu = new Question(que, correctA, aone, atwo, athree);

      // Adds the new question to the linked list
      questions.add(qu);

      // Creates a new button (will be added to the listview
      Button button = new Button(que);
      button.setOnAction(event1 -> {
        // Creates a new instance of the this class
        createNewQuestionScreen curr = new createNewQuestionScreen();

        // Gets where this button is stored in the listview
        int inder = questionsButton.getItems().indexOf(button);

        // When the button in the listview is clicked, it will redirect itself back to this class
        // and display the contents of the question
        curr.show(qu, inder, primaryStage, questions, questionsButton, original);
      });
      // Enters if this is not a new question
      // Removes old question from linked list and replaces button in listview
      if (prequestion != null) {
        questionsButton.getItems().set(index, button);
        questions.remove(prequestion);
      }
      // Enters if this is a new question
      // Adds it to the listview
      else {
        questionsButton.getItems().add(button);
      }

      // Shows the new screen
      primaryStage.setScene(original);
      primaryStage.show();

      // Sets the functionality of the button in the listview
      button.setOnAction(event1 -> {

        // Creates a new instance of the this class
        createNewQuestionScreen curr = new createNewQuestionScreen();

        // Gets the index of the the button in the listview
        int inder = questionsButton.getItems().indexOf(button);

        //Shows the create new question screen but with all the fields of this current question
        curr.show(qu, inder, primaryStage, questions, questionsButton, original);
      });
    });
  }
}
