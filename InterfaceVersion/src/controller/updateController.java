package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class updateController  implements Initializable {

    @FXML
    TextField tf1;
    @FXML
    TextField tf2;
@FXML
Button updateWordd;

    private String word;
    void initData(String word, String description)
    {

        this.word=word;
        tf1.setText(word);
        tf2.setText(description);

    }
    @FXML
    public void updateWord()  {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/Main.fxml"));
        try
        {
            loader.load();
        }
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
       MainController m=loader.getController();
        String text1 = tf1.getText();
        String text2 = tf2.getText();
         m.updateWord(text1, text2,word);
        Stage stage =  (Stage)updateWordd.getScene().getWindow();
        stage.close();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
