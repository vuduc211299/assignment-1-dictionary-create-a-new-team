package main;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import  javafx.stage.*;


public class Main extends Application {


 double x,y;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/Main.fxml"));

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Dictionary");
       primaryStage.initStyle(StageStyle.DECORATED);
        // primaryStage.initStyle(StageStyle.TRANSPARENT);
        root.getStylesheets().add(getClass().getResource("../style/style.css").toString());


        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                x = event.getSceneX();
                y = event.getSceneY();
            }
        });
        primaryStage.show();

    }




    public static void main(String[] args) {
        launch(args);
    }
}
