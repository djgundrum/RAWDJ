package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color: #fa8072;");


        TextField title = new TextField();
        title.setText("CS400 Quiz Generator");
        title.setStyle("-fx-background-color: #fa8072;");
        gridPane.add(title, 0,0, 2,1);
        //gridPane.setAlignment(Pos.TOP_CENTER);



        TextField title2 = new TextField();
        title.setText("Declan gay - Hi atessa");
        title.setStyle("-fx-background-color: #fa8072;");
        gridPane.add(title2, 0,20, 2,1);
        //gridPane.setAlignment(Pos.CENTER);



        primaryStage.setScene(new Scene(gridPane, 300, 275));



        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
