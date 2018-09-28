package controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import java.io.IOException;
import  javafx.scene.text.Text;
import main.GoogleTranslate;
import main.IsConnected;

import java.util.Map;
import  java.util.HashMap;

import java.net.URL;
import java.util.ResourceBundle;

public class GGController implements  Initializable  {
 private   Map<String, String> map = new HashMap<String, String>();
    @FXML
    private  ChoiceBox<String> cb1; private  String rescb1;
    @FXML
     private  ChoiceBox<String> cb2; private  String rescb2;
    @FXML
     private  Button change;
@FXML
private  TextField tf; private  String resTf;
@FXML
private  Button trans;
@FXML
private  Text res;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
      map.put("Vietnamese","vi");
        map.put("English","en");
        map.put("Korean","ko");
        map.put("Japanese","ja");
        map.put("Chinese","zh");
        map.put("Thailand","th");
        ObservableList<String> languages
                = FXCollections.observableArrayList("Vietnamese","English","Korean","Chinese","Thailand","Japanese");

        cb1.setItems(languages);
        cb2.setItems(languages);
    }
    @FXML
    public  void exchange()
    {
        String temp=cb1.getValue();
        cb1.setValue(cb2.getValue());
        cb2.setValue(temp);


    }

    @FXML
    public  void translate()
    {
          rescb1=map.get(cb1.getValue());
          rescb2=map.get(cb2.getValue());
          resTf=tf.getText();
          try
          {
              if (IsConnected.IsConnecting() ) {
                  System.out.println(rescb1);
                  res.setText(GoogleTranslate.translate(rescb1, rescb2, resTf));
              }
              else
              {
                res.setText("Khong co ket noi internet");
              }



          }
          catch (IOException e) {
              e.printStackTrace();
          } catch (InterruptedException e) {
              e.printStackTrace();
          }

    }


}
