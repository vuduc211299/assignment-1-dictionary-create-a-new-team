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
public class addController  implements  Initializable {
    @FXML
    TextField awtf1;
    @FXML
    TextField awtf2;
      @FXML
      Button addWordd;

    @FXML
    public  void addWord()
    {
        FXMLLoader loader;
        loader = new FXMLLoader();
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
        String text1 = awtf1.getText();
        String text2 = awtf2.getText();
        m.addWord(text1, text2);
        Stage stage =  (Stage)addWordd.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
