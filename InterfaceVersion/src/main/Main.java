package main;

import animatefx.animation.*;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import  javafx.stage.*;




public class Main extends Application {


 private  double x,y;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/Main.fxml"));

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Dictionary");
    //  primaryStage.initStyle(StageStyle.DECORATED);
         primaryStage.initStyle(StageStyle.TRANSPARENT);
        root.getStylesheets().add(getClass().getResource("../style/style.css").toString());
        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
        root.setOnMouseDragged(mouseEvent -> {
            primaryStage.setX(mouseEvent.getScreenX()-x );
            primaryStage.setY(mouseEvent.getScreenY() -y);
        });
        primaryStage.show();
        new FadeIn(root).play();

    }



    public static void main(String[] args) {
        launch(args);
    }
}
